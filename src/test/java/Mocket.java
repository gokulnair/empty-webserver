import com.fire.Socket;

public class Mocket implements Socket {
    public boolean Started;

    @Override
    public void start() {
        Started = true;
    }

  @Override
  public String readSocketData() {
    return "GET /file1 HTTP/1.1";
  }

  @Override
  public void writeSocketData(String input) {

  }
}
