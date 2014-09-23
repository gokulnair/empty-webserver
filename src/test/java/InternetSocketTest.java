import com.fire.*;
import com.fire.Socket;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

import static org.junit.Assert.assertEquals;

public class InternetSocketTest {

  private java.net.Socket createSocket(int portNumber) throws Exception {
    InetAddress host = InetAddress.getLocalHost();
    EchoHandler handler = new EchoHandler();
    final Socket socket = new InternetSocket(handler, portNumber);

    new Thread() {
      public void run() {
        socket.start();
      }
    }.start();

    return new java.net.Socket(host.getHostName(), portNumber);
  }

  @Test
  public void StartsTheSocket() throws Exception {
    java.net.Socket client = createSocket(5000);

    try {
      PrintWriter out = new PrintWriter(client.getOutputStream());
      BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

      out.println("Test\n");
      out.flush();

      assertEquals("Test", in.readLine());
    }
    finally {
      client.close();
    }
  }

  @Test
  public void ServerShouldReturn404Error() throws Exception {
    java.net.Socket client = createSocket(5001);

    try {
      PrintWriter out = new PrintWriter(client.getOutputStream());
      BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

      out.println("GET FOOBAR\n");
      out.flush();

      assertEquals("HTTP/1.1 404 Not Found", in.readLine());
    }
    finally {
      client.close();
    }
  }
  
  @Test
  public void ServerShouldReturn200OK() throws Exception
  {
    java.net.Socket client = createSocket(5002);

    try {
      PrintWriter out = new PrintWriter(client.getOutputStream());
      BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

      out.println("GET / HTTP/1.1");
      out.flush();

      assertEquals("HTTP/1.1 200 OK", in.readLine());
    }
    finally {
      client.close();
    }
  }
  
}
