package com.example.cursojs.model;


import org.json.JSONException;
import org.json.JSONObject;


public class User {
	
	//declaración de variables
	private int id;
	private String nick;
	private String pass;

	//constructores
	public User() {
	}
	public User(int id) {
		this.setId(id);
	}
	public User(String nick, String pass) {
		this.setNick(nick);
		this.setPass(pass);
	}
	
	//metodos de acceso a las variables
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	//metodo para convertir el objeto en JSON
	public JSONObject toJSON() throws JSONException{
		JSONObject jso = new JSONObject();
		jso.put("id", this.id);
		jso.put("nick", this.nick);
		
		return jso;

	}

	

}
