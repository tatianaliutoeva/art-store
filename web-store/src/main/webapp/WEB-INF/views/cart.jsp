<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Корзина</title>
	<script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
	<script type="text/javascript" src="assets/script.js"></script>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<link href="<c:url value="assets/main.css" />" rel="stylesheet">
	<style>
    <c:if test="${user_id!=null}">
    	#enter{display: none;}
	</c:if>
    <c:if test="${isOrderAdded == true}">
		.toOrder{display: none;}
	</c:if>
    </style>
    
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
	
		<div style="padding:20px;"><a href="Catalog" style="margin-left:20 px;color:black;">В Каталог</a></p><br>
		<h3>Товары в корзине:</h3>
	<table style="border:1px solid;padding:0;margin:5px;width:70%;">
		<tr>
			<th>Номер п/п</th>
			<th>Название</th>
			<th>Стоимость (руб.)</th>
			<th>Количество</th>
			<th>Итого (руб.)</th>
		</tr>
		<c:set var="total" value="${0}"/>
		<c:forEach var="good" items="${goods}" varStatus="num"> 
		    <tr>
			  <td>${num.count}</td>
			  <td>${good.title}</td>
			  <td>${good.price}</td>
			  <td>${good.count}</td>
			  <td>${good.price*good.count}</td>
			  
			</tr>
			<c:set var="total" value="${total + good.price*good.count}" />
		</c:forEach>
	</table>
	
	<h3>Итого:<c:out value="${total}" /></h3>
	</div>
	<c:if test="${total != 0 }">
	<button class ="toOrder" onclick=addOrder(${user_id})>Оформить заказ</button><br>
	</c:if>
    
</body>
<footer>
    Copyright © 2023 Все права защищены
</footer>
</html>