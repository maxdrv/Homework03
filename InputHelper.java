import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHelper {

    private final static String DEMO_FILE = "file.txt";

    public void printMenu() {
        System.out.println("+---------------------------+");
        System.out.println("|Please make a selection:   |");
        System.out.println("|1) Use demonstration file  |");
        System.out.println("|2) Use your own file       |");
        System.out.println("|0) Exit                    |");
        System.out.println("+---------------------------+");

    }
    public void Menu(){
        boolean exit = false;
        while(!exit){
            printMenu();
            try{
                int menuItem = getUserInput();
                if(menuItem >= 0 && menuItem <= 2){

                    switch (menuItem) {
                        case 0: exit = true; break;
                        case 1: Logic.go(DEMO_FILE); break;
                        case 2: Logic.go(getFileName()); break;
                    }

                } else{
                    System.out.println("there is no such menu item: " + menuItem + ". Use 1, 2, 0 numbers only.");
                }
            } catch(InputMismatchException ex) {
                System.out.println("InputMismatchException. Use 1, 2, 0 numbers only.");
            }
        }
    }

    private int getUserInput(){
        Scanner input = new Scanner(System.in);
        System.out.print("Make your selection: ");
        int number = input.nextInt();
        return number;
    }

    public String getFileName() {
        System.out.println("Enter the full path of the file:");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        return name;
    }

}
