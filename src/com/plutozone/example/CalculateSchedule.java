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
 * Program		: com.plutozone.programming.java
 * Description	: The program calculates the training schedule based on the time given to subjects.
 * Environment	: JRE 1.7 or more
 * File			: CalculateSchedule.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20230817120414][pluto#brightsoft.co.kr][CREATE: Initial Release]
 */
package com.plutozone.example;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @version 1.0.0
 * @author pluto#brightsoft.co.kr
 * 
 * @since 2023-08-17
 * <p>DESCRIPTION: 휴일이 제외된 시간(hour)을 일자(date) 그리고 기간(period)으로 계산(Calculate)</p>
 * <p>IMPORTANT:</p>
 */
public class CalculateSchedule {
	
	/** 공휴일 */
	static final String[] HOLIDAYS = {
								"20240515"												// 부처님(15)
								, "20240606"											// 현충일(6)
								, "20240703"											// 휴가#1(3)
								, "20240815"											// 광복절(15)
								, "20240916", "20240917", "20240918"					// 추석(16 ~ 18)
								, "20241003", "20241009"								// 개천절(3), 한글날(9)
								, "20241225"											// 크리스마스(25)
								/*
								"20240515", "20240531"									// 부처님(15), 휴가#1(31)
								, "20240606", "20240628"								// 현충일(6), 휴가#2(28)
								, "20240703"											// 휴가#3(3)
								, "20240815", "20240830"								// 광복절(15), 휴가#4(30)
								, "20240916", "20240917", "20240918", "20240927"		// 추석(16 ~ 18), 휴가#5(27)
								, "20241003", "20241009", "20241025"					// 개천절(3), 한글날(9), 휴가#6(25)
								, "20241225"											// 크리스마스(25)
								*/
							};
	
	/**
	 * @param args [파라미터]
	 * 
	 * @since 2023-08-17
	 * <p>DESCRIPTION:</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	public static void main(String[] args) {
		
		/** 시작 일자(초기값 포함), 종료 일자와 남은 시간(초기값) */
		String dateStart		= "20240527";
		String dateEnd			= "";
		int dateEndRemainHour	= 0;
		
		String hourFrom			= "09시";
		String hourTo			= "18시";
		
		/** 소요 시간 */
		String[] arrSubject	= {"e-Commerce", "HTML + CSS + JavaScript", "Java + JSP + Spring", "DBMS + SQL", "Service + Plaform", "Android", "AI(Phthon) + RPA", "Payment", "Credit", "Mini Project", "Main Project"};
		int[] arrHour		= {12, 80, 200, 64, 80, 56, 80, 32, 16, 60, 280};
		
		//String[] arrSubject	= {"e-Commerce", "HTML + CSS + JavaScript", "Java + JSP + Spring", "DBMS + SQL", "Service + Plaform(Solution-A)", "Android", "AI(Phthon) + RPA", "Payment", "Credit", "Mini Project(Solution-B)", "Main Project(Solution-A)"};
		//int[] arrHour		= {12, 60, 158, 50, 58, 80, 150, 26, 26, 60, 280};
		
		//String[] arrSubject	= {"e-Commerce", "Java + JSP + Spring", "DBMS + SQL", "HTML + CSS + JavaScript", "Service + Plaform(Solution-A)", "Payment", "Credit", "Android", "AI(Phthon) + RPA", "Mini Project(Solution-B)", "Main Project(Solution-A)"};
		//int[] arrHour		= {12, 158, 50, 60, 58, 26, 26, 80, 150, 60, 280};
		
		//String[] arrSubject	= {"1", "2", "3", "4"};
		//int[] arrHour		= {14, 4, 8, 120 * 8 - 26};
		
		//String[] arrSubject	= {"All"};
		//int[] arrHour		= {960};
		
		
		int countSubject	= arrSubject.length;
		/** 소요 일수 */
		float[] arrDay	= new float[countSubject];
		/** 총 시간 */
		int sumHour		= 0;
		
		/** 소요 일수 및 시작 일자와 종료 일자를 계산 */
		for (int loop = 0; loop < countSubject; loop++) {
			
			// # Region [2023-08-17][pluto#brightsoft.co.kr][UPDATE: 소수점 있는 소요 일수 적용]
			arrDay[loop] = (float) (arrHour[loop] / 8.0);
			// arrDay[loop] = arrHour[loop] / 8;
			// # End region
			
			/** 총 소요 시간 */
			sumHour	+= arrHour[loop];
			
			// # Region [2023-08-17][pluto#brightsoft.co.kr][UPDATE: 포맷 적용 for 소요 일수]
			System.out.print(String.format("%2s", loop + 1) + ". " + String.format("%32s", arrSubject[loop])
					+ "\t: "
					+ String.format("%4d", arrHour[loop]) + "시간("
					+ String.format("%#5.2f", arrDay[loop])
					+ "일)" 
					+ String.format("%5d", sumHour) + "시간(" + String.format("%#6.2f", sumHour / 8.0 ) + "일)"
					);
			// # End
			
			try {
				
				/** 시작 일시의 요일과 시간 */
				LocalDate dateStartDay = LocalDate.of(Integer.parseInt(dateStart.substring(0, 4))
						, Integer.parseInt(dateStart.substring(4, 6))
						, Integer.parseInt(dateStart.substring(6, 8)));
				DayOfWeek dayOfWeek = dateStartDay.getDayOfWeek();
				String dateStartDayName = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREAN);
				
				if (dateEndRemainHour == 8 || dateEndRemainHour == 0) hourFrom = "09시";
				else if (dateEndRemainHour == 7) hourFrom = "10시";
				else if (dateEndRemainHour == 6) hourFrom = "11시";
				else if (dateEndRemainHour == 5) hourFrom = "13시";
				else if (dateEndRemainHour == 4) hourFrom = "14시";
				else if (dateEndRemainHour == 3) hourFrom = "15시";
				else if (dateEndRemainHour == 2) hourFrom = "16시";
				else if (dateEndRemainHour == 1) hourFrom = "17시";
				else hourFrom = "??시";
				
				/** 남은 시간, 시작 일자 및 소요 시간을 통하여 다음 종료 일자와 남은 시간을 산출 */
				Map<String, Integer> result = calculateDateEnd(dateEndRemainHour, dateStart, arrHour[loop]);
				for (String key: result.keySet()) {
					dateEnd				= key;					// 종료 일자
					dateEndRemainHour	= result.get(key);		// 남은 시간
				}
				
				LocalDate dateEndDay = LocalDate.of(Integer.parseInt(dateEnd.substring(0,4))
						, Integer.parseInt(dateEnd.substring(4, 6))
						, Integer.parseInt(dateEnd.substring(6, 8)));
				dayOfWeek = dateEndDay.getDayOfWeek();
				String dateEndDayName = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREAN);
				
				/** 종료 일시의 요일과 시간 */
				if (dateEndRemainHour == 0 || dateEndRemainHour == 8) hourTo = "18시";
				else if (dateEndRemainHour == 1) hourTo = "17시";
				else if (dateEndRemainHour == 2) hourTo = "16시";
				else if (dateEndRemainHour == 3) hourTo = "15시";
				else if (dateEndRemainHour == 4) hourTo = "14시";
				else if (dateEndRemainHour == 5) hourTo = "12시";
				else if (dateEndRemainHour == 6) hourTo = "11시";
				else if (dateEndRemainHour == 7) hourTo = "10시";
				else hourTo = "??시";
				// System.out.print("[남은 시간: " + dateEndRemainHour + "]");
				
				System.out.println("\t"
						+ dateStart.substring(0, 4) + "년 "
						+ dateStart.substring(4, 6) + "월 "
						+ dateStart.substring(6, 8) + "일"
						+ "(" + dateStartDayName +") " + hourFrom
						+ " ~ "
						+ dateEnd.substring(0, 4) + "년 "
						+ dateEnd.substring(4, 6) + "월 "
						+ dateEnd.substring(6, 8) + "일"
						+ "(" + dateEndDayName + ") " + hourTo
						+ " [" + String.format("%2d", (int) Math.ceil(arrDay[loop])) + "일간]"
						);
				
				/** 종료 일자를 다음 시작 일자로 설정(단, 종료 일자의 남은 시간이 없을 경우 다음날 일자로 설정) */
				if (!dateStart.equals(dateEnd))	dateStart = dateEnd;
				if (dateEndRemainHour % 8 == 0)	dateStart = calculateDate(dateEnd, 1);
				
				/** 시작 일자가 주말 또는 공휴일인 경우 재설정 */
				dateStart = calculateDate(dateStart, -1);
				
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @param dateEndRemainHour [이전 과목의 남은 시간]
	 * @param dateStart [시작 일자]
	 * @param hour [소요 시간]
	 * @return Map<String, Integer>
	 * @throws Exception 
	 * 
	 * @since 2023-08-18
	 * <p>DESCRIPTION: 시작 일자와 소요 시간에 따른 종료 일자 등 계산</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	private static Map<String, Integer> calculateDateEnd(int dateEndRemainHour, String dateStart, int hour) throws Exception {
		
		/** 이번 과목의 종료 일자와 남은 시간 */
		Map<String, Integer> dateEnds = new HashMap<String, Integer>();
		
		int days = (int) Math.ceil(hour / 8.0);	
		if (dateEndRemainHour == 8 ) dateEndRemainHour = 0;
		
		// 이전 과목의 종료 일자에 남은 시간이 있는 경우
		if (dateEndRemainHour > 0) {
			// 남은 시간이 소요 시간보다 많은 경우
			if (dateEndRemainHour - hour >= 0) {
				/** 이번 과목의 종료 일자 = 시작 일자 and 남은 시간 = 남은 시간 - 소요 시간 */
				dateEnds.put(dateStart, dateEndRemainHour - hour);
			}
			// 남은 시간이 소요 시간보다 작은 경우
			else {
				/** 이번 과목의 종료 일자 = 소요 일자 또는 소요 일자 - 1일 and 남은 시간 = 8 - 소진 후 소요 시간의 남은 시간 */
				if ((hour - dateEndRemainHour) / 8 == days - 1 && (hour - dateEndRemainHour) % 8 > 0 ) {
					dateEnds.put(calculateDate(dateStart, days), 8 - (hour - dateEndRemainHour) % 8);
				}
				else {
					dateEnds.put(calculateDate(dateStart, days - 1), 8 - (hour - dateEndRemainHour) % 8);
				}
			}
		}
		// 이전 과목의 종료 일자에 남은 시간이 없을 경우
		else {
			/** 이번 과목의 종료 일자 = 소요 일자 - 1일 and 남은 시간 = 8 - 소요 시간의 남은 시간 */
			dateEnds.put(calculateDate(dateStart, days - 1), 8 - (hour % 8));
		}
		return dateEnds;
	}
	
	/**
	 * @param dateBase [기준 일자]
	 * @param day [일]
	 * @throws ParseException
	 * @return int
	 * 
	 * @since 2023-08-17
	 * <p>DESCRIPTION:</p>
	 * <p>IMPORTANT: 휴일 일수를 포함한 일수</p>
	 * <p>EXAMPLE:</p>
	 */
	public static int addHoliday(String dateBase, int day) throws ParseException {
		
		DateFormat formater		= new SimpleDateFormat("yyyyMMdd");
		Calendar beginCalendar	= Calendar.getInstance();
		
		beginCalendar.setTime(formater.parse(dateBase));
		
		int sumDay				= 0;
		// int countHoliday		= 0;
		LocalDate eachDay		= null;
		DayOfWeek dayOfWeek		= null;
		
		/** 과목 시작 일자가 주말 또는 휴일일 경우 사용 시(day == -1) */
		if (day == -1) {
			
			boolean isholiday = false;
			
			LocalDate dateStartDay = LocalDate.of(Integer.parseInt(formater.format(beginCalendar.getTime()).substring(0,4))
					, Integer.parseInt(formater.format(beginCalendar.getTime()).substring(4, 6))
					, Integer.parseInt(formater.format(beginCalendar.getTime()).substring(6, 8)));
			
			dayOfWeek = dateStartDay.getDayOfWeek();
			
			// 주말일 경우
			if (dayOfWeek.getValue() > 5) isholiday = true;
			// 주중이면서 공휴일이 있을 경우
			for (int loop = 0; loop < HOLIDAYS.length; loop++) {
				if (dayOfWeek.getValue() < 6 && formater.format(beginCalendar.getTime()).equals(HOLIDAYS[loop])) {
					isholiday = true;
				}
			}
			
			// 주말도 아니고 휴일도 아닐때까지 일자 증가
			if (isholiday) {
				
				while (isholiday) {
					
					beginCalendar.add(Calendar.DATE, 1);
					
					eachDay = LocalDate.of(Integer.parseInt(formater.format(beginCalendar.getTime()).substring(0,4))
							, Integer.parseInt(formater.format(beginCalendar.getTime()).substring(4, 6))
							, Integer.parseInt(formater.format(beginCalendar.getTime()).substring(6, 8)));
					
					dayOfWeek = eachDay.getDayOfWeek();
					
					// 평일
					if (dayOfWeek.getValue() < 6) {
						
						for (int loop = 0; loop < HOLIDAYS.length; loop++) {
							if (formater.format(beginCalendar.getTime()).equals(HOLIDAYS[loop])) {
								isholiday = true;
								loop = HOLIDAYS.length;
							}
							else {
								isholiday = false;
							}
						}
					}
					else isholiday = true;
					
					sumDay++;
				}
			}
		}
		
		/** 휴일이 포함된 일수 */
		for (int loop = 1; loop < day + 1; loop++) {
			
			beginCalendar.add(Calendar.DATE, 1);
			
			eachDay = LocalDate.of(Integer.parseInt(formater.format(beginCalendar.getTime()).substring(0,4))
					, Integer.parseInt(formater.format(beginCalendar.getTime()).substring(4, 6))
					, Integer.parseInt(formater.format(beginCalendar.getTime()).substring(6, 8)));
			
			dayOfWeek = eachDay.getDayOfWeek();
			
			// String eachDayName = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREAN);
			// System.out.println(formater.format(beginCalendar.getTime()) + eachDayName + "(" + dayOfWeek.getValue() + ")");
			
			// 주말이 있을 경우
			if (dayOfWeek.getValue() > 5) {
				loop--;
				// countHoliday++;
			}
			
			// 주중이면서 공휴일이 있을 경우
			for (int loop2 = 0; loop2 < HOLIDAYS.length; loop2++) {
				if (dayOfWeek.getValue() < 6 && formater.format(beginCalendar.getTime()).equals(HOLIDAYS[loop2])) {
					loop--;
					// countHoliday++;
				}
			}
			sumDay++;
		}
		
		// if (day > -1) System.out.print(" 휴일 " + String.format("%2d", countHoliday) + "일");
		
		return sumDay;
	}
	
	/**
	 * @param dateBase [기준 일자]
	 * @param day [소요 일수]
	 * @throws Exception
	 * @return String
	 * 
	 * @since 2023-08-17
	 * <p>DESCRIPTION: 기준 일자(dateBase)에 증감 일수를 더한 일자(추가적으로 휴일을 포함)를 계산</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	private static String calculateDate(String dateBase, int day) throws Exception {
	
		SimpleDateFormat dtFormat	= new SimpleDateFormat("yyyyMMdd");
		Calendar cal				= Calendar.getInstance();
		Date dt						= dtFormat.parse(dateBase);
		
		/** 기준 일자(dateBase)부터 증감 일수까지 휴일이 있을 경우 휴일 일수(holiday)만큼 day를 증가 */
		int addedHoliday = addHoliday(dateBase, day);
		
		cal.setTime(dt);
		cal.add(Calendar.DATE, addedHoliday);
		
		String dateEnd = dtFormat.format(cal.getTime());
		
		return dateEnd;
	}
}
