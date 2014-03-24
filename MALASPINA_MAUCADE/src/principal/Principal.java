package principal;

import java.util.LinkedList;

import enchere.Enchere;
import utilisateur.Utilisateur;

public class Principal {

	private LinkedList<Utilisateur> utilisateurs = new LinkedList<Utilisateur>();
	private LinkedList<Enchere> encheres = new LinkedList<Enchere>();

	private static  Principal principal = null;
	private Alerte alerte;
	public Principal() {
		alerte = new Alerte();
	}

	public static Principal getPrincipal() {
		if(Principal.principal == null)
			Principal.principal = new Principal();

		return Principal.principal;
	}

	public LinkedList<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}

	public LinkedList<Enchere> getEncheres() {
		return encheres;
	}


	public void ajoutUtilisateur(Utilisateur utilisateur) {

		utilisateurs.add(utilisateur);
	}

	public void ajoutEnchere(Enchere enchere)
	{
		this.encheres.add(enchere);
	}

	public Alerte getAlerte() {
		return this.alerte;
	}

}
