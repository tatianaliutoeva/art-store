<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<link href="<c:url value="assets/main.css" />" rel="stylesheet">
	<style>
	<c:if test="${user_id!=null}">
    	#enter{display: none;}
	</c:if>
	</style>
	<title>Зарегистрироваться</title>
	<script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
	<script type="text/javascript" src="assets/script.js"></script>
	
</head>
<body>
	<header>
            <div id="logo" class="cell">
            	<a href="Main"><img src="images/logo.png" alt=""></a>
            </div>
            <div id="phone" class="cell">
                <p>8(931)777-00-77</p>
            </div>
            <div id="enter" class="cell">
                <a href="Authorisation" class="cell">Вход / регистрация</a>
            </div>
            <c:if test="${user_id!=null}">
            <div id="user" class="cell">
                <p>Вы авторизованы!</p>
            </div>
            <div id="cart" class="cell">
                <a href="Cart" id="cart" class="cell">Корзина</a>
            </div>
            <div id="exit" class="cell">
                <a href="Exit" id="exitlk" class="cell">Выйти</a>
            </div></c:if>      
    </header>
	<div class="auth">
		<p>Регистрация</p>
		<input type="text" id="login" placeholder="логин"><br><br>
   		<input type="password" id="pass" placeholder="пароль"><br><br>
   		<input type="text" id="tel" placeholder="телефон"><br><br>
   		<input type="text" id="name" placeholder="Имя"><br><br>
   		<input type="text" id="lastname" placeholder="Фамилия"><br><br>
   		<input type="button" id="regis" value="Зарегистрироватьcя" onclick="reg()"><br><br>
   		
    </div>
</body>
<footer>
    Copyright © 2023 Все права защищены
</footer>
</html>