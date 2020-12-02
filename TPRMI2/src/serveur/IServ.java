package serveur;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IServ extends Remote{
	//Test
	public String welcomeMsg(String string) throws RemoteException;
	
	//Chat
	public void send(String user, String msgStr) throws RemoteException;
	public ArrayList<Message> getMsgMemory() throws RemoteException;
	public Message getMsgMemory(int i) throws RemoteException, IndexOutOfBoundsException;
	
}
