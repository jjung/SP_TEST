package networkIo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client1 {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket client = new Socket("127.0.0.1", 9090);
		
		BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
		String printStr = input.readLine();
		System.out.println(printStr);
	}
}
