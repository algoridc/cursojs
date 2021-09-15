/**
 * 
 */
    function login() {
		var info = {
				
			nick : $("#nick").val(),
			pass : $("#pass").val()
		};

		var data = {
			data : JSON.stringify(info),
			url : "login",
			type : "post",
			contentType : 'application/json',
			dataType : 'json',
			success : function(response) {
				
				alert(response.resp);
				
				if(response.resp=="OK"){
					sessionStorage.setItem("id", response.id);
					sessionStorage.setItem("user", response.user);
					window.location.href = "wall.html";
				}
				
			},
			error : function(response) {
				alert(response.message);
			}
		};
		$.ajax(data);
	}
    