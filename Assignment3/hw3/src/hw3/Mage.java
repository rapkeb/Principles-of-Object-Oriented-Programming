package hw3;

public class Mage extends Player {

	private Integer spellPower;
	private Integer manaPool;
	private Integer currentMana;
	private Integer cost;
	private Integer hitTimes;
	private Integer range;

	public Mage(String name,Integer healthPool, Integer attackPoints, Integer defensePoints,Position position, Integer spellPower, Integer manaPool, Integer cost, Integer hitTimes, Integer range) {
		super(name, healthPool, attackPoints, defensePoints, position);
		this.spellPower = spellPower;
		this.manaPool = manaPool;
		this.currentMana = this.manaPool / 4;
		this.cost = cost;
		this.hitTimes = hitTimes;
		this.range = range;
	}

	public Integer GetRange()
	{
		return this.range;
	}

	public void levelUp()
	{
		if (CheckForLevelUp())
		{
			while(this.experience >= (50 * this.level))
			{
				levelGoUp();
				this.manaPool = this.manaPool + (25 * this.level);
				this.currentMana = Math.min(this.currentMana + (this.manaPool)/4, this.manaPool);
				this.spellPower = this.spellPower + (10 * this.level);
				String massage="Level up: +"+(10 * this.level)+"Health, +"+(5 * this.level)+"Attack, +"+(2 * this.level)+"Defence\n" + "+"+(25 * this.level)+"maximum mana, +"+(10 * this.level)+"spell power\n";
				notifyObservers(massage);
			}
		}
	}

	public void GameTick()
	{
		this.currentMana = Math.min(this.manaPool, this.currentMana + 1);
	}

	public void AbilityCast()
	{
		String massage="";
		if (this.currentMana < this.cost) {
			massage += "you can't use your special ability";
			notifyObservers(massage);
		}
		else
		{
			massage+=this.name+" cast Blizzard.\n";
			notifyObservers(massage);
			this.currentMana = this.currentMana - this.cost;
			Integer hits = 0;
			while (hits < this.hitTimes && ExistsEnemyInRange(this.gameBoard,this.range))
			{
				LinkedList<Position> current = PositionsInRange(this.gameBoard,this.range);
				int random = (int)(Math.random() * (current.size()));
				DealDamage(current.get(random),this.spellPower);
				hits = hits + 1;
			}
		}
	}

	public String ToString()
	{
		String tmp = this.name + "      Health:" + this.currentHealth + "      Attack damage:" + this.attackPoints + "      Defense:" + this.defensePoints + "\n"
				+ "      Level:" + this.level + "      Experience:" + this.experience +"/"+this.level*50 + "      SpellPower:" + this.spellPower + "      Mana:" + this.currentMana+"/"+this.manaPool;
		return tmp;
	}

	public void UserToString()
	{
		String tmp = this.name + "      Health:" + this.currentHealth + "      Attack damage:" + this.attackPoints + "      Defense:" + this.defensePoints + "\n"
				+ "      Level:" + this.level + "      Experience:" + this.experience +"/"+this.level*50 + "      SpellPower:" + this.spellPower + "      Mana:" + this.currentMana+"/"+this.manaPool;
		notifyObservers(tmp);
	}
}
