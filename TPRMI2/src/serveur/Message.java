package serveur;

import java.io.Serializable;

public class Message implements Serializable{
	private static final long serialVersionUID = 1L;
	
	String user;
	String msgStr;
	
	public Message(String user, String msgStr) {
		this.user = user;
		this.msgStr = msgStr;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getMsgStr() {
		return msgStr;
	}

	public void setMsgStr(String msgStr) {
		this.msgStr = msgStr;
	}

}
