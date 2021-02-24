package Tests;

import hw3.Enemy;
import hw3.Position;
import hw3.RandomNumbersGenerator;
import static org.junit.Assert.*;

import hw3.GameBoard;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest {

    private GameBoard g1;

    @Before
    public void setup(){
        this.g1 = new GameBoard("C:\\objectOriented\\hw3\\BoardForTests.txt");
        RandomNumbersGenerator.setDeterministic();
        g1.ChoosePlayer(Integer.parseInt(RandomNumbersGenerator.Action()));
    }

    @Test
    public void testMonsterRandomMovement() {

        Enemy e1 = g1.GetEnemies().get(0);
        Position e1CurrentPos = e1.getPosition();
        e1.Movement();
        assertEquals(e1CurrentPos, e1.getPosition());//expect a battle, no damage
        Enemy e2 = g1.GetEnemies().get(1);
        int e2CurrentPos = e2.getPosition().GetY();
        e2.Movement();
        assertEquals(e2CurrentPos -1, e2.getPosition().GetY());//expect to go up, against whats written in the file
        Enemy e3 = g1.GetEnemies().get(2);
        int e3CurrentPos = e3.getPosition().GetX();
        e3.Movement();
        assertEquals(e3CurrentPos+1, e3.getPosition().GetX());//expect to go left, as in the file

    }


}