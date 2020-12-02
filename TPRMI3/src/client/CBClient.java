package client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CBClient extends UnicastRemoteObject implements ICBClient{
	private static final long serialVersionUID = 1L;
	
	protected CBClient() throws RemoteException {
		super();
	}

	@Override
	public void notifyMe(String user, String content) throws RemoteException {
		//Mise en forme du Message
		if (user.equals("Serveur")){ //On différencie les notifications purement serveur de la réception de message d'utilisateurs.
			System.out.println(" *** "+content+" *** ");
		} else {
			System.out.println(user+" :	"+content);
		}
	}

}
