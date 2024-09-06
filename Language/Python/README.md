- pip
```
# pip list						# 설치된 목록 보기
# pip install LIBRARY			# 라이브러리 설치
# pip install beautifulsoup4
# pip install selenium			# Firefox headless와 geckodriver가 자동 설치됨(사전에 하위 버전의 FireFox 설치 권장)
# pip install openpyxl
# pip install jupyter			# C:\jupyter notebook(웹 브라우저에서 실행되는 대화형 파이썬 환경)
```

- os(운영체제에서 제공되는 여러 기능을 다룰 수 있는 파이썬 모듈) 모듈의 listdir(현재 경로의 파일 또는 폴더의 리스트를 반환하는 함수) 함수를 사용 방법들
	- Case 1. import os
		- 현재 python 파일에서 listdir 함수를 사용 하려면 os.listdir( )이라고 입력
	- Case 2. from os import *
		- 현재 python 파일에서 listdir 함수를 사용하려면 listdir( )만 입력
		- 이때 주의할 점은 from으로 불러온 모듈에 같은 이름의 함수가 있으면 문제가 발생
		- 참고로 import *를 와일드 임포트(wild import)
	- Case 3. from os import listdir
		- 하나의 함수만 가져오는 것도 가능(함수 사용법은 Case 2.와 같음)
		- 와일드 임포트는 뜻하지 않게 기존의 변수나 함수를 덮어 쓸 때가 있을 수 있으므로 해당 방법이 바람직

- Python Library
	- NumPy(넘파이, Numerical Python)
		- C 언어로 구현된 숫자, 배열 등 수학 계산에 유용
		- NumPy 배열의 차원(Dimension)
			- Scalar(스칼라)는 0차원 배열
			- Vector(벡터)는 1차원 배열
			- Matrix(행렬)는 2차원 배열
			- Tensor(텐서)는 3차원 이상의 배열
		- 배열의 랭크(Rank)는 차원(Dimension)의 크기이고 모양(Shape)는 크기를 의미(예: 3행 4열의 2차원 배열은 Rank[2]이고 Shape[3,4])
	- Pandas(판다스)
		- Data Frame(데이터 프레임) 및 Series(시리즈) 데이터(대용량 테이블 형태) 분석에 특화

- 인공지능
	- 일반인공지능 or 강인공지능=The Terminator
	- 약인공지능=Char GPT

- 런닝머신
	- 런닝머신은 수 많은 데이터를 학습 시켜서 패턴을 찾아 내고 패턴을 기반으로 데이터를 분류하고 예측하는 것(예: 크기, 색깔 등에 따른 독버섯 구분)
	- SVM(Support Vector Machine)과 Random Forest 알고리즘 at 사이킷 런(=구글에서 개발한 머신러닝 프레임워크)

- 딥러닝
	- 머신러닝의 일종으로 스스로 학습(인공 신경망)하여 특징을 인식(특히 이미지 관련)
	- TensorFlow는 구글이 오픈 소스로 공개한 딥러닝 알고리즘
	- 텍스트
		- 베이지안 필터를 통한 텍스트 분류(예: 스팸 메일, 카테고리 분류 등)
		- 마르코프 체인은 확률 기반, LSTM은 런닝머신 기반으로 예측하여 문자를 생성
	- 이미지
		- 유사 이미지 검출, 이미지 분류, 얼굴 인식(OpenCV) 등

- 코랩(https://colab.research.google.com, 구글이 대화식 개발(파이션 등) 환경인 Jupyter(https://jupyter.org)를 커스터마이징하여 온라인으로 제공)
	- 노트북(Notebook)은 Jupyter 프로젝트의 대표적인 기능(제품)이며 코랩 노트북은 구글 클라우드의 가상 서버를 사용
	- 노트북(Notebook) = 텍스트 셀(Text Cell) + 코드 셀(Code Cell)