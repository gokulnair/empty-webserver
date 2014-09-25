package com.fire;

public class Server {

  private Socket socket;

  public Server(Socket socket) {
      this.socket = socket;
  }

  public static void main(String[] args) throws Exception
  {
      Server server = new Server(new InternetSocket(5000));
      server.run();
  }

  public void run() throws Exception {
    socket.start();
    String input = socket.readSocketData();

    String res = processRequest(input);


    socket.writeSocketData(res);
  }

  public String processRequest(String input) {
    if(input.equals("Test")) {
      return new String(input);
    } else if(input.equals("GET / HTTP/1.1")) {
      return new String("HTTP/1.1 200 OK");
    } else {
      return new String("HTTP/1.1 404 Not Found");
    }
  }
}
