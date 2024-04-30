/**
 * YOU ARE STRICTLY PROHIBITED TO COPY, DISCLOSE, DISTRIBUTE, MODIFY OR USE THIS PROGRAM
 * IN PART OR AS A WHOLE WITHOUT THE PRIOR WRITTEN CONSENT OF BRIGHTSOFT.CO.KR.
 * BRIGHTSOFT.CO.KR OWNS THE INTELLECTUAL PROPERTY RIGHTS IN AND TO THIS PROGRAM.
 * COPYRIGHT (C) 2023 BRIGHTSOFT.CO.KR ALL RIGHTS RESERVED.
 *
 * 하기 프로그램에 대한 저작권을 포함한 지적재산권은 brightsoft.co.kr에 있으며,
 * brightsoft.co.kr이 명시적으로 허용하지 않는 사용, 복사, 변경 및 제 3자에 의한 공개, 배포는 엄격히 금지되며
 * brightsoft.co.kr의 지적재산권 침해에 해당된다.
 * Copyright (C) 2023 brightsoft.co.kr All Rights Reserved.
 *
 *
 * Program		: com.plutozone.programming.java
 * Description	:
 * Environment	: JRE 1.7 or more
 * File			: AutoReservation.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20230826151339][pluto#brightsoft.co.kr][CREATE: Initial Release]
 */
package com.plutozone.util;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
// import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version 1.0.0
 * @author pluto#brightsoft.co.kr
 * 
 * @since 2023-08-26
 * <p>DESCRIPTION:</p>
 * <p>IMPORTANT:</p>
 */
public class AutoReservation {
	
	private static Logger logger = LoggerFactory.getLogger(AutoReservation.class);
	
	/** Web Driver */
	private WebDriver webDriver				= null;
	
	/** Properties */
	private String webDriver_id				= null;
	private String webDriver_path			= null;
	
	private static boolean isRunPalace	= false;
	
