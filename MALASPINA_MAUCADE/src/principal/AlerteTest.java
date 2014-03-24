package principal;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import utilisateur.Acheteur;
import utilisateur.Vendeur;
import enchere.Enchere;
import enchere.Objet;
import enchere.Offre;

public class AlerteTest {

	Principal principal = new Principal();
	Vendeur ut1;
	Acheteur ut2,ut3;
	Objet ob1;
	Objet ob2;
	Enchere ech1,ech2;

	@Before
	public void initialize()
	{
		ut1 = new Vendeur("ut1");
		ut2 = new Acheteur("ut2");
		ut3 = new Acheteur("ut3");

		principal.ajoutUtilisateur(ut1);
		principal.ajoutUtilisateur(ut2);
		principal.ajoutUtilisateur(ut3);

		ob1 = new Objet(1, "montre");
		ob2 = new Objet(2, "table");

		Date date = new Date();
		date.setTime(date.getTime()+100000);

		ech1 = ut1.creeEnchere(ob1, date);
		ech2 = ut1.creeEnchere(ob2, date);

		ech1.setPrixmin(5);
		ech1.setPrixreserve(50);

	}

	@Test
	public void testAlerte()
	{
		//Verifie qu'un vendeur recoit bien une alerte lorsque qu'une offre est faite
		ut1.publierEnchere(ech1);
		assertEquals(true, Principal.getPrincipal().getEncheres().contains(ech1));
		ut2.faireOffre(ech1, new Offre(ut2, 10));
		assertEquals(true,ut1.getAlertes().contains("Nouvelle offre sur la vente : montre"));

		//Verifie qu'un acheteur recoit bien une alerte en fonction de sa configuration 

		//L'alerte prix de reserve est activee et pas l'alerte offre superieure pour ut2
		ut2.setAlerteOffreSuperieur(false);
		ut3.faireOffre(ech1, new Offre(ut3,55));
		assertEquals(true,ut2.getAlertes().contains("Prix de reserver atteint pour l'enchere : montre"));
		assertEquals(false,ut2.getAlertes().contains("Attention, un acheteur a surencheri pour l'enchere : montre"));

		//L'alerte annulation est activee pour ut2 mais pas ut3
		ut3.setAlerteAnnulation(false);
		ut3.faireOffre(ech1, new Offre(ut3,57));
		ech1.setPrixreserve(60);
		ut1.annulerEnchere(ech1);
		assertEquals(true,ut2.getAlertes().contains("L'enchere : montre a ete annulee"));
		assertEquals(false,ut3.getAlertes().contains("L'enchere : montre a ete annulee"));
	}


}
