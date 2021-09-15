package com.example.cursojs.controller;



import java.util.Map;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cursojs.dao.UserDAO;
import com.example.cursojs.model.User;


@RestController
//@RequestMapping(path="/csrApp") // This means URL's start with /demo (after Application path)
public class MainController {


	@PostMapping("/login")
	public String login(HttpSession session, @RequestBody Map<String, Object> credentials) {
		
		//declaro un JSON para la respuesta
		JSONObject result = new JSONObject();

		try {
			//declaro un JSON, jso para los datos recibidos
			JSONObject jso = new JSONObject(credentials);
			
			// obtengo las variables del JSON
			String nick = jso.getString("nick");
			String pass = jso.getString("pass");
			
			//busco el nick en la base de datos
			User user = new UserDAO().selectByNick(nick);
			
			//comparo la contraseña del user de la base de datos con la del formulario
			if(user !=null && user.getPass().equals(pass)){
				
				//si las contraseñas coinciden, devuelvo resp=OK y el user
				result.put("resp", "OK");
				result.put("user", user.toJSON());
			}
			else {
				//si las contraseñas no son correctas devuelvo resp=ERROR
				result.put("resp", "USUARIO NO EXISTE");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		//devuelvo el resultado
		return result.toString();
	}
	
}
