package networkIo;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server1 {
	public static void main(String[] args) throws IOException {
		ServerSocket listener = new ServerSocket(9090);
		
		try {
			Socket socket = listener.accept();
			try{
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				Date today = new Date();
			    SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
				out.println(date.format(today));
			} finally {
				socket.close();
			}
		} finally {
			listener.close();
		}
	}
	
}
