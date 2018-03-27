package com.athongkun.web.listener;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.athongkun.bean.Permission;
import com.athongkun.utils.StringUtil;

public class ServerStartupListener extends ContextLoaderListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
	
		ServletContext application = event.getServletContext();
		// 将web应用路径保存起来，让用户可以通过EL表达式直接访问
		
		// web应用路径
		String path = application.getContextPath();
		
		// pageContext, request, Session, Application
		application.setAttribute("APP_PATH", path);
		
		// 初始化Spring环境
		super.contextInitialized(event);
		
		// 在下面增加对Spring框架的访问代码
		
//		WebApplicationContext context =
//			WebApplicationContextUtils.getWebApplicationContext(application);
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
//		
//		application.setAttribute("authURLs", authURLSet);
	}

}
