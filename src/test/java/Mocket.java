import com.fire.Socket;

public class Mocket implements Socket {
    public boolean Started;

    @Override
    public void start() {
        Started = true;
    }

  @Override
  public String readSocketData() {
    return new String("404");
  }

  @Override
  public void writeSocketData(String input) {

  }
}
