package com.lp.framework.manage.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
	
	@Autowired
	private Environment env;

	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		shiroFilter.setSecurityManager(securityManager);
		LinkedHashMap<String, Filter> filtersMap = new LinkedHashMap<>();
		LogoutFilter logoutFilter = new LogoutFilter();
		logoutFilter.setRedirectUrl("/toLogin");
		filtersMap.put("logout", logoutFilter);

		shiroFilter.setFilters(filtersMap);
		Map<String, String> chainDefinition = new LinkedHashMap<>();
		// 静态资源与登录请求不拦截
		chainDefinition.put("/plugins/**", "anon");
		chainDefinition.put("/public/**", "anon");
		//登录不校验
		chainDefinition.put("/login", "anon");
		chainDefinition.put("/actuator/**", "anon");
		//登出
		chainDefinition.put("/logout", "logout");
		// 其他都需要认证
		chainDefinition.put("/**", "authc");
		shiroFilter.setFilterChainDefinitionMap(chainDefinition);
		// 当 用户身份失效时重定向到 loginUrl
		shiroFilter.setLoginUrl("/toLogin");
		// 用户登录后默认重定向请求
		shiroFilter.setSuccessUrl("/index");
		return shiroFilter;
	}

	/**
	 * 安全管理器
	 * @return
	 */
	@Bean
	public DefaultWebSecurityManager securityManager(SessionManager sessionManager) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setAuthenticator(authenticator());
		securityManager.setRealm(customerSecurityRealm());
		securityManager.setCacheManager(ehCacheManager());
		securityManager.setSessionManager(sessionManager);
		return securityManager;
	}

	/**
	 * 认证器
	 * @return
	 */
	@Bean
	public Authenticator authenticator() {
		ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
		authenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
		return authenticator;
	}

	/**
	 * 自定义realm
	 * @return
	 */
	@Bean
	public AuthorizingRealm customerSecurityRealm() {
		CustomerSecurityRealm customerSecurityRealm = new CustomerSecurityRealm();
		customerSecurityRealm.setCredentialsMatcher(hashedCredentialsMatcher());
		customerSecurityRealm.setAuthorizationCachingEnabled(true);
		customerSecurityRealm.setAuthenticationCachingEnabled(true);
		return customerSecurityRealm;
	}

	/**
	 * 加密策略
	 * @return
	 */
	@Bean
	public CredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("MD5");
		hashedCredentialsMatcher.setHashIterations(10);
		return hashedCredentialsMatcher;
	}
	
	/**
	 * 整合shiro标签
	 * @return
	 */
	@Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }
	
	/**
	 * sessionId生成器
	 * @return
	 */
	@Bean
	public SessionIdGenerator sessionIdGenerator() {
		return new JavaUuidSessionIdGenerator();
	}
	
	/**
	 * SessionDAO
	 * @return
	 */
	@Bean
	public SessionDAO sessionDAO() {
		EnterpriseCacheSessionDAO enterpriseCacheSessionDAO = new EnterpriseCacheSessionDAO();
		enterpriseCacheSessionDAO.setActiveSessionsCacheName("shiro-activeSessionCache");
		enterpriseCacheSessionDAO.setSessionIdGenerator(sessionIdGenerator());
		return enterpriseCacheSessionDAO;
	}

	@Bean
	public SimpleCookie sessionIdCookie() {
		//这个参数是cookie的名称
		SimpleCookie simpleCookie = new SimpleCookie("simpleCookie");
		simpleCookie.setHttpOnly(true);
		//maxAge=-1表示浏览器关闭时失效此Cookie
		simpleCookie.setMaxAge(-1);
		//jsessionId的path为/用于多个系统共享jsessionId
		simpleCookie.setPath("/");
		return simpleCookie;
	}
	
	@Bean
	public EhCacheManager ehCacheManager() {
		EhCacheManager em = new EhCacheManager();
		em.setCacheManagerConfigFile("classpath:ehcache.xml");
		return em;
	}

	@Bean
	public SessionManager sessionManager() {
		DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
		defaultWebSessionManager.setSessionDAO(sessionDAO());
		//去除URL中的JSESSIONID
		defaultWebSessionManager.setSessionIdUrlRewritingEnabled(false);
		//设置超时时间
		defaultWebSessionManager.setGlobalSessionTimeout(1000*60*30);
		defaultWebSessionManager.setSessionIdCookie(sessionIdCookie());
		defaultWebSessionManager.setSessionValidationSchedulerEnabled(true);
		return defaultWebSessionManager;
	}
	

}
