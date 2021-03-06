package com.fire;
import java.io.*;
import java.net.ServerSocket;

public class InternetSocket implements Socket {
  private java.net.Socket socket;
  public int portNumber;
  private ServerSocket serverSocket = null;

  public InternetSocket(int portNumber) {
    this.portNumber = portNumber;
    try {
      serverSocket = new ServerSocket(portNumber);
    } catch (IOException e) {
      close();
    }
  }

  @Override
  public void start() {
    try {
      socket = serverSocket.accept();
    } catch (Exception e) {
      close();
    }
  }

  public void close() {
    if (serverSocket != null)
      try {
        socket.close();
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
      out.close();
    }
    catch (Exception e)
    {
      close();
    }
  }

  public void writeSocketData(byte[] byteArray) {
    try {
      OutputStream out = socket.getOutputStream();
      out.write(byteArray);
      out.write("\n".getBytes());
      out.flush();
      out.close();
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
