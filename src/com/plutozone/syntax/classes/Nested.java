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
 * File			: Nested.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240603071934][pluto#brightsoft.co.kr][CREATE: Initial Release]
 */
package com.plutozone.syntax.classes;

/**
 * @version 1.0.0
 * @author pluto#brightsoft.co.kr
 * 
 * @since 2024-06-03
 * <p>DESCRIPTION: 중첩 클래스(Nested Class)의 종류</p>
 * <p>IMPORTANT:</p>
 */
public class Nested {
	
	Nested() {
		System.out.println("0. 중첩 객체가 생성됨");
	}
	
	/** 1. 멤버 클래스 */
	/** 1-1. 인스턴스 멤버 클래스(Nested 내부에서 선언되며 Nested를 생성해야만 MemberInstance를 사용 가능) */
	public class MemberInstance {
		
		MemberInstance() {
			System.out.println("1-1. 인스턴스 멤버 객체가 생성됨");
		}
		
		int field1;
		// static int field2;			// Java 17 version or higher
		void method1() { }
		// static void method2() { }	// Java 17 version or higher
	}
	
	/** 1-2. 정적 멤버 클래스(Nested 내부에서 static으로 선언되며 Nested로 MemberStatic를 바로 사용 가능 */
	static class MemberStatic {
		
		MemberStatic() {
			System.out.println("1-2. 정적 멤버 객체가 생성됨");
		}
		
		int field1;
		static int field2;
		void method1() { }
		static void method2() { }
	}
	
	void method() {
		
		/** 2. 로컬 클래스(Nested의 Method 내부에서 선언되며 Method가 실행되면 사용할 수 있는 Local 클래스 */
		class Local {
			
			Local() {
				System.out.println("2. 로컬 객체가 생성됨");
			}
			
			@SuppressWarnings("unused")
			int field1;
			// static int field2;			// Java 17 version or higher
			void method1() { }
			// static void method2() { }	// Java 17 version or higher
		}
		Local local = new Local();
		local.field1 = 3;
		local.method1();
		// local.field2 = 3;				// Java 17 version or higher
		// local.method2();					// Java 17 version or higher
	}
}