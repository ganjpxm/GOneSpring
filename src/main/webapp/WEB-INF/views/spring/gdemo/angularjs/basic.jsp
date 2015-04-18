<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
  <title>GDemo Angular JS</title>
  <%@ include file="/WEB-INF/views/spring/gdemo/common/head.jsp" %>
  <script src="<c:url value='/resources/angularJS/angular-1.3.15.js'/>"></script>
  
  <style>
	table, th , td  {
	  border: 1px solid grey;
	  border-collapse: collapse;
	  padding: 5px;
	}
	table tr:nth-child(odd)	{
	  background-color: #f1f1f1;
	}
	table tr:nth-child(even) {
	  background-color: #ffffff;
	}
  </style>
</head>
<body>
<%@ include file="/WEB-INF/views/spring/gdemo/common/menu.jsp" %>
<div id="content" ng-app="jpApp">
  <div class="container">
    <div style="margin:10px 0px;">
      <b>AngularJS extends HTML </b> with ng-directives. <br/>
      The <b>ng-app</b> directive defines an AngularJS application. eg : ng-app=""<br/>
  	  The <b>ng-model</b> directive binds the value of HTML controls (input, select, textarea) to application data. eg : ng-model="name"<br/>
	  The <b>ng-init</b> directive defines initial values for an AngularJS application. eg : ng-init="firstName='Jianping';lastName='Gan'"<br/>
	  The <b>ng-bind</b> directive binds application data to the HTML view. eg : ng-bind="name" <br/>
	  The <b>ng-repeat</b> directive clones HTML elements once for each item in a collection (in an array).<br/>
	  The <b>ng-controller</b> directive defines the application controller.
	<div>
	
	<div style="margin:20px 0px;">
		<div>
		 1. <b>AngularJS Expressions and Directives</b> : <br/>
		 (1) AngularJS Expressions are written inside double braces: <b>{ { expression } }</b> <br/>
		 (2) AngularJS Expressions bind data to HTML the same way as the <b>ng-bind</b> directive. <br/>
		 (3) Data Binding : {{ firstName }} is synchronized with ng-model="firstName".
		</div>
		<div>
		  <label>Name : </label>
		  <input type="text" ng-model="yourName" placeholder="Enter a name here"/>
		  <span style="font-size:18px;">Hello {{yourName}}!</span>, <span ng-bind="yourName"></span>
		</div>
		<div ng-init="firstName='Jianping';lastName='Gan'"> <!-- data-ng-init, ng-init -->
		  String The name is {{ firstName + " " + lastName }}, <span ng-bind="firstName"></span>
		</div>
		<div ng-init="quantity=1;cost=5">
		  Number : Total in dollar = {{ quantity * cost }}, <span ng-bind="quantity * cost"></span>
		</div>
		<div ng-init="points=[1,15,19,2,40]">
		  <p>Arrays : The third result is {{ points[2] }}</p>
		</div>
		<div ng-app="" ng-init="names=[{name:'Jani',country:'Norway'},{name:'Kai',country:'Denmark'}]">
		<ul>
  		  <li ng-repeat="x in names">
    		{{ x.name + ', ' + x.country }}
  		  </li>
        </ul>
	<div>
	
	<div style="margin:20px 0px;">
		<p>2. <b>AngularJS Controllers</b><br/>
		  (1) AngularJS <b>modules</b> define AngularJS applications.<br/>
		  (2) AngularJS <b>controllers</b> control AngularJS applications.<br/>
		</p>
		<div ng-controller="personCtrl">
		  First Name: <input type="text" ng-model="firstName" /><br>
		  Last Name: <input type="text" ng-model="lastName" /><br>
		  Full Name: {{firstName + " " + lastName}}
		</div>
		<div ng-controller="citysCtrl">
		  <ul>
	  		<li ng-repeat="city in citys">
	    		{{ city.name + ', ' + city.country }}
	  		</li>
		  </ul>
		</div>
	</div>
	
	<div style="margin:20px 0px;">
		<div>3. <b>AngularJS Filters</b><br/>
		  currency	Format a number to a currency format.<br/>
		  filter	Select a subset of items from an array.<br/>
		  lowercase	Format a string to lower case.<br/>
		  orderBy	Orders an array by an expression.<br/>
		  uppercase	Format a string to upper case.<br/>
		</div>
		<div ng-controller="personCtrl">
		  The last name uppercase is {{ lastName | uppercase }}
		</div>
		<div ng-controller="costCtrl">
		  Total currency = {{ (quantity * price) | currency }}</p>
		</div>
		<div ng-controller="citysCtrl">
		  <input type="text" ng-model="test"><br/>
		  <ul>
		    <li ng-repeat="x in citys | filter:test | orderBy:'country'">
		      {{ x.name + ', ' + x.country }}
		    </li>
		  </ul>
	    </div>
	</div>   
	
	<div style="margin:20px 0px;">
	  <div>
	    4. <b>AngularJS XMLHttpRequest</b><br/>
	    <b>$scope</b> is the application object (the owner of application variables and functions).<br/>
		<b>$http</b> is an XMLHttpRequest object for requesting external data.<br/>
	  </div>
	  <div ng-controller="customersCtrl"> 
		<ul>
		  <li ng-repeat="x in customers">
		    {{ x.Name + ', ' + x.Country }}
		  </li>
		</ul>
		<table>
		  <tr ng-repeat="x in customers | orderBy : 'Country'">
		    <td>{{ x.Name }}</td>
		    <td>{{ x.Country | uppercase}}</td>
		  </tr>
		</table>
	  </div> 
	</div>
	
	<div style="margin:20px 0px;">
	  <div>
	    5. <b>AngularJS HTML DOM</b><br/>
	    <b>ng-disabled</b> directive binds the application data mySwitch to the HTML button's disabled attribute.<br/>
	    <b>ng-show</b> directive shows or hides an HTML element.<br/>
		<b>ng-hide</b> directive hides or shows an HTML element.<br/>
	  </div>
	  <div style="margin-top:10px;"> 
		<div>
		  <button ng-disabled="mySwitch">Click Me!</button> <br/>
		  <input type="checkbox" ng-model="mySwitch">Button
		</div>
		<div ng-show="hour > 12">I am visible.</div>
		<div ng-hide="false">I am visible.</div>
	  </div> 
	</div>
	
	<div style="margin:20px 0px;">
	  <div>
	    6. <b>AngularJS Events</b><br/>
	    <b>ng-click</b> directive defines an AngularJS click event.<br/>
	    <b>ng-hide</b> directive can be used to set the visibility of a part of an application.<br/>
		<b>ng-show</b> directive can also be used to set the visibility of a part of an application.<br/>
	  </div>
	  <div style="margin-top:10px;"> 
		<div ng-app="" ng-controller="myCtrl">
		  <button ng-click="count = count + 1">Click me!</button>
		  <p>{{ count }}</p>
		</div>
		
		<div ng-controller="personCtrl">
		  <button ng-click="toggle()">Toggle</button>
		  <p ng-hide="myVar">
		    First Name : <input type="text" ng-model="firstName"><br>
		    Last Name : <input type="text" ng-model="lastName"><br>
		    <br/>
		    Full Name: {{firstName + " " + lastName}}
		  </p>
		</div>
	  </div> 
	</div>
	
	<div style="margin:20px 0px;" ng-controller="formCtrl">
	  <div>
	    7. <b>AngularJS Events</b><br/>
	  </div>
	  <form novalidate>
	    First Name : <br/>
	    <input type="text" ng-model="user.firstName"><br>
	    Last Name : <br/>
	    <input type="text" ng-model="user.lastName">
	    <br><br>
	    <button ng-click="reset()">RESET</button>
	  </form>
	  <p>form = {{user}}</p>
	  <p>master = {{master}}</p>
	</div>
	
	<div style="margin:20px 0px;">
	  <div>
	    8. <b>AngularJS Input Validation</b><br/>
	    ng-show, the spans with color:red are displayed only when user or email is <b>$dirty</b> and <b>$invalid</b>.<br/>
	  </div>
	  <form name="myForm" ng-controller="validateCtrl" novalidate>
		<p>Username:<br>
		<input type="text" name="user" ng-model="user" required>
		<span style="color:red" ng-show="myForm.user.$dirty && myForm.user.$invalid">
		  <span ng-show="myForm.user.$error.required">Username is required.</span>
		</span>
		</p>
		
		<p>Email:<br>
		  <input type="email" name="email" ng-model="email" required>
		  <span style="color:red" ng-show="myForm.email.$dirty && myForm.email.$invalid">
		    <span ng-show="myForm.email.$error.required">Email is required.</span>
		    <span ng-show="myForm.email.$error.email">Invalid email address.</span>
		  </span>
		</p>
		
		<p>
		  <input type="submit" ng-disabled="myForm.user.$dirty && myForm.user.$invalid || myForm.email.$dirty && myForm.email.$invalid">
		</p>
	  </form>
	</div>

  </div>
