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

  @Test
  public void ItStartsASocket() throws Exception {
    Mocket mocket = new Mocket();
    Server server = new Server(mocket);

    server.run();

    assertTrue(mocket.Started);
  }

  @Test
  public void ItGet404() throws Exception {
    Mocket mocket = new Mocket();
    Server server = new Server(mocket);
    server.run();
    String response = server.processRequest("/foobar");
    assertEquals("HTTP/1.1 404 Not Found", response);
  }

}
