<%
/**
 * YOU ARE STRICTLY PROHIBITED TO COPY, DISCLOSE, DISTRIBUTE, MODIFY OR USE THIS PROGRAM
 * IN PART OR AS A WHOLE WITHOUT THE PRIOR WRITTEN CONSENT OF PLUTOZONE.COM.
 * PLUTOZONE.COM OWNS THE INTELLECTUAL PROPERTY RIGHTS IN AND TO THIS PROGRAM.
 * COPYRIGHT (C) 2018 PLUTOZONE.COM ALL RIGHTS RESERVED.
 *
 * 하기 프로그램에 대한 저작권을 포함한 지적재산권은 plutozone.com에 있으며,
 * plutozone.com이 명시적으로 허용하지 않는 사용, 복사, 변경 및 제 3자에 의한 공개, 배포는 엄격히 금지되며
 * plutozone.com의 지적재산권 침해에 해당된다.
 * Copyright (C) 2018 plutozone.com All Rights Reserved.
 *
 *
 * Program		: com.plutozone.backoffice
 * Description	:
 * Environment	: JRE 1.7 or more
 * File			: /WEB-INF/view/backoffice/common/userAuthForm.jsp
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20181121134700][pluto#plutozone.com][CREATE: Initial Release]
 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ page info="/WEB-INF/view/backoffice/common/userAuthForm.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/include/header.jsp" %>
	<link rel="stylesheet" href="/css/main.css" type="text/css" />
	<script type="text/javascript" src="/console/js/common.Form.min-1.0.0.js"></script>
	<script type="text/javascript" src="/console/js/common.Number.min-1.0.0.js"></script>
	<script type="text/javascript" src="/console/js/common.Text.min-1.0.0.js"></script>
	<script type="text/javascript" src="/console/js/common.User.min-1.0.0.js"></script>
	<script type="text/javascript" src="/console/js/common.Ajax.min-1.0.0.js"></script>
	<script type="text/javascript" src="/console/js/common/userAuthForm.min-1.0.0.js"></script>
</head>
<body>
<div id="layerBackground" style="width: 100%; height:100%; top: 0px; position: absolute; z-index: 1; background: #0F0F0F; visibility: hidden; opacity: 0.7;">
	<div id="openLayerPopup" style="display: none;">
		<div>
			<div style="margin-top: 130px; text-align: center; z-index: 2; color: white;"><span id="MSG_INF_WAIT_EMAIL"></span></div>
			<div id="closeLayerPopup" style="margin-top: 125px;"><!-- <a href="javascript: closeLayer();"> CLOSE </a> --></div>
		</div>
	</div>
</div>
<form name="frmMain" id="frmMain" method="post">
<input type="hidden" id="email" name="email" value="" />
<input type="hidden" id="seq_usr_auth_his" name="seq_usr_auth_his" value="0" />
<div class="container">
	<header>
	</header>
	<section class="content">
		<article>
			<div id="box_write">
				<div class="frmWrite">
					<table class="tblColEditable" summary="사용자 본인 인증 유형 선택 테이블">
						<caption>사용자 본인 인증 유형 선택</caption>
						<colgroup>
							<col style="width: 30%;">
							<col style="width: 70%;">
						</colgroup>
						<tbody>
							<tr>
								<th scope="row"><span id="TXT_USR_AUTH_TYPE"></span></th>
								<td>
									<select id="tc_usr_auth_type" name="tc_usr_auth_type" style="width: 150px;">
										<option value="14">이메일</option>
										<option value="15">휴대폰 번호</option>
										<option value="16">휴대폰 명의자</option>
									</select>
								</td>
							</tr>
						</tbody>
					</table>
					<br />
					<p id="TXT_INPUT_REQUIRED" class="txtLeft"></p> 
					<table class="tblColEditable" summary="등록형 테이블">
						<caption>등록</caption>
						<colgroup>
							<col style="width: 20%;">
							<col style="width: 80%;">
						</colgroup>
						<tbody>
							<tr id="name" style="display: none;">
								<th scope="row">* <span id="TXT_NM"></span></th>
								<td>
									<input type="text" id="nm_family" name="nm_family" placeholder="성" style="width: 50px;" minlength="3" maxlength="32" autocomplete="off" onkeydown="if (event.keyCode==13) checkSubmit(); common.Form.changeFocus(this, 32, 'nm_given');" onClick="common.Form.removeValue(this);" />
									<input type="text" id="nm_given" name="nm_given" placeholder="이름" style="width: 100px;" minlength="3" maxlength="32" autocomplete="off" onkeydown="if (event.keyCode==13) checkSubmit(); common.Form.changeFocus(this, 32, 'en_given');" onClick="common.Form.removeValue(this);" />
								</td>
							</tr>
							<tr id="birthday_" style="display: none;">
								<th scope="row">* <span id="TXT_BIRTHDAY"></span></th>
								<td>
									<input type="text" id="birthday" name="birthday" placeholder="YYYYMMDD" style="width: 80px;" minlength="8" maxlength="8" autocomplete="off" onkeydown="if (event.keyCode==13) checkSubmit(); common.Form.changeFocus(this, 8, 'cellphone_1');" onClick="common.Form.removeValue(this);" />
								</td>
							</tr>
							<tr id="gender" style="display: none;">
								<th scope="row" style="padding-top: 10px; padding-bottom: 10px;">* <span id="TXT_GENDER"></span></th>
								<td>
									<input type="radio" name="flg_man" value="Y" autocomplete="off" checked onkeydown="if (event.keyCode==13) checkSubmit();" />&nbsp;<span id="TXT_MAN"></span>
									<input type="radio" name="flg_man" value="N" autocomplete="off" onkeydown="if (event.keyCode==13) checkSubmit();" />&nbsp;<span id="TXT_WOMAN"></span>
								</td>
							</tr>
							<tr id="nation" style="display: none;">
								<th scope="row" style="padding-top: 10px; padding-bottom: 10px;">* <span id="TXT_NATION"></span></th>
								<td>
									<input type="radio" name="flg_forienger" value="N" autocomplete="off" checked onkeydown="if (event.keyCode==13) checkSubmit();" />&nbsp;<span id="TXT_NATIVE"></span>
									<input type="radio" name="flg_forienger" value="Y" autocomplete="off" onkeydown="if (event.keyCode==13) checkSubmit();" />&nbsp;<span id="TXT_FORIENGER"></span>
								</td>
							</tr>
							<tr id="phone" style="display: none;">
								<th scope="row">* <span id="TXT_PHONE"></span></th>
								<td>
									<input type="text" id="cellphone_1" name="cellphone_1" style="width: 30px;" minlength="3" maxlength="3" autocomplete="off" onkeypress="common.Number.checkNumber(event);" onkeydown="if (event.keyCode==13) checkSubmit(); common.Form.changeFocus(this, 3, 'cellphone_2');" onClick="common.Form.removeValue(this);" /> -
									<input type="text" id="cellphone_2" name="cellphone_2" style="width: 40px;" minlength="4" maxlength="4" autocomplete="off" onkeypress="common.Number.checkNumber(event);" onkeydown="if (event.keyCode==13) checkSubmit(); common.Form.changeFocus(this, 4, 'cellphone_3');" onClick="common.Form.removeValue(this);" /> -
									<input type="text" id="cellphone_3" name="cellphone_3" style="width: 40px;" minlength="4" maxlength="4" autocomplete="off" onkeypress="common.Number.checkNumber(event);" onClick="common.Form.removeValue(this);" />
									<span id="phoneNumber" style="display: none;">
										<a href="javascript:getNumber();" class="btnOnTable"><span id="BTN_REQUEST"></span></a>
										&nbsp;&nbsp;&nbsp;인증 번호&nbsp;<input type="text" id="number" name="number" style="width: 60px;" minlength="6" maxlength="6" autocomplete="off" onkeypress="common.Number.checkNumber(event);" onkeydown="if (event.keyCode==13) checkSubmit();" onClick="common.Form.removeValue(this);" />
									</span>
								</td>
							</tr>
							<tr id="telecom" style="display: none;">
								<th scope="row">* <span id="TXT_TELECOM"></span></th>
								<td>
									<select id="tc_telecom" name="tc_telecom" style="width: 150px;">
										<option id="TXT_INPUT_SELECT" value=""></option>
										<c:choose>
											<c:when test="${empty listTelecom}">
											</c:when>
											<c:otherwise>
												<c:forEach items="${listTelecom}" var="list">
													<c:choose>
														<c:when test="${LANG == 'en-us'}">
															<option value="${list.seq_code_total}">${list.code_total_en}</option>
														</c:when>
														<c:otherwise>
															<option value="${list.seq_code_total}">${list.code_total_nm}</option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</select>
								</td>
							</tr>
							<tr id="email_">
								<th scope="row">* <span id="TXT_EMAIL"></span></th>
								<td>
									<input type="text" id="email1" name="email1" style="width: 80px;" required minlength="1" maxlength="16" autocomplete="off" onkeydown="if (event.keyCode==13) checkSubmit(); common.Form.changeFocus(this, 16, 'email2');" onClick="common.Form.removeValue(this);" /> @
									<input type="text" id="email2" name="email2" style="width: 80px;" required minlength="3" maxlength="16" autocomplete="off" onkeydown="if (event.keyCode==13) checkSubmit();" onClick="common.Form.removeValue(this);" />
									<span id="selectEmail"></span>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div>
					<a id="submit" href="javascript:checkSubmit();" class="btnBasic"><span id="BTN_CONFIRM"></span></a>
				</div>
			</div>
		</article>
	</section>
	<footer>
		<%@ include file="/include/footer.jsp" %>
	</footer>
</div>
</form>
</body>
</html>