/**
 * YOU ARE STRICTLY PROHIBITED TO COPY, DISCLOSE, DISTRIBUTE, MODIFY OR USE THIS PROGRAM
 * IN PART OR AS A WHOLE WITHOUT THE PRIOR WRITTEN CONSENT OF PLUTOZONE.COM.
 * PLUTOZONE.COM OWNS THE INTELLECTUAL PROPERTY RIGHTS IN AND TO THIS PROGRAM.
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
 * File			: Reference.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240524151553][pluto#brightsoft.co.kr][CREATE: Initial Release]
 */
package com.plutozone.syntax.type;

/**
 * @version 1.0.0
 * @author pluto#brightsoft.co.kr
 * 
 * @since 2024-05-24
 * <p>DESCRIPTION: Example of Reference Type(참조 타입)</p>
 * <p>IMPORTANT: Web Framework에서 개체 생성(예: request.getParameter) 시 new 연산자를 통해 생성될 수 있으므로 문자열 값을 비교 시 가능한 equals()를 사용</p>
 */
public class Reference {
	
	public static void main(String[] args) {
		
		String str1 = "String";
		String str2 = "String";
		String str3 = new String("String");
		
		
		if (str1 == str2) System.out.println("str1 == str2");
		
		if (str2 != str3) System.out.println("str2 != str3");
		
		if (str2.equals(str3)) System.out.println("str2 == str3");
	}
}