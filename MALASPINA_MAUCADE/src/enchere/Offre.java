package enchere;

import utilisateur.Utilisateur;

public class Offre {

	private Utilisateur emetteur;
	private float prix;

	public Offre(Utilisateur emetteur, float prix)
	{
		this.emetteur = emetteur;
		this.prix = prix;

	}

	public Utilisateur getEmetteur() {
		return emetteur;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(Float prix) {
		this.prix=prix;
	}

}
