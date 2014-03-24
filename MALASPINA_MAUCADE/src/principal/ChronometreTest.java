package principal;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import utilisateur.Acheteur;
import utilisateur.Utilisateur;
import utilisateur.Vendeur;
import enchere.Enchere;
import enchere.Etat;
import enchere.Objet;
import enchere.Offre;

public class ChronometreTest {


	Principal principal = new Principal();
	Chronometre chronometre = new Chronometre();

	Utilisateur ut1,ut2;
	Objet ob1;
	Objet ob2;
	Enchere enchere;
	Date date;

	@Before
	public void initialize()
	{
		ut1= new Vendeur("ut1");
		ut2 = new Acheteur("ut2");

		principal.ajoutUtilisateur(ut1);
		principal.ajoutUtilisateur(ut2);

		ob1 = new Objet(1, "montre");


		date = new Date();
		date.setTime(date.getTime()+1000);


		enchere = ((Vendeur) ut1).creeEnchere(ob1, date);	
		((Vendeur) ut1).publierEnchere(enchere);

		((Acheteur)ut2).faireOffre(enchere, new Offre(ut2,20));
	}


	@Test
	//Verifie que le chronometre marche, que l'enchere se termine a la date limite
	//Et que l'offre la plus forte remporte l'objet

	public void test()
	{
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals(Etat.TERMINEE, enchere.getEtat());
		assertEquals(ut2, enchere.getMeilleuroffre().getEmetteur());

	}
}
