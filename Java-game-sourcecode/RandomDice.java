import java.awt.event.KeyAdapter;
import java.util.Random;
import java.util.Scanner;
import java.awt.event.KeyEvent;
public class RandomDice {
    public static void play(){
        //start new game
        Scanner in=new Scanner(System.in);
        boolean out = true;
        boolean out2 = true;
        //while loop for inputs on how many players
        while(out){
            out2 = true;
            System.out.println("Would you like to play (1) player or (2) or return to menu (exit)");
            String input = "";
            //accept input to be tested if not 1 or 2 ask for another input
            while(out2) {
                input =in.nextLine();
                if (input.equals("1")) {
                    System.out.println();
                    OnePlayer(in);
                    out2 = false;
                } else if (input.equals("2")) {
                    System.out.println();
                    TwoPlayer(in);
                    out2 = false;
                } else if (input.equalsIgnoreCase("exit")){
                    out = false;
                    out2 = false;
                    Menu.menu();
                }
                else {
                    System.out.println("Please enter 1 or 2");
                }
            }
            System.out.println("would you like to play again (y/n)");
            input =in.nextLine();
            if(input.equalsIgnoreCase("y")){}
            if(input.equalsIgnoreCase("n")){
                Menu.menu();
                out = false;
            }
        }

    }
    public static void OnePlayer(Scanner in){
        Random rand = new Random();
        //intitialize health
        int playerOneHealth = 10;
        int playerTwoHealth = 10;
        int i = 1;
        boolean goesFirst = true;
        while(goesFirst){
            int playerOneRoll = rand.nextInt(6)+1;
            int playerTwoRoll = rand.nextInt(6)+1;
            System.out.println("You rolled a "+playerOneRoll);
            System.out.println("NPC rolled a "+playerTwoRoll);
            if(playerOneRoll<playerTwoRoll && playerOneRoll!=playerTwoRoll){
                i++;
                System.out.println("NPC rolled higher and goes first! \n");
                goesFirst = false;
            }
            if(playerTwoRoll<playerOneRoll && playerOneRoll!=playerTwoRoll){
                System.out.println("you rolled higher and goes first! \n");
                goesFirst = false;
            }
            if(playerOneRoll==playerTwoRoll){
                System.out.println("You tied! Re-roll!");
            }
        }
        //while both players have not zero health keep playing
        while(playerOneHealth != 0 && playerTwoHealth != 0){
            //if counting variable is odd then player ones turn
            if(i%2 == 1){
                //let player know what to do
                System.out.println("Your turn");
                System.out.println("Press enter to roll");
                //get input
                String input = in.nextLine();
                //get random number from between 1-6
                int intrandom = rand.nextInt(6)+1;
                //tell playing how much damage they did
                System.out.println("You dealt " + String.valueOf(intrandom) + " damage \n");
                //check if player health is exactly zero if it is game ends and player 1 wins else tell player they missed
                if((playerTwoHealth-intrandom) >= 0){
                    playerTwoHealth=playerTwoHealth-intrandom;
                }
                else{
                    System.out.println("You over-swung and missed \n");
                }
                if(playerTwoHealth==0){
                    System.out.println("You win!!");
                    break;
                }
                //end of turn tell player health of both players
                System.out.println("Your Health: " + playerOneHealth);
                System.out.println("NPC Health: " + playerTwoHealth + "\n");
                i++;

            }

            //if counting variable is even it is players twos turn
            if(i%2 == 0){
                System.out.println("CPU's turn");
                //creates a random number from 1 to 6
                int intrandom = rand.nextInt(6)+1;
                System.out.println("CPU hit you for " + String.valueOf(intrandom) + " health");
                //change player one health based on roll
                if((playerOneHealth-intrandom) >= 0){
                    playerOneHealth=playerOneHealth-intrandom;
                }
                else{
                    System.out.println("CPU over-swung and missed you completely");
                }
                if(playerOneHealth==0){
                    System.out.println("CPU won!!");
                    break;
                }
                System.out.println("Your Health: " + playerOneHealth);
                System.out.println("CPU Health: " + playerTwoHealth + "\n");
                i++;
            }
        }
    }
    public static void TwoPlayer(Scanner in){
        Random rand = new Random();
        int playerOneHealth = 10;
        int playerTwoHealth = 10;
        int i = 1;
        boolean goesFirst = true;
        while(goesFirst){
            int playerOneRoll = rand.nextInt(6)+1;
            int playerTwoRoll = rand.nextInt(6)+1;
            System.out.println("player one rolled "+playerOneRoll);
            System.out.println("player two rolled "+playerTwoRoll);
            if(playerOneRoll<playerTwoRoll && playerOneRoll!=playerTwoRoll){
                i++;
                System.out.println("player two rolled higher and goes first!\n");
                goesFirst = false;
            }
            if(playerTwoRoll<playerOneRoll && playerOneRoll!=playerTwoRoll){
                System.out.println("player one rolled higher and goes first!\n");
                goesFirst = false;
            }
            if(playerOneRoll==playerTwoRoll){
                System.out.println("You tied! Re-roll!");
            }
        }

        while(playerOneHealth != 0 && playerTwoHealth != 0){
            //if counting variable is odd then player ones turn
            if(i%2 == 1){
                //let player know what to do
                System.out.println("player one's turn");
                System.out.println("Press enter to roll");
                //get input
                String input = in.nextLine();
                //get random number from between 1-6
                int intrandom = rand.nextInt(6)+1;
                //tell playing how much damage they did
                System.out.println("Player one rolled a " + String.valueOf(intrandom));
                //check if player health is exactly zero if it is game ends and player 1 wins else tell player they missed
                if((playerTwoHealth-intrandom) >= 0){
                    playerTwoHealth=playerTwoHealth-intrandom;
                }
                else{
                    System.out.println("Player one over-swung and missed");
                }
                //end of turn tell player health of both players
                System.out.println("Player One Health: " + playerOneHealth);
                System.out.println("Player Two Health: " + playerTwoHealth);
                i++;
                if(playerTwoHealth==0){
                    System.out.println("Player one won!!");
                    break;
                }
            }
            if(i%2 == 0){
                //let player know what to do
                System.out.println("player two's turn");
                System.out.println("Press enter to roll");
                //get input
                String input = in.nextLine();
                //get random number from between 1-6
                int intrandom = rand.nextInt(6)+1;
                //tell playing how much damage they did
                System.out.println("Player two rolled a " + String.valueOf(intrandom));
                //check if player health is exactly zero if it is game ends and player 1 wins else tell player they missed
                if((playerOneHealth-intrandom) >= 0){
                    playerOneHealth=playerOneHealth-intrandom;
                }
                else{
                    System.out.println("Player two over-swung and missed");
                }
                //end of turn tell player health of both players
                System.out.println("Player One Health: " + playerOneHealth);
                System.out.println("Player Two Health: " + playerTwoHealth);
                i++;
                if(playerOneHealth==0){
                    System.out.println("Player two won!!");
                    break;
                }
            }
        }


    }
}
