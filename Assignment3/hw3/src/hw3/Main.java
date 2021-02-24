package hw3;

import java.io.File;
import java.util.Iterator;
import java.util.Scanner;

public class Main extends Observer {


    public static void main (String [] args) {
        File folder = new File(args[0]);
        File[] levelFiles = folder.listFiles();
        if (args.length>1) {
            RandomNumbersGenerator.setDeterministic();
        }
        LinkedList<GameBoard> boardList = createBoardList(levelFiles);
        Iterator<GameBoard> iter = boardList.iterator();
        GameBoard currentBoard = iter.next();
        currentBoard.PrintAllPlayers();
        String choice = RandomNumbersGenerator.Action();
        currentBoard.ChoosePlayer(Integer.parseInt(choice));
        System.out.println("You have selected:");
        while (currentBoard.GetPlayer() != null && (!currentBoard.GetEnemies().isEmpty())) {
            //print the chosen player and print currentBoard.
            currentBoard.GetPlayer().UserToString();
            System.out.println("Use w/s/a/d to move.\n" + "Use e for special ability or q to pass.");
            currentBoard.BoardtoString();
            String plyaermove = RandomNumbersGenerator.Action();
            currentBoard.GetPlayer().PlayerMove(plyaermove);
            currentBoard.GetPlayer().GameTick();
            //enemys make a move
            for (Enemy c:currentBoard.GetEnemies()){
                c.Movement();
            }
            if (currentBoard.GetEnemies().isEmpty() && iter.hasNext()) {
                GameBoard tmp = iter.next();
                currentBoard.GetPlayer().position = tmp.FindPlayer();
                currentBoard.GetPlayer().gameBoard = tmp;
                tmp.SetPlayer(currentBoard.GetPlayer());
                currentBoard = tmp;
                //currentBoard.register(O);
            }
        }
        if (currentBoard.GetPlayer() == null) {
            currentBoard.BoardtoString();
            System.out.println("And now your watch has ended, valar morghulis");
        } else {//the player won
            System.out.println("In the GAME OF THRONES you win or you die... so congrats!");
        }
    }

    private static LinkedList<GameBoard> createBoardList (File[] file){
        LinkedList<GameBoard> boardList = new LinkedList<>();
        for (int i = 0; i<file.length;i++){
            GameBoard tmp = new GameBoard(file[i].getPath());
            boardList.add(tmp);
        }
        return boardList;
    }


}
