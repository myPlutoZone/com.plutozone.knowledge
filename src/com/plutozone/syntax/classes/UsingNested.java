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
 * File			: UsingNested.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240603073513][pluto#brightsoft.co.kr][CREATE: Initial Release]
 */
package com.plutozone.syntax.classes;

/**
 * @version 1.0.0
 * @author pluto#brightsoft.co.kr
 * 
 * @since 2024-06-03
 * <p>DESCRIPTION: 중첩 클래스(Nested Class) 사용법</p>
 * <p>IMPORTANT:</p>
 */
public class UsingNested {
	
	public static void main(String[] args) {
		
		Nested nested = new Nested();
		
		/** 1-1. 인스턴스 멤버 클래스 객체 생성 */
		Nested.MemberInstance memberInstance = nested.new MemberInstance();
		memberInstance.field1 = 3;
		memberInstance.method1();
		
		// Nested.MemberInstance.field2 = 3;	// Java 17 version or higher
		// Nested.MemberInstance.method2(); 	// Java 17 version or higher
		
		/** 1-2. 정적 멤버 클래스 객체 생성 */
		Nested.MemberStatic c = new Nested.MemberStatic();
		c.field1 = 3;
		c.method1();
		Nested.MemberStatic.field2 = 3;
		Nested.MemberStatic.method2();
		
		/** 2. 로컬 클래스 객체 생성을 위한 메소드 호출 */
		nested.method();
	}
}