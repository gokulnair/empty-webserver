package com.fire;

import java.util.ArrayList;

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

    String data;
    ArrayList<String> methods = new ArrayList<String>();
    methods.add("POST");
    methods.add("PUT");
    methods.add("OPTION");

    System.out.println(request.getMethod());
    if(methods.contains(request.getMethod())) {
      data = new String(response.getResponse(
        request.getMethod(),
        request.getPath(),
        ""));
      socket.writeSocketData(data);
    } else {
      socket.writeSocketData(response.getResponse(
        request.getMethod(),
        request.getPath(),
        ""));
    }
  }
}
