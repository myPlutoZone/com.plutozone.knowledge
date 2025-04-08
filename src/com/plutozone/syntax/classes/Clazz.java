/**
 * YOU ARE STRICTLY PROHIBITED TO COPY, DISCLOSE, DISTRIBUTE, MODIFY OR USE THIS PROGRAM
 * IN PART OR AS A WHOLE WITHOUT THE PRIOR WRITTEN CONSENT OF BRIGHTSOFT.CO.KR.
 * BRIGHTSOFT.CO.KR OWNS THE INTELLECTUAL PROPERTY RIGHTS IN AND TO THIS PROGRAM.
 * COPYRIGHT (C) 2024 PLUTOZONE.COM ALL RIGHTS RESERVED.
 *
 * 하기 프로그램에 대한 저작권을 포함한 지적재산권은 plutozone.com에 있으며,
 * plutozone.com이 명시적으로 허용하지 않는 사용, 복사, 변경 및 제 3자에 의한 공개, 배포는 엄격히 금지되며
 * plutozone.com의 지적재산권 침해에 해당된다.
 * Copyright (C) 2024 plutozone.com All Rights Reserved.
 *
 *
 * Program		: com.plutozone.knowledge
 * Description	:
 * Environment	: JRE 1.7 or more
 * File			: Clazz.java
 * Notes		: 클래스 정보
 * History		: [NO][Programmer][Description]
 *				: [20240606234041][pluto#brightsoft.co.kr][CREATE: Initial Release]
 */
package com.plutozone.syntax.classes;

/**
 * @version 1.0.0
 * @author pluto#brightsoft.co.kr
 * 
 * @since 2024-06-06
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
public class Clazz {
	
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws Exception {
		
		// 클래스로 정보 얻기-1
		Class aClazz = Clazz.class;
		
		// 클래스로 정보 얻기-2
		// Class aClazz = Class.forName("com.plutozone.syntax.classes.Clazz");
		
		// 객체로 정보 얻기
		// Clazz clazz = new Clazz();
		// Class aClazz = clazz.getClass();
		
		System.out.println(aClazz.getName());
		System.out.println(aClazz.getSimpleName());
		System.out.println(aClazz.getPackage().getName());
	}

}