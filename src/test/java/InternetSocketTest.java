import com.fire.*;
import com.fire.Socket;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InternetSocketTest {

  private int port = 5000;
  private InternetSocket socket;

  @Before
  public void setup() throws Exception{
    socket = new InternetSocket(port);
    new Thread() {
      public void run() {
        socket.start();
      }
    }.start();

    Thread.sleep(500);
  }

  @After
  public void tearDown() throws Exception{
    socket.close();
  }

  @Test
  public void ItShouldOpenASocket() throws Exception
  {
    InetAddress host = InetAddress.getLocalHost();
    java.net.Socket client = new java.net.Socket(host.getHostName(), port);
    assertTrue(client.isConnected());
    client.close();
  }

  @Test
  public void ItShouldCloseASocket() throws Exception
  {
    InetAddress host = InetAddress.getLocalHost();
    java.net.Socket client = new java.net.Socket(host.getHostName(), port);
    client.close();
    assertTrue(client.isClosed());
  }

  @Test
  public void SocketShouldWriteData() throws Exception
  {
    InetAddress host = InetAddress.getLocalHost();
    java.net.Socket client = new java.net.Socket(host.getHostName(), 5000);

    socket.writeSocketData("HTTP/1.1 200 OK");
    BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

    assertEquals("HTTP/1.1 200 OK", in.readLine());

    client.close();
  }

  @Test
  public void SocketShouldReadData() throws Exception
  {
    InetAddress host = InetAddress.getLocalHost();
    java.net.Socket client = new java.net.Socket(host.getHostName(), 5000);

    PrintWriter out = new PrintWriter(client.getOutputStream());
    out.println("GET /foobar");
    out.flush();

    assertEquals("GET /foobar", socket.readSocketData());

    client.close();
  }
}
