package principal;

import utilisateur.Acheteur;
import enchere.Enchere;
import enchere.Etat;

public class Alerte {

	public void nouvelleOffre(Enchere enchere)
	{
		enchere.getEmetteur().getAlertes().add("Nouvelle offre sur la vente : "+enchere.getObjet().getDescription());

	}

	public void prixdereserveatteint(Enchere enchere, Acheteur ut)
	{
		if(enchere.getMeilleuroffre().getPrix()>=enchere.getPrixreserve(enchere.getEmetteur()))
		{
			if(ut.isAlertePrixdeReserve())
				ut.getAlertes().add("Prix de reserver atteint pour l'enchere : "+enchere.getObjet().getDescription());

		}
	}

	public void enchereannulee(Enchere enchere, Acheteur ut)
	{

		if(enchere.getEtat()==Etat.ANNULEE)
		{
			if(ut.isAlerteAnnulation())
				ut.getAlertes().add("L'enchere : "+enchere.getObjet().getDescription()+" a ete annulee");
		}
	}

	public void offresuperieur(Enchere enchere, Acheteur ut)
	{
		if(enchere.getMeilleuroffre().getEmetteur()!=ut)
		{
			if(ut.isAlerteOffreSuperieur())
				ut.getAlertes().add("Attention, un acheteur a surencheri pour l'enchere : "+enchere.getObjet().getDescription());
		}
	}
}
