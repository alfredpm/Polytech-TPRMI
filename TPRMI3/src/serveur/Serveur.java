package serveur;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import client.ICBClient;

public class Serveur extends UnicastRemoteObject implements IServ {
	private static final long serialVersionUID = 1L;
	
	
	
	
	private ArrayList<Message> msgMemory=new ArrayList<>();	//Historique Message
	private ArrayList<ICBClient> CBs =new ArrayList<>();	//Liste de diffusion
	
	public Serveur() throws RemoteException {
		super(0);
	}

	
	
	//Voir IServ pour commentaires.
	//Test
	@Override
	public String welcomeMsg(String username) throws RemoteException {
		return "Bienvenue "+username+" !";
	}
	
	//CONNEXION
	@Override
	public void Connexion(String username, ICBClient CB, boolean wantHistory) throws RemoteException {
		addCB(CB);
		if (wantHistory) {
			getHistory(CB);
		}
		notify(CBs, new Message("Serveur", username+" a rejoint le chat."));
	}
	@Override
	public void getHistory(ICBClient CB) throws RemoteException {
		for (Message msg : msgMemory) {
			CB.notifyMe(msg.getUser(), msg.getMsgStr());
		}
	}
	
	//CHAT
	@Override
	public synchronized void send(String user, String msgStr) throws RemoteException {
		msgMemory.add(new Message(user, msgStr));
		notify(CBs, new Message(user, msgStr));
	}
	@Override
	public synchronized void notify(ArrayList<ICBClient> CBarr, Message msg) throws RemoteException {
		for (ICBClient CB : CBarr) {
			try {
				CB.notifyMe(msg.getUser(), msg.getMsgStr());
			} catch (RemoteException e) {
				e.printStackTrace();
				rmCB(CB);	//Retire CB de la liste des callbacks actifs s'il ne fonctionne pas.
			}
		}
	}
	@Override
	public void quit(String username, ICBClient CB) throws RemoteException{
		notify(CBs, new Message("Serveur", username+" a quitté le chat."));
		rmCB(CB);
	}

	//MEMOIRE
	@Override
	public Message getMsgMemory(int index) throws RemoteException, IndexOutOfBoundsException{
		return msgMemory.get(index);
	}
	
	//GESTION CB
	@Override
	public void addCB(ICBClient CB) throws RemoteException{
		CBs.add(CB);
	}
	@Override
	public void rmCB(ICBClient CB) throws RemoteException{
		CBs.remove(CB);
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
