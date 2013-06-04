package GmailPull;

import java.io.File;
import java.util.ArrayList;

public class GmailPullTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String accountName = "username@gmail.com";
		String password = "password";
		ArrayList<GmailMessage> gmailMessages;
		
		// If the file doesn't exist, generate it
		String TTCArchiveFilePath = "output\\TTCData.gma";
		File TTCArchiveFile = new File(TTCArchiveFilePath);
		if (!TTCArchiveFile.exists()) {
			GmailPull gp = new GmailPull(accountName, password);
			gmailMessages = gp.getMessagesFromFolderWhereSenderIs("Inbox", "TTC@myttce-alerts.com");
			MessageArchiver.archive(gmailMessages, TTCArchiveFilePath);
		} else {
			gmailMessages = MessageArchiver.restore(TTCArchiveFilePath);			
		}
	}

}
