package com.fire;

public class Server {

    private Socket socket;

    public Server(Socket socket) {
        this.socket = socket;
    }

    public static void main(String[] args)
    {
        WebserviceHandler handler = new WebserviceHandler();
        Server server = new Server(new InternetSocket(handler));
        server.run();
    }

    public void run() {
        socket.start();
    }
}
