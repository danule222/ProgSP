package main;

import java.sql.SQLException;

import controllers.Login;
import models.Empleado;

public class Main {

	public static void main(String[] args) {
		
		try {
			Empleado e = Login.login("Login;1234567890");
			System.out.println(e.getApellidos());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
