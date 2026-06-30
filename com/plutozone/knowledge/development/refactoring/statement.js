/**
 * YOU ARE STRICTLY PROHIBITED TO COPY, DISCLOSE, DISTRIBUTE, MODIFY OR USE THIS PROGRAM
 * IN PART OR AS A WHOLE WITHOUT THE PRIOR WRITTEN CONSENT OF PLUTOZONE.COM.
 * PLUTOZONE.COM OWNS THE INTELLECTUAL PROPERTY RIGHTS IN AND TO THIS PROGRAM.
 * COPYRIGHT (C) 2026 PLUTOZONE.COM ALL RIGHTS RESERVED.
 *
 * 하기 프로그램에 대한 저작권을 포함한 지적재산권은 plutozone.com에 있으며,
 * plutozone.com이 명시적으로 허용하지 않는 사용, 복사, 변경 및 제 3자에 의한 공개, 배포는 엄격히 금지되며
 * plutozone.com의 지적재산권 침해에 해당된다.
 * Copyright (C) 2026 plutozone.com All Rights Reserved.
 *
 *
 * Program		: com.plutozone.knowledge
 * Description	:
 * Environment	: JavaScript
 * File			: statament.js
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20260630114300][pluto#plutozone.com][CREATE: Initial Release]
 */
function statement(invoice, plays) {
	let totalAmount = 0;
	let volumeCredits = 0;
	let result = `청구 내역(고객명: ${invoice.customer})\n`;
	const format = new Intl.NumberFormat("en-US",
						{ style:"currency", currency: "USD"
							, minimumFractionDigits: 2}).format;
	
	for (let perf of invoice.performances) {
		const play = plays[perf.playID];
		let thisAmount = 0;
		
		switch (play.type) {
			case "tragedy": // 비극
				thisAmount = 40000;
				if (perf.audience > 30) {
					thisAmount += 1000 * (perf.audience - 30);
				}
				break;
			case "comedy": // 희극
				thisAmount = 30000;
				if (perf.audience > 20) {
					thisAmount += 10000 + 500 * (perf.audience - 20);
				}
				thisAmount += 300 * perf.audience;
				break;
			default:
				throw new Error(`알 수 없는 장르: ${play.type}`);
		}
		
		// 포인트 적립
		volumeCredits += Math.max(perf.audience - 30, 0);
		// 희극 관객 5명마다 추가 포인트 제공
		if ("comedy" === play.type) volumeCredits += Math.floor(perf.audience / 5);
		
		// 청구 내역 출력
		result += `${play.name}: ${format(thisAmount/100)} (${perf.audience}석)\n`;
		totalAmount += thisAmount;
	}
	
	result += `총액: ${format(totalAmount/100)}\n`;
	result += `적립 포인트: ${volumeCredits}점\n`;
	
	return result;
}

module.exports = statement;