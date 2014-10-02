package com.fire;

public class Server {

  private Socket socket;

  public Server(Socket socket) {
      this.socket = socket;
  }

  public static void main(String[] args) throws Exception {

    Server server = new Server(new InternetSocket(5000));
    server.start();

    while (true) {
      server.run();
    }
  }

  public void start() {
    socket.start();
  }

  public void run() throws Exception {
    RequestHandler request = new RequestHandler(socket.readSocketData());
    ResponseHandler response = new ResponseHandler();

    socket.writeSocketData(response.getResponse(
      request.getMethod(),
      request.getPath(),
      ""));
  }
}
