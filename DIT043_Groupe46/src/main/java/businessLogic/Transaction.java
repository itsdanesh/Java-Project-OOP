package businessLogic;

import static facade.Facade.decimal;

public class Transaction {

    private final String ID;
    private final int amount;
    private final double purchasePrice;

    public Transaction(String ID,int amount,double purchasePrice){
        this.ID=ID;
        this.amount=amount;
        this.purchasePrice=purchasePrice;
    }

    public int getAmount() { return this.amount; }
    public double getPurchasePrice(){ return this.purchasePrice; }
    public String getID(){ return this.ID;}

    public String toString(){
        return this.ID+": "+this.amount+" item(s). "+String.format("%.2f",decimal(this.purchasePrice,2))+" SEK";
    }
}
