package com.lp.framework.manage.config;

import com.lp.framework.manage.model.Authority;
import com.lp.framework.manage.model.User;
import com.lp.framework.manage.service.*;
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
import java.util.*;

public class CustomerSecurityRealm extends AuthorizingRealm implements Serializable {

	private static final long serialVersionUID = 3437294411962320359L;

	@Autowired
	private MenuService menuService;

	@Autowired
	private UserService userSerive;

	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private RoleMenuService roleMenuService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//1. 从 PrincipalCollection 中来获取登录用户的信息
		Object principal = principals.getPrimaryPrincipal();
		String userCode = (String) principal;
		Set<String> permissions = new HashSet<>();
		Set<String> roles = new HashSet<String>();
		Map<String,Object> params = new HashMap<>();
		params.put("userCode",userCode);
		List<String> userRoles = userRoleService.selectRoles(params);
		roles.addAll(userRoles);
		List<String> authoritys = new ArrayList<>();
		if(roles.contains("admin")|| "admin".equals(userCode)){
			authoritys = menuService.selectUserMenus();
		}else{
			authoritys = roleMenuService.selectUserMenus(params);
		}
		permissions.addAll(authoritys);
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
		User user = userSerive.selectByUserCode(userName);
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
