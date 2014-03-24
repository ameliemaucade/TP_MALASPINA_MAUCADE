package utilisateur;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import principal.Principal;
import enchere.Enchere;
import enchere.Objet;
import enchere.Offre;

public class UtilisateurTest {

Principal principal = new Principal();
	
	Utilisateur ut1,ut2,ut3;
	Objet ob1,ob2,ob3;
	Enchere ech1,ech2;
	Date date;

	@Before
	public void initialize()
	{
		ut1 = new Utilisateur("ut1");
		ut2 = new Utilisateur("ut2");
		ut3 = new Utilisateur("ut3");
		
		
		principal.ajoutUtilisateur(ut1);
		principal.ajoutUtilisateur(ut2);
		principal.ajoutUtilisateur(ut3);
			
		ob1 = new Objet(1, "montre");
		ob2 = new Objet(2, "table");
		ob3 = new Objet(2, "poster");
		
		Date date = new Date();
		date.setTime(date.getTime()+1000);
				
	}
	
	@Test
	public void testUtilisateur()
	{
		//ut1 devient vendeur et cree et publie une enchere
		//On verifie que l'enchere est dans la liste de ut1
		ut1 = ut1.devientVendeur(ut1);
		ech1 = ((Vendeur) ut1).creeEnchere(ob1, date);
		((Vendeur) ut1).publierEnchere(ech1);
		assertEquals(true, ((Vendeur)ut1).getEncheres().contains(ech1));
	

		//ut2 fait une offre sur ut1. On verifie que l'enchere est dans la liste de ut2
		ut2 = ut2.devientAcheteur(ut2);
		((Acheteur)ut2).faireOffre(ech1, new Offre(ut2,40));
		assertEquals(true, ((Acheteur)ut2).getEncheres().contains(ech1));
		
		//ut2 devient vendeur et cree et publie ech3. On verifie que ech3 appartient à la liste
		ut2 = ut2.devientVendeur(ut2);
		ech2 = ((Vendeur)ut2).creeEnchere(ob3, date);
		((Vendeur) ut2).publierEnchere(ech2);
		assertEquals(true, ((Vendeur)ut2).getEncheres().contains(ech2));
		
		//On verifie ut1 ne peut pas faire d'offre sur sa propre enchere
		ut1 = ut1.devientAcheteur(ut1);
		((Acheteur)ut1).faireOffre(ech1, new Offre(ut1,30));
		assertEquals(false, ech1.getAcheteurs().contains(ut1));
	}
}
