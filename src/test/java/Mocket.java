import com.fire.Socket;

public class Mocket implements Socket {
    public boolean Started;

    @Override
    public void start() {
        Started = true;
    }
}
