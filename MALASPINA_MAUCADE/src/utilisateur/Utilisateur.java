package utilisateur;

import java.util.LinkedList;

import principal.Principal;

public class Utilisateur {
	
	private String nom;
	private String prenom;
	private String login;
	
	private LinkedList<String> alertes = new LinkedList<String>();
	
	public Utilisateur(String login)
	{
		this.login=login;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getLogin() {
		return login;
	}
	
	public LinkedList<String> getAlertes()
	{
		return alertes;
	}
	
	public void NouveauMessage(String message) {
		this.alertes.add(message);
	}
	
	public Vendeur devientVendeur(Utilisateur utilisateur)
	{
		utilisateur = this;
		Principal.getPrincipal().getUtilisateurs().remove(utilisateur);
		utilisateur=new Vendeur(utilisateur.getLogin());
		Principal.getPrincipal().ajoutUtilisateur(utilisateur);
		
		return (Vendeur) utilisateur;
		
	}
	
	public Acheteur devientAcheteur(Utilisateur utilisateur)
	{
		utilisateur = this;
		Principal.getPrincipal().getUtilisateurs().remove(utilisateur);
		utilisateur=new Acheteur(utilisateur.getLogin());
		Principal.getPrincipal().ajoutUtilisateur(utilisateur);
		
		return (Acheteur) utilisateur;
		
	}
}
