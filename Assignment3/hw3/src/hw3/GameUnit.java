package hw3;

import java.util.Iterator;

public abstract class GameUnit extends Observable{

	protected GameBoard gameBoard;
	protected String name;
	protected Integer healthPool;
	protected Integer currentHealth;
	protected Integer attackPoints;
	protected Integer defensePoints;
	protected Position position;

	public GameUnit(String name,Integer healthPool,Integer attackPoints,Integer defensePoints,Position position)
	{
		this.name = name;
		this.healthPool = healthPool;
		this.currentHealth = healthPool;
		this.attackPoints = attackPoints;
		this.defensePoints = defensePoints;
		this.position = position;
	}

	public double Range(GameUnit p)
	{
		return Math.sqrt(((p.position.GetX() - this.position.GetX())*(p.position.GetX() - this.position.GetX())) + ((p.position.GetY() - this.position.GetY())*(p.position.GetY() - this.position.GetY())));
	}

	public double Range(int x, int y)
	{
		return Math.sqrt(((x - this.position.GetX())*(x - this.position.GetX())) + ((y - this.position.GetY())*(y - this.position.GetY())));
	}

	public Position getPosition() {
		return position;
	}

	public abstract void DealDamage(Position position, int points);

	public abstract void CheckForDeleteUnit(Position position);

	public boolean ExistsEnemyInRange(GameBoard g1,int range)
	{
		LinkedList<Position> current = PositionsInRange(g1,range);
		return (!current.isEmpty());
	}

	public LinkedList<Position> PositionsInRange(GameBoard p1,int range)
	{
		LinkedList<Position> current = new LinkedList<>();
		for (int i = 0; i<p1.GetUsers().length; i++)
		{
			for (int j = 0; j<p1.GetUsers()[i].length; j++)
			{
				if (p1.GetUsers()[i][j] != null && p1.GetUsers()[i][j].Range(this) < range)
					current.add(new Position(j,i));
			}
		}
		Iterator<Position> iter = current.iterator();
		while(iter.hasNext())
		{
			Position tmp = iter.next();
			if (tmp.GetY() == this.position.GetY() & tmp.GetX() == this.position.GetX())
				current.remove(tmp);
		}
		return current;
	}
	public abstract void UserToString();

	public abstract String ToString();
}
