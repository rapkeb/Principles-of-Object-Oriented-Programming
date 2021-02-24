package hw3;

import java.util.Iterator;

public class Trap extends Enemy {

	private Integer reLocationRange;
	private Integer reLocationTime;
	private Integer visibilityTime;
	private Integer ticksCount = 0;
	private boolean isVisible = true;

	public Trap(String name,Integer healthPool, Integer attackPoints, Integer defensePoints,Position position,char tile,Integer reLocationRange,Integer reLocationTime,Integer visibilityTime,Integer experience) {
		super(name, healthPool, attackPoints, defensePoints, position,tile,experience);
		this.reLocationRange = reLocationRange;
		this.reLocationTime = reLocationTime;
		this.visibilityTime = visibilityTime;
	}

	public void Movement()
	{
		if(this.gameBoard.GetPlayer() != null)
		{
		if (this.ticksCount  == this.reLocationTime)
		{
			this.ticksCount = 0;
			LinkedList<Position> current = FreePositionsInRange(this.gameBoard,this.reLocationRange);
			Position tmp = current.get((int)(Math.random() * current.size()));
			MoveTrapToPosition(tmp);
		}
		else
		{
			this.ticksCount = this.ticksCount + 1;
			if (this.Range(this.gameBoard.GetPlayer()) < 2)
				DealDamage(this.gameBoard.GetPlayer().position,this.attackPoints);
		}
		if (this.ticksCount < this.visibilityTime)
		{
			this.isVisible = true;
			this.gameBoard.GetBoard()[this.position.GetY()][this.position.GetX()] = this.GetTile();
		}
		else
		{
			this.isVisible = false;
			this.gameBoard.GetBoard()[this.position.GetY()][this.position.GetX()] = '.';
		}
		}
	}
	public LinkedList<Position> FreePositionsInRange(GameBoard p1,int range)
	{
		LinkedList<Position> current = new LinkedList<>();
		for (int i = 0; i<p1.GetBoard().length; i++)
		{
			for (int j = 0; j<p1.GetBoard()[i].length; j++)
			{
				if (p1.GetBoard()[i][j] == '.' && this.Range(j,i) <= this.reLocationRange)
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
	private void MoveTrapToPosition(Position position)
	{
		this.gameBoard.GetBoard()[position.GetY()][position.GetX()] = this.GetTile();
		this.gameBoard.GetUsers()[position.GetY()][position.GetX()] = this;
		this.gameBoard.GetUsers()[this.position.GetY()][this.position.GetX()] = null;
		this.gameBoard.GetBoard()[this.position.GetY()][this.position.GetX()] = '.';
		this.position.SetX(position.GetX());
		this.position.SetY(position.GetY());
	}
}
