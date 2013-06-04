package GmailPull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.AndTerm;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.FromStringTerm;
import javax.mail.search.ReceivedDateTerm;
import javax.mail.search.SearchTerm;

import com.sun.mail.imap.IMAPBodyPart;

public class GmailPull {
	private Store _imapStore;
	
	// Recursive function to build the parts of a message.   Most message parts are strings, but they can be nested messages
	private void buildParts(MimeMultipart mmpt, StringBuilder partialMsg) {
		if (mmpt != null) {
			try {
				for (int partIdx = 0; partIdx < mmpt.getCount(); partIdx++) {
					IMAPBodyPart bp = (IMAPBodyPart)(mmpt.getBodyPart(partIdx));
					Object contentObj = bp.getContent();
					if (contentObj instanceof String) {
						partialMsg.append((String)(bp.getContent()));
					} else if (contentObj instanceof MimeMultipart) {
						buildParts((MimeMultipart)contentObj, partialMsg);
					}
				}
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private String getContent(Message m) {
		String body = "";

		try {
			Object o = m.getContent();
			if (o instanceof MimeMultipart) {
				StringBuilder sb = new StringBuilder();
				MimeMultipart mmpt = (MimeMultipart)o;
				buildParts(mmpt, sb);
				body += sb.toString();
			} else if (o instanceof String) {
				body += (String)o;
			}
				
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return body;
	}
	
	private String getFrom(Message m) {
		String fromString = "";
		try {
			InternetAddress addr[] = (InternetAddress[])(m.getFrom());
			fromString += addr[0].getAddress();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fromString;
	}
	
	private String getSubject(Message m) {
		String subjectString = "";
		try {
			subjectString = m.getSubject();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		return subjectString;
	}
	
	private long getDate(Message m) {
		try {
			Date d = m.getSentDate();
			return d.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	private GmailMessage javaxMsgToMsg(Message m) throws MessagingException {
		GmailMessage gmsg = new GmailMessage();
	
		gmsg.setDateSent(getDate(m));
		gmsg.setBody(getContent(m));
		gmsg.setFromAddress(getFrom(m));
		gmsg.setSubject(getSubject(m));
				
		return gmsg;
	}
	

	public GmailPull(String login, String password) {
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");
		try {
			Session session = Session.getDefaultInstance(props, null);
			_imapStore = session.getStore("imaps");
			_imapStore.connect("imap.gmail.com", login, password);
		
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (MessagingException e) {
			e.printStackTrace();
			System.exit(2);
		}
	}
	
	public ArrayList<GmailMessage> getAllMessagesFromFolder(String folderName) { 
		ArrayList<GmailMessage> gmailMessages = new ArrayList<GmailMessage>();
		try {
			Folder inbox = _imapStore.getFolder(folderName);
			inbox.open(Folder.READ_ONLY);
			Message messages[] = inbox.getMessages();
			int msgIdx = 0;
			for(Message message:messages) {
				System.out.println("Downloading message " + msgIdx + " of " + messages.length);
				GmailMessage gmsg = javaxMsgToMsg(message);
				gmailMessages.add(gmsg);
				msgIdx++;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return gmailMessages;
	}
	
	public ArrayList<GmailMessage> getFirstNMessagesFromFolder(String folderName, int N) {
		ArrayList<GmailMessage> gmailMessages = new ArrayList<GmailMessage>();
		try {
			Folder inbox = _imapStore.getFolder(folderName);
			inbox.open(Folder.READ_ONLY);
			Message messages[] = inbox.getMessages();
			int msgIdx = 0;
			for(Message message:messages) {
				System.out.println("Downloading message " + (msgIdx+1) + " of " + N);
				GmailMessage gmsg = javaxMsgToMsg(message);
				gmailMessages.add(gmsg);
				msgIdx++;
				if (msgIdx == N) {
					break;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return gmailMessages;
	}
	
	public ArrayList<GmailMessage> getMessagesFromFolderBetween(String folderName, long startDate, long endDate) {
		ArrayList<GmailMessage> gmailMessages = new ArrayList<GmailMessage>();
		try {
			Folder inbox = _imapStore.getFolder(folderName);
			inbox.open(Folder.READ_ONLY);

			int msgIdx = 0;
			
			// Build the search terms
			SearchTerm olderThan = new ReceivedDateTerm(ComparisonTerm.LE, new Date(endDate));
			SearchTerm newerThan = new ReceivedDateTerm(ComparisonTerm.GE, new Date(startDate));
			SearchTerm andTerm = new AndTerm(olderThan, newerThan);
			Message messages[] = inbox.search(andTerm);
			
			for(Message message:messages) {
				System.out.println("Downloading message " + msgIdx + " of " + messages.length);
				GmailMessage gmsg = javaxMsgToMsg(message);
				gmailMessages.add(gmsg);
				msgIdx++;
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return gmailMessages;		
	}
	
	public ArrayList<GmailMessage> getMessagesFromFolderWhereSenderIs(String folderName, String senderAddress) {
		ArrayList<GmailMessage> gmailMessages = new ArrayList<GmailMessage>();
		try {
			Folder inbox = _imapStore.getFolder(folderName);
			inbox.open(Folder.READ_ONLY);

			int msgIdx = 0;
			
			// Build the search terms
			FromStringTerm ft = new FromStringTerm(senderAddress);
			Message messages[] = inbox.search(ft);
			
			for(Message message:messages) {
				System.out.println("Downloading message " + msgIdx + " of " + messages.length);
				GmailMessage gmsg = javaxMsgToMsg(message);
				gmailMessages.add(gmsg);
				msgIdx++;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return gmailMessages;	
	}
}
