package com.example.cursojs.agents;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SQLAgent {
	private static SQLAgent instancia;
	private Connection connection ;
	private Statement stmt;

	private SQLAgent() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");  
			this.connection=DriverManager.getConnection("jdbc:mysql://192.168.1.100:3306/cursojs","cursojs","cursojs");
			this.stmt = connection.createStatement();
			
		}catch(Exception e){ System.out.println("Fallo al crear la conexion a la db");System.out.println(e);}  
	}
	public static SQLAgent get(){
		if (instancia==null){
			instancia = new SQLAgent();
		}
		return instancia;
	}
	public void close() throws SQLException {
		stmt.close();
		connection.close();
		instancia = null;
		
	}
	
	public int insert(String query) throws SQLException {
		return stmt.executeUpdate(query);
		
	}
	public ResultSet select(String query) throws SQLException {
		return stmt.executeQuery(query);
		
	}
	public int update(String query) throws SQLException {
		return insert(query);
		
	}
	public int delete(String query) throws SQLException {
		return insert(query);
	}
}