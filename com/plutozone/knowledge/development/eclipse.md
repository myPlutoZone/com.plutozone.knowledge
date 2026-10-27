# com.plutozone.knowledge.development.Eclispe


## Contents
- Configuration
- Plug-in(Help > Install New Software)
- Tips


## Configuration
- When an error called `"Downloading external resources is disabled."` occurs
	```text
	Tick off the option "download artifact javadoc" at Window > Preference > Maven
	```
- Workspace settings when starting Eclipse
	```cmd
	# 하기 파일에서 RECENT_WORKSPACES 값을 삭제 또는 변경
	C:\> notepad.exe %ECLIPSE%\configuration\.settings\org.eclipse.ui.ide.prefs
	```
- Code Templates at Window > References > Java > Code Style > Code Templates > Code > New Java files
	```java
	/**
	 * YOU ARE STRICTLY PROHIBITED TO COPY, DISCLOSE, DISTRIBUTE, MODIFY OR USE THIS PROGRAM
	 * IN PART OR AS A WHOLE WITHOUT THE PRIOR WRITTEN CONSENT OF PLUTOZONE.COM.
	 * PLUTOZONE.COM OWNS THE INTELLECTUAL PROPERTY RIGHTS IN AND TO THIS PROGRAM.
	 * COPYRIGHT (C) ${year} PLUTOZONE.COM ALL RIGHTS RESERVED.
	 *
	 * plutozone.com의 사전 서면 동의 없이 본 문서의 전부 또는 일부를 복사, 공개, 배포, 수정하거나 사용하는 행위는 엄격히 금지됩니다.
	 * 본 문서에 대한 지적재산권은 plutozone.com이 보유하고 있습니다.
	 * Copyright (C) ${year} plutozone.com All Rights Reserved.
	 *
	 *
	 * Program		: com.plutozone.knowledge
	 * Description	:
	 * Environment	: JRE 1.7 or more
	 * File			: ${file_name}
	 * Notes		:
	 * History		: [NO][Programmer][Description]
	 *				: [${currentDateTime:date('yyyyMMddHHmmss')}][pluto#plutozone.com][CREATE: Initial Release]
	*/
	${package_declaration}

	/**
	 * @version 1.0.0
	 * @author pluto#plutozone.com
	 * 
	 * @since ${currentDate:date('yyyy-MM-dd')}
	 * <p>DESCRIPTION:</p>
	 * <p>IMPORTANT:</p>
	 */
	${typecomment}
	${type_declaration}
	```


## Plug-in(Help > Install New Software)
- Properties Editor(http://propedit.sourceforge.jp/eclipse/updates)
	```cmd
	# 하기와 같이 수동으로 플러그인 설치 가능
	C:> copy jp.gr.java_conf.ussiy.app.propedit_6.0.5.jar %ECLIPSE%\plugins
	C:> notepad.exe %ECLIPSE%\configuration\org.eclipse.equinox.simpleconfigurator\bundles.info
	...
	# 하기 라인을 추가
	jp.gr.java_conf.ussiy.app.propedit,6.0.5,plugins/jp.gr.java_conf.ussiy.app.propedit_6.0.5.jar,4,false
	...
	```


## Tips
- When a Git password Error occurs
	```cmd
	Delete ID/Value Informations at References > General > Security > Secure Storage > Contents > GIT
	or
	C:\del C:\Users\%USER%\.eclipse\org.eclipse.equinox.security\secure_storage
	```
- Remove Launch History
	```cmd
	# 하기 경로에서 파일을 삭제
	%WORKSPACE%/.metadata/.plugins/org.eclipse.debug.core/.launches
	```