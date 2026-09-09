# com.plutozone.knowledge.language.React


## Install Node 등
```cmd
C:\> node -v
C:\> npm -v
REM npm install -g create-react-app
REM cd %WORKSPACE%
REM create-react-app -h
REM create-react-app my-react
C:\> cd %WORKSPACE%
REM Project Naming Rule(Not allowed '-')
C:\> npm init react-app my-react
C:\> ren my-react myReact
C:\> cd myReact
REM npm run start
C:\> npm start
```


## React is
```text
      State 변경
          ↓
  Component가 다시 render
          ↓
    새로운 JSX 생성
          ↓
React가 이전 결과와 비교
          ↓
  필요한 DOM만 업데이트
          ↓
   필요한 Effect 실행
```
### Component
- UI를 독립적인 단위(=컴포넌트)로 분리
- 함수형 또는 클래스형 컴포넌트
- 컴포넌트의 합성(Composition)

### Declarative(선언적) UI
- DOM을 직접 조작하기보다 "현재 상태라면 UI가 어떻게 보여야 하는가"를 선언

### Render
- props나 state가 변경되면 React가 컴포넌트를 다시 실행하고 UI를 업데이트

### One-way data flow
- 부모에서 자식 방향으로 데이터를 전달하는 구조

### Composition vs Inheritance
- React에서는 상속보다 컴포넌트 조합을 선호


## JSX
```js
const element = <h1>Hello, {name}</h1>;
```
- JSX 문법
- {}를 이용한 JavaScript 표현식
- JSX에서 className 등 HTML과 다른 attribute
- Fragment: <>...</>
- 조건부 렌더링
	```js
	{isLoggedIn ? <Dashboard /> : <Login />}
	```
- 배열을 이용한 렌더링
- key
	```js
	{users.map(user => (
	<UserCard key={user.id} user={user} />
	))}
	```


## Props
> React 컴포넌트 간 데이터를 전달하는 기본 메커니즘
```js
function UserCard({ name, age }) {
  return <div>{name} ({age})</div>;
}

<UserCard name="John" age={30} />
```
- props란
- destructuring
- children
	```js
	<Card>
	<UserProfile />
	</Card>
	```
- props는 기본적으로 읽기 전용
- 부모에서 자식으로만 데이터 전달
- 컴포넌트 인터페이스 설계

### Context
- Props를 여러 단계로 전달(=prop drilling)해야 하는 상황을 해결
```text
App
 ↓
Layout
 ↓
Sidebar
 ↓
UserMenu
 ↓
User
```
```js
const UserContext = createContext();
```
```js
<UserContext.Provider value={user}>
	<App />
</UserContext.Provider>
```
```js
const user = useContext(UserContext);

```
## State
```js
const [count, setCount] = useState(0);
```
- state란
- useState
- state 변경은 re-render
- state를 직접 변경하면 안 되는 이유
- 이전 state를 기반으로 업데이트
```js
setCount(count + 1);
// 함수 형태로 표현 = 이전(prev=임의 함수명) 상태에 1을 더하라!!!
setCount(prev => prev + 1);
```

### State Immutability
```js
/* 잘못된 예
user.name = "John";
setUser(user);
*/
// 올바른 예
setUser({
	...user,
	name: "John"
});

```
- immutable update
- spread syntax
- 배열 업데이트
- 객체 업데이트
- nested state 업데이트


### State 끌어올리기
- 여러 컴포넌트가 같은 state를 필요로 할 때 사용하는 핵심 패턴
- Search와 List가 같은 데이터를 필요로 한다면 state를 공통 부모인 App으로 올리며 이를 Lifting State Up이라고 한다.
```text
        App
       /   \
   Search  List
```

## Event
```js
<button onClick={handleClick}>
	Click
</button>
```
- onClick
- onChange
- onSubmit
- event 객체
- 이벤트 핸들러
- 이벤트에서 state 변경
	```js
	<input
		value={name}
		onChange={e => setName(e.target.value)}
	/>
	```


## Rendering과 Re-rendering
```js
function App() {
	const [count, setCount] = useState(0);

	console.log("render");

	return (
		<button onClick={() => setCount(count + 1)}>
			{count}
		</button>
	);
}
```
- render
- re-render
- reconciliation
- React element
- Virtual DOM에 대한 기본 개념
- component function의 재실행
- state와 props 변경
- key의 역할


## Controlled / Uncontrolled Component
### Controlled
- React state가 input의 값을 관리
	```js
	const [email, setEmail] = useState("");

	<input
		value={email}
		onChange={e => setEmail(e.target.value)}
	/>
	```

### Uncontrolled
- DOM 자체가 값을 관리하고 ref 등을 통해 접근


## Hooks
- 일반적인 우선 순위
	1. useState
	2. useEffect
	3. useRef
	4. useContext
	5. useMemo
	6. useCallback
	7. Custom Hook
- Hook은 컴포넌트의 상태와 React의 생명주기/렌더링 과정에 연결되는 함수

### Custom Hook
- Custom Hook을 이용해서 상태 관리 + Effect + 재사용 가능한 로직을 분리
```js
function useUsers() {
	const [users, setUsers] = useState([]);

	// fetch logic ...

	return users;
}
```
```js
const users = useUsers();
```


## useEffect
```js
useEffect(() => {
	fetchUsers();
}, []);
```
- Effect란
- dependency array
- cleanup
- 컴포넌트 mount/update/unmount와의 관계
- Effect가 언제 실행되는가
- Effect를 불필요하게 사용하지 않는 방법
- 특히 중요한 것은 "useEffect = componentDidMount"가 아니라는 것


## Ref
```js
const inputRef = useRef(null);

<input ref={inputRef} />
```
- 주요 용도
	- DOM element 접근
	- focus
	- 이전 값 보관
	- 렌더링과 무관한 mutable value 저장
	- useState의 변경은 render를 발생시키지만 useRef의 .current 변경은 일반적으로 render를 발생시키지 않는다.


## Routing for SPA
- route
- nested route
- dynamic route
- navigation
- URL parameter
- query parameter
- protected route


## 개발 시
### 컴포넌트 설계
```text
ProductPage
```
```text
ProductPage
 ├── ProductHeader
 ├── ProductImage
 ├── ProductInfo
 ├── ProductOptions
 ├── ProductPrice
 └── AddToCartButton
```
- 컴포넌트 책임
- 재사용성
- Composition
- Container / Presentational 개념
- 컴포넌트 간 의존성 줄이기

### 상태(State) 관리
- only React
	- useState
	- useReducer
	- useContext
- 그 다음 필요에 따라
	- Redux Toolkit
	- Zustand
	- Jotai
	- MobX

### 데이터 Fetching
```js
useEffect(() => {
	fetch("/api/users")
		.then(...)
}, []);
```
- loading
- error
- retry
- caching
- refetch
- pagination
- optimistic update
- TanStack Query(React Query, 서버 상태 관리 도구)

### 성능 최적화
- React.memo
- useMemo
- useCallback
- lazy loading
- code splitting
- virtualization

### 최신 렌더링 모델
- Concurrent Rendering
- Suspense
- Transitions
- Server Components
- Server Actions
- Streaming
- hydration
- Next.js