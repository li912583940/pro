package com.chinamobo.zuul.filter;

import javax.servlet.http.HttpServletRequest;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class MyFilter extends ZuulFilter{

	
	@Override
	public Object run() {
		 RequestContext ctx = RequestContext.getCurrentContext();
	        HttpServletRequest request = ctx.getRequest();


	        Object accessToken = request.getParameter("token");
	        if(accessToken == null) {
	            System.out.println("token 为空");
	            ctx.setSendZuulResponse(false);
	            ctx.setResponseStatusCode(401);
	            return null;
	        }
	        System.out.println("有token");
	        return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public String filterType() {
		return "rec";
	}

}
