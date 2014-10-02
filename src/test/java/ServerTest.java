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
    server.start();
    assertTrue(mocket.Started);
  }
}
