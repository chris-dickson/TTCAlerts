package GmailPull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MessageArchiver {
	public static void archive(ArrayList<GmailMessage> messages, String absolutePath) {
		// If file exists, delete it
		File archiveFile = new File(absolutePath);
		if (archiveFile.exists()) {
			archiveFile.delete();
		}

		FileOutputStream fos;
		try {
			fos = new FileOutputStream(absolutePath);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(messages);
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<GmailMessage> restore(String absolutePath) {
		
		FileInputStream fis; 
		try {
			fis = new FileInputStream(absolutePath);
			ObjectInputStream iis = new ObjectInputStream(fis);
			
			@SuppressWarnings("unchecked")
			ArrayList<GmailMessage> readob = (ArrayList<GmailMessage>)iis.readObject();
			
			fis.close();
			return readob;
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return null;
	}
	
	
	
	

	

}
