package com.example.cursojs.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.example.cursojs.agents.SQLAgent;
import com.example.cursojs.exceptions.ExistingUserException;
import com.example.cursojs.model.User;

public class UserDAO {

	private static SQLAgent agent;

	public UserDAO() {
		agent = SQLAgent.get();
	}

	public List<User> selectAll() throws SQLException {
		return find("SELECT * FROM user ");
	}
	
	public User selectByNick(String nick) throws SQLException {
		List<User> ls = find("SELECT * FROM user  WHERE nick=\""+nick+"\" ");
		return(ls.isEmpty())?null:ls.get(0);
	}
	public User select(User user) throws SQLException {
		List<User> ls = find("SELECT * FROM user WHERE _id=\""+user.getId()+"\" ");
		return(ls.isEmpty())?null:ls.get(0);
	}

	public boolean insert(User user, int idCompany) throws SQLException, ExistingUserException {
		if (selectByNick(user.getNick())!=null)
			throw new ExistingUserException("User exists");
		
		boolean flag = agent.insert("INSERT INTO user (nick,pass) VALUES (\"" + user.getNick()
				+ "\", \"" + user.getPass()  + "\")") > 0;
		if(flag) {
			user = selectByNick(user.getNick());
		}
		return flag;
	}

	public boolean updatePassword(User user) throws SQLException {
		boolean flag = agent.update("UPDATE user SET pass=\"" +user.getPass()+ "\" WHERE _id=\"" + user.getId() + "\"") > 0;

		return flag;
	}

	public boolean delete(User user) throws SQLException {
		boolean flag = agent.update("DELETE FROM user WHERE _id=\"" + user.getId() + "\"") > 0;
		return flag;
	}

	
	private List<User> find(String sql) throws SQLException {
		List<User> result = new LinkedList<>();
		User aux;
		ResultSet rs = agent.select(sql);

		while (rs.next()) {
			aux = new User(rs.getInt("_id"));
			aux.setNick(rs.getString("nick"));
			aux.setPass(rs.getString("pass"));
			
			result.add(aux);
		}
		return result;
	}

}
