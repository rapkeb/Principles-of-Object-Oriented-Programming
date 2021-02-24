package hw3;

public class Monster extends Enemy {

	private Integer visionRange;

	public Monster(String name,char tile,Integer healthPool, Integer attackPoints, Integer defensePoints,Position position,Integer experience,Integer visionRange) {
		super(name, healthPool, attackPoints, defensePoints, position,tile,experience);
		this.visionRange = visionRange;
	}

	public void Movement()
	{
		if(this.gameBoard.GetPlayer() != null)
		{
		if (this.Range(this.gameBoard.GetPlayer()) < this.visionRange)
		{
			Integer dx = this.position.GetX() - this.gameBoard.GetPlayer().position.GetX();
			Integer dy = this.position.GetY() - this.gameBoard.GetPlayer().position.GetY();
			if (Math.abs(dx) > Math.abs(dy))
			{
				if (dx > 0)
					MoveLeft(this);
				else
					MoveRight(this);
			}
			else
			{
				if (dy > 0)
					MoveUp(this);
				else
					MoveDown(this);
			}
		}
		else
			MoveRadomly();
		}
	}
	private void MoveLeft(Monster m1)
	{
		if (CanMoveLeft())
		{
			if (this.gameBoard.GetBoard()[position.GetY()][position.GetX()-1] != '@')
			{
				this.gameBoard.GetBoard()[position.GetY()][position.GetX()] = '.';
				this.gameBoard.GetUsers()[position.GetY()][position.GetX()] = null;
				m1.position.SetX(m1.position.GetX()-1);
				this.gameBoard.GetUsers()[position.GetY()][position.GetX()] = this;
				this.gameBoard.GetBoard()[position.GetY()][position.GetX()] = this.GetTile();
			}
			else
			{
				DealDamage(this.gameBoard.GetPlayer().position,this.attackPoints);
			}
		}
	}
	private void MoveRight(Monster m1)
	{
		if (CanMoveRight())
		{
			if (this.gameBoard.GetBoard()[position.GetY()][position.GetX()+1] != '@')
			{
				this.gameBoard.GetBoard()[position.GetY()][position.GetX()] = '.';
				this.gameBoard.GetUsers()[position.GetY()][position.GetX()] = null;
				m1.position.SetX(m1.position.GetX()+1);
				this.gameBoard.GetUsers()[position.GetY()][position.GetX()] = this;
				this.gameBoard.GetBoard()[position.GetY()][position.GetX()] = this.GetTile();
			}
			else
			{
				DealDamage(this.gameBoard.GetPlayer().position,this.attackPoints);
			}
		}
	}
	private void MoveUp(Monster m1)
	{
		if (CanMoveUp())
		{
			if (this.gameBoard.GetBoard()[position.GetY()-1][position.GetX()] != '@')
			{
				this.gameBoard.GetBoard()[position.GetY()][position.GetX()] = '.';
				this.gameBoard.GetUsers()[position.GetY()][position.GetX()] = null;
				m1.position.SetY(m1.position.GetY()-1);
				this.gameBoard.GetUsers()[position.GetY()][position.GetX()] = this;
				this.gameBoard.GetBoard()[position.GetY()][position.GetX()] = this.GetTile();
			}
			else
			{
				DealDamage(this.gameBoard.GetPlayer().position,this.attackPoints);
			}
		}
	}
	private void MoveDown(Monster m1)
	{
		if (CanMoveDown())
		{
			if (this.gameBoard.GetBoard()[position.GetY()+1][position.GetX()] != '@')
			{
				this.gameBoard.GetBoard()[position.GetY()][position.GetX()] = '.';
				this.gameBoard.GetUsers()[position.GetY()][position.GetX()] = null;
				m1.position.SetY(m1.position.GetY()+1);
				this.gameBoard.GetUsers()[position.GetY()][position.GetX()] = this;
				this.gameBoard.GetBoard()[position.GetY()][position.GetX()] = this.GetTile();
			}
			else
			{
				DealDamage(this.gameBoard.GetPlayer().position,this.attackPoints);
			}
		}
	}
	private void MoveRadomly()
	{
		double tmp = Math.random() * 50;
		if (tmp >= 0 & tmp < 10)
			MoveLeft(this);
		else if (tmp >= 10 & tmp < 20)
			MoveRight(this);
		else if (tmp >= 20 & tmp < 30)
			MoveUp(this);
		else if (tmp >= 30 & tmp < 40)
			MoveDown(this);
	}
	private boolean CanMoveLeft()
	{
		if (this.position.GetX() != 0 && (this.gameBoard.GetBoard()[position.GetY()][position.GetX()-1] == '.' | this.gameBoard.GetBoard()[position.GetY()][position.GetX()-1] == '@'))
			return true;
		return false;
	}
	private boolean CanMoveRight()
	{
		if (this.position.GetX() != this.gameBoard.GetBoard()[0].length-1 && (this.gameBoard.GetBoard()[position.GetY()][position.GetX()+1] == '.' | this.gameBoard.GetBoard()[position.GetY()][position.GetX()+1] == '@'))
			return true;
		return false;
	}
	private boolean CanMoveUp()
	{
		if (this.position.GetY() != 0 && (this.gameBoard.GetBoard()[position.GetY()-1][position.GetX()] == '.' | this.gameBoard.GetBoard()[position.GetY()-1][position.GetX()] == '@'))
			return true;
		return false;
	}
	private boolean CanMoveDown()
	{
		if (this.position.GetY() != this.gameBoard.GetBoard().length-1 && (this.gameBoard.GetBoard()[position.GetY()+1][position.GetX()] == '.' | this.gameBoard.GetBoard()[position.GetY()+1][position.GetX()] == '@'))
			return true;
		return false;
	}
}
