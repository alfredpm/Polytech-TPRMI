package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import serveur.IServ;
import serveur.Message;

public class PollThread extends Thread {

	private IServ Serveur;
	
	public PollThread() throws MalformedURLException, RemoteException, NotBoundException {
		this.Serveur = (IServ) Naming.lookup("//localhost/RmiServer");
	}
	public PollThread(IServ serveur) throws MalformedURLException, RemoteException, NotBoundException{
		this.Serveur = serveur;
	}

	public void run() {
		int i = 0;
		while (true) {
			try {
				Message msg=this.Serveur.getMsgMemory(i);
				System.out.println(i+" : "+msg.getUser()+" \n	"+msg.getMsgStr());
				i++;
			} catch (RemoteException e1) {
				e1.printStackTrace();
			} catch (IndexOutOfBoundsException e2) {
				
			}
		}
	}
}
