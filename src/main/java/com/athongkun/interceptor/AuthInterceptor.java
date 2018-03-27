package com.athongkun.interceptor;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 权限拦截器
 * 对用户请求进行拦截，如果拥有相应的权限，可以继续访问，如果没有权限，无法访问，跳转到错误页面。
 * @author 18801
 *
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		// 获取用户的请求路径
		String uri = request.getRequestURI();
		
		// 判断请求路径是否在授权的路径中
		
		// 用户请求路径必须在合法的授权路径中
//		WebApplicationContext context =
//			WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
//		
//		PermissionService permissionService =
//				(PermissionService)context.getBean("permissionServiceImpl");
//		
//		List<Permission> allPermissions = permissionService.queryAll();
//		
//		// 白名单
//		Set<String> authURLSet = new HashSet<String>();
//		
//		for ( Permission permission : allPermissions ) {
//			if ( StringUtil.isNotEmpty(permission.getUrl()) ) {
//				authURLSet.add(permission.getUrl());
//			}
//		}
		;
		Set<String> authURLSet = (Set<String>)request.getSession().getServletContext().getAttribute("authURLs");
		
		if ( authURLSet.contains(uri) ) {
			// 用户请求路径必须在系统的授权路径中
			Set<String> authURLs = (Set<String>)request.getSession().getAttribute("authURL");
			if ( authURLs.contains(uri) ) {
				// 如果在授权的路径中，继续访问
				return true;
			} else {
				// 如果不在授权的路径中，跳转到错误页面
				response.sendRedirect("/error.jsp");
				return false;
			}
		} else {
			return true;
		}
	}

}
