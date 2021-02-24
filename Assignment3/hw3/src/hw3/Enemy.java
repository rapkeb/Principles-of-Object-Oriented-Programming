package hw3;

public abstract class Enemy extends GameUnit {

	private Integer experience;
	private char tile;

	public Enemy(String name, Integer healthPool, Integer attackPoints, Integer defensePoints,Position position,char tile,Integer experience) {
		super(name, healthPool, attackPoints, defensePoints, position);
		this.tile = tile;
		this.experience = experience;
	}

	public Integer getExperience() {
		return experience;
	}

	public char GetTile()
	{
		return this.tile;
	}

	public void DealDamage(Position position,int points)
	{
		String massage = this.name + " engaged in battle with " + this.gameBoard.GetPlayer().name+"\n"
				+ this.ToString()+"\n"
				+ this.gameBoard.GetPlayer().ToString()+"\n";
		int attacker = RandomNumbersGenerator.Random(points);
		int attacked = RandomNumbersGenerator.Random(this.gameBoard.GetUsers()[position.GetY()][position.GetX()].defensePoints);
		massage += this.name + " rolled "+attacker+" attack points.\n"
				+this.gameBoard.GetPlayer().name+" rolled "+attacked+" defense points.\n";
		int damage = attacker-attacked;
		if (damage > 0)
		{
			massage+= this.name +" hit "+this.gameBoard.GetPlayer().name +" for "+damage+" damage\n";
			this.gameBoard.GetUsers()[position.GetY()][position.GetX()].currentHealth = this.gameBoard.GetUsers()[position.GetY()][position.GetX()].currentHealth - attacker + attacked;

		}
		else {
			massage+= this.name +" hit "+this.gameBoard.GetPlayer().name +" for "+0+" damage\n";
		}
		notifyObservers(massage);
		CheckForDeleteUnit(position);
	}

	public void CheckForDeleteUnit(Position position)
	{
		if (this.gameBoard.GetUsers()[position.GetY()][position.GetX()].currentHealth <= 0)
		{
			this.gameBoard.SetPlayer();
			this.gameBoard.GetUsers()[position.GetY()][position.GetX()] = null;
			this.gameBoard.GetBoard()[position.GetY()][position.GetX()] = 'X';
		}
	}

	public abstract void Movement();

	public String ToString()
	{
		String tmp = this.name + "      Health:" + this.currentHealth + "      Attack damage:" + this.attackPoints + "      Defense:" + this.defensePoints;
		return tmp;
	}

	public void UserToString()
	{
		String tmp = this.name + "      Health:" + this.currentHealth + "      Attack damage:" + this.attackPoints + "      Defense:" + this.defensePoints;
		notifyObservers(tmp);
	}
}
