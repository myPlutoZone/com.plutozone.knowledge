# Introduction to Android



## Contents


## 안드로이드란?
### 안드로이드의 이해
### 안드로이드 생태계
- 구글
- 단말 제조사와 이동 통신사
- 개발자


## 개발 도구
### 안드로이드 스튜디오 설치
- 메모리 설정


## "Hello, Android" 앱 만들기
### 첫 프로젝트
- Project Name, Package Name 등
- MainActivity.java + main_activity.xml

### AVD로 "Hello, Android" 실행
- Virtual Device and Run

### "Hello, Android" 변경
- Modify or Delete TextView

### 여러 개의 버튼 추가
- Insert Buttons


## 실제 단말 연결
### PC에 Driver 설치
### 단말의 개발자 모드 설정
### PC와 단말 연결


## 안드로이드 스튜디오
### 안드로이드 스튜디오의 이해
### 뷰와 뷰의 속성
### 레이아웃 기초
- Guidelines


## 레이아웃
### 대표적인 레이아웃
- 제약 레이아웃(기본 레이아웃으로 연결선을 제약 조건으로 사용)
- 뷰 영역(콘텐츠+패딩+테두리+마진) vs. CSS 박스 모델

### 리니어 레이아웃
- 박스 모델(수직 또는 수평 또는 중첩)

### 상대 레이아웃
- 규칙 모델(부모와의 상대적인 위치, 제약 레이아웃 이후에 미권장)

### 테이블 레이아웃
- 격자 모델(HTML 테이블과 유사하지만 통상적으로 미사용)

### 프레임 레이아웃과 뷰의 전환
- 싱글 모델(여러 개의 뷰 또는 뷰 그룹을 중첩하고 최상위만 표시)

### 스크롤 뷰
- 하나의 뷰나 뷰 그룹을 넣어 스크롤 표시


## 기본 위젯과 드로어블
### 기본 위젯
### 드로어블 만들기
### 이벤트 처리의 이해
### 토스트, 스낵바 그리고 대화상자
### 프로그래스바


## 화면 전환
### 레이아웃 인플레이션
### 여러 화면 생성 후 화면 전환
### 인턴트
### 플래그와 부가 데이터 사용
### 태스트 관리
### 액티비티 생명주기와 SharedPreference


## 프래그먼트(Fragment)
### 프래그먼트란?
- 액티비티 위에서 동작하는 부분 화면(XML + Java)
- [프로젝트] Fragment로 제작된 메인과 메뉴의 화면 전환

### 프래그먼트 화면 만들기
- [프로젝트] Fragment로 제작된 상단에서 버튼들 클릭 시 하단의 이미지 변경

### 액션바 사용하기(ActionBar를 사용하는 테마로 변경할 것)
- 컨텍스트 메뉴 vs. 옵션 메뉴
- [프로젝트] 옵션 메뉴 설정([버그] switch를 if로 변경)
- [프로젝트] 액션바의 타이틀을 아이콘으로 변경
- [프로젝트] 액션바에서 검색어를 입력

### 상단 탭과 하단 탭 만들기
- 최근에는 하단 탭 사용이 대다수이며 상단 탭의 경우는 액션바에 탭 기능을 제공하고 하단 탭은 별도의 위젯으로 제공
- [프로젝트] 상단 탭(NoActionBar 테마로 변경할 것)
- [프로젝트] 하단 탭

### 뷰페이지(ViewPager) 만들기
- 좌우 스크롤 기능

### 바로가기 메뉴 만들기


## 서비스와 수신자
- 안드로이드 앱 = 액티비티 + 서비스 + (브로드케스트) 수신자 + 내용 제공자

### 서비스
- 백그라운드 vs. 포그라운드
- [프로젝트] 서비스 추가 후 1) 액티비티에서 서비스로 부가 정보 전달 2) 서비스에서 액티비티로 부가 정보 전달하고 액티비티는 받기

### 브로드캐스트 수신자
- 브로드캐스트 메시지를 받고자 할 경우 SMS 등 수신자로 등록(수신자로 등록될 경우에도 서비스처럼 백그라운드에서 실행 가능)
- [프로젝트] 1) SMS 수신자로 등록하여 SMS 수신 2) SMS 위험 권한 추가 3) 가상 SMS 발송 4) LogCat이 아닌 액티비티가 수신

### 위험 권한 부여

### 리소스와 메니패스트
- 안드로이드 앱 = 자바 코드 + 리소스(레이아웃 + 이미지 등)

### 그래들(Gradle)


## 선택 위젯 만들기
- 선택 위젯(예: 리싸이클뷰)들은 어댑터가 데이터와 뷰를 관리한다.

