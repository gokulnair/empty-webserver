package com.fire;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;

public class InternetSocket implements Socket {
  private SocketHandler handler;
  private java.net.Socket socket;
  public int portNumber;
  private PrintWriter out = null;
  private ServerSocket serverSocket = null;

  public InternetSocket(SocketHandler handler, int portNumber) {
    this.handler = handler;
    this.portNumber = portNumber;
  }

  @Override
  public void start() {
    try {
      serverSocket = new ServerSocket(portNumber);
      socket = serverSocket.accept();
    } catch (Exception e) {
      if (serverSocket != null) {
        try {
          serverSocket.close();
        } catch (IOException e1) {
          e1.printStackTrace();
        }
      }
    }
  }

  public String readSocketData() {
    try {
      out = new PrintWriter(socket.getOutputStream());
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      String input = in.readLine();
      return input;

    }
    catch (Exception e)
    {
      if (serverSocket != null)
        try {
          serverSocket.close();
        } catch (IOException e1) {
          e1.printStackTrace();
        }
    }

    return new String();
  }

  public void writeSocketData(String input) {
    try {
      if(input.equals("Test")) {
        out.println(input);
      } else if(input.equals("GET / HTTP/1.1")) {
        out.write("HTTP/1.1 200 OK\r\n");
      } else {
        out.write("HTTP/1.1 404 Not Found\r\n");
      }

      out.flush();
    }
    catch (Exception e)
    {
      if (serverSocket != null)
        try {
          serverSocket.close();
        } catch (IOException e1) {
          e1.printStackTrace();
        }
    }
  }
}
