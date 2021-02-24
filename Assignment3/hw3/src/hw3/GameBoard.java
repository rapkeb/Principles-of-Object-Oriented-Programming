package hw3;
import java.io.BufferedReader;

import java.io.FileReader;

import java.io.IOException;

public class GameBoard extends Observable{

    private char [][] board;
    private GameUnit [][] users;
    private Player player;
    private LinkedList<Enemy> enemies;

    public GameBoard(String source)
    {
        this.register(new Observer());
        enemies = new LinkedList<>();
        int line = 0;
        int length = 0;

        try {
            BufferedReader sc = new BufferedReader((new FileReader(source)));
            String s;
            while ((s=sc.readLine())!=null)
            {
                length = s.length();
                line++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.board = new char [line][length];
        this.users = new GameUnit [line][length];

        updateTable(source);
    }

    public void updateTable(String source)
    {
        try {
            BufferedReader sc = new BufferedReader(new FileReader(source));
            String current ;
            int line = -1;
            while ((current=sc.readLine())!=null)
            {
                line++;
                for (int i = 0; i<current.length(); i++)
                {
                    this.board[line][i] = current.charAt(i);
                    if (current.charAt(i) != '@' & current.charAt(i) != '#' & current.charAt(i) != '.')
                    {
                        updateEnemies(line,i,this.board[line][i]);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GameUnit [][] GetUsers()
    {
        return this.users;
    }

    public void SetPlayer()
    {
        this.player = null;
    }

    public void SetPlayer(Player player)
    {
        this.player = player;
    }

    public LinkedList<Enemy> GetEnemies()
    {
        return this.enemies;
    }

    public void updateEnemies(int i,int j,char c)
    {
        Enemy e1=null;
        switch(c){
            case 's':
                e1 = (LannisterSolider(new Position(j,i)));
                e1.gameBoard = this;
                this.users[i][j] = e1;
                enemies.add(e1);
                break;
            case 'k':
                e1 = (LannisterKnight(new Position(j,i)));
                e1.gameBoard = this;
                this.users[i][j] = e1;
                enemies.add(e1);
                break;
            case 'q':
                e1 = (QueensGuard(new Position(j,i)));
                e1.gameBoard = this;
                this.users[i][j] = e1;
                enemies.add(e1);
                break;
            case 'z':
                e1 = (Wright(new Position(j,i)));
                e1.gameBoard = this;
                this.users[i][j] = e1;
                enemies.add(e1);
                break;
            case 'b':
                e1 = (BearWright(new Position(j,i)));
                e1.gameBoard = this;
                this.users[i][j] = e1;
                enemies.add(e1);
                break;
            case 'g':
                e1 = (GiantWright(new Position(j,i)));
                e1.gameBoard = this;
                this.users[i][j] = e1;
                enemies.add(e1);
                break;
            case 'w':
                e1 = (WhiteWalker(new Position(j,i)));
                e1.gameBoard = this;
                this.users[i][j] = e1;
                enemies.add(e1);
                break;
            case 'M':
                e1 = (TheMountain(new Position(j,i)));
                e1.gameBoard = this;
                this.users[i][j] = e1;
                enemies.add(e1);
                break;
            case 'C':
                e1 = (QueenCersei(new Position(j,i)));
                e1.gameBoard = this;
                this.users[i][j] = e1;
                enemies.add(e1);
                break;
            case 'K':
                e1 = (NightsKing(new Position(j,i)));
                e1.gameBoard = this;
                this.users[i][j] = e1;
                enemies.add(e1);
                break;
            case 'B':
                e1 = (BonusTrap(new Position(j,i)));
                e1.gameBoard = this;
                this.users[i][j] = e1;
                enemies.add(e1);
                break;
            case 'Q':
                e1 = (QueensTrap(new Position(j,i)));
                e1.gameBoard = this;
                this.users[i][j] = e1;
                enemies.add(e1);
                break;
            case 'D':
                e1 = (DeathTrap(new Position(j,i)));
                e1.gameBoard = this;
                this.users[i][j] = e1;
                enemies.add(e1);
                break;
        }
        for(Observer o : obList){
            e1.register(o);
        }
    }

    private void ChoosePlayerHelp(int input)
    {
        String [] s1 = FindPlayer(this.board).split("_");
        int x = Integer.valueOf(s1[0]).intValue();
        int y = Integer.valueOf(s1[1]).intValue();
        switch (input){
            case 1:
                this.player = JonSnow(new Position(y,x));
                break;
            case 2:
                this.player = TheHound(new Position(y,x));
                break;
            case 3:
                this.player = Melisandre(new Position(y,x));
                break;
            case 4:
                this.player = ThorosOfMyr(new Position(y,x));
                break;
            case 5:
                this.player = AryaStark(new Position(y,x));
                break;
            case 6:
                this.player = Bronn(new Position(y,x));
                break;
        }
    }

    public void ChoosePlayer(int input)
    {
        ChoosePlayerHelp(input);
        this.player.gameBoard = this;
       /* for(Enemy m : enemies)
        {
            for(Observer o : obList)
                m.register(o);
        }*/
        for(Observer o : obList){
            player.register(o);
        }
        this.users[this.player.position.GetY()][this.player.position.GetX()] = this.player;
    }

    public Position FindPlayer()
    {
        for (int i = 0; i<board.length; i++)
            for (int j = 0; j<board[i].length; j++)
            {
                if (board[i][j] =='@')
                    return new Position(j,i);
            }
        return null;
    }

    private String FindPlayer(char[][] c1)
    {
        for (int i = 0; i<c1.length; i++)
            for (int j = 0; j<c1[i].length; j++)
            {
                if (c1[i][j] =='@')
                    return ""+ i + "_" + j;
            }
        return "Not found";
    }
    public char[][] GetBoard()
    {
        return this.board;
    }

    public Player GetPlayer()
    {
        return this.player;
    }

    public Enemy LannisterSolider(Position position)
    {
        Enemy m1 = new Monster("Lannister Solider",'s',Integer.valueOf(80),Integer.valueOf(8),Integer.valueOf(3),position,Integer.valueOf(25),Integer.valueOf(3));
        return m1;
    }

    public Enemy LannisterKnight(Position position)
    {
        Enemy m1 = new Monster("Lannister Knight",'k',Integer.valueOf(200),Integer.valueOf(14),Integer.valueOf(8),position,Integer.valueOf(50),Integer.valueOf(4));
        return m1;
    }

    public Enemy QueensGuard (Position position)
    {
        Enemy m1 = new Monster("Queen's Guard",'q',Integer.valueOf(400),Integer.valueOf(20),Integer.valueOf(15),position,Integer.valueOf(100),Integer.valueOf(5));
        return m1;
    }

    public Enemy Wright(Position position)
    {
        Enemy m1 = new Monster("Wright",'z',Integer.valueOf(600),Integer.valueOf(30),Integer.valueOf(15),position,Integer.valueOf(100),Integer.valueOf(3));
        return m1;
    }

    public Enemy BearWright(Position position)
    {
        Enemy m1 = new Monster("Bear-Wright",'b',Integer.valueOf(1000),Integer.valueOf(75),Integer.valueOf(30),position,Integer.valueOf(250),Integer.valueOf(4));
        return m1;
    }

    public Enemy GiantWright(Position position)
    {
        Enemy m1 = new Monster("Giant-Wright",'g',Integer.valueOf(1550),Integer.valueOf(100),Integer.valueOf(40),position,Integer.valueOf(500),Integer.valueOf(5));
        return m1;
    }

    public Enemy WhiteWalker(Position position)
    {
        Enemy m1 = new Monster("White Walker",'w',Integer.valueOf(2000),Integer.valueOf(150),Integer.valueOf(50),position,Integer.valueOf(1000),Integer.valueOf(6));
        return m1;
    }

    public Enemy TheMountain(Position position)
    {
        Enemy m1 = new Monster("The Mountain",'M',Integer.valueOf(1000),Integer.valueOf(60),Integer.valueOf(25),position,Integer.valueOf(500),Integer.valueOf(6));
        return m1;
    }

    public Enemy QueenCersei(Position position)
    {
        Enemy m1 = new Monster("Queen Cersei",'C',Integer.valueOf(100),Integer.valueOf(10),Integer.valueOf(10),position,Integer.valueOf(1000),Integer.valueOf(1));
        return m1;
    }

    public Enemy NightsKing(Position position)
    {
        Enemy m1 = new Monster("Night's king",'K',Integer.valueOf(5000),Integer.valueOf(300),Integer.valueOf(150),position,Integer.valueOf(5000),Integer.valueOf(8));
        return m1;
    }

    public Enemy BonusTrap(Position position)
    {
        Enemy m1 = new Trap("Bonus Trap",Integer.valueOf(1),Integer.valueOf(1),Integer.valueOf(1),position,'B',Integer.valueOf(5),Integer.valueOf(6),Integer.valueOf(2),Integer.valueOf(250));
        return m1;
    }

    public Enemy QueensTrap(Position position)
    {
        Enemy m1 = new Trap("Queen's Trap",Integer.valueOf(250),Integer.valueOf(50),Integer.valueOf(10),position,'Q',Integer.valueOf(4),Integer.valueOf(10),Integer.valueOf(4),Integer.valueOf(100));
        return m1;
    }

    public Enemy DeathTrap(Position position)
    {
        Enemy m1 = new Trap("Death Trap",Integer.valueOf(500),Integer.valueOf(100),Integer.valueOf(20),position,'D',Integer.valueOf(6),Integer.valueOf(10),Integer.valueOf(3),Integer.valueOf(250));
        return m1;
    }

    public Player JonSnow(Position position)
    {
        Player p1 = new Warrior("Jon Snow",Integer.valueOf(300),Integer.valueOf(30),Integer.valueOf(4),position,Integer.valueOf(6));
        return p1;
    }

    public Player TheHound(Position position)
    {
        Player p1 = new Warrior("The Hound",Integer.valueOf(400),Integer.valueOf(20),Integer.valueOf(6),position,Integer.valueOf(4));
        return p1;
    }

    public Player Melisandre(Position position)
    {
        Player p1 = new Mage("Melisandre",Integer.valueOf(160),Integer.valueOf(10),Integer.valueOf(1),position,Integer.valueOf(40),Integer.valueOf(300),Integer.valueOf(30),Integer.valueOf(5),Integer.valueOf(6));
        return p1;
    }

    public Player ThorosOfMyr(Position position)
    {
        Player p1 = new Mage("Thoros Of Myr",Integer.valueOf(250),Integer.valueOf(25),Integer.valueOf(3),position,Integer.valueOf(15),Integer.valueOf(150),Integer.valueOf(50),Integer.valueOf(3),Integer.valueOf(3));
        return p1;
    }

    public Player AryaStark(Position position)
    {
        Player p1 = new Rogue("Arya Stark",Integer.valueOf(150),Integer.valueOf(40),Integer.valueOf(2),position,Integer.valueOf(20));
        return p1;
    }

    public Player Bronn(Position position)
    {
        Player p1 = new Rogue("Bronn",Integer.valueOf(250),Integer.valueOf(35),Integer.valueOf(3),position,Integer.valueOf(60));
        return p1;
    }

    public void PrintAllPlayers()
    {
        String tmp ="Select Player:\n"
                + "1. Jon Snow  		Health: 300		Attack damage: 30		Defense: 4" + "\n"
                + "	Level: 1		Experience: 0/50		Ability cooldown: 6		Remaining: 0" + "\n"
                + "2. The Hound		Health: 400		Attack damage: 20		Defense: 6" + "\n"
                + "  Level: 1		Experience: 0/50		Ability cooldown: 4		Remaining: 0" + "\n"
                + "3. Melisandre		Health: 160		Attack damage: 10		Defense: 1" + "\n"
                + "  Level: 1		Experience: 0/50		SpellPower: 15		Mana: 75/300" + "\n"
                + "4. Thoros of Myr		Health: 250		Attack damage: 25		Defense: 3" + "\n"
                + "  Level: 1		Experience: 0/50		SpellPower: 20		Mana: 37/150" + "\n"
                + "5. Arya Stark		Health: 150		Attack damage: 40		Defense: 2" + "\n"
                + "  Level: 1		Experience: 0/50		Energy: 100/100" + "\n"
                + "6. Bronn		Health: 250		Attack damage: 35		Defense: 3" + "\n"
                + "  Level: 1		Experience: 0/50		Energy: 100/100";
        notifyObservers(tmp);
    }

    public void BoardtoString()
    {
        String tmp = "";
        for (int i = 0; i<this.board.length; i++)
        {
            for (int j = 0; j<this.board[i].length; j++)
                tmp = tmp + this.board[i][j];
            tmp = tmp + "\n";
        }
        notifyObservers(tmp);
    }
}