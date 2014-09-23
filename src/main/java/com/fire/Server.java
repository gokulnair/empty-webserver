package com.fire;

public class Server {

  private Socket socket;

  public Server(Socket socket) {
      this.socket = socket;
  }

  public static void main(String[] args) throws Exception
  {
      WebserviceHandler handler = new WebserviceHandler();
      Server server = new Server(new InternetSocket(handler, 5000));
      server.run();
  }

  public void run() throws Exception {
    socket.start();
    String input = socket.readSocketData();
    //process inout and build return
    socket.writeSocketData(input);
  }

  public String processRequest(String input) {
    return new String("404");
  }
}
