import java.util.Scanner;

public class SafeInput {
    /**
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return a String response that is not zero length
     */
    public static String getNonZeroLenString(Scanner pipe, String prompt)
    {
        String retString = ""; // Set this to zero length. Loop runs until it isn't
        do {
            System.out.print("\n" + prompt + ": "); // show prompt add space
            retString = pipe.nextLine();
        }while(retString.length() == 0);
        return retString;
    }

    /**
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return a String response that is not zero length
     */
    public static int getInt(Scanner pipe, String prompt){
        int retInt = 0;
        String badInput = "";
        boolean done = false;
        do {
            System.out.print("\n" + prompt + ": ");
            if(pipe.hasNextInt()){
                retInt = pipe.nextInt();
                pipe.nextLine();
                done = true;
            }
            else{
                badInput = pipe.nextLine();
                System.out.println("Your input (" + badInput + ") is invalid. Please input a valid integer.");
            }
        }while(!done);
        return retInt;
    }
    /**
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return a String response that is not zero length
     */
    public static double getDouble(Scanner pipe, String prompt){
        double retDouble = 0;
        String badInput = "";
        boolean done = false;
        do {
            System.out.print("\n" + prompt + ": ");
            if(pipe.hasNextDouble()){
                retDouble = pipe.nextDouble();
                pipe.nextLine();
                done = true;
            }
            else{
                badInput = pipe.nextLine();
                System.out.println("Your input (" + badInput + ") is invalid. Please input a valid double.");
            }
        }while(!done);
        return retDouble;
    }

    /**
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @param low low value for the input range
     * @param high high value for the input range
     * @return a String response that is not zero length
     */
    public static int getRangedInt(Scanner pipe, String prompt, int low, int high){
        if (low > high){
            System.out.println("Input Bounds are invalid.");
            return 0;
        }
        else{
            int retInt = 0;
            String badInput = "";
            boolean done = false;
            do {
                System.out.print("\n" + prompt + " from " + low + " to " + high + ": ");
                if(pipe.hasNextInt()){
                    retInt = pipe.nextInt();
                    if(retInt >= low && retInt <= high){
                        done = true;
                    }
                    else{
                        System.out.println("Your input of " + retInt + " is out of range.");
                    }
                    pipe.nextLine();
                }
                else{
                    badInput = pipe.nextLine();
                    System.out.println("Your input (" + badInput + ") is invalid. Please input a valid integer.");
                }
            }while(!done);
            return retInt;
        }
    }

    /**
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @param low low value for the input range
     * @param high high value for the input range
     * @return a String response that is not zero length
     */
    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high){
        if (low > high){
            System.out.println("Input Bounds are invalid.");
            return 0;
        }
        else{
            double retDouble = 0;
            String badInput = "";
            boolean done = false;
            do {
                System.out.print("\n" + prompt + " from " + low + " to " + high + ": ");
                if(pipe.hasNextDouble()){
                    retDouble = pipe.nextDouble();
                    if(retDouble >= low && retDouble <= high){
                        done = true;
                    }
                    else{
                        System.out.println("Your input of " + retDouble + " is out of range.");
                    }
                    pipe.nextLine();
                }
                else{
                    badInput = pipe.nextLine();
                    System.out.println("Your input (" + badInput + ") is invalid. Please input a valid double.");
                }
            }while(!done);
            return retDouble;
        }
    }

    /**
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return a String response that is not zero length
     */
    public static boolean getYNConfirm(Scanner pipe, String prompt){
        boolean done = false;
        String userInput = "";
        do {
            System.out.print("\n" + prompt + ": ");
            userInput = pipe.nextLine();
            if (userInput.equalsIgnoreCase("y") || (userInput.equalsIgnoreCase("n"))){
                done = true;
            }
            else{
                System.out.println("Your input (" + userInput + ") is invalid.");
            }
        }while(!done);
        return userInput.equalsIgnoreCase("y");
    }

    /**
     * @param pipe a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @param regEx regEx pattern
     * @return a String response that is not zero length
     */
    public static String getRegExString(Scanner pipe, String prompt, String regEx){
        String retString = "";
        boolean done = false;
        do {
            System.out.print("\n" + prompt + ": ");
            retString = pipe.nextLine();
            if(retString.matches(regEx)){
                done = true;
            }
            else{
                System.out.println("Your input (" + retString + ") is invalid.");
            }
        }while(!done);
        return retString;
    }

    /**
     * @param msg Header message
     * @return a String response that is not zero length
     */
    public static void prettyHeader(String msg){
        int blankSpace = 54 - msg.length();
        int blankRight = blankSpace / 2;
        int blankLeft = blankSpace - blankRight;
        blankRight = msg.length() + blankRight;
        for (int i = 0; i < 60; i++){
            System.out.print("*");
        }
        System.out.print("\n");
        int j = 0;
        for (int i = 0; i < 60; i++){
            if (i < 3 || i > 56){
                System.out.print("*");
            }
            else if ((i >= 3 && i < blankLeft) || (i >= blankRight && i <= 56)){
                System.out.print(" ");
            }
            else{
                System.out.print(msg);
                i+= msg.length()-1;
            }
        }
        System.out.print("\n");
        for (int i = 0; i < 60; i++){
            System.out.print("*");
        }
        System.out.print("\n");
    }
}
