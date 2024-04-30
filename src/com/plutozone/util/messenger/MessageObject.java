/**
 * YOU ARE STRICTLY PROHIBITED TO COPY, DISCLOSE, DISTRIBUTE, MODIFY OR USE THIS PROGRAM
 * IN PART OR AS A WHOLE WITHOUT THE PRIOR WRITTEN CONSENT OF PLUTOZONE.COM.
 * PLUTOZONE.COM OWNS THE INTELLECTUAL PROPERTY RIGHTS IN AND TO THIS PROGRAM.
 * COPYRIGHT (C) 2017 PLUTOZONE.COM ALL RIGHTS RESERVED.
 *
 * 하기 프로그램에 대한 저작권을 포함한 지적재산권은 plutozone.com에 있으며,
 * plutozone.com이 명시적으로 허용하지 않는 사용, 복사, 변경 및 제 3자에 의한 공개, 배포는 엄격히 금지되며
 * plutozone.com의 지적재산권 침해에 해당된다.
 * Copyright (C) 2017 plutozone.com All Rights Reserved.
 *
 *
 * Program		: com.plutozone.util
 * Description	:
 * Environment	: JRE 1.7 or more
 * File			: MessageObject.java
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20170614133300][pluto#plutozone.com][CREATE: Initial Release]
 */
package com.plutozone.util.messenger;

import java.io.Serializable;

/**
 * @version 1.0.0
 * @author pluto#plutozone.com
 * 
 * @since 2017-06-14
 * <p>DESCRIPTION: 메신저 메시지 클래스</p>
 * <p>IMPORTANT:</p>
 */
class MessageObject implements Serializable {
	
	/** Serial version UID */
	private static final long serialVersionUID = 20170614134100L;
	
	/** Name */
	protected String name;
	
	/** Message */
	protected String message;
	
	/** Control */
	protected int control;
	
	/**
	 * @param control [상태]
	 * @param name [이름]
	 * @param message [메시지]
	 * 
	 * @since 2017-06-14
	 * <p>DESCRIPTION:</p>
	 * <p>IMPORTANT:</p>
	 * <p>EXAMPLE:</p>
	 */
	public MessageObject(int control, String name, String message) {
		this.control	= control;
		this.name		= name;
		this.message	= message;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public int getControl() {
		return control;
	}
	
	public void setControl(int control) {
		this.control = control;
	}
}