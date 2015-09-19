package com.alfred.shiro.realm;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.alfred.constant.UserConstant;
import com.alfred.model.User;
import com.alfred.model.UserPermission;
import com.alfred.service.UserPermissionService;
import com.alfred.service.UserService;
//@Component("userRealm")
public class UserRealm extends AuthorizingRealm {

	@Autowired
	@Qualifier("userPermissionService")
	private UserPermissionService userPermissionService;

	@Autowired
	@Qualifier("userService")
	private UserService userService;

	private static Log log = LogFactory.getLog(UserRealm.class);
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		try {
			// 获取当前登录的用户名,等价于(String)principals.fromRealm(this.getName()).iterator().next()
			String currentUsername = (String) super.getAvailablePrincipal(principals);
			
			List<String> roleList = new ArrayList<String>();
			List<String> permissionList = new ArrayList<String>();
			
			// 从数据库中获取当前登录用户的详细信息
			User user = userService.selectByAccountName(currentUsername);
			if (null != user) {
				List<UserPermission> uplist = userPermissionService.selectByUserId(user.getId());
				if (null != uplist && uplist.size() > 0) {
					for (UserPermission pmss : uplist) {
						// roleList.add(role.getName());
						if (!StringUtils.isEmpty(pmss.getRule())) {
							permissionList.add(pmss.getRule());
							log.info("the current has the permission: "+pmss.getRule());
						}
					}
				}
			} 
			// 为当前用户设置角色和权限
			SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
			simpleAuthorInfo.addRoles(roleList);
			simpleAuthorInfo.addStringPermissions(permissionList);
			return simpleAuthorInfo;
		} catch (AuthorizationException authorizationException) {
			log.error(this,authorizationException);
			throw new AuthorizationException();
		}	catch (Exception e) {
			log.error(this,e);
			return null;
		}
	}

	/**
	 * 验证当前登录的Subject
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {
		try {
			UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
			//		log.info("当前Subject获取到token为" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
			User user = userService.selectByAccountName(token.getUsername());
			if (null != user && user.getType() >= UserConstant.USER_TYPE_CLOUD) {
				AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getAccountName(), user.getPassword(),
						user.getFirstName() + user.getLastName());
				//清除未正常关闭的session
				clearCache(authcInfo.getPrincipals());
				this.setSession("currentUser", user);
				return authcInfo;
			} else {
				return null;
			}
		} catch (Exception e) {
			log.error(this,e);
			return null;
		}
	}

	private void setSession(Object key, Object value) {
		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser) {
			Session session = currentUser.getSession();
			if (null != session) {
				session.setAttribute(key, value);
			}
		}
	}

}
