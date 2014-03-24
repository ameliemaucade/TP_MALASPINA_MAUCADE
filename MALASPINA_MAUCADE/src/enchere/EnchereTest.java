package enchere;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import principal.Chronometre;
import principal.Principal;
import utilisateur.Acheteur;
import utilisateur.Utilisateur;
import utilisateur.Vendeur;

import org.junit.*;

public class EnchereTest {

	Principal principal = new Principal();
	Chronometre chronometre = new Chronometre();

	Utilisateur ut1,ut2;
	Objet ob1;
	Objet ob2;
	Enchere ench1,ench2,ench3;
	Date date;

	@Before
	public void initialize()
	{
		ut1= new Vendeur("ut1");
		ut2 = new Acheteur("ut2");

		principal.ajoutUtilisateur(ut1);
		principal.ajoutUtilisateur(ut2);

		ob1 = new Objet(1, "montre");
		ob2 = new Objet(2, "table");

		date = new Date();
		date.setTime(date.getTime()+500000);


		ench1 = ((Vendeur) ut1).creeEnchere(ob1, date);	
	}

	@Test
	//Verifie que l'enchere n'est pas visible mais bien cree
	// et qu'il n'est pas possible de faire une offre dessus
	public void test1()
	{
		assertEquals(false, ((Acheteur)ut2).faireOffre(ench1, new Offre(ut2,20)));
		assertEquals(false,Principal.getPrincipal().getEncheres().contains(ench1));
	}

	@Test
	//Verifie que l'enchere est mise à la vente
	//Verifie que l'enchere est annulee si le prix de reserve n'est pas atteint
	//Et que l'enchere reste visible pour le vendeur et les acheteurs ayant emis une offre
	public void test2()
	{
		//Mise en vente
		((Vendeur) ut1).publierEnchere(ench1);
		assertEquals(Etat.PUBLIEE,ench1.getEtat());
		assertEquals(true,Principal.getPrincipal().getEncheres().contains(ench1));

		//Annulation
		ench1.setPrixreserve(50);
		((Acheteur)ut2).faireOffre(ench1, new Offre(ut2,20));
		((Vendeur) ut1).annulerEnchere(ench1);
		assertEquals(Etat.ANNULEE,ench1.getEtat());

		//Enchere visible seulement pour vendeur et acheteur ayant emis une offre
		assertEquals(false,Principal.getPrincipal().getEncheres().contains(ench1));
		assertEquals(true,((Acheteur) ut2).getEncheres().contains(ench1));
		assertEquals(true,((Vendeur) ut1).getEncheres().contains(ench1));
	}

	@Test
	//Verifie que l'enchere n'est pas annulee si le prix de reserve est atteint
	public void test3()
	{
		((Vendeur) ut1).publierEnchere(ench1);
		assertEquals(Etat.PUBLIEE,ench1.getEtat());
		assertEquals(true,Principal.getPrincipal().getEncheres().contains(ench1));

		ench1.setPrixreserve(50);
		((Acheteur) ut2).faireOffre(ench1, new Offre(ut2, 55));
		assertEquals(ut2, ench1.getMeilleuroffre().getEmetteur());
		((Vendeur) ut1).annulerEnchere(ench1);
		assertEquals(Etat.PUBLIEE,ench1.getEtat());
		assertEquals(true,Principal.getPrincipal().getEncheres().contains(ench1));
	}

	@Test
	//Verifie que l'enchere qu'une offre n'est pas acceptee si en dessous de prix min
	public void test4()
	{
		((Vendeur) ut1).publierEnchere(ench1);
		assertEquals(Etat.PUBLIEE,ench1.getEtat());
		assertEquals(true,Principal.getPrincipal().getEncheres().contains(ench1));

		ench1.setPrixmin(10);
		((Acheteur) ut2).faireOffre(ench1, new Offre(ut2, 5));
		assertEquals(null,ench1.getMeilleuroffre());
	}

	@Test
	//Verifie que seul le vendeur peut voir le prix de reserve
	//Mais que le prix minimum est visible par les autres
	public void test5()
	{
		((Vendeur) ut1).publierEnchere(ench1);

		//Prix de reserve
		ench1.setPrixreserve(50);
		assertEquals(-1,ench1.getPrixreserve(ut2));
		assertEquals(50,ench1.getPrixreserve(ut1));

		//Prix minimum
		ench1.setPrixmin(10);
		assertEquals(10,ench1.getPrixmin());
	}

	@Test
	//Verifie que les utilisateurs peuvent voir si le prix a été atteint ou pas
	public void test6()
	{
		ench1.setPrixreserve(50);
		((Vendeur) ut1).publierEnchere(ench1);
		assertEquals(false, ench1.prixreserveAtteint());										
		((Acheteur) ut2).faireOffre(ench1, new Offre(ut2, 55));
		assertEquals(true, ench1.prixreserveAtteint());	
	}

}