</div>
<script>
var app = angular.module('jpApp', []);
app.controller('personCtrl', function($scope) {
  $scope.firstName= "Jianping";
  $scope.lastName= "Gan";
  $scope.myVar = false;
  $scope.toggle = function() {
      $scope.myVar = !$scope.myVar;
  };
});
app.controller('citysCtrl', function($scope) {
  $scope.citys = [
     {name:'New York', country:'USA'},
     {name:'Beijing', country:'China'}
  ];
});
app.controller('costCtrl', function($scope) {
  $scope.quantity = 1;
  $scope.price = 9.99;
});
app.controller('customersCtrl', function($scope, $http) {
  $http.get("http://www.w3schools.com/angular/customers.php")
  .success(function(response) {
    $scope.customers = response.records;
  });
});

app.controller('myCtrl', function($scope) {
  $scope.count = 0;
});

app.controller('formCtrl', function($scope) {
  $scope.master = {firstName: "Jianping", lastName: "Gan"};
  $scope.reset = function() {
    $scope.user = angular.copy($scope.master);
  };
  $scope.reset();
});

app.controller('validateCtrl', function($scope) {
  $scope.user = 'Gan Jianping';
  $scope.email = 'ganjp@gmail.com';
});

$(document).ready(function() {
  
});
</script>
</body>
</html>