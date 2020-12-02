package client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICBClient extends Remote {
	public void notifyMe(String user, String msgStr) throws RemoteException;		//Interprète utilisateur et contenu
}
