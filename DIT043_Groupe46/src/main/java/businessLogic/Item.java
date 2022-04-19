package businessLogic;

import java.util.ArrayList;
import java.util.Objects;
import static facade.Facade.decimal;

public class Item {

    private final String ID;
    private String name;
    private double price;
    private ArrayList<Review> reviews;


    public Item(String ID, String name, double price) {
        this.ID=ID;
        this.name=name;
        this.price=price;
        reviews= new ArrayList<>();
    }

    public String toString(){
        String value= getID()+": "+getName()+". "+String.format("%.2f", decimal(getPrice(),2))+" SEK";
        return value;
    }

    public String getID() {return this.ID;}
    public String getName() {return this.name;}
    public void setName(String name) {this.name = name;}
    public double getPrice() {return this.price;}
    public void setPrice(double price) {this.price = price;}
    public void addReview(Review review){reviews.add(review);}
    public int reviewSize(){
        return reviews.size();
    }
    public Review getReview(int index){return reviews.get(index);}

    public boolean equals(Object anotherObject) {
        if (anotherObject==this){
            return true;
        }if (anotherObject==null){
            return false;
        }if (anotherObject.getClass()==Item.class){
            Item anotherItem= (Item) anotherObject;
            return getID().equals(anotherItem.getID());
        }else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(ID);
    }

}
