import com.fire.Socket;

/**
 * Created by jonathan.alviar on 9/22/14.
 */
class MockSeverSocket implements Socket {
  public boolean Started;

  @Override
  public void start() {
    Started = true;
  }
}

