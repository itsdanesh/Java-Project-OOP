package menus;

import facade.Facade;
import input.Input;
import static facade.Facade.findItem;

public class ReviewMenu {

    static final String EOL= System.lineSeparator();
    static final Facade facade= MainMenu.facade;

    public static void doReviewMenu() throws Exception{
        System.out.println("Reviews options menu:" +EOL+
                "0. Return to Main Menu." +EOL+
                "1. Create a review for an Item." +EOL+
                "2. Print a specific review of an Item." +EOL+
                "3. Print all reviews of an Item." +EOL+
                "4. Print mean grade of an Item" +EOL+
                "5. Print all comments of an Item." +EOL+
                "6. Print all registered reviews." +EOL+
                "7. Print item(s) with most reviews." +EOL+
                "8. Print item(s) with least reviews." +EOL+
                "9. Print item(s) with best mean review grade." +EOL+
                "10. Print item(s) with worst mean review grade." +EOL+
                EOL +
                "Type an option number: " );

        int menuOption= Input.readAndVerifyOptionInput(0,10);

        switch(menuOption){
            case 0: MainMenu.doMainMenu();
                break;
            case 1:
                reviewItem();
                doReviewMenu();
            break;
            case 2:
                getPrintedItemReview();
                doReviewMenu();
            break;
            case 3:
                getPrintedReviews();
                doReviewMenu();
            break;
            case 4:
                getItemMeanGrade();
                doReviewMenu();
            break;
            case 5:
                getItemCommentsPrinted();
                doReviewMenu();
            break;
            case 6:
                System.out.println(facade.printAllReviews());
                doReviewMenu();
            break;
            case 7:
                facade.printMostReviewedItems();
                doReviewMenu();
            break;
            case 8:
                facade.printLeastReviewedItems();
                doReviewMenu();
            break;
            case 9:
                facade.printBestReviewedItems();
                doReviewMenu();
            break;
            case 10:
                facade.printWorseReviewedItems();
                doReviewMenu();
            break;
        }
    }


    public static void reviewItem(){
        String itemID = Input.readString("Enter the ID of the item that you want :");
        int reviewGrade = Input.readInt("Enter the grade that you are considering for this product (between 1-5) :");
        String reviewComment = Input.readString("Type your comment :");
        if (reviewComment.isEmpty() ){
            System.out.println(facade.reviewItem(itemID,reviewGrade));
        }else { System.out.println(facade.reviewItem(itemID,reviewComment,reviewGrade)); }
    }

    public static void getPrintedItemReview(){
        String itemID = Input.readString("Enter the ID of the item that you want : ");
        int reviewNumber = Input.readInt("Enter the number of the review that you want to see :");
        System.out.println(facade.getPrintedItemReview(itemID,reviewNumber));
    }

    public static void getPrintedReviews(){
        String itemID = Input.readString("Enter the ID of the item that you want : ");
        System.out.println(facade.getPrintedReviews(itemID));
    }

    public static void getItemMeanGrade(){
        String itemID = Input.readString("Enter the ID of the item that you want : ");
        if (facade.containsItem(itemID)){
            if (facade.getNumberOfReviews(itemID)>0){
                System.out.println(facade.getItemMeanGrade(itemID));
            }else {
                System.out.println("Item " + findItem(itemID, facade.items).getName() + " has not been reviewed yet.");
            }
        }else {
            System.out.println("Item " + itemID + " was not registered yet.");
        }
    }

    public static void getItemCommentsPrinted(){
        String itemID = Input.readString("Enter the ID of the item that you want : ");
        System.out.println(facade.getItemCommentsPrinted(itemID));
    }

}