### 나인 패치 이미지
- [프로젝트] 일반 이미지 vs. 나인 패치 이미지 for 버튼

### 새로운 뷰
- [프로젝트] 버튼을 상속하여 사용자 정의 버튼

### 레이아웃 정의하고 카드뷰 넣기
- [프로젝트] 레이아웃을 상속하여 새로운 레이아웃을 정의하고 카드뷰(CardView, 프로필과 같은 간단한 정보를 각 영역별로 저장하는 뷰)를 배치

### 리싸이클뷰
- 리스트와 유사
- [프로젝트] 1열 리스트
- [프로젝트] 2열 리스트

### 스피너
- 콤보박스와 유사


## 애니메이션과 다양한 위젯 
### 애니메이션
### 페이지 슬라이딩 
### 앱 화면에 웹 브라우저 넣기(WebView)
- [프로젝트] 웹사이트 접속 vs. 모바일for 팀프로젝트

### 시크바(SeekBar)

### 키패드 제어
- Input Type 속성


## 스레드와 핸들러
- 스레드는 하나의 프로세스에서 구동되는 작업들
- 멀티 스레드에서 발생할 수 있는 데드락
- 안드로이드 앱은 하나의 프로세스만 허용하므로 서비스 또는 스레드를 이용하여 다중 처리 가능 vs. 단, UI 처리는 핸들러를 통해서만 가능

### 핸들러
- [프로젝트] 1) Log는 가능 2) 스레드는 UI 처리 불가이므로 핸들러로 변경
- [프로젝트] Thread + Handler
- [프로젝트] Runnable + Handler

### 일정 시간 이후 실행하기
### 스레드로 애니메이션 만들기


## 서버에 데이터 요청하고 응답 받기
###  네트워킹이란?
- 2 Tier 연결 방식: 클라이언트 vs. 서버
- 3 Tiier 연결 방식: 클라이언트 vs. 응용 서버 vs. 데이터 서버
- P2P 연결 방식

### 소켓 사용하기
- IP
- TCP vs. UDP
- HTTP(비연결형) vs. Socket(연결형 + Thread) 프로그래밍
- [프로젝트] 소켓 통신

### 웹(HTTP)으로 요청하기
- GET vs. POST & Content-type(application/x-www-form-urlencoded vs. multipart/form-data vs. application/json)
- [프로젝트] HTTP 요청에 따른 코드 및 화면 표시(resCode 처리 추가 필요)  String vs. JSON

### Volley 사용하기
- volley 요청과 응답의 스레드 기능 제공
- [프로젝트] String or JSON

### JSON 데이터 다루기
- GSON
- [프로젝트] JSON

### 영화 정보 가져와 보여주기
- [프로젝트] 영화 정보 vs. 판매 상품 목록 및 상세


## 데이터베이스와 내용 제공자
### 모바일 데이터베이스
### 데이터베이스와 테이블 만들기
### 헬퍼 클래스로 업그레이드 지원하기
### 데이터 조회
### 내용 제공자(Content Provider)
### 앨범과 연락처 조회


## 뷰에 그래픽 그리기
### 뷰에 그래픽 그리기
### 드로어블 객체로 만들어 그리기
### 비트맵 이미지 사용하기
### 페인트 보드 만들기
### 멀티터치 이미지 뷰어 만들기
### 머티리얼 디자인


## 멀티미디어(권한 및 API 버전 + API KEY)
### 카메라로 사진 찍어 저장
### 화면에 카메라 미리보기
### 음악 파일 재생
### 동영상 재생
### 오디오 녹음하여 저장
### 동영상 녹화하여 저장
### 유튜브 영상 재생


## 위치기반 서비스와 앱 위젯(권한 및 API 버전 + API KEY)
### GPS로 나의 위치 확인
### 현재 위치의 지도 보여주기
### 지도에 아이콘 추가
### 앱 위젯 만들기


## 푸시 서비스와 센서 및 단말 기능(권한 및 API 버전 + API KEY)
- 진동 VS. 소리 VS. 상단 알림

### 진동과 소리
- [프로젝트] 버튼(진동, 기본 음원, 사용자 음원) 클릭 시

### 상단 알림
-  [프로젝트] 1) 버튼 클릭 시 단순 메시지 2) 버튼 클릭 시 상세(일단 Main으로) 링크

### 푸시
- SMS 전송 vs. 서버에서 전송 vs. Push 전송
- [프로젝트] 푸시 발송을 위한 서비스 프로세스 및 개발

### 센서
- 가속 vs. 자이로스코프 등

### 시스템 서비스 활용
- Activity/Package/Alarm Manager

### 네트워크 기능 활용
- Data vs. WiFi

### 다중 창 지원
- 여러 화면(Activity)