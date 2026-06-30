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
 * Environment	: .NET Framework x.x
 * File			: statament.cs
 * Notes		:
 * History		: [NO][Programmer][Description]
 *				: [20260630114300][pluto#plutozone.com][CREATE: Initial Release]
 */
using System.Globalization;
using System.Text.Json;
using System.IO;


// 프로젝트 폴더
string projectPath = Directory.GetParent(AppContext.BaseDirectory)!.Parent!.Parent!.FullName;

// JSON 파일 경로
string playsPath = Path.Combine(projectPath, "../plays.json");
string invoicesPath = Path.Combine(projectPath, "../invoices.json");

// JSON 읽기
string playsJson = File.ReadAllText(playsPath);
string invoicesJson = File.ReadAllText(invoicesPath);

// JSON 파싱
var plays = JsonSerializer.Deserialize<Dictionary<string, Dictionary<string, string>>>(playsJson);
var invoices = JsonSerializer.Deserialize<List<Dictionary<string, object>>>(invoicesJson);

Console.WriteLine(Statement(invoices[0], plays));

string Statement(
	Dictionary<string, object> invoice,
	Dictionary<string, Dictionary<string, string>> plays)
{
	int totalAmount = 0;
	int volumeCredits = 0;

	string customer = invoice["customer"].ToString();

	string result = $"청구 내역 (고객명: {customer})\n";

	JsonElement performances =
		(JsonElement)invoice["performances"];

	foreach (JsonElement perf in performances.EnumerateArray())
	{
		string playID = perf.GetProperty("playID").GetString();
		int audience = perf.GetProperty("audience").GetInt32();

		var play = plays[playID];

		int thisAmount = 0;

		switch (play["type"])
		{
			case "tragedy":
				thisAmount = 40000;

				if (audience > 30)
					thisAmount += 1000 * (audience - 30);

				break;

			case "comedy":
				thisAmount = 30000;

				if (audience > 20)
				{
					thisAmount += 10000;
					thisAmount += 500 * (audience - 20);
				}

				thisAmount += 300 * audience;
				break;

			default:
				throw new Exception($"알 수 없는 장르 : {play["type"]}");
		}

		// 포인트 계산
		volumeCredits += Math.Max(audience - 30, 0);

		if (play["type"] == "comedy")
			volumeCredits += audience / 5;

		// 출력
		result += $"{play["name"]}: {thisAmount / 100.0:C2} ({audience}석)\n";

		totalAmount += thisAmount;
	}

	result += $"총액: {totalAmount / 100.0:C2}\n";
	result += $"적립 포인트: {volumeCredits}점\n";

	return result;
}