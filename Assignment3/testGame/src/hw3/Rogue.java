package hw3;

import java.util.Iterator;

public class Rogue extends Player {

	private Integer cost;
	private Integer currentEnergy = 100;

	public Rogue(String name,Integer healthPool, Integer attackPoints, Integer defensePoints,Position position, Integer cost) {
		super(name, healthPool, attackPoints, defensePoints, position);
		this.cost = cost;
	}
	public void levelUp()
	{
		if (CheckForLevelUp())
		{
			while(this.experience >= (50 * this.level))
			{
			levelGoUp();
			this.currentEnergy = 100;
			this.attackPoints = this.attackPoints + (3 * this.level);
			String massage = "Level up: +"+(10 * this.level)+"Health, +"+(5 * this.level)+"Attack, +"+(2 * this.level)+"Defence\n";
			notifyObservers(massage);
			}
		}
	}
	public void GameTick()
	{
		this.currentEnergy = Math.min(this.currentEnergy + 10, 100);
	}
	public void AbilityCast()
	{
		String massage="";
		if (this.currentEnergy < this.cost) {
			massage += "you can't use your special ability";
			notifyObservers(massage);
		}
		else
		{
			massage+=this.name+" cast Fan of Knives.\n";
			notifyObservers(massage);
			this.currentEnergy = this.currentEnergy - this.cost;
			LinkedList<Position> current = PositionsInRange(this.gameBoard,2);
			Iterator<Position> iter = current.iterator();
			while (iter.hasNext())
				DealDamage(iter.next(),this.attackPoints);
		}

	}

	public String ToString()
	{
		String tmp = this.name + "      Health:" + this.currentHealth + "      Attack damage:" + this.attackPoints + "      Defense:" + this.defensePoints + "\n"
				+ "      Level:" + this.level + "      Experience:" + this.experience +"/"+this.level*50 + "      Energy:"+this.currentEnergy+"/100" ;
		return tmp;
	}
	
	public void UserToString()
	{
		String tmp = this.name + "      Health:" + this.currentHealth + "      Attack damage:" + this.attackPoints + "      Defense:" + this.defensePoints + "\n"
				+ "      Level:" + this.level + "      Experience:" + this.experience +"/"+this.level*50 + "      Energy:"+this.currentEnergy+"/100" ;
		notifyObservers(tmp);
	}

}
