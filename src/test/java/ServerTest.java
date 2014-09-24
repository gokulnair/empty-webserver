import com.fire.InternetSocket;
import com.fire.Server;
import com.fire.Socket;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ServerTest {
  private Mocket mocket = new Mocket();
  private Server server = new Server(mocket);

  @Test
  public void ItStartsASocket() throws Exception {
    server.run();
    assertTrue(mocket.Started);
  }

  @Test
  public void ItGet404() throws Exception {
    server.run();
    String response = server.processRequest("/foobar");
    assertEquals("HTTP/1.1 404 Not Found", response);
  }

  @Test
  public void ItGet200() throws Exception {
    server.run();
    String response = server.processRequest("GET / HTTP/1.1");
    assertEquals("HTTP/1.1 200 OK", response);
  }
}
