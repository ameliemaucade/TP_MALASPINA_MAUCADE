package enchere;

public class Objet {

	private int id;
	private String description;

	public Objet(int id,String description)
	{
		this.id=id;
		this.description=description;
	}

	public int getId() {

		return id;
	}

	public String getDescription() {
		return description;
	}


}
