package serveur;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Serveur extends UnicastRemoteObject implements IServ{
	private static final long serialVersionUID = 1L;
	public ArrayList<Message> msgMemory=new ArrayList<>();
	
	//Constructeur
	public Serveur() throws RemoteException {
		super(0);
	}

	//Test
	@Override
	public String welcomeMsg(String username) throws RemoteException {
		return "Bienvenue "+username+" !";
	}
	
	//Chat
	@Override
	public void send(String user, String msgStr) throws RemoteException {
		msgMemory.add(new Message(user, msgStr));
	}

	@Override
	public ArrayList<Message> getMsgMemory() throws RemoteException {
		return msgMemory;
	}
	
	public Message getMsgMemory(int index) throws RemoteException, IndexOutOfBoundsException{
		return msgMemory.get(index);
	}

	
	//Main
	public static void main(String args[]) throws Exception {
		try {
			LocateRegistry.createRegistry(1099);
		} catch (RemoteException e) {
			System.out.println("Remote exception serv : "+e);
		}
		Serveur chatServeur = new Serveur();
		Naming.rebind("//localhost/RmiServer", chatServeur);
		System.out.println("Serveur prêt.");
	}

}
