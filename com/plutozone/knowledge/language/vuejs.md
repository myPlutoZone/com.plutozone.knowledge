# com.plutozone.knowledge.language.VueJs


## Vue.js
- Vue.js vs. React vs. AngularJS


## 개발 환경 설정


## Instance and Component
<details><summary>Instance Life Cycle</summary>
```html
<html>
<head>
	<title>Vue Instance Lifecycle</title>
</head>
<body>
	<div id="app">
		{{message}}
	</div>
	<script src="https://cdn.jsdelivr.net/npm/vue@2.5.2/dist/vue.js"></script>
	<script>
		new Vue({
			el: "#app"
			, data: {
				message: "Vue.js"
			}
			, beforeCreate: function() {
				alert("1. beforeCreate()");
				console.log("message=" + this.message + " at beforeCreate()");
			}
			, created: function() {
				alert("2. created()");
				console.log("message=" + this.message + " at created()");
			}
			, beforeMount: function() {
				alert("3. beforeMount()");
				console.log("message=" + this.message + " at beforeMount()");
			}
			, mounted: function() {
				alert("4. mounted()");
				console.log("message=" + this.message + " at mounted(1)");
				this.message = "Hello, Vue.js";
				console.log("message=" + this.message + " at mounted(2)");
			},
			beforeUpdate: function() {
				alert("5. beforeUpdate()");
				console.log("message=" + this.message + " at beforeUpdate()");
			}
			, updated: function() {
				alert("6. updated()");
				console.log("message=" + this.message + " at updated()");
			}
			, beforeDestory: function() {
				alert("7. beforeDestory()");
				console.log("message=" + this.message + " at beforeDestory()");
			}
			, Destroy: function() {
				alert("8. Destroy()");
				console.log("message=" + this.message + " at Destroy()");
			}
		});
	</script>
</body>
</html>
```
</details>
