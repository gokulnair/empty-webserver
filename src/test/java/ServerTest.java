import com.fire.Server;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ServerTest {

    @Test
    public void ItStartsASocket() {
        Mocket mocket = new Mocket();
        Server server = new Server(mocket);

        server.run();

        assertTrue(mocket.Started);
    }
  }
