package com.fire;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args)
    {
      try {
        ServerSocket sSocket = new ServerSocket(9898);
        while (true) {
          Socket socket = sSocket.accept();
          try {
            System.out.print(socket.getInputStream());
          } catch (Exception e) {

          }
        }
      } catch (IOException e) {
          e.printStackTrace();
      }
    }
}
