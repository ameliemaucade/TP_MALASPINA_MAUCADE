package principal;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import enchere.Enchere;


public class Chronometre implements Runnable {

	private static volatile Chronometre chrono = null;
	private Date heure;


	@Override
	public void run() {
		while(true) {
			try {

				Thread.sleep(1000);
				this.heure = Calendar.getInstance().getTime();
				this.Alarme();

			} catch(Exception e) {System.out.println(e);}
		}
	}

	public Chronometre()
	{
		super();
		this.heure = new Date();

		Thread th = new Thread(this);
		th.start();
	}

	public final static Chronometre getChronometre() {

		if(Chronometre.chrono == null)
			Chronometre.chrono = new Chronometre();

		return Chronometre.chrono;
	}

	private void Alarme()
	{
		LinkedList<Enchere> encheres = Principal.getPrincipal().getEncheres();
		for (Enchere enchere: encheres) {
			enchere.setTerminee(heure);	
	}

	}
}
