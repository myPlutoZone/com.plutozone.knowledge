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
 * File			: statament.c
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20260630114300][pluto#plutozone.com][CREATE: Initial Release]
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "cJSON.h"	// Download from https://github.com/DaveGamble/cJSON


char* readFile(const char* filename)
{
	FILE* fp = fopen(filename, "rb");
	if (!fp)
		return NULL;

	fseek(fp, 0, SEEK_END);
	long size = ftell(fp);
	rewind(fp);

	char* buffer = (char*)malloc(size + 1);
	fread(buffer, 1, size, fp);
	buffer[size] = '\0';

	fclose(fp);

	return buffer;
}

void statement(cJSON* invoice, cJSON* plays)
{
	int totalAmount = 0;
	int volumeCredits = 0;

	cJSON* customer = cJSON_GetObjectItem(invoice, "customer");

	printf("청구 내역 (고객명: %s)\n", customer->valuestring);

	cJSON* performances = cJSON_GetObjectItem(invoice, "performances");

	int count = cJSON_GetArraySize(performances);

	for (int i = 0; i < count; i++)
	{
		cJSON* perf = cJSON_GetArrayItem(performances, i);

		char* playID =
			cJSON_GetObjectItem(perf, "playID")->valuestring;

		int audience =
			cJSON_GetObjectItem(perf, "audience")->valueint;

		cJSON* play = cJSON_GetObjectItem(plays, playID);

		char* type =
			cJSON_GetObjectItem(play, "type")->valuestring;

		char* name =
			cJSON_GetObjectItem(play, "name")->valuestring;

		int thisAmount = 0;

		if (strcmp(type, "tragedy") == 0)
		{
			thisAmount = 40000;

			if (audience > 30)
				thisAmount += 1000 * (audience - 30);
		}
		else if (strcmp(type, "comedy") == 0)
		{
			thisAmount = 30000;

			if (audience > 20)
			{
				thisAmount += 10000;
				thisAmount += 500 * (audience - 20);
			}

			thisAmount += 300 * audience;
		}
		else
		{
			printf("알 수 없는 장르 : %s\n", type);
			exit(1);
		}

		if (audience > 30)
			volumeCredits += audience - 30;

		if (strcmp(type, "comedy") == 0)
			volumeCredits += audience / 5;

		printf("%s: $%.2f (%d석)\n",
			name,
			thisAmount / 100.0,
			audience);

		totalAmount += thisAmount;
	}

	printf("총액: $%.2f\n", totalAmount / 100.0);
	printf("적립 포인트: %d점\n", volumeCredits);
}
	
int main(void)
{
	char* playsJson = readFile("plays.json");
	char* invoicesJson = readFile("invoices.json");

	if (playsJson == NULL || invoicesJson == NULL)
	{
		printf("JSON 파일을 읽을 수 없습니다.\n");
		return 1;
	}

	cJSON* plays = cJSON_Parse(playsJson);
	cJSON* invoices = cJSON_Parse(invoicesJson);

	if (plays == NULL || invoices == NULL)
	{
		printf("JSON 파싱 실패\n");
		return 1;
	}

	cJSON* firstInvoice = cJSON_GetArrayItem(invoices, 0);

	statement(firstInvoice, plays);

	cJSON_Delete(plays);
	cJSON_Delete(invoices);

	free(playsJson);
	free(invoicesJson);

	return 0;
}