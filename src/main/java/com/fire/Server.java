package com.fire;

public class Server {

  private Socket socket;

  public Server(Socket socket) {
      this.socket = socket;
  }

  public static void main(String[] args) throws Exception {

    while (true){
      Server server = new Server(new InternetSocket(5000));
      server.run();
    }
  }

  public void run() throws Exception {
    socket.start();

    RequestHandler request = new RequestHandler(socket.readSocketData());
    ResponseHandler response = new ResponseHandler(request);

    socket.writeSocketData(response.getResponse(
      request.getMethod(),
      request.getPath(),
      ""));

  }
}
