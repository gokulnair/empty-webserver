package com.fire;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;

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

  }

  public int processRequest(String s) {
    return 404;
  }
}
