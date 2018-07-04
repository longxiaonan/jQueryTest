import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class fsfs {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("127.0.0.1", 8702);
		try {
			Thread.sleep(500000000000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
