package utilisateur;

import java.util.LinkedList;

import principal.Principal;
import enchere.Enchere;
import enchere.Etat;
import enchere.Offre;

public class Acheteur extends Utilisateur {

	private boolean alertePrixdeReserve;
	private boolean alerteAnnulation;
	private boolean alerteOffreSuperieur;
	
	private LinkedList<Enchere> encheres = new LinkedList<Enchere>();
	
	public Acheteur(String login) {
		
		super(login);
		this.alertePrixdeReserve=true;
		this.alerteAnnulation=true;
		this.alerteOffreSuperieur=true;
	}
	
	public boolean faireOffre(Enchere enchere,Offre offre)
	{
		if(enchere.getEtat()==Etat.PUBLIEE)
		{
		if(offre.getPrix()>=0)
		{
			
			if(enchere.getEmetteur().getLogin()!=this.getLogin())
			{
				
				if(enchere.getMeilleuroffre() == null)
				{
					if(offre.getPrix()>=enchere.getPrixmin())
					{
					
						enchere.setOffre(offre);
						encheres.add(enchere);
						enchere.getAcheteurs().add((Acheteur)offre.getEmetteur());
						Principal.getPrincipal().getAlerte().nouvelleOffre(enchere);
					
					for (Acheteur acheteur : enchere.getAcheteurs()) {
							Principal.getPrincipal().getAlerte().offresuperieur(enchere, acheteur);
					}
						
					return true;	
					}
				
						System.out.println("L'offre est trop basse");
						return false;
						
				}
				else if(offre.getPrix()>enchere.getMeilleuroffre().getPrix())
						{
							enchere.setOffre(offre);
							enchere.getAcheteurs().add(this);
							encheres.add(enchere);
							
							Principal.getPrincipal().getAlerte().nouvelleOffre(enchere);
							
							for (Acheteur acheteur : enchere.getAcheteurs()) {
								Principal.getPrincipal().getAlerte().offresuperieur(enchere, acheteur);
								Principal.getPrincipal().getAlerte().prixdereserveatteint(enchere, acheteur);
							}
							return true;	
						}
			}
			else
			{
				System.out.println("Vous ne pouvez miser sur votre enchere");
				return false;
			}
		}
	
			System.out.println("Le prix doit etre positif");
			return false;
		}
		System.out.println("L'enchere n'existe pas");
		return false;	
	}
	
	public LinkedList<Enchere> getEncheres()
	{
		return this.encheres;
	}

	public boolean isAlertePrixdeReserve() {
		return alertePrixdeReserve;
	}

	public boolean isAlerteAnnulation() {
		return alerteAnnulation;
	}

	public boolean isAlerteOffreSuperieur() {
		return alerteOffreSuperieur;
	}

	public void setAlertePrixdeReserve(boolean alertePrixdeReserve) {
		this.alertePrixdeReserve = alertePrixdeReserve;
	}

	public void setAlerteAnnulation(boolean alerteAnnulation) {
		this.alerteAnnulation = alerteAnnulation;
	}

	public void setAlerteOffreSuperieur(boolean alerteOffreSuperieur) {
		this.alerteOffreSuperieur = alerteOffreSuperieur;
	}

}
