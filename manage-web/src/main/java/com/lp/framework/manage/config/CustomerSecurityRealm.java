package com.lp.framework.manage.config;

import com.lp.framework.manage.model.Authority;
import com.lp.framework.manage.model.User;
import com.lp.framework.manage.service.AuthorityService;
import com.lp.framework.manage.service.UserService;
import com.lp.framework.manage.utils.ByteSourceUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomerSecurityRealm extends AuthorizingRealm implements Serializable {

	private static final long serialVersionUID = 3437294411962320359L;
	@Autowired
	private AuthorityService authorityService;

	@Autowired
	private UserService userSerive;
	
//	@Autowired
//	private IJdbcService jdbcServiceImpl;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//1. 从 PrincipalCollection 中来获取登录用户的信息
		Object principal = principals.getPrimaryPrincipal();
		

		Set<String> permissions = new HashSet<>();
		Set<String> roles = new HashSet<String>();
		System.out.println(principals);
		
		List<Authority> authorityList = authorityService.selectByPage(new HashMap<>());
		for (Authority authority : authorityList) {
			permissions.add(authority.getAuthorityName());
		}
		roles.add("user");
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(permissions);
		info.setRoles(roles);
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken uptoken = (UsernamePasswordToken) token;
		String userName = uptoken.getUsername();
		String realmName = getName();
		
		Object principal = userName;
		User record = new User();
		record.setUserName(userName);
		User user = userSerive.selectByPrimaryKey(1);
		Object hashedCredentials = user.getPwd();
		//ByteSource credentialsSalt = ByteSource.Util.bytes(userName);
		//重写
		ByteSource credentialsSalt = ByteSourceUtils.bytes(userName);
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, hashedCredentials, credentialsSalt, realmName);
		return info;
	}
	
	public void clearAuthorizationCache() {
		PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
		super.clearCache(principals);
	}
	
	public static void main(String[] args) {
		String hashAlgorithmName = "MD5";//加密算法
		Object credentials = "123";//密码
//		ByteSource salt = ByteSource.Util.bytes("zs");
		ByteSource salt = ByteSourceUtils.bytes("zs");
		int hashIterations = 10;//加密次数
		
		Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
		System.out.println(result);
	}

}
