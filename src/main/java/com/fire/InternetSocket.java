package com.fire;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;

public class InternetSocket implements Socket {
  private java.net.Socket socket;
  public int portNumber;
  private ServerSocket serverSocket = null;

  public InternetSocket(int portNumber) {
    this.portNumber = portNumber;
  }

  @Override
  public void start() {
    try {
      serverSocket = new ServerSocket(portNumber);
      socket = serverSocket.accept();
    } catch (Exception e) {
      close();
    }
  }

  public void close() {
    if (serverSocket != null)
      try {
        serverSocket.close();
      } catch (IOException e1) {
        e1.printStackTrace();
      }
  }

  public void writeSocketData(String input) {
    try {
      PrintWriter out = new PrintWriter(socket.getOutputStream());
      out.println(input);

      out.flush();
    }
    catch (Exception e)
    {
      close();
    }
  }

  public String readSocketData() {
    try {
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      String input = in.readLine();
      return input;
    }
    catch (Exception e)
    {
      close();
    }
    return new String();
  }
}
