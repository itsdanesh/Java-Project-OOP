package menus;

import facade.Facade;
import input.Input;

public class TransactionHistoryMenu {

    static final String EOL= System.lineSeparator();
    static final Facade facade= MainMenu.facade;

    public static void doTransactionHistoryMenu() throws Exception{

        System.out.println("Transaction History options menu:" +EOL+
                "0. Return to Main Menu." +EOL+
                "1. Print total profit from all item purchases" +EOL+
                "2. Print total units sold from all item purchases" +EOL+
                "3. Print the total number of item transactions made." +EOL+
                "4. Print all transactions made." +EOL+
                "5. Print the total profit of a specific item." +EOL+
                "6. Print the number of units sold of a specific item." +EOL+
                "7. Print all transactions of a specific item." +EOL+
                "8. Print item with highest profit." +EOL+
                EOL +
                "Type an option number: " );

        int menuOption= Input.readAndVerifyOptionInput(0,8);

        switch(menuOption){
            case 0: MainMenu.doMainMenu();
                break;
            case 1:
                facade.getTotalProfit();
                doTransactionHistoryMenu();
            break;
            case 2:
                facade.getTotalUnitsSold();
                doTransactionHistoryMenu();
            break;
            case 3:
                facade.getTotalTransactions();
                doTransactionHistoryMenu();
            break;
            case 4:
                facade.printAllTransactions();
                doTransactionHistoryMenu();
            break;
            case 5:
                getProfit();
                doTransactionHistoryMenu();
            break;
            case 6:
                getUnitsSolds();
                doTransactionHistoryMenu();
            break;
            case 7:
                printItemTransactions();
                doTransactionHistoryMenu();
            break;
            case 8:
                facade.printMostProfitableItems();
                doTransactionHistoryMenu();
            break;
        }
    }

    public static void getProfit(){
        String itemID= Input.readString("Enter the ID of the item that you want : ");
        System.out.println(facade.getProfit(itemID));
    }

    public static void getUnitsSolds(){
        String itemID= Input.readString("Enter the ID of the item that you want : ");
        System.out.println(facade.getUnitsSolds(itemID));
    }

    public static void printItemTransactions(){
        String itemID= Input.readString("Enter the ID of the item that you want : ");
        System.out.println(facade.printItemTransactions(itemID));
    }

}