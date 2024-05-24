/**
 * YOU ARE STRICTLY PROHIBITED TO COPY, DISCLOSE, DISTRIBUTE, MODIFY OR USE THIS PROGRAM
 * IN PART OR AS A WHOLE WITHOUT THE PRIOR WRITTEN CONSENT OF PLUTOZONE.COM.
 * PLUTOZONE.COM OWNS THE INTELLECTUAL PROPERTY RIGHTS IN AND TO THIS PROGRAM.
 * COPYRIGHT (C) 2023 PLUTOZONE.COM ALL RIGHTS RESERVED.
 *
 * 하기 프로그램에 대한 저작권을 포함한 지적재산권은 plutozone.com에 있으며,
 * plutozone.com이 명시적으로 허용하지 않는 사용, 복사, 변경 및 제 3자에 의한 공개, 배포는 엄격히 금지되며
 * plutozone.com의 지적재산권 침해에 해당된다.
 * Copyright (C) 2023 plutozone.com All Rights Reserved.
 *
 *
 * Program		: com.plutozone.knowledge
 * Description	:
 * Environment	: JRE 1.7 or more
 * File			: ForEach.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20230911172852][pluto#brightsoft.co.kr][CREATE: Initial Release]
 */
package com.plutozone.syntax.control;

/**
 * @version 1.0.0
 * @author pluto#brightsoft.co.kr
 * 
 * @since 2023-09-11
 * <p>DESCRIPTION: Example of For Each</p>
 * <p>IMPORTANT:</p>
 */
public class ForEach {
	
	public static void main(String[] args) {
		
		int[] marks			= {125, 132, 95, 116, 110};
		int highest_marks	= maximum(marks);
		
		
		System.out.println("The highest score is " + highest_marks);
	
	}
	
	/**
	 * @param numbers [숫자들]
	 * @return int
	 * 
	 * @since 2023-09-11
	 * <p>DESCRIPTION:</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	public static int maximum(int[] numbers) {
		
		int maxSoFar = numbers[0];
		
		/** for each loop in Java
		for (type var : array) { 
			statements using var;
		}
		*/
		for (int number : numbers) {
			if (number > maxSoFar) maxSoFar = number;
		}
		
		return maxSoFar;
	}
}