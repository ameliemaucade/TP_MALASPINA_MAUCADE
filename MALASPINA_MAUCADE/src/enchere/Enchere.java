package enchere;

import java.util.Date;
import java.util.LinkedList;

import principal.Principal;
import utilisateur.Acheteur;
import utilisateur.Utilisateur;

public class Enchere {

	private Date datelimite;
	private float prixmin;
	private float prixreserve;
	private Objet objet;
	private Utilisateur emetteur;
	private Offre meilleuroffre;
	private Etat etat;
	private LinkedList<Acheteur> acheteurs = new LinkedList<Acheteur>();


	public Enchere(Date datelimite,Utilisateur emetteur, Objet objet)
	{
		this.datelimite=datelimite;
		this.prixmin = 0;
		this.prixreserve = 0;
		this.objet = objet;
		this.emetteur = emetteur;
		this.meilleuroffre = null;
		this.etat = Etat.CREE;

	}

	public Date getDatelimite() {
		return datelimite;
	}

	public Objet getObjet() {
		return objet;
	}

	public Utilisateur getEmetteur() {
		return emetteur;
	}

	public Offre getMeilleuroffre() {
		return meilleuroffre;
	}

	public Etat getEtat() {
		return etat;
	}

	public int getPrixmin()
	{
		return (int)prixmin;
	}

	public int getPrixreserve(Utilisateur ut)
	{
		if(ut.getLogin()==emetteur.getLogin())
			return (int)prixreserve;

		return -1;
	}

	public LinkedList<Acheteur> getAcheteurs()
	{
		return this.acheteurs;
	}

	public void setPrixmin(float prixmin) {
		this.prixmin = prixmin;
	}

	public void setPrixreserve(float prixreserve) {
		this.prixreserve = prixreserve;
	}

	public void setOffre(Offre offre) {

		this.meilleuroffre = offre;
	}

	public void setEtat(Etat etat)
	{
		this.etat = etat;
	}

	public void setPubliee()
	{
		if(this.etat == Etat.CREE)
			setEtat(Etat.PUBLIEE);
		Principal.getPrincipal().ajoutEnchere(this);

	}

	public void setAnnulee()
	{
		setEtat(Etat.ANNULEE);
	}

	public void setTerminee(Date date) {
	
			this.etat = Etat.TERMINEE;
	}

	public boolean prixreserveAtteint()
	{
		if(this.meilleuroffre==null)
			return false;

		else if(this.meilleuroffre.getPrix()>=this.prixreserve)
			return true;

		else
			return false;
	}


}
