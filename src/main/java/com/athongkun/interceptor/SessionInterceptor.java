package com.athongkun.interceptor;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.athongkun.bean.User;
import com.athongkun.utils.Const;

/**
 * 用户登录拦截器
 * 将未登陆的用户进行拦截
 * 
 * 适配器模式也称之为叫转换器模式
 * 可以将本来因为接口不一致而无法一起工作的两个对象通过转换后，可以一起工作了。
 * 
 * @author 18801
 *
 */
public class SessionInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// 如果preHandle方法返回false，会执行此方法。
		// 控制器不会执行，逻辑直接返回
		
		// 如果preHandle方法返回true，
		// 那么此方法会在渲染视图之后执行
		
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// 在控制器执行完毕后执行
		// 会将所有的拦截器从最后到开始一个一个循环执行
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// 在控制器之前执行
		// 会将所有的拦截器从开始到最后一个一个循环执行
		
		// 获取请求路径
		// http://127.0.0.1:8080/test/tes.jsp
		// uri = /test/tes.jsp
		// url = http://127.0.0.1:8080/test/tes.jsp
		
		// 不是所有的路径都需要进行登陆验证
		
		// 判断需要登录验证的路径都有哪些？
		String uri = request.getRequestURI();
		
		// 白名单
		Set<String> whiteSet = new HashSet<String>();
		whiteSet.add("/login.htm");
		whiteSet.add("/dologin.do");
		//System.out.println( "uri = " + uri );
		
		if ( whiteSet.contains(uri) ) {
			return true;
		} else {
			// 判断当前的请求用户是否已经登陆
			User loginUser = (User)request.getSession().getAttribute(Const.LOGIN_USER);
			
			if ( loginUser == null ) {
				// 如果没有登陆，可以回到登陆页面
				response.sendRedirect("/");
				return false;
			} else {
				// 如果登陆，那么请求继续执行
				return true;
			}
		}
	}

}
