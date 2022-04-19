package menus;

import facade.Facade;
import input.Input;

public class MainMenu {

    static final String EOL= System.lineSeparator();
    static final Facade facade= new Facade();

    public static void doMainMenu() throws Exception{

        System.out.println("Main Menu: Please choose among the options below." + EOL +
                EOL +
                "0. Close system." + EOL +
                "1. Open Item options." + EOL +
                "2. Open Review options." + EOL +
                "3. Open Transaction History options." + EOL +
                "4. Open Employee options."+EOL+
                EOL +
                "Type an option number: ");

        int menuOption = Input.readAndVerifyOptionInput(0,4);

        switch (menuOption) {
            case 0:
                System.out.println("Good bye. Hope to see you again.");
                break;
            case 1:
                ItemMenu.doItemMenu();
                break;
            case 2:
                ReviewMenu.doReviewMenu();
                break;
            case 3:
                TransactionHistoryMenu.doTransactionHistoryMenu();
                break;
            case 4:EmployeeMenu.doEmployeeMenu();
                break;
        }

    }
}