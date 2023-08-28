import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Menu {
    static void menu(){
    System.out.println("What game would you like to play? (1) Random Dice (2) Race for Riches (3) Jeopardy (4) Who Done It (5) Exit" );
    Scanner in=new Scanner(System.in);
    String input = "";
    boolean isDone = false;
        while(!isDone){
        input=in.nextLine();
        try{
            parseInt(input);
            isDone = true;
        }catch (Exception e){
            System.out.println("please enter game number you would like to play");
        }
    }
        switch (parseInt(input)){
        case 1:
            System.out.println("Now playing Random Dice");
            RandomDice.play();
            break;
        case 2:
            System.out.println("Now playing Race Around the World");
            RaceAroundTheWorld.play();
            break;
        case 3:
            System.out.println("Now playing Jeopardy");
            Jeopardy.play();
            break;
        case 5:
        	System.exit(0);
        	break;
        default: System.out.println("Invalid input");
            break;
    }
}
}
