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
 * File			: UsingAnonymousImplement.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20240604235401][pluto#brightsoft.co.kr][CREATE: Initial Release]
 */
package com.plutozone.syntax.anonymous;

/**
 * @version 1.0.0
 * @author pluto#brightsoft.co.kr
 * 
 * @since 2024-06-04
 * <p>DESCRIPTION: 익명 구현 객체 사용</p>
 * <p>IMPORTANT:</p>
 */
public class UsingAnonymousImplement {
	
	public static void main(String[] args) {
		AnonymousImplement anonymousImplement = new AnonymousImplement();
		// 익명 객체 필드 사용
		anonymousImplement.field.turnOn();
		// 익명 객체 로컬변수 사용
		anonymousImplement.method1();
		// 익명 객체 매개값 사용
		anonymousImplement.method2(
			new RemoteControl() {
				@Override
				public void turnOn() {
					System.out.println("SmartTV를 켭니다.");
				}
				@Override
				public void turnOff() {
					System.out.println("SmartTV를 끕니다.");
				}
			}
		);
	}
}