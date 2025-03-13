package com.svenruppert.securecoding.jca;

public class LoginDemo05 {

  public static class Client{
    public void connectToServer(Connection con){}
    public void inputLoginData() {}
  }
  public static class Server{
	  public Login requestLogin() {return new Login();}
  }
  public static class Connection{}

  public static class Login{
	  char[] u; char[] p;
  }

  public static void main(String[] args) {

    //register Process

    Client client = new Client();
    Server server = new Server();

    //Login Process
    //client verbindet sich mit dem Server
    Connection connection = new Connection();
    client.connectToServer(connection);
    //server fordert auf: Username / Passwort
    Login login = server.requestLogin();
    //client vom User: Username Passwort Eingabe
    //login.setUser
    //login.setPasswort

    //client sendet an den Server
    // Server verifiziert Username/Passwort
    // Server sendet die Antwort OK/Nicht OK
    // Wenn OK: Login Process

  }


}
