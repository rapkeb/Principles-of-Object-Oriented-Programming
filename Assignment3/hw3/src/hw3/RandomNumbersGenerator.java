package hw3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.Scanner;

public class RandomNumbersGenerator {

    private static boolean deterministic = false;
    private static BufferedReader reader;
    private static BufferedReader ActionReader;

    public static void setDeterministic() {
        deterministic=true;
    }

    public static int Random(int range){
        if (!deterministic)
            return Integer.parseInt(String.valueOf(Math.round(Math.random()*range)));
        return deterministicMode();
    }

    private static int deterministicMode(){
        try {
            if (reader == null) {
                reader = new BufferedReader(new FileReader(Paths.get("random_numbers.txt").toString()));
            }
            int number = Integer.valueOf(reader.readLine());
            return number;
        }
        catch (Exception e){
            System.exit(-1);
        }
        return 0;
    }

    public static String Action(){
        if (!deterministic){
            Scanner myScanner = new Scanner(System.in);
            return myScanner.nextLine();
        }
        return deterministicModeAction();
    }

    private static String deterministicModeAction(){
        try {
            if (ActionReader == null) {
                ActionReader = new BufferedReader(new FileReader(Paths.get("user_actions.txt").toString()));
            }
            String move = ActionReader.readLine();
            return move;
        }
        catch (Exception e){
            System.exit(-1);
        }
        return "";
    }

}
