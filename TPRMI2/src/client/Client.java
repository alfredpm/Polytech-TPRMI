package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import serveur.IServ;	// Devrait être IServ obtenu dans le package Client

public class Client {
	
	private IServ Serveur;
	private String Username;
	
	//Constructeur
	public Client() throws MalformedURLException, RemoteException, NotBoundException{
		this.Serveur = (IServ) Naming.lookup("//localhost/RmiServer");
		this.Username="Unknown user";
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
		System.out.println(chatClient.getServeur().welcomeMsg(chatClient.getUsername()));
		//Chat
		new PollThread(chatClient.getServeur()).start();
		String message = sc.nextLine();
		while (!message.equals("quit")) {
			chatClient.Serveur.send(chatClient.getUsername(), message);
			message = sc.nextLine();
		}
	}
}
