- pip
```
# pip list					# 설치된 목록 보기
# pip install LIBRARY		# 라이브러리 설치
```

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
	- 런닝머신은 수 많은 데이터를 학습 시켜서 패턴을 찾아 내고 패턴을 기반으로 데이터를 분류하고 예측하는 것(예: 문자 인식)
	- TensorFlow는 구글이 오픈 소스로 공개한 머신러닝 알고리즘으로 대규모 숫자 계산과 다차원 행렬 계산을 지원
	- SVM(Support Vector Machine)과 Random Forest 알고리즘 at 사이킷 런(=구글에서 개발한 머신러닝 프레임워크)

- 딥러닝
	- 머신러닝의 일종으로 스스로 학습(인공 신경망)하여 특징을 인식한다. 특히 이미지 관련

- 코랩(https://colab.research.google.com, 구글이 대화식 개발(파이션 등) 환경인 Jupyter(https://jupyter.org)를 커스터마이징하여 온라인으로 제공)
	- 노트북(Notebook)은 Jupyter 프로젝트의 대표적인 기능(제품)이며 코랩 노트북은 구글 클라우드의 가상 서버를 사용
	- 노트북(Notebook) = 텍스트 셀(Text Cell) + 코드 셀(Code Cell)