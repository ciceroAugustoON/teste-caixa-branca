package caixaBranca;

import caixaBranca.login.User;

public class App {

	public static void main(String[] args) {
		User user = new User();
		user.conectarBD();
	}

}
