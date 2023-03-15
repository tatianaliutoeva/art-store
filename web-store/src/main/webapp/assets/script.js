/**
 * 
 */
 function reg() {
		let login = $("#login").val();
		let pass = $("#pass").val();
		let tel = $("#tel").val();
		let name = $("#name").val();
		let lastname = $("#lastname").val();
		let str = "login=" + login + "&pass=" + pass + "&tel=" + tel + "&name=" + name + "&lastname=" + lastname;
		$.ajax({
				type: "POST",
				url: "Registration",
				data: str,
				success:function(answer){
					alert(answer);
					if (answer==="Вы успешно зарегистрированы! Войдите под своим логином и паролем!") {
						window.location.href = "Authorisation";
					}
					}
		});
	}
		
function auth(){
		let login = $("#log-auth").val();
		let password = $("#pass-auth").val();
		let str = "login=" + login + "&password=" + password;
		$.ajax( {
			type:"POST",
			data:str,
			url:"Authorisation",
			success: function(answer) {
			alert(answer);
			window.location.href = "Main";
			if (answer==="Пользователь не зарегистрирован!") {
				window.location.href = "Registration";
			} 
		}
		});
	}
	
function addToCart(id) {
			let str = "id="+id;
			$.ajax({
				  type: "POST",
				  url: "Catalog",
				  data: str,
				  success: function(answer){
				   	 alert(answer);
				  }
				});
		}	

function addOrder(user_id) {
			let str = "user_id="+user_id;
			$.ajax({
				  type: "POST",
				  url: "Cart",
				  data: str,
				  success: function(answer){
				   	 alert(answer);
				   	 location.reload();
				  }
				});
		}	