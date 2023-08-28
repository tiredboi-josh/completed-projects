import java.rmi.server.ServerNotActiveException;
import java.sql.SQLOutput;
import java.util.Random;
import java.util.Scanner;

public class Jeopardy{

    static int player1points = 0;
    static int player2points = 0;
    static boolean isSinglePlayer;
    static boolean isPlayer1Turn;
    public static void play(){
        //ask user for multiPlayer() or singlePlayer() @TODO test
        Scanner scnr = new Scanner(System.in);

        String userGameSelection = "";

        while (true){
            player1points = 0;
            player2points = 0;

            System.out.println("Would you like to play single (1), multi (2) or Quit (3)?");


                userGameSelection = scnr.nextLine();
                


            if (userGameSelection.equalsIgnoreCase("1")){
                isSinglePlayer = true;
                singlePlayer();
            }
            else if (userGameSelection.equalsIgnoreCase("2")){
                isSinglePlayer = false;
                multiPlayer();
            }
            else if (userGameSelection.equalsIgnoreCase("3")){
                Menu.menu();
            }

            else {
                System.out.println("Not a valid input only enter '1', '2' or '3'.");
            }
        }
    }
    public static void multiPlayer(){
        //roll to see who goes first
        if(diceRoll()==1){ //Returns 1 or 2 for which player goes first
            isPlayer1Turn = true;
            System.out.println();
            System.out.println("ALEX TREBEK SAYS 'PLAYER ONE GOES FIRST'");
            System.out.println();
            //player 1
            //ask dice roll winner which board to play
            switch (askGame()){
                case 1:
                    playGeoGuesser();
                    break;
                case 2:
                    playITT();
                    break;
                case 3:
                    playNTP();
                    break;
            }
        }
        else{
            isPlayer1Turn = false;
            System.out.println();
            System.out.println("ALEX TREBEK SAYS 'PLAYER TWO GOES FIRST'");
            System.out.println();
            //player 2
            //ask dice roll winner which board to play
            switch (askGame()){
                case 1:
                    playGeoGuesser();
                    break;
                case 2:
                    playITT();
                    break;
                case 3:
                    playNTP();
                    break;
            }
        }
    }
    public static void singlePlayer(){
        //ask which board to play @TODO
        isPlayer1Turn = true;
        switch (askGame()){

            case 1:
                playGeoGuesser();
                break;
            case 2:
                playITT();
                break;
            case 3:
                playNTP();
                break;
        }
    }

