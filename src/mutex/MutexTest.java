package mutex;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.locks.ReentrantLock;

public class MutexTest {
	public static void main(String[] args) {

		ThreadOutput aclass = new ThreadOutput("./INPUT/MyAll.txt");

	}
}

class ThreadOutput extends Thread { // 'Thread' Class를 상속받는다 
	
	static ReentrantLock lock = new ReentrantLock();
	
    String file_name; 
    public ThreadOutput(String name) { 
        this.file_name = name; 
    } 

    public void run() { 
    	lock.lock();
    	try {
    		File file = new File(file_name);
    		final int BUFFER_SIZE = 512;
    		int readLine;

    		try {
    			InputStream inputStream = new FileInputStream(file);
    			OutputStream outputStream = new FileOutputStream("./INPUT/MyAllReplace.txt");

    			byte[] buffer = new byte[BUFFER_SIZE];

    			while ((readLine = inputStream.read(buffer)) != -1) {
    				outputStream.write(buffer, 0, readLine);
    			}

    			inputStream.close();
    			outputStream.close();
    		} catch (FileNotFoundException e) {
    			e.printStackTrace();
    		} catch (IOException ex) {
    			ex.printStackTrace();
    		}
    		
    		
    		
    		
    		
    		
    		
    		PrintStringNums(file_name);
    	}
    	finally {
    		lock.unlock();
    	}
    }
    
    static void PrintStringNums(String str)
    {
    	int i;
    	
    	System.out.println(str);
    	
    	for (i=1; i<=30; i++)
    	{
    		System.out.print(i+" ");
    	}	
    	System.out.println();
    }
}