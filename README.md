# HttpBbs
Node.js로 데이터 가져오기(1)

### Node.Js 기초 문법
##### 변수 
```Node.Js
1. 변수는 var로 설정한다.
var name;
var number;
  
2. 문자열을 입력할 때는 '' 나 ""를 써야한다.
name = "김영호"
id = 'kwave'
  
3.  변수 선언과 동시에 값을 입력할 수 있다.
var num = 5;
  
4. 두 개의 변수를 더해서 다른 변수에 입력할 수 있다.
  var sum = num1 + num2;
  
5. 문자열 + 숫자는 문자열이 된다.
var sum = name + num1;
var tag = kwave + 5;
```

##### 함수
```Node.Js
1. 함수는 
    function 함수이름(파라미터){
        실행코드
    }
    
2. 세개의 파라미터를 더한 후 결과값을 리턴하는 함수를 선언
function sum(param1, param2, param3){
    return param1+param2+param3;
}
        
3. 함수 실행 후 결과값을 result에 대입
var result = sum(1,2,3);
  
4. result에 담긴 결과값을 출력
console.log('result='result);
  
5. 결과값이 없는 함수 선언
function print(param1){
    console.log('param1='param1);
}
  
6. 함수호출 : return이 없는 함수는 로직을 자체적으로 처리하기 때문에 결과값 대입 불필요
print('출력내용');
```  
##### 조건문
``` Node.Js
1. if와 switch를 사용

var a = 10;
if(a>10){
    console.log('a가 10보다 크다');
}
else if(a=10){
    console.log('a가 10이다');
}
else {
     console.log('a가 10보다 작다');
}
```

##### 반복문
```Node.Js
1. 반복문은 while, for, do~while이 있음
for(var i=0; i<10;i++){
    var result = i;
}
```
##### Class

<p>javascript는 프로토 타입 기반의 함수형 언어이기 때문에 특별하게 객체지향을 위한 class는 없다.  </p>
but 함수형 언어들은 함수자체를 하나의 객체로 취급하기 때문에 단일 함수 또는 파일 자체를 하나의 class처럼 사용할 수 있다.

``` Node.Js  
//class의 선언 - 낙타표기법으로 첫번째 글자를 대문자로 함수를 하나 선언한다.
function Clazz(msg){
    this.name = 'I am Class';
    this.message = msg;

    message2 = "find me!";
}
//Clazz 객체의 prototype 을 가져와서 message값을 리턴하는 함수를 하나 추가한다.
Clazz.prototype.getMessage = function(){
    return this.message;
}

Clazz.prototype.getMessage2 = function(){
    return this.message2;
}

// 객체를 생성
var myClazz = new Clazz('good to see u!');
console.log(myClazz.getMessage());
// 내부에 선언된 함수와는 다르게 prototype으로 선언한 함수는 값을 사용할 수 없다.
console.log(myClazz.getMessage2());
```