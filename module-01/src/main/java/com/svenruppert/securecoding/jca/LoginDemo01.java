package com.svenruppert.securecoding.jca;

public class LoginDemo01 {

  public static class Connection {
    public void toServer(String value) {}
    public void receiveFromServer(String value) {}



    public void receiveFromClient(String value) {}
    public void toClient(String value) {}
  }

  public static class Client{
    public Connection connectToServer(String request){
      return new Connection();
    }

    public void collectCredentials() {

    }

    public static void main(String[] args) {
      Client client = new Client();
      Connection connection = client.connectToServer("helo");

      connection.toServer("plz send current news feed");
      connection.receiveFromClient("needs authentication (user/password)");

      client.collectCredentials();
      connection.toServer("credentials");


    }
  }
  public static class Server{
    public Connection createClientConnection(String request){
      return new Connection();
    }

    public boolean validateCredentials(String credentials) {
      return true;
    }

    public static void main(String[] args) {
        Server server = new Server();
        Connection connection = server.createClientConnection("helo");

        connection.receiveFromClient("plz send current news feed");
        connection.toClient("needs authentication (user/password)");

        connection.receiveFromServer("credentials");
        if (server.validateCredentials("plz send current news feed")) {
          connection.toClient("feed");
        } else {
          connection.toClient("authentication error");
        }


    }
  }

  public static void main(String[] args) {

    //register Process

    Client client = new Client();
    Server server = new Server();
    //Login Process
    //client verbindet sich mit dem Server
    //server fordert auf: Username / Passwort
    //client vom User: Username Passwort Eingabe
    //client sendet an den Server
    // Server verifiziert Username/Passwort
    // Server sendet die Antwort OK/Nicht OK
    // Wenn OK: Login Process

  }


}
