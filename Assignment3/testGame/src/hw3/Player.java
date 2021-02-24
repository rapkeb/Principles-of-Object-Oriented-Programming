package hw3;

import java.util.Iterator;

public abstract class Player extends GameUnit {

	protected Integer experience = 0;
	protected Integer level = 1;

	public Player(String name,Integer healthPool,Integer attackPoints,Integer defensePoints,Position position)
	{
		super(name,healthPool,attackPoints,defensePoints,position);
	}
	public abstract void levelUp();

	public abstract void GameTick();

	public abstract void AbilityCast();

	public abstract String ToString();
	
	public abstract void UserToString();

	public void DealDamage(Position position,int points)
	{
		GameUnit m1 = this.gameBoard.GetUsers()[position.GetY()][position.GetX()];
		String massage = this.name + " engaged in battle with " + m1.name+"\n"
				+ this.ToString()+"\n"
				+ m1.ToString()+"\n";
		int attacker = (int)(Math.random() * points);
		int attacked = (int)(Math.random() * this.gameBoard.GetUsers()[position.GetY()][position.GetX()].defensePoints);
		massage += this.name + " rolled "+attacker+" attack points.\n"
				+m1.name+" rolled "+attacked+" defense points.";
		int damage = attacker-attacked;
		if (damage > 0)
		{
			massage+= this.name +" hit "+m1.name +" for "+damage+" damage\n";
			this.gameBoard.GetUsers()[position.GetY()][position.GetX()].currentHealth = this.gameBoard.GetUsers()[position.GetY()][position.GetX()].currentHealth - damage;
			CheckForDeleteUnit(position);
		}
		else {
			massage+= this.name +" hit "+m1.name +" for "+0+" damage\n";
		}
		notifyObservers(massage);
		levelUp();
	}

	public void CheckForDeleteUnit(Position position)
	{
		if (this.gameBoard.GetUsers()[position.GetY()][position.GetX()].currentHealth <= 0)
		{
			Iterator<Enemy> iter = this.gameBoard.GetEnemies().iterator();
			boolean found = false;
			while (iter.hasNext() & !found)
			{
				Enemy current = iter.next();
				if (current.position == position) {
					String massage = current.name + " died," + this.name + " gaind " + current.getExperience() + " experience points\n";
					notifyObservers(massage);
					this.experience += current.getExperience();
					this.gameBoard.GetEnemies().remove(current);
				}
			}
			this.gameBoard.GetUsers()[position.GetY()][position.GetX()] = null;
			this.gameBoard.GetBoard()[position.GetY()][position.GetX()] = '.';

		}
	}

	public void levelGoUp()
	{
		this.experience = this.experience - (50 * this.level);
		this.level++;
		this.healthPool = this.healthPool + (10 * this.level);
		this.currentHealth = this.healthPool;
		this.attackPoints = this.attackPoints + (5 * this.level);
		this.defensePoints = this.defensePoints + (2 * this.level);
	}
	public boolean CheckForLevelUp()
	{
		if (this.experience >= (50 * this.level))
			return true;
		return false;
	}

	public void PlayerMove(String move){
		switch (move){
			case "d":
				MoveRight();
				break;
			case "s":
				MoveDown();
				break;
			case "w":
				MoveUp();
				break;
			case "a":
				MoveLeft();
				break;
			case "e":
				AbilityCast();
				break;
			case "q":
				break;
		}
	}

	public void MoveLeft()
	{
		if (CanMoveLeft())
		{
			//combat
			if (this.gameBoard.GetUsers()[position.GetY()][position.GetX()-1] != null)
				DealDamage(this.gameBoard.GetUsers()[position.GetY()][position.GetX()-1].position,this.attackPoints);

			else
			{
				this.gameBoard.GetBoard()[position.GetY()][position.GetX()] = '.';
				this.gameBoard.GetUsers()[position.GetY()][position.GetX()] = null;
				this.position.SetX(this.position.GetX()-1);
				this.gameBoard.GetUsers()[position.GetY()][position.GetX()] = this;
				this.gameBoard.GetBoard()[position.GetY()][position.GetX()] = '@';
			}
		}
	}

	public void MoveRight()
	{
		if (CanMoveRight())
		{
			if (this.gameBoard.GetUsers()[position.GetY()][position.GetX()+1] != null)
				DealDamage(this.gameBoard.GetUsers()[position.GetY()][position.GetX()+1].position,this.attackPoints);
			else
			{
				this.gameBoard.GetBoard()[position.GetY()][position.GetX()] = '.';
				this.gameBoard.GetUsers()[position.GetY()][position.GetX()] = null;
				this.position.SetX(this.position.GetX()+1);
				this.gameBoard.GetUsers()[position.GetY()][position.GetX()] = this;
				this.gameBoard.GetBoard()[position.GetY()][position.GetX()] = '@';
			}
		}
	}
	public void MoveUp()
	{
		if (CanMoveUp())
		{
			if (this.gameBoard.GetUsers()[position.GetY()-1][position.GetX()] != null)
				DealDamage(this.gameBoard.GetUsers()[position.GetY()-1][position.GetX()].position,this.attackPoints);
			else
			{
				this.gameBoard.GetBoard()[position.GetY()][position.GetX()] = '.';
				this.gameBoard.GetUsers()[position.GetY()][position.GetX()] = null;
				this.position.SetY(this.position.GetY()-1);
				this.gameBoard.GetUsers()[position.GetY()][position.GetX()] = this;
				this.gameBoard.GetBoard()[position.GetY()][position.GetX()] = '@';
			}
		}
	}
	public void MoveDown()
	{
		if (CanMoveDown())
		{
			if (this.gameBoard.GetUsers()[position.GetY()+1][position.GetX()] != null)
				DealDamage(this.gameBoard.GetUsers()[position.GetY()+1][position.GetX()].position,this.attackPoints);
			{
				this.gameBoard.GetBoard()[position.GetY()][position.GetX()] = '.';
				this.gameBoard.GetUsers()[position.GetY()][position.GetX()] = null;
				this.position.SetY(this.position.GetY()+1);
				this.gameBoard.GetUsers()[position.GetY()][position.GetX()] = this;
				this.gameBoard.GetBoard()[position.GetY()][position.GetX()] = '@';
			}
		}
	}
	private boolean CanMoveLeft()
	{
		if (this.position.GetX() != 0 && (this.gameBoard.GetBoard()[position.GetY()][position.GetX()-1] != '#' ))
			return true;
		return false;
	}
	private boolean CanMoveRight()
	{
		if (this.position.GetX() != this.gameBoard.GetBoard()[0].length-1 && (this.gameBoard.GetBoard()[position.GetY()][position.GetX()+1] != '#'))
			return true;
		return false;
	}
	private boolean CanMoveUp()
	{
		if (this.position.GetY() != 0 && (this.gameBoard.GetBoard()[position.GetY()-1][position.GetX()] != '#'))
			return true;
		return false;
	}
	private boolean CanMoveDown()
	{
		if (this.position.GetY() != this.gameBoard.GetBoard().length-1 && (this.gameBoard.GetBoard()[position.GetY()+1][position.GetX()] != '#'))
			return true;
		return false;
	}

}
