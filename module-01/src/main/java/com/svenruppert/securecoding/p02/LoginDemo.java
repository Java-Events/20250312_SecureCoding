package com.svenruppert.securecoding.p02;

public class LoginDemo {

    public static class Client {

        private Server server;

        public void connect(Server server) {
            this.server = server;
            server.connect(this);
        }

        private void authorize(String username, char[] password) {
            server.authorize(username, password);
        }

        public void pleaseAuthorize() {
            //authorize("username", new char[]{'a', 'b', 'c'});
            authorize("denied", new char[]{'a', 'b', 'c'});
        }

        public void ok() {
            System.out.println("OK");
        }

        public void unauthorized() {
            System.out.println("Unauthorized");
        }
    }

    public static class Server {

        private Client client;

        public void connect(Client client) {
            this.client = client;
            pleaseAuthorize();
        }

        private void pleaseAuthorize() {
            client.pleaseAuthorize();
        }

        private void authorize(String username, char[] password) {
            if (isValid(username, password)) {
                client.ok();
            } else {
                client.unauthorized();
            }
        }

        private boolean isValid(String username, char[] password) {
            // TODO create real verification
            if ("denied".equals(username)) {
                return false;
            }
            return true;
        }
    }

    public static void main(String[] args) {

        // login
        Client client = new Client();
        Server server = new Server();

        // client connects to server
        client.connect(server);

        // server fordert user password an
        //server.pleaseAuthorize();

        // client vom user: username / passwort
        //client.authorize("username", new char[]{});

        // client sendet an den server

        // server verifiziert

        // server sendet ok / nicht ok

        // wenn ok => login

    }

}
