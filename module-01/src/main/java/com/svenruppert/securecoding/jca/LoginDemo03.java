package com.svenruppert.securecoding.jca;

public class LoginDemo03 {

	public static class Client {

		public void connectToServer() {

		}

		public void setUsername(String username) {
			
		}

		public void setPassword() {
			
		}
	}

	public static class Server {

		public void checkUsernamePassword() {

		}

		public boolean isLoginSuccess() {
			return true;
		}
	}

	public static void main(String[] args) {
		// register Process
		Client client = new Client();
		Server server = new Server();
		// Login Process
		// client verbindet sich mit dem Server
		client.connectToServer();
		// server fordert auf: Username / Passwort
		// client vom User: Username Passwort Eingabe
		client.setUsername("admin");
		client.setPassword();
		// client sendet an den Server
		// Server verifiziert Username/Passwort
		server.checkUsernamePassword();
		// Server sendet die Antwort OK/Nicht OK
		server.isLoginSuccess();
		// Wenn OK: Login Process

	}

}
