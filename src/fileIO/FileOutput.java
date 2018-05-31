package fileIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileOutput {
	public static void main(String[] args) {

		File directory = new File("./INPUT");
		
		File[] fList = directory.listFiles();
		
		boolean isExist = false;
		for(File file : fList){
			if(file.isDirectory() && file.getName().equals("OUTPUT")){
				isExist = true;
			}
		}
		
		if(!isExist){
			File theDir = new File("./INPUT/OUTPUT");
			theDir.mkdir();
		}
		
		for (File file : fList){
			if(file.isDirectory()){
				System.out.println("D : " + file.getName());
			}else{	
				final int BUFFER_SIZE = 512;
				int readLine;
				
				if(file.length() >= 2000){
					try{
						
						InputStream inputStream = new FileInputStream(file);
						OutputStream outputStream = new FileOutputStream("./INPUT/OUTPUT/" + file.getName());
						
						byte[] buffer = new byte[BUFFER_SIZE];
						
						while((readLine = inputStream.read(buffer)) != -1){
							outputStream.write(buffer, 0, readLine);
						}
						
						inputStream.close();
						outputStream.close();
					} catch (FileNotFoundException e){
						e.printStackTrace();
					} catch (IOException ex){
						ex.printStackTrace();				
					}
				}
				System.out.println("file: " + file.getName() + ", size: " + file.length() + "byte");
			}
		}
	}
	
}
