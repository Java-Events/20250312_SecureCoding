package com.svenruppert.securecoding.jca;

public class LoginDemo04 {

  public static class Client{
    public void connectToServer(){}
    public char[] getUsername() { return new char[]{}; }
    public char[] getPassword() { return new char[]{}; }
    public void sendAuthenticaten(char[] username, char[] password) {};
  }
  public static class Server{
    public boolean confirmConnection() {return true;}
    public boolean authenticate(char[] username, char[] password) {return false;}
    public void doLogin(char[] username, char[] password) {}

  }

  public static void main(String[] args) {

    //register Process

    Client client = new Client();
    Server server = new Server();
    //Login Process
    //client verbindet sich mit dem Server
    client.connectToServer();
    //server fordert auf: Username / Passwort
    //client vom User: Username Passwort Eingabe
    //client sendet an den Server
    // Server verifiziert Username/Passwort
    // Server sendet die Antwort OK/Nicht OK
    // Wenn OK: Login Process

  }


}
