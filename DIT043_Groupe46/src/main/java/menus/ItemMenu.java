package menus;

import facade.Facade;
import input.Input;

public class ItemMenu {

    static final String EOL= System.lineSeparator();
    static final Facade facade= MainMenu.facade;

    public static void doItemMenu() throws Exception{

        System.out.println("Item options menu:" +EOL+
                "0. Return to Main Menu." +EOL+
                "1. Create an Item." +EOL+
                "2. Remove an Item." +EOL+
                "3. Print all registered Items." +EOL+
                "4. Buy an Item." +EOL+
                "5. Update an item’s name." +EOL+
                "6. Update an item’s price." +EOL+
                "7. Print a specific item."+EOL+
                EOL +
                "Type an option number: ");

        int menuOption= Input.readAndVerifyOptionInput(0,7);

        switch (menuOption){
            case 0: MainMenu.doMainMenu();
                break;
            case 1:
                createItem();
                doItemMenu();
            break;
            case 2:
                removeItem();
                doItemMenu();
            break;
            case 3:
                printAllItems();
                doItemMenu();
            break;
            case 4:
                buyItem();
                doItemMenu();
            break;
            case 5:
                updateItemName();
                doItemMenu();
            break;
            case 6:
                updateItemPrice();
                doItemMenu();
            break;
            case 7:
                printItem();
                doItemMenu();
            break;
        }
    }

    public static void createItem(){
        String ID = Input.readString("Enter the ID : ");
        String name = Input.readString("Enter the name : ");
        double price = Input.readDouble("Enter the price per unit : ");
        System.out.println(facade.createItem(ID,name,price));
    }

    public static void removeItem(){
        String ID= Input.readString("Enter the ID of the item that you want to remove : ");
        System.out.println(facade.removeItem(ID));
    }

    public static void buyItem(){
        String itemID = Input.readString("Enter the ID of the item that you want : ");
        int amount = Input.readInt("Enter the amount of items that you want to buy : ");
        System.out.println(String.format("%.2f",facade.buyItem(itemID,amount)));
    }

    public static void updateItemName(){
        String itemID=Input.readString("Enter the ID of the item that you want to update : ");
        String newName=Input.readString("Type the new name : ");
        System.out.println(facade.updateItemName(itemID,newName));
    }

    public static void updateItemPrice(){
        String itemID = Input.readString("Enter the ID of the item that you want to update : ");
        double newPrice=Input.readDouble("Type the new price : ");
        System.out.println(facade.updateItemPrice(itemID,newPrice));
    }

    public static void printAllItems(){
        System.out.println(facade.printAllItems());
    }

    public static void printItem(){
        String itemID = Input.readString("Enter the ID of the item that you want : ");
        facade.printItem(itemID);
    }

}
