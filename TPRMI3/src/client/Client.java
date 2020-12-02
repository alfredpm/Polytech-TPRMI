package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import serveur.IServ;	// Devrait être IServ obtenu dans le package Client

public class Client {
	
	static String defaultUsername="Unknown user";
	
	private IServ Serveur;		//Stub serveur
	private String Username;	//Nom d'utilisateur, donné pour avoir l'auteur des Message
	
	//Constructeur
	public Client() throws MalformedURLException, RemoteException, NotBoundException{
		this.Serveur = (IServ) Naming.lookup("//localhost/RmiServer");
		this.Username=defaultUsername;
	}
	
	//Getters/Setters
	public IServ getServeur() {
		return Serveur;
	}
	public void setServeur(IServ serveur) {
		Serveur = serveur;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}

	//Main
	public static void main(String args[]) throws Exception{
		Client chatClient=new Client();
		@SuppressWarnings("resource")
		Scanner sc=new Scanner(System.in);
		chatClient.setUsername(sc.nextLine());
		
		//Test
		System.out.println(chatClient.Serveur.welcomeMsg(chatClient.Username));
		
		//Connexion
		CBClient CB = new CBClient();
		chatClient.Serveur.Connexion(chatClient.Username,CB, true);

		//Chat
		String message = sc.nextLine();
		while (!message.equals("quit")) {
			chatClient.Serveur.send(chatClient.Username, message);
			message = sc.nextLine();
		}
		chatClient.Serveur.quit(chatClient.Username, CB);
	}
}
