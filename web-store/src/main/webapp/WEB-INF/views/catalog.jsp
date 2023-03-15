<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Каталог</title>
    <script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
    <script type="text/javascript" src="assets/script.js"></script>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="<c:url value="assets/main.css" />" rel="stylesheet">
    <style>
    <c:if test="${user_id!=null}">
    	#enter{display: none;}
    	.add-to-cart {display: block;}
 		.actions a {width: 50%;}
	</c:if>
	<c:if test="${user_id==null}">
    	#enter{display: block;}
    	.add-to-cart {display: none;}
 		.actions a {width: 100%;}
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
    <div id="menu_content">
        <div id="menu"><p style="font-size: 20px; font-family: 'Noto Sans', sans-serif; color: #303030;margin:22px;font-weight:600;">Каталог:</p>
            <div class="menu-item">
                <a href="Catalog">Все</a>
            </div>
            <div class="menu-item">
                <a href="Catalog?genre=Природа">Природа</a>
            </div>
            <div class="menu-item">
                <a href="Catalog?genre=Абстракция">Абстракция</a>
            </div>
            <div class="menu-item">
                <a href="Catalog?genre=Портрет">Портрет</a>
            </div>
            <div class="menu-item">
                <a href="Catalog?genre=Города и страны">Города и страны</a>
            </div>
            <div class="menu-item">
                <a href="Catalog?genre=Цветы">Цветы</a>
            </div>
            <div class="menu-item">
                <a href="Catalog?genre=Анимализм">Анимализм</a>
            </div>
            <div class="menu-item">
                <a href="Catalog?genre=Натюрморт">Натюрморт</a>
            </div>
        </div>
        <div id="content">
            <c:forEach var="good" items="${goods}" varStatus="num">
                <div class="product-inner">
                    <div class="product-wrap">
                        <a href="#"><img src="images/${good.photo}" alt=""></a>
                        <div class="actions">
                            <a href="#" onclick="addToCart(${good.id})" class="add-to-cart"></a>
                            <a href="?good_id=${good.id}" class="quickview"></a>
                        </div>
                    </div>
                    <div class="product-info">
                        <h3 class="product-title">${good.title}</h3>
                        <span class="price">&#x20bd ${good.price}</span>
                    </div>
                </div>
            </c:forEach>            
        </div>
    </div>
</body>
<footer>
    Copyright © 2023 Все права защищены
</footer>
</html>