# com.plutozone.knowledge.language.CSS


## Contents
1. 개요와 특징
2. 구조
3. 선택자(Selector)
4. 속성(Attribute)과 Value(값)
5. 레이아웃(Layout) 설계 및 구현


## 1. 개요와 특징
- ...


## 2. 구조
- ...


## 3. 선택자(Selector)
### 3-1. 직접
- Universal(*)
- Tag(<?>)
- Class(.)
- ID(#)

### 3-2. CSS 기반(http://flukeout.github.io/)
| 선택자  | 기능 |
| :--- | :--- |
| ul `*` {...} | ul `내부의 모든` 하위 요소 |
| ul `li` {...} | ul `내부의 모든 li` 요소 |
| ul `li:first-child` {...} | ul `내부의 첫번째 자식인 li` 요소 |
| ul `li:only-child` {...} | ul `내부의 유일한 자식인 li` 요소 |
| ul `.CLASS` {...} | ul `내부의 CLASS 클래스를 가진 모든` 요소 |
| ul `.CLASS *` {...} | ul `내부의 CLASS 클래스를 가진 요소의 모든 하위` 요소 |
| ul`,` ol {...} | `모든 ul` 요소와 `모든 ol` 요소 |
| ul`, .CLASS` {...} | `모든 ul` 요소와 `CLASS 클래스를 가진 모든` 요소 |
| div ul {...} | div `내부의 모든 하위 ul` 요소 |
| div `>` ul {...} | div의 `직계 첫째 자식 ul` 요소 |
| div `~` ul {...} | div `뒤에 나오는 모든 형제 ul` 요소 |
| div `+` ul {...} | div `바로 다음에 나오는 첫째 형제 ul` 요소 |
| #ID li {...} | ID `내부의 모든 하위 li` 요소 |
| #ID `>` li {...} | ID의 `직계 첫째 자식 li` 요소 |
| #ID `~` li {...} | ID `뒤에 나오는 모든 형제 li` 요소 |
| #ID `+` li {...} | ID `바로 다음에 나오는 첫째 형제 li` 요소 |
- `내부`는 직계 자식뿐 아니라 모든 하위 단계의 요소를 포함
- `>`는 `직계 자식`만 선택
- `+`는 바로 다음 `형제` 요소 하나만 선택
- `~`는 뒤에 나오는 `형제` 요소 중 조건에 맞는 모든 요소를 선택
- `:first-child`와 `:only-child`는 `ul` 내부에서의 순서가 아니라 해당 요소의 부모를 기준으로 판단


## 4. 속성(Attribute)과 Value(값)
- font-family
- font-size
- color 등
- ...


## 5. 레이아웃(Layout) 설계 및 구현
### 5-1. 레이아웃(Layout)의 역사
- table
- position
- float
- flex

### 5-2. Layout
- Holy Grail
	- [Flex](/WebContent/knowledge/language/css/layout/holyGrailByFlex.html)
	- [Float](/WebContent/knowledge/language/css/layout/holyGrailByFlex.html)
	- [Position](/WebContent/knowledge/language/css/layout/holyGrailByFlex.html)
- [Float](/WebContent/knowledge/language/css/layout/float.html)
- [Masonry](/WebContent/knowledge/language/css/layout/masonry.html)
- [Sticky](/WebContent/knowledge/language/css/layout/sticky.html)

### 5-3. Example
- [Button](/WebContent/knowledge/language/css/button.html)
- [Random Background](/WebContent/knowledge/language/css/randomBackground.html)
- [Tree](/WebContent/knowledge/language/css/tree.html)
- [Thumbnail](/WebContent/knowledge/language/css/thumbnail/thumbnail.html)