	/**
	 * @param args [파라미터들]
	 * 
	 * @since 2023-08-26
	 * <p>DESCRIPTION:</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	public static void main(String[] args) {
		
		try {
			
			String browser					= args[0];
			String target					= args[1];
			AutoReservation autoReservation	= null;
			
			if (browser == null)	browser = "";
			if (target == null)		target = "";
			
			if (target.equals("palace")) {
				
				boolean isMessage = true;
				
				for (;;) {
					
					Calendar calendar			= Calendar.getInstance();
					int dayOfWeek				= calendar.get(Calendar.DAY_OF_WEEK);
					String dateReservation		= calculateDate(getNow("yyyyMMdd"), 7);
					String dateReservationNext	= "";
					
					// 예약 1) 일요일 08시 59분에 로그인(최대 1초 지연) 및 대기 후 "차주 일요일 14시와 15시에 B코드"로 예약 실행
					if (dayOfWeek == 1) {
						if (getNow("HH:mm").equals("08:59")) isRunPalace = true;
					}
					// 예약 2) 수요일 05시 59분에 로그인(최대 1초 지연) 및 대기 후 "차주 수요일 19시와 20시에 B코드"로 예약 실행
					else if (dayOfWeek == 4) {
						if (getNow("HH:mm").equals("05:59")) isRunPalace = true;
					}
					// 예약 3) 금요일 05시 59분에 로그인(최대 1초 지연) 및 대기 후 "차주 금요일 19시와 20시에 B코드"로 예약 실행
					else if (dayOfWeek == 6) {
						if (getNow("HH:mm").equals("05:59")) isRunPalace = true;
					}
					
					if (isRunPalace) {
						logger.info("---------------------------------------------------------------------------------------------");
						logger.info("[" + dateReservation + "] 자동 예약이 시작되었습니다.");
						
						isRunPalace = false;
						
						autoReservation = new AutoReservation(browser);
						boolean isRun = autoReservation.startPalace(dayOfWeek, dateReservation);
						
						if (isRun) {
							logger.info("[" + dateReservation + "] 자동 예약이 성공되었습니다.");
							logger.info("---------------------------------------------------------------------------------------------");
						}
						else {
							logger.info("[" + dateReservation + "] 자동 예약이 실패되었습니다!");
							logger.info("---------------------------------------------------------------------------------------------");
						}
						isMessage = true;
					}
					
					if (isMessage) {
						// 예약 1) 일/월/화(수요일 자정 이전에 실행 시)
						if (dayOfWeek == 1 || dayOfWeek == 2 || dayOfWeek == 3) dateReservationNext = "수요일 05시 59분";
						// 예약 2) 수/목(금요일 자정 이전에 실행 시)
						else if (dayOfWeek == 4 || dayOfWeek == 5) dateReservationNext = "금요일 05시 59분";
						// 예약 3) 금/토(일요일 자정 일전에 실행 시)
						else if (dayOfWeek == 6 || dayOfWeek == 7) dateReservationNext = "일요일 08시 59분";
						
						logger.info("---------------------------------------------------------------------------------------------");
						logger.info("다음 자동 예약은 [" + dateReservationNext + "] 입니다.");
						logger.info("---------------------------------------------------------------------------------------------");
						isMessage = false;
					}
					
					Thread.sleep(1000); // 1초 단위
				}
			}
			else if (target.equals("tennis")) {
				autoReservation = new AutoReservation(browser);
				autoReservation.startTennis();
			}
			else {
				logger.error("--------------------------------------------");
				logger.error(" [FAILURE] Please enter the correct Target! ");
				logger.error("--------------------------------------------");
				System.exit(0);
			}
		}
		catch (ArrayIndexOutOfBoundsException e) {
			logger.error("----------------------------------");
			logger.error(" [FAILURE] Enter your Parameters! ");
			logger.error("----------------------------------");
			System.exit(0);
		}
		catch (Exception e) {
			logger.error("[com.plutozone.example.AutoReservation.main()] " + e.getMessage(), e);
			// e.printStackTrace();
		}
	}
	
	/**
	 * @param broswer [브라우저]
	 * 
	 * @since 2023-08-26
	 * <p>DESCRIPTION:</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	public AutoReservation(String broswer) {
		
		super();
		
		if (broswer.equals("firefox")) {
			this.webDriver_id = "webdriver.gecko.driver";
			this.webDriver_path = "./geckodriver.exe";
			
			webDriver = (WebDriver) new FirefoxDriver();
		}
		else if (broswer.equals("chrome")) {
			this.webDriver_id = "webdriver.chrome.driver";
			this.webDriver_path = "./chromedriver.exe";
			
			webDriver = (WebDriver) new ChromeDriver();
		}
		else {
			logger.error("---------------------------------------------");
			logger.error(" [FAILURE] Please enter the correct Browser! ");
			logger.error("---------------------------------------------");
			System.exit(0);
		}
		
		System.setProperty(webDriver_id, webDriver_path);
		
		webDriver.manage().window().maximize();
	}
	
	/**
	 * @since 2023-08-29
	 * <p>DESCRIPTION:</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	public void startTennis() {
		
		try {
			
		}
		catch (Exception e) {
			logger.error("[com.plutozone.example.AutoReservation.startTennis()] " + e.getMessage(), e);
			// e.printStackTrace();
		}
		finally {
			// 브라우저 종료
			webDriver.close();
		}
	}
	
	/**
	 * @param dayOfWeek [요일]
	 * @param dateReservation [예약일]
	 * @return boolean
	 * 
	 * @since 2023-08-26
	 * <p>DESCRIPTION:</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	public boolean startPalace(int dayOfWeek, String dateReservation) {
		
		boolean isRun = false;
		
		try {
			
			Properties properties	= new Properties();
			properties.load(new FileInputStream("./conf/autoReservation.properties"));
			
			// 01. 로그인
			webDriver.get(properties.getProperty("palace.url.login"));
			// logger.info(driver.getPageSource()); 페이지 소스 출력
			
			// iframe으로 구성된 곳은 해당 프레임으로 이동
			// driver.switchTo().frame(driver.findElement(By.id("loginForm")));
			
			WebElement webElement = webDriver.findElement(By.id("building_cd"));
			webElement.sendKeys(properties.getProperty("palace.building_cd"));
			
			webElement = webDriver.findElement(By.id("room_no"));
			webElement.sendKeys(properties.getProperty("palace.room_no"));
			
			webElement = webDriver.findElement(By.id("pass_word"));
			webElement.sendKeys(properties.getProperty("palace.pass_word"));
			
			webElement.submit();
			
			int millisecond = Integer.parseInt(properties.getProperty("palace.millisecond"));
			logger.info("로그인에 성공하였으며 " + millisecond + " / 1000 초를 대기 후 예약 신청 화면으로 이동합니다.");
			Thread.sleep(millisecond);
			
			// 02. 예약 신청-1
			webDriver.get(properties.getProperty("palace.url.reservation-1"));
			
			// 03. [중지]예약 신청-2(선택 기반)
			// Select select = new Select(webDriver.findElement(By.name("mem_no")));
			// select.selectByValue(properties.getProperty("palace.mem_no"));
			// Thread.sleep(1000);
			
			// select = new Select(webDriver.findElement(By.name("place_cd")));
			// select.selectByValue(properties.getProperty("palace.place_cd"));
			// Thread.sleep(1000);
			
			// webElement = webDriver.findElement(By.id("base_date__20230831"));																							// [실패] 날짜 선택-1(직접 날짜 설정)
			// webElement = webDriver.findElement(By.xpath("/html/body/div/div[2]/div/div[1]/section/div/article/div/div/form/fieldset/div[3]/table/tbody/tr[1]/td[2]"));	// [성공] 날짜 선택-2(클릭 날짜 설정)
			// webElement.click();
			// Thread.sleep(1000);
			
			// 03. [완료]예약 신청-2(URL 기반 = 신청자 + 코트 그리고 날짜)
			webDriver.get(properties.getProperty("palace.url.reservation-2") + dateReservation);
			int times = 2;
			// Thread.sleep(1000);
			
			// 예약 1) 일요일
			if (dayOfWeek == 1) {
				
				webElement = webDriver.findElement(By.id("time_seq6"));		// 일요일 14시(time_seq6)
				boolean isEnabled = webElement.isEnabled();
				if (isEnabled) webElement.click();
				else {
					times--;
					logger.error("[" + dateReservation + "] 14시는 이미 예약되어있습니다!");
				}
				// Thread.sleep(1000);
				
				webElement = webDriver.findElement(By.id("time_seq7"));		// 일요일 15시(time_seq7)
				isEnabled = webElement.isEnabled();
				if (isEnabled) webElement.click();
				else {
					times--;
					logger.error("[" + dateReservation + "] 15시는 이미 예약되어있습니다!");
				}
				// Thread.sleep(1000);
			}
			// 예약 2) 수요일
			// 예약 3) 금요일
			else if (dayOfWeek == 4 || dayOfWeek == 6) {
				
				webElement = webDriver.findElement(By.id("time_seq12"));	// 평일+토요일 19시(time_seq12)
				boolean isEnabled = webElement.isEnabled();
				if (isEnabled) webElement.click();
				else {
					times--;
					logger.error("[" + dateReservation + "] 19시는 이미 예약되어있습니다!");
				}
				// Thread.sleep(1000);
				
				webElement = webDriver.findElement(By.id("time_seq13"));	// 평일+토요일 20시(time_seq13)
				isEnabled = webElement.isEnabled();
				if (isEnabled) webElement.click();
				else {
					times--;
					logger.error("[" + dateReservation + "] 20시는 이미 예약되어있습니다!");
				}
				// Thread.sleep(1000);
			}
			
			if (times >= 1) {
				webElement = webDriver.findElement(By.id("reserveForm_proc"));	// 시간 신청(Form ID를 통한 submit)
				webElement.submit();
				logger.info("시간 예약에 성공하였으며 1초를 대기 후 최종 신청 화면으로 이동합니다.");
				Thread.sleep(1000);												// 화면 이동을 해야 동의 체크가 존재하므로
				
				// 04. 예약 신청-3
				webElement = webDriver.findElement(By.id("agree1"));			// 동의 체크
				webElement.click();
				// Thread.sleep(1000);
				
				if (properties.getProperty("palace.run").equals("Y")) {
					webElement.submit();										// 최종 신청
					logger.info("축하드립니다. 최종 신청에 성공하였습니다.");
					isRun = true;
				}
			}
			else {
				logger.error("[" + dateReservation + "] 죄송합니다. 신청한 시간이 이미 예약되었습니다. 다음 기회에 이용하여 주십시오!");
			}
			
			Thread.sleep(3000);
		}
		catch (Exception e) {
			logger.error("[com.plutozone.example.AutoReservation.startPalace()] " + e.getMessage(), e);
			// e.printStackTrace();
		}
		finally {
			// 브라우저 종료
			webDriver.close();
		}
		
		return isRun;
	}
	
	/**
	 * @param datetimeFormat [날짜/시간 형식]
	 * @return String
	 * 
	 * @since 2023-08-30
	 * <p>DESCRIPTION: 현재 날짜/시각을 지정한 형식(datetimeFormat)의 문자열로 얻기</p>
	 * <p>IMPORTANT: 파라미터는 데이터 타입 및 형식을 만족하여야 한다.</p>
	 * <p>EXAMPLE: <code>datetimeFormat = "yyyy-MM-dd" or "yyyy-MM-dd HH:mm:ss" or "yyyy-MM-dd HH:mm:ss SSS"</code></p>
	 */
	public static String getNow(String datetimeFormat) {
		
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat(datetimeFormat);
		
		return formatter.format(currentDate.getTime());
	}
	
	/**
	 * @param dateBase [기준 일자]
	 * @param day [소요 일수]
	 * @throws Exception
	 * @return String
	 * 
	 * @since 2023-08-30
	 * <p>DESCRIPTION: 기준 일자(dateBase)에 증감 일수를 더한 일자를 계산</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	private static String calculateDate(String dateBase, int day) throws Exception {
		
		SimpleDateFormat dtFormat	= new SimpleDateFormat("yyyyMMdd");
		Calendar cal				= Calendar.getInstance();
		Date dt						= dtFormat.parse(dateBase);
		
		cal.setTime(dt);
		cal.add(Calendar.DATE, day);
		
		String date = dtFormat.format(cal.getTime());
		
		return date;
	}
}