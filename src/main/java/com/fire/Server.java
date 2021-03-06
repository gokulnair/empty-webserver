package com.fire;

public class Server {

  private Socket socket;

  public Server(Socket socket) {
    this.socket = socket;
  }

  public static void main(String[] args) throws Exception {

    Server server = new Server(new InternetSocket(5000));

    while (true) {
      server.run();
    }
  }

  public void run() throws Exception {
    socket.start();
    RequestHandler request = new RequestHandler(socket.readSocketData());
    ResponseHandler response = new ResponseHandler();

    response.getResponse(
      request.getMethod(),
      request.getPath(),
      "");

    socket.writeSocketData(response.buildResponse());

  }
}
