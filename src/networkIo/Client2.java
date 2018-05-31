package networkIo;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client2 {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("127.0.0.1", 9091);
		
        String filePath = "./";
        String fileNm = "test.exe";
        FileSender fs = new FileSender(socket, filePath, fileNm);
        fs.start();

	}
}

//���� ���ۿ� Ŭ���� 
class FileSender extends Thread {

  String filePath;
  String fileNm;
  Socket socket;
  DataOutputStream dos;
  FileInputStream fis;
  BufferedInputStream bis;

  public FileSender(Socket socket, String filePath, String fileNm) {
   
      this.socket = socket;
      this.fileNm = fileNm;
      this.filePath = filePath;
      
      try {
          // ������ ���ۿ� ��Ʈ�� ����
          dos = new DataOutputStream(socket.getOutputStream());
      } catch (IOException e) {
          e.printStackTrace();
      }
  }
  
  // @Override
  public void run() {
   
       try {
            //���������� ������ �˸���.('file' ������ ����)
           dos.writeUTF("file");
           dos.flush();
 
           //������ ������ �о Socket Server�� ����
           String result = fileRead(dos);
           /*test*/
           System.out.println("result : " + result);
    
       }catch (IOException e){
           e.printStackTrace();
       }finally{ //���ҽ� �ʱ�ȭ
           try { dos.close(); } catch (IOException e) { e.printStackTrace(); }
           try { bis.close(); } catch (IOException e) { e.printStackTrace(); }
       }

  }
  
  private String fileRead(DataOutputStream dos){
   
      String result;
   
      try {
          dos.writeUTF(fileNm);
          /*test*/
          System.out.println("���� �̸�(" + fileNm + ")�� �����Ͽ����ϴ�.");

          // ������ �о ������ ����

          File file = new File(filePath + "/" + fileNm);
          fis = new FileInputStream(file);
          bis = new BufferedInputStream(fis);
          
          int len;
          int size = 4096;
          byte[] data = new byte[size];
          while ((len = bis.read(data)) != -1) {
              dos.write(data, 0, len);
          }
          
          //������ ����

          dos.flush();
          
          result = "SUCCESS";
      } catch (IOException e) {
          e.printStackTrace();
          result = "ERROR";
      }finally{
          try { fis.close(); } catch (IOException e) { e.printStackTrace(); }
      }
      
      return result;
  }
}