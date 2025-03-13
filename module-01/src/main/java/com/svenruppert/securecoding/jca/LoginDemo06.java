package com.svenruppert.securecoding.jca;

import java.security.Key;
import java.security.PublicKey;

public class LoginDemo06 {

  public static class Client{
    public void connectToServer(Server server){}
    private boolean validateKey(PublicKey key){return false;}
    public Key generateRandomSymmetricKey(PublicKey key) {return null;}
  }
  public static class Server{
    public PublicKey getPublicKey() {return null;}
    public void setSymmetricKey(Key key) {}
  }

  public static void main(String[] args) {

    //register Process

    Client client = new Client();
    Server server = new Server();
    //Login Process
    //client verbindet sich mit dem Server
    client.connectToServer(server);
    //server fordert auf: Username / Passwort
    //client vom User: Username Passwort Eingabe
    //client sendet an den Server
    // Server verifiziert Username/Passwort
    // Server sendet die Antwort OK/Nicht OK
    // Wenn OK: Login Process

  }


}
