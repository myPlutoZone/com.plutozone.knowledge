/**
 * YOU ARE STRICTLY PROHIBITED TO COPY, DISCLOSE, DISTRIBUTE, MODIFY OR USE THIS PROGRAM
 * IN PART OR AS A WHOLE WITHOUT THE PRIOR WRITTEN CONSENT OF PLUTOZONE.COM.
 * PLUTOZONE.COM OWNS THE INTELLECTUAL PROPERTY RIGHTS IN AND TO THIS PROGRAM.
 * COPYRIGHT (C) 2026 PLUTOZONE.COM ALL RIGHTS RESERVED.
 *
 * 하기 프로그램에 대한 저작권을 포함한 지적재산권은 plutozone.com에 있으며,
 * plutozone.com이 명시적으로 허용하지 않는 사용, 복사, 변경 및 제 3자에 의한 공개, 배포는 엄격히 금지되며
 * plutozone.com의 지적재산권 침해에 해당된다.
 * Copyright (C) 2026 plutozone.com All Rights Reserved.
 *
 *
 * Program		: com.plutozone.knowledge
 * Description	:
 * Environment	: JRE 1.7 or more
 * File			: CorsFilter.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20260914162843][pluto#brightsoft.co.kr][CREATE: Initial Release]
 */
package com.plutozone.util.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
/**
 * @version 1.0.0
 * @author pluto#brightsoft.co.kr
 * 
 * @since 2026-09-14
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
public class CorsFilter implements Filter {
	
	/** Logger */
	private static Logger logger = LoggerFactory.getLogger(CorsFilter.class);
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpServletRequest	= (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
		String origin = httpServletRequest.getHeader("Origin");
		
		logger.info("---------------------------------------------------------------------------");
		logger.info("httpServletRequest.getHeader(\"Origin\"): " + origin);
		logger.info("httpServletRequest.getMethod(): " + httpServletRequest.getMethod());
		logger.info("---------------------------------------------------------------------------");
		
		if ("http://localhost:5173".equals(origin)
				|| "http://127.0.0.1:5173".equals(origin)) {
			
			httpServletResponse.setHeader("Access-Control-Allow-Origin"			, origin);
			httpServletResponse.setHeader("Access-Control-Allow-Credentials"	, "true");
			httpServletResponse.setHeader("Access-Control-Allow-Methods"		, "GET, POST, PUT, DELETE, OPTIONS");
			httpServletResponse.setHeader("Access-Control-Allow-Headers"		, "Content-Type, Authorization, X-Requested-With");
		}
		
		// Preflight 요청
		if ("OPTIONS".equalsIgnoreCase(httpServletRequest.getMethod())) {
			httpServletResponse.setStatus(HttpServletResponse.SC_OK);
			return;
		}
		
		chain.doFilter(request, response);
	}
}