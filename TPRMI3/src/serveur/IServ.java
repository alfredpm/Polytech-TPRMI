package serveur;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import client.ICBClient;

public interface IServ extends Remote{
	//Test
	public String welcomeMsg(String username) throws RemoteException;					//Ecrit dans la console client bienvenue + nom d'utilisateur (Confirme juste que la communication avec le serveur est possible.)
	
	//CONNEXION
	public void Connexion(String username, ICBClient CB, boolean wantHistory) throws RemoteException;	//Enregistre le callback pour diffusion future. Appelle getHistory si wantHistory vaut true. Notifie de la connexion.
	public void getHistory(ICBClient CB) throws RemoteException;						//Envoie au CB renseigné tous les messages dans msgMemory, un par un via notifyMe (mise en page chat).
	
	//CHAT
	public void send(String username, String msgStr) throws RemoteException;			//Construit un Message a partir du username et de la string. Le stocke dans msgMemory puis le diffuse. 
	public void notify(ArrayList<ICBClient> CBarr, Message msg) throws RemoteException;	//Diffuse le Message sur les callbacks indiqués. Dans un soucis de flexibilité, CBarr n'est pas CBs.
	public void quit(String username, ICBClient CB) throws RemoteException;				//Déconnecte proprement l'utilisateur, rm(CB) et notifie les autres

	//MEMOIRE
	public Message getMsgMemory(int index) throws RemoteException, IndexOutOfBoundsException;	//Renvoie un message spécifique de l'historique de message. Pas utilisé dans cette version, mais pourrait l'être.
	
	//GESTION CB
	public void addCB(ICBClient CB) throws RemoteException;								//Ajoute CB à la liste de diffusion.
	public void rmCB(ICBClient CB) throws RemoteException;								//Retire CB de la liste de diffusion.



	
}
