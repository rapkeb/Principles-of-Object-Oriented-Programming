package hw3;

public class Warrior extends Player {

	private Integer cooldown;
	private Integer remaining = 0 ;

	public Warrior(String name,Integer healthPool, Integer attackPoints, Integer defensePoints,	Position position,Integer cooldown)
	{
		super(name, healthPool, attackPoints, defensePoints,position);
		this.cooldown = cooldown;
	}

	public void levelUp()
	{
		if (CheckForLevelUp())
		{
			while(this.experience >= (50 * this.level))
			{
			levelGoUp();
			this.remaining = 0;
			this.healthPool = this.healthPool + (5 * this.level);
			this.defensePoints = this.defensePoints + (1 * this.level);
			String massage = "Level up: +"+(10 * this.level)+"Health, +"+(5 * this.level)+"Attack, +"+(2 * this.level)+"Defence\n";
			notifyObservers(massage);
			}
		}
	}

	public void GameTick()
	{
		this.remaining--;
	}

	public void AbilityCast()
	{
		String massage="";
		if (this.remaining > 0)
			massage+=this.name+" tried to cast Heal, but there is a cool-down: "+this.cooldown+"\n";
		else
		{
			this.remaining = this.cooldown;

			if ((this.currentHealth + (2 * this.defensePoints)<this.healthPool)){
				this.currentHealth =this.currentHealth + (2 * this.defensePoints);
				massage+=this.name+" cast Heal,healing for "+2 * this.defensePoints+"\n";
			}
			else {
				this.currentHealth=this.healthPool;
				massage+=this.name+" cast Heal,healing is at max\n";
			}
		}
		notifyObservers(massage);
	}
	
	public void UserToString()
	{
		String tmp = this.name + "      Health:" + this.currentHealth + "      Attack damage:" + this.attackPoints + "      Defense:" + this.defensePoints + "\n"
				+ "      Level:" + this.level + "      Experience:" + this.experience +"/"+this.level*50 + "      Ability cooldown:" + this.cooldown + "      Remaining:" + this.remaining;
		notifyObservers(tmp);
	}
	public String ToString()
	{
		String tmp = this.name + "      Health:" + this.currentHealth + "      Attack damage:" + this.attackPoints + "      Defense:" + this.defensePoints + "\n"
				+ "      Level:" + this.level + "      Experience:" + this.experience +"/"+this.level*50 + "      Ability cooldown:" + this.cooldown + "      Remaining:" + this.remaining;
		return (tmp);
	}
}
