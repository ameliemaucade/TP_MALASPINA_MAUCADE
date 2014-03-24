package utilisateur;

import java.util.Date;
import java.util.LinkedList;

import principal.Principal;
import enchere.Enchere;
import enchere.Objet;

public class Vendeur extends Utilisateur {
	
	public LinkedList<Enchere> encheres = new LinkedList<Enchere>();
	
	public Vendeur(String login) {

		super(login);
	}

	public Enchere creeEnchere(Objet objet, Date datelimite)
	{
		Enchere enchere = new Enchere(datelimite,this,objet);
		this.encheres.add(enchere);
		System.out.println("L'enchere a ete cree");
		
		return enchere;
	}
	
	public void publierEnchere(Enchere enchere)
	{
		this.encheres.get(encheres.indexOf(enchere)).setPubliee();
		System.out.println("L'enchere a ete publiee");
	}
	
	public boolean annulerEnchere(Enchere enchere)
	{
		if(Principal.getPrincipal().getEncheres().contains(enchere))
		{
			if(!enchere.prixreserveAtteint())
			{
				enchere.setAnnulee();
				Principal.getPrincipal().getEncheres().remove(enchere);
				System.out.println("L'enchere a ete annulee");
				for (Acheteur acheteur : enchere.getAcheteurs()) {
					Principal.getPrincipal().getAlerte().enchereannulee(enchere, acheteur);
				}
				return true;	
			}
		
				System.out.println("Impossible d'annuler, le prix de reserve est atteint");
				return false;
				
		}
		
			System.out.println("L'enchere n'existe pas");
			return false;
			
	}
	
	public LinkedList<Enchere> getEncheres()
	{
		return this.encheres;
	}

}
