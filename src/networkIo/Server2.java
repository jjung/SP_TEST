package networkIo;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server2 {
	public static void main(String[] args) throws IOException {

//		Scanner sc = new Scanner(System.in);
//		String data = "";

		ServerSocket listener = new ServerSocket(9091);
//		while (!"QUIT".equals(data)) {
//			data = sc.nextLine();
//			System.out.println(data);
			try {

				Socket socket = listener.accept();
				try {
					// ���� ���ſ� Ŭ���� ���� �� ����
					Receiver receiver = new Receiver(socket);
					receiver.start();
				} finally {
					socket.close();
				}
			} finally {
				listener.close();
			}
//		}
	}

}

class Receiver extends Thread {

	Socket socket;
	DataInputStream dis = null;
	FileOutputStream fos = null;
	BufferedOutputStream bos = null;

	public Receiver(Socket socket) {
		this.socket = socket;
	}

	// @Override
	public void run() {

		try {
			dis = new DataInputStream(socket.getInputStream());
			String type = dis.readUTF();

			/* type��('file'�Ǵ� 'msg')�� �������� ������ ���۵ƴ��� ���ڿ��� ���۵ƴ��� �����Ѵ�. */

			if (type.equals("file")) {
				// ���۵� ���� ����!
				String result = fileWrite(dis);
				System.out.println("result : " + result);

			} else if (type.equals("msg")) {
				// ���ŵ� �޼��� ����
				String result = getMsg(dis);
				System.out.println("result : " + result);
			}

			// Ŭ���̾�Ʈ�� ��� ���� - �����̵ȴ�.
			// DataOutputStream dos = new
			// DataOutputStream(socket.getOutputStream());
			// dos.writeUTF(result);

		} catch (IOException e) {
			System.out.println("run() Fail!");
			e.printStackTrace();
		}
	}

	private String fileWrite(DataInputStream dis) {

		String result;
		String filePath = "./";

		try {
			System.out.println("���� ���� �۾��� �����մϴ�.");

			// ���ϸ��� ���� �ް� ���ϸ� ����
			String fileNm = dis.readUTF();
			System.out.println("���ϸ� " + fileNm + "�� ���۹޾ҽ��ϴ�.");

			// ������ �����ϰ� ���Ͽ� ���� ��� ��Ʈ�� ����
			File file = new File(filePath + "/r" + fileNm);
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			System.out.println(fileNm + "������ �����Ͽ����ϴ�.");

			// ����Ʈ �����͸� ���۹����鼭 ���
			int len;
			int size = 4096;
			byte[] data = new byte[size];
			while ((len = dis.read(data)) != -1) {
				bos.write(data, 0, len);
			}

			// bos.flush();
			result = "SUCCESS";

			System.out.println("���� ���� �۾��� �Ϸ��Ͽ����ϴ�.");
			System.out.println("���� ������ ������ : " + file.length());
		} catch (IOException e) {
			e.printStackTrace();
			result = "ERROR";
		} finally {
			try {
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				dis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	private String getMsg(DataInputStream dis) {

		String result;

		try {
			System.out.println("���� ���� �۾��� �����մϴ�.");

			// ���ϸ��� ���� �ް� ���ϸ� ����
			String msg = dis.readUTF();
			System.out.println("msg : " + msg);

			result = "SUCCESS";
			System.out.println("�޼��� ���� �۾��� �Ϸ��Ͽ����ϴ�.");
		} catch (IOException e) {
			e.printStackTrace();
			result = "ERROR";
		} finally {
			try {
				dis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