    public static int askGame(){
        Scanner scnr = new Scanner(System.in);
        String userGameSelection = "";
        boolean isVaild = false;

        while(!isVaild){

            System.out.println("Enter 1 for Geoguesser \nEnter 2 for IT Triva \nEnter 3 for Name That person \n");
            userGameSelection = scnr.nextLine();
            
            if (userGameSelection.equalsIgnoreCase("1") || userGameSelection.equalsIgnoreCase("2") || userGameSelection.equalsIgnoreCase("3")){
                isVaild = true;
            }
            else {
                System.out.println("Not a vaild input only enter '1' , '2' or '3'.");
            }
        }
        return Integer.parseInt(userGameSelection);
    }
    public static void printGameBoard(boolean[][] subgameBoolArray, String cata1, String cata2, String cata3){
        System.out.println("\n\n(1) " +cata1 + " (2) " + cata2 + " (3) " + cata3 + "\n");
        System.out.println("           Game Board          ");
        System.out.println("    (1)       (2)       (3)    ");
             String line = "+---------+---------+---------+";
        for (int row = 0; row < 3; row++) {
            System.out.println(line);
            for (int column = 0; column < 3; column++) {
                System.out.print("|");
                if(row == 0){
                    if(subgameBoolArray[row][column] == true){
                        System.out.print("         ");
                    }
                    else{
                        System.out.print("   100   ");
                    }
                }
                if(row == 1){
                    if(subgameBoolArray[row][column] == true){
                        System.out.print("         ");
                    }
                    else{
                        System.out.print("   300   ");
                    }
                }
                if(row == 2){
                    if(subgameBoolArray[row][column] == true){
                        System.out.print("         ");
                    }
                    else{
                        System.out.print("   500   ");
                    }
                }
            }
            System.out.println("|");
        }
        System.out.println(line);
        if(!isSinglePlayer){
            if(isPlayer1Turn){
                System.out.println("\n         PLAYER ONE TURN       ");
            }
            else{
                System.out.println("\n         PLAYER TWO TURN       ");
            }
        }
        /*
        System.out.println("           Game Board          ");
        System.out.println(cata1 + " " + cata2 + " " + cata3);
        System.out.println("+---------+---------+---------+");
        System.out.println("|   100   |   100   |   100   |");
        System.out.println("+---------+---------+---------+");
        System.out.println("|   300   |   300   |   300   |");
        System.out.println("+---------+---------+---------+");
        System.out.println("|   500   |   500   |   500   |");
        System.out.println("+---------+---------+---------+");
        */
    }
    //returns true if board not done
    public static boolean checkIfGameBoardDone(boolean[][] subgameBoolArray){
        for(int i = 0; subgameBoolArray.length > i; i++){
            for(int row = 0; row<3 ;row++){
                for(int column = 0; column<3 ;column++){
                    if (subgameBoolArray[row][column] == false){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static void getPlayerQSelection(String[][] QA, String[][] MC,  boolean[][] BA, int [][] CMC){
        //indexing array
        int [][] IA = new int[3][3];
        int x = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                IA[i][j] = x;
                x++;
            }
        }
        int column = 0;
        int row = 0;
        Scanner scnr = new Scanner(System.in);
        String input = "-1";
        //get player column and row
        boolean isPlayerAnswer = false;
        boolean isInputGood = false;
        while(!isInputGood){
            System.out.println("Which column would you like to play?");
            while (!isPlayerAnswer) {
                try {
                    input = scnr.nextLine();
                    
                }
                catch (Exception e) {
                    System.out.println("please enter 1, 2 or 3");
                }
                if (input.equalsIgnoreCase("1") || input.equalsIgnoreCase("2") || input.equalsIgnoreCase("3")) {
                    column = Integer.parseInt(input) - 1;
                    isPlayerAnswer = true;
                } else {
                    System.out.println("please enter 1, 2 or 3");
                }
            }
            System.out.println("Which row would you like to play?");
            isPlayerAnswer = false;
            while (!isPlayerAnswer) {
                try {
                    input = scnr.nextLine();
                }
                catch (Exception e) {
                    System.out.println("please enter 1, 2 or 3");
                }
                if (input.equalsIgnoreCase("1") || input.equalsIgnoreCase("2") || input.equalsIgnoreCase("3")) {
                    row = Integer.parseInt(input) - 1;
                    isPlayerAnswer = true;
                } else {
                    System.out.println("please enter 1, 2 or 3");
                }
            }
            if(BA[row][column] == false){
                isInputGood = true;
                isPlayerAnswer = true;
            }
            else{
                System.out.println("This question has already been answered. Please enter a different question.");
                isInputGood = false;
                isPlayerAnswer = false;
            }
        }
        //once player column and row, askQuestion()
        BA[row][column] = true;
        String question = QA[row][column];
        int answer = CMC[row][column];

        String MC1 = MC[IA[row][column]][0];
        String MC2 = MC[IA[row][column]][1];
        String MC3 = MC[IA[row][column]][2];
        //find how to get both correct answer and correct point value for the question to input into askQuestion()
        if (row == 0){
            askQuestion(question, MC1, MC2, MC3, answer, 100);
        }
        if (row == 1){
            askQuestion(question, MC1, MC2, MC3, answer, 300);
        }
        if (row == 2){
            askQuestion(question, MC1, MC2, MC3, answer, 500);
        }
    }
    public static void askQuestion(String question, String choice1, String choice2, String choice3, int CorrectAnswer, int points){
        Scanner scnr = new Scanner(System.in);
        boolean answered = false;
        System.out.println(question + "\n" + "\n" + "(1) " + choice1 + "\n" + "(2) " + choice2 + "\n" + "(3) " + choice3);
        //basically while false
        while (!answered){
            //get input
            String input = scnr.nextLine();
            //if 1, 2 or 3 == proper input
            if(input.equalsIgnoreCase("1") || input.equalsIgnoreCase("2") || input.equalsIgnoreCase("3")){
                //if user answer and correct answer are the same reward int points
                answered = true;
                if(input.equalsIgnoreCase(String.valueOf(CorrectAnswer))){
                    if(isPlayer1Turn){
                        System.out.println("\nPlayer one is correct, " + points + " points awarded\n");
                        player1points = player1points + points;
                        System.out.println("Player one has " + player1points + " points");
                        System.out.println("Player two has " + player2points + " points");
                    }
                    else{
                        System.out.println("\nPlayer two is correct, " + points + " points awarded\n");
                        player2points = player2points + points;
                        System.out.println("Player one has " + player1points + " points");
                        System.out.println("Player two has " + player2points + " points");
                    }

                    //if return true should be added to bool array
                }
                else{
                    //else the question was answered wrong and return true to bool array to show question was answered
                    if(isPlayer1Turn){
                        System.out.println("\nPlayer one is incorrect\n");
                        System.out.println("Player one has " + player1points + " points");
                        System.out.println("Player two has " + player2points + " points");
                    }
                    else{
                        System.out.println("\nPlayer two is incorrect\n");
                        System.out.println("Player one has " + player1points + " points");
                        System.out.println("Player two has " + player2points + " points");
                    }

                }
            }
            else {
                System.out.println("Enter number 1, 2 or 3");
            }
        }
    }

    public static void playGeoGuesser(){
        //INITIALIZE VARIABLES
        //GeoGuesser Questions Array
        String[][] GGQA = new String[3][3];
        //GeoGuesser Correct Multiple Choice
        int [][] GGCMC = new int[3][3];
        //multiple choice
        String[][] GGMC = new String[9][3];
        boolean[][] GGBA = new boolean[3][3];
        //go till array done

        //FLAGS Questions
        GGQA[0][0] = "This green white and red flag has a picture of an eagle eating a snake in its center.";
        GGCMC[0][0] = 1; GGMC[0][0] = "Mexico"; GGMC[0][1] = "USA"; GGMC[0][2] = "Russia";

        GGQA[1][0] = "This white flag has a red circle in the middle.";
        GGCMC[1][0] = 3; GGMC[3][0] = "Brazil"; GGMC[3][1] = "USA"; GGMC[3][2] = "Japan";

        GGQA[2][0] = "This is a flag with vertical green white and red stripes.";
        GGCMC[2][0] = 3; GGMC[6][0] = "Canada"; GGMC[6][1] = "India"; GGMC[6][2] = "Italy";
        //LANDMARKS Questions
        GGQA[0][1] = "This is where StoneHenge is located.";
        GGCMC[0][1] = 1; GGMC[1][0] = "England"; GGMC[1][1] = "Ireland"; GGMC[1][2] = "Russia";

        GGQA[1][1] = "This wonder of the world, is over 13,000 miles long.";
        GGCMC[1][1] = 2; GGMC[4][0] = "The pyramids"; GGMC[4][1] = "Great wall of china"; GGMC[4][2] = "The colosseum";

        GGQA[2][1] = "The Colosseum, located in Rome, previously known as.";
        GGCMC[2][1] = 3; GGMC[7][0] = "The octagon"; GGMC[7][1] = "The pit"; GGMC[7][2] = "Flavian Amphitheatre";
        //MAP SILHOUETTE Questions
        GGQA[0][2] = "This country has a population of ~300 million people, and was founded in 1776";
        GGCMC[0][2] = 1; GGMC[2][0] = "USA"; GGMC[2][1] = "Canada"; GGMC[2][2] = "Dominican Republic";

        GGQA[1][2] = "This country consists of a group of islands off the northwest coast of Europe";
        GGCMC[1][2] = 1; GGMC[5][0] = "UK"; GGMC[5][1] = "France"; GGMC[5][2] = "Italy";

        GGQA[2][2] = "This is a country located in the heart of Europe";
        GGCMC[2][2] = 2; GGMC[8][0] = "Belgium"; GGMC[8][1] = "Germany"; GGMC[8][2] = "Spain";

        //SINGLE PLAYER
        if(isSinglePlayer){
            while (checkIfGameBoardDone(GGBA)) {
                //print game board
                printGameBoard(GGBA, "Flags", "Wonders of the World", "Country Description");
                //ask player to select question
                getPlayerQSelection(GGQA, GGMC, GGBA, GGCMC);
                //loop to ask player to select question
            }
            System.out.println("All questions are answered, time for the final question");
            finalQuestion("What is the most visited country in the world?", "France");
        }
        //MULTI PLAYER
        else{
            while (checkIfGameBoardDone(GGBA)) {
                //print game board
                printGameBoard(GGBA, "Flags", "Wonders of the World", "Country Description");
                //ask player to select question
                getPlayerQSelection(GGQA, GGMC, GGBA, GGCMC);
                //loop to ask player to select question
                if(isPlayer1Turn){
                    //tell that it is player two turn
                    isPlayer1Turn = false;
                }
                else{
                    isPlayer1Turn = true;
                }
            }
            System.out.println("All questions are answered, time for the final question");
            finalQuestion("What is the most visited country in the world?", "France");
        }
    }
    public static void playNTP(){
        //INITIALIZE VARIABLES
        //ADD QUESTIONS
        //Name That Person Question Array
        String[][] NTPQA = new String[3][3];
        String timeForFinalQuestion = "\nAll questions are answered, time for the final question.";
        String NTPFQ = "\nThe Art Deco movement focuses specifically on which element of art?";
        String NTPFA = "Decorative";
        //Name That Person Correct Multiple Choice
        int [][] NTPCMC = new int[3][3];
        //multiple choice
        String[][] NTPMC = new String[9][3];
        boolean[][] NTPBA = new boolean[3][3];
        //go till array done

        //ACTORS Questions
        NTPQA[0][0] = "\nWhich actor is well known for their appearance in the 1980’s TV show MacGyver?";
        NTPCMC[0][0] = 2; 
        NTPMC[0][0] = "Ted Danson"; NTPMC[0][1] = "Richard Dean Anderson"; NTPMC[0][2] = "Lucas Till";

        NTPQA[1][0] = "\nWhich actress is known as the first female action star?";
        NTPCMC[1][0] = 1;
        NTPMC[3][0] = "Sigourney Weaver"; NTPMC[3][1] = "Jennifer Lawrence"; NTPMC[3][2] = "Linda Hamilton";

        NTPQA[2][0] = "\nWhich actor came to fame in the silent film era?";
        NTPCMC[2][0] = 2;
        NTPMC[6][0] = "Orson Welles"; NTPMC[6][1] = "Charlie Chaplin"; NTPMC[6][2] = "Anthony Perkins";
        
        //MUSICIANS Questions
        NTPQA[0][1] = "\nWhich famous musician sings, plays bass, and is well known for having a extremely long tongue?";
        NTPCMC[0][1] = 3; 
        NTPMC[1][0] = "Steven Tyler"; NTPMC[1][1] = "Sting"; NTPMC[1][2] = "Gene Simmons";

        NTPQA[1][1] = "\nWhich left-handed guitarist gained fame partly due to their innovation of taking a right-handed guitar, flipping it and restringing it?";
        NTPCMC[1][1] = 1; 
        NTPMC[4][0] = "Jimi Hendrix"; NTPMC[4][1] = "Kurt Cobain"; NTPMC[4][2] = "Albert King";
        
        NTPQA[2][1] = "\nWhich famous blind piano player is a Michigan native known for the hit song “Superstition”?";
        NTPCMC[2][1] = 2; 
        NTPMC[7][0] = "Ray Charles"; NTPMC[7][1] = "Stevie Wonder"; NTPMC[7][2] = "George Shearing";
        
        //ARTISTS Questions
        NTPQA[0][2] = "\nWhich famous artist painted the Mona Lisa?";
        NTPCMC[0][2] = 2;
        NTPMC[2][0] = "Donatello"; NTPMC[2][1] = "Leonardo da Vinci"; NTPMC[2][2] = "Raphael";
        
        NTPQA[1][2] = "\nWhich famous historical artist cut off their own ear?";
        NTPCMC[1][2] = 3;
        NTPMC[5][0] = "Paul Klee"; NTPMC[5][1] = "Mike Tyson"; NTPMC[5][2] = "Vincent Van Gogh";
        
        NTPQA[2][2] = "\nFamous artist Michaelangelo painted the ceiling of which historic building?";
        NTPCMC[2][2] = 3;
        NTPMC[8][0] = "Blue Mosque"; NTPMC[8][1] = "Library of Congress"; NTPMC[8][2] = "The Sistine Chapel";
        
        //SINGLE PLAYER
        if(isSinglePlayer){
            while (checkIfGameBoardDone(NTPBA)) {
                //print game board
                printGameBoard(NTPBA, "Actors", "Musicians", "Artists");
                //ask player to select question
                getPlayerQSelection(NTPQA, NTPMC, NTPBA, NTPCMC);
                //loop to ask player to select question
            }
            System.out.println(timeForFinalQuestion);
            finalQuestion(NTPFQ, NTPFA);
        }
        //MULTI PLAYER
        else{
            while (checkIfGameBoardDone(NTPBA)) {
                //print game board
                printGameBoard(NTPBA, "Actors", "Musicians", "Artists");
                //ask player to select question
                getPlayerQSelection(NTPQA, NTPMC, NTPBA, NTPCMC);
                //loop to ask player to select question
                if(isPlayer1Turn){
                    //tell that it is player two turn
                    isPlayer1Turn = false;
                }
                else{
                    isPlayer1Turn = true;
                }
            }
            System.out.println(timeForFinalQuestion);
            finalQuestion(NTPFQ, NTPFA);
        }

    } // End playNTP()
    public static void playITT(){
        //INITIALIZE VARIABLES
        //ADD QUESTIONS @TODO
        //IT Trivia Questions Array
        String[][] ITTQA= new String[3][3];
        String timeForFinalQuestion = "\nAll questions are answered, time for the final question.";
        String ITTFQ = "\nWhat term is used for the most basic level or core of an operating system, responsible for resource allocation, file management, and security?";
        String ITTFA = "Kernel";
        //IT Trivia Correct Multiple Choice
        int [][] ITTCMC = new int[3][3];
        //multiple choice
        String[][] ITTMC = new String[9][3];
        boolean[][] ITTBA = new boolean[3][3];

        //CyberSecurity	
       	ITTQA[0][0] = "\nWhich is not a form of cyber security attack?";
        ITTCMC[0][0] = 3; 
        ITTMC[0][0] = "Phishing"; ITTMC[0][1] = "Malware"; ITTMC[0][2] = "Infection";

        ITTQA[1][0] = "\nWhich of the following is an example of a phishing attack?";
        ITTCMC[1][0] = 1;
        ITTMC[3][0] = "gaggle.com asking you to log in with your gmail log in"; ITTMC[3][1] = "an invite to go fishing with friends"; ITTMC[3][2] = "everything in your spam email folder";

        ITTQA[2][0] = "\nWhich kind of risks can be minimized by using a Virtual Private Network?";
        ITTCMC[2][0] = 3;
        ITTMC[6][0] = "Create an encrypted connection between the internet and you"; ITTMC[6][1] = "Provide data privacy from your Internet Service Provider"; ITTMC[6][2] = "Both 1 and 2";
        
        //Hardware
        ITTQA[0][1] = "\nWhat was the first storage device?";
        ITTCMC[0][1] = 2; 
        ITTMC[1][0] = "Hard Drive"; ITTMC[1][1] = "Tape drive"; ITTMC[1][2] = "Thin Film Memory";

        ITTQA[1][1] = "\nWhat is known as the brains of the computer?";
        ITTCMC[1][1] = 3; 
        ITTMC[4][0] = "Motherboard"; ITTMC[4][1] = "GPU"; ITTMC[4][2] = "Processor";
        
        ITTQA[2][1] = "\nHow many bits make a Byte?";
        ITTCMC[2][1] = 1; 
        ITTMC[7][0] = "8"; ITTMC[7][1] = "32"; ITTMC[7][2] = "64";
        
        //software
        ITTQA[0][2] = "\nMac and Windows are examples of what?";
        ITTCMC[0][2] = 1;
        ITTMC[2][0] = "Operating System"; ITTMC[2][1] = "Files"; ITTMC[2][2] = "Programs";
        
        ITTQA[1][2] = "\nWhat was the name of the virual assistant in Microsoft office programs between 1997 - 2007?";
        ITTCMC[1][2] = 2;
        ITTMC[5][0] = "Alexa"; ITTMC[5][1] = "Clippy"; ITTMC[5][2] = "Cortana";
        
        ITTQA[2][2] = "\nWhat operating system has its own mascot named Tux the penguin?";
        ITTCMC[2][2] = 3;
        ITTMC[8][0] = "MacOS"; ITTMC[8][1] = "Ubuntu"; ITTMC[8][2] = "Linux";
        //SINGLE PLAYER @TODO
        if(isSinglePlayer){
            while (checkIfGameBoardDone(ITTBA)) {
                //print game board
                printGameBoard(ITTBA, "Cyber Security", "Hardware", "Software");
                //ask player to select question
                getPlayerQSelection(ITTQA, ITTMC, ITTBA, ITTCMC);
                //loop to ask player to select question
            }
            System.out.println(timeForFinalQuestion);
            finalQuestion(ITTFQ, ITTFA);
        }
        //MULTI PLAYER @TODO
        else{
            while (checkIfGameBoardDone(ITTBA)) {
                //print game board
                printGameBoard(ITTBA, "Cyber Security", "Hardware", "Software");
                //ask player to select question
                getPlayerQSelection(ITTQA, ITTMC, ITTBA, ITTCMC);
                //loop to ask player to select question
                if(isPlayer1Turn){
                    //tell that it is player two turn
                    isPlayer1Turn = false;
                }
                else{
                    isPlayer1Turn = true;
                }
            }
            System.out.println(timeForFinalQuestion);
            finalQuestion(ITTFQ, ITTFA);
        }
    }

    public static void finalQuestion(String finalQ, String finalA){
        Scanner scnr = new Scanner(System.in);
            if(isSinglePlayer){
                //ask player for points wagered & if points wagered is greater than 0 and less than player points total accept value
                int pointsWagered1 = askPointsWagered(player1points, "player 1 ");
                if(askFinalQ(finalQ, finalA)){
                    player1points = pointsWagered1*2 + player1points;
                    System.out.println("you are correct you got " + pointsWagered1);
                    System.out.println("your final score is " + player1points);
                    
                }
                else{
                    player1points = player1points - pointsWagered1;
                    System.out.println("you were incorrect, you lost " + pointsWagered1);
                    System.out.println("your final score is " + player1points);
                    
                }
            }
            else if(!isSinglePlayer){
                //get points wagered
                int pointsWagered1 = askPointsWagered(player1points, "player 1 ");
                int pointsWagered2 = askPointsWagered(player2points, "player 2 ");
                //ask final question
                System.out.print("\nPlayer 1: ");
                boolean isPlayer1Correct = askFinalQ(finalQ, finalA);
                System.out.print("\nPlayer 2: ");
                boolean isPlayer2Correct = askFinalQ(finalQ, finalA);
                //if only player 1
                checkIfCorrect(isPlayer1Correct, isPlayer2Correct, pointsWagered1, pointsWagered2);
                checkWhoWon();
                
        }
    }
    static public void checkIfCorrect(boolean isPlayer1Correct, boolean isPlayer2Correct, int pointsWagered1, int pointsWagered2){
        if(isPlayer1Correct && isPlayer2Correct){
            player1points = player1points + (pointsWagered1*2);
            player2points = player2points + (pointsWagered2*2);
            System.out.println("\nplayer 1 was correct " + (pointsWagered1*2) + " awarded");
            System.out.println("player 2 was correct " + (pointsWagered2*2) + " awarded");
        }
        else if(isPlayer1Correct && !isPlayer2Correct){
            player1points = player1points + (pointsWagered1*2);
            player2points = player2points - pointsWagered2;
            System.out.println("\nplayer 1 was correct " + (pointsWagered1*2) + " awarded");
            System.out.println("player 2 was incorrect " + pointsWagered2 + " deducted");
        }
        //if only player 2
        else if(isPlayer2Correct && !isPlayer1Correct){
            player2points = player2points + (pointsWagered2*2);
            player1points = player1points - pointsWagered1;
            System.out.println("\nplayer 2 was correct " + (pointsWagered2*2) + " awarded");
            System.out.println("player 1 was incorrect " + pointsWagered1 + " deducted");
        }
        else if(!isPlayer1Correct && !isPlayer2Correct){
            player1points = player1points - pointsWagered1;
            player2points = player2points - pointsWagered2;
            System.out.println("\nplayer 1 was incorrect " + pointsWagered1 + " deducted");
            System.out.println("player 2 was incorrect " + pointsWagered2 + " deducted");
        }
    }
    static public void checkWhoWon(){
        if(player1points>player2points){
            System.out.println("\nPlayer one had " + player1points + " points");
            System.out.println("Player two had " + player2points + " points\n");
            System.out.println("player 1 WON!\n");
        }
        else if(player2points>player1points){
            System.out.println("\nPlayer one had " + player1points + " points");
            System.out.println("Player two had " + player2points + " points\n");
            System.out.println("player 2 WON!\n");
        }
        else if(player2points == player1points){
            System.out.println("\nPlayer one had " + player1points + " points");
            System.out.println("Player two had " + player2points + " points\n");
            System.out.println("Player 1 and 2 tied\n");
        }
    }
    static public int askPointsWagered(int playerPoints, String player){
        Scanner scnr = new Scanner(System.in);
        boolean isWagerMade = false;
        int pointsWager = -1;
        if(isSinglePlayer){
            if (player1points == 0) {
                System.out.println("Player 1 has 0 points, 100 points will be wagered");
                
                return 100;
            }
            while (!isWagerMade) {
                System.out.println(player + "how many points would you like to wager?");
                try {
                    pointsWager = scnr.nextInt();
                    
                }
                catch (Exception e){
                    System.out.println("Please enter a number.");
                }
                if (pointsWager > 0 && pointsWager <= playerPoints) {
                    return pointsWager;
                } else {
                    System.out.println("Please enter value greater than 0 and less or equal to your points total.");
                }
            }
        }
        else {
            if (player1points == 0) {
                System.out.println("Player 1 has 0 points, 100 points will be wagered");
                
                return 100;
            }
            if (player2points == 0) {
                System.out.println("Player 2 has 0 points, 100 points will be wagered");
                
                return 100;
            }
            while (!isWagerMade) {
                System.out.println(player + "how many points would you like to wager?");
                try {
                    pointsWager = scnr.nextInt();
                    
                }
                catch (Exception e){
                    System.out.println("Please enter a number.");
                }
                if (pointsWager > 0 && pointsWager <= playerPoints) {
                    return pointsWager;
                } else {
                    System.out.println("Please enter value greater than 0 and less or equal to your points total.");
                }
            }
        }
        return -1;
    }
    static public boolean askFinalQ(String finalQuestion, String correctAnswer){
        Scanner scnr = new Scanner(System.in);
        System.out.println(finalQuestion);
        boolean isFinalAnswer = false;
        while(!isFinalAnswer){
            String finalAnswer = scnr.nextLine();
            System.out.println("Is " + finalAnswer + " your final answer?");
            String in = scnr.nextLine();
            if(in.equalsIgnoreCase("yes")){
                isFinalAnswer = true;
                if(finalAnswer.equalsIgnoreCase(correctAnswer)){
                    //award double points wagered
                	
                    return true;
                }
                else{
                	
                    return false;
                }
            }
            else{
                System.out.println("please re-enter your final answer");
            }
        }
        
        return false;
    }
    public static int diceRoll(){
        //get random int
        Random rand = new Random();
        boolean goesFirst = true;
        //roll till winner
        while(goesFirst){
            int playerOneRoll = rand.nextInt(6)+1;
            int playerTwoRoll = rand.nextInt(6)+1;
            //player 1 wins
            if(playerOneRoll<playerTwoRoll && playerOneRoll!=playerTwoRoll){
                return 1;
            }
            //player 2 wins
            if(playerTwoRoll<playerOneRoll && playerOneRoll!=playerTwoRoll){
                return 2;
            }
        }
        return 0;
    }
}