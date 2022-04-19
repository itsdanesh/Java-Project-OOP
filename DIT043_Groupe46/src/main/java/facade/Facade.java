package facade;

import businessLogic.*;
import exceptions.*;
import java.util.*;



public class Facade {

    // This class only has the skeleton of the methods used by the test.
    // You must fill in this class with your own code. You can (and should) create more classes
    // that implement the functionalities listed in the Facade and in the Test Cases.


    static final String EOL= System.lineSeparator();

    public static Item findItem(String IdOfItem, ArrayList<Item> items){
        for (Item currentItem : items){
            if (currentItem.getID().equals(IdOfItem)) {
                return currentItem;
            }
        }return null;
    }

    public static Employee findEmployee(String IDofEmployee,ArrayList<Employee> employees){
        for (Employee currentEmp : employees) {
           if (currentEmp.getID()==IDofEmployee){
               return currentEmp;
           }
        }return null;
    }

    public static double decimal(double number, int decimal){
        double value1=number*Math.pow(10,decimal);
        int value2=(int) value1;
        double value3=(double)value2/Math.pow(10,decimal);
        return value3;
    }

    public ArrayList<Item> items;
    public ArrayList<Transaction> transactions;
    static ArrayList<Employee> employees;

    public Facade(){
        this.items= new ArrayList<>();
        this.transactions= new ArrayList<>();
        this.employees= new ArrayList<>();
    }


    public double meanGrade(){
        return 0;
    }

    public String createItem(String itemID, String itemName, double unitPrice){
        if (!itemID.isBlank()&&!itemName.isBlank()&&unitPrice>0){
            if (!containsItem(itemID)){
            items.add(new Item(itemID,itemName,unitPrice));
            return "Item "+itemID+" was registered successfully.";}
            return "The item with this ID already exists.";
        }return "Invalid data for item.";
    }

    public String printItem(String itemID) {
       if (containsItem(itemID)){
           return findItem(itemID,items).toString();
       }return "Item "+itemID+" was not registered yet.";
    }

    public String removeItem(String itemID) {
       if (containsItem(itemID)&&items.remove(findItem(itemID,items))){
           items.remove(findItem(itemID,items));
           return "Item " + itemID + " was successfully removed.";
       }return "Item " + itemID + " could not be removed.";
    }

    public boolean containsItem(String itemID) {
        for (Item currentItem : items){
            if (currentItem.getID().equals(itemID)) {
                return true;
            }
        }return false;
    }

    public double buyItem(String itemID, int amount) {
        if (containsItem(itemID) && amount>0){
            double payAmount;
            if (amount > 4) {
                payAmount = (findItem(itemID, items).getPrice()*0.7) * (amount-4)
                        +findItem(itemID, items).getPrice()*4;
                transactions.add(new Transaction(itemID,amount,payAmount));
                return decimal(payAmount,2);
            } else if (amount< 4) {
                payAmount = (findItem(itemID, items).getPrice() * amount);
                transactions.add(new Transaction(itemID,amount,payAmount));
                return payAmount;
            }
        }return -1;
    }

    public String reviewItem(String itemID, String reviewComment, int reviewGrade) {
        if (containsItem(itemID)){
            int highestGrade = 5;
            int lowestGrade = 1;
            if (reviewGrade >= lowestGrade && reviewGrade <= highestGrade) {
                findItem(itemID, items).addReview(new Review(reviewComment,reviewGrade));
                return "Your item review was registered successfully.";
            } else { return "Grade values must be between 1 and 5."; }
        }return "Item " + itemID + " was not registered yet.";
    }

    public String reviewItem(String itemID, int reviewGrade) {
       return reviewItem(itemID,"",reviewGrade);
    }

    public String getItemCommentsPrinted(String itemID) {
        return getItemComments(itemID).toString();
    }

    public List<String> getItemComments(String itemID) {
        ArrayList<String> comments=new ArrayList<>();
        for (int i=0; i<findItem(itemID,items).reviewSize();i++){
            if (!findItem(itemID,items).getReview(i).getComment().isEmpty()){
                comments.add(findItem(itemID,items).getReview(i).getComment());}
        }return comments;
    }

    public double getItemMeanGrade(String itemID) {
        double sum=0;
        for (int i=0;i<findItem(itemID,items).reviewSize();i++){
            sum += findItem(itemID,items).getReview(i).getGrade();
        }return decimal(sum/(double) findItem(itemID,items).reviewSize(),1);
    }

    public int getNumberOfReviews(String itemID) {
        if (containsItem(itemID)){
            return findItem(itemID,items).reviewSize();
        }return 0;
    }

    public int getNumberOfAllReviews(){
        int numberOfAllReviews=0;
        for (Item currentItem : items){
            numberOfAllReviews+=getNumberOfReviews(currentItem.getID());
        }return numberOfAllReviews;
    }

    public String getPrintedItemReview(String itemID, int reviewNumber) {
        if (containsItem(itemID)){
            if (getNumberOfReviews(itemID)>0) {
                if (reviewNumber>0 && reviewNumber<=getNumberOfReviews(itemID)){
                if (findItem(itemID,items).getReview(reviewNumber-1)!=null) {
                    String answer = findItem(itemID, items).getReview(reviewNumber-1).toString();
                    return answer;
                }else { return "Invalid review number. Choose between 1 and " + getNumberOfReviews(itemID) +"."; }
                } else { return "Invalid review number. Choose between 1 and " + getNumberOfReviews(itemID) +"."; }
            } else { return "Item " + findItem(itemID, items).getName() + " has not been reviewed yet."; }
        }return "Item " + itemID + " not found.";
    }

    public String getPrintedReviews(String itemID) {
        if (containsItem(itemID)) {
            if (getNumberOfReviews(itemID) > 0) {
                String value = "Review(s) for " + findItem(itemID, items).getID() + ": " + findItem(itemID, items).getName()
                        + ". " +String.format("%.2f",decimal(findItem(itemID, items).getPrice(),2))+ " SEK" + EOL;
                for (int i=0;i<findItem(itemID,items).reviewSize();i++) {
                        value += findItem(itemID, items).getReview(i) + EOL;
                    }
                return value;
            } else { return "Review(s) for " + findItem(itemID, items).getID() + ": "
                        + findItem(itemID, items).getName() + ". " +String.format("%.2f",decimal(findItem(itemID, items).getPrice(),2))+ " SEK"
                        + EOL + "The item " + findItem(itemID, items).getName() + " has not been reviewed yet.";
            }
        }return "Item " + itemID + " was not registered yet.";
    }

    public String printMostReviewedItems() {
        if (!items.isEmpty()){
            if (getNumberOfAllReviews()!=0){
                String result="Most reviews: "+findItem(getMostReviewedItems().get(0),items).reviewSize()+" review(s) each."+EOL;
                for (String currentItem : getMostReviewedItems()){
                    result+= findItem(currentItem, items).toString()+EOL;
                }return result;
            }return "No items were reviewed yet.";
        }return "No items registered yet.";
    }

    public List<String> getMostReviewedItems() {
        ArrayList<String> mostReview = new ArrayList<>();
        if (getNumberOfAllReviews()!=0 && items.size()!=0) {
            int highSize = 0;
            for (Item currentItem : items) {
                if (currentItem.reviewSize() >= highSize && currentItem.reviewSize() != 0) {
                    highSize = currentItem.reviewSize();
                }
            }
            for (Item currentItem : items) {
                if (currentItem.reviewSize() == highSize && currentItem.reviewSize() != 0) {
                    mostReview.add(currentItem.getID());
                }
            }return mostReview;
        }return mostReview;
    }

    public List<String> getLeastReviewedItems() {
        ArrayList<String> leastReview = new ArrayList<>();
        if (getNumberOfAllReviews()!=0 && items.size()!=0) {
            int lowSize = items.get(0).reviewSize();
            for (Item currentItem : items) {
                if (currentItem.reviewSize() <= lowSize && currentItem.reviewSize() != 0) {
                    lowSize = currentItem.reviewSize();
                }
            }
            for (Item currentItem : items) {
                if (currentItem.reviewSize() == lowSize && currentItem.reviewSize() != 0) {
                    leastReview.add(currentItem.getID());
                }
            }return leastReview;
        }return leastReview;
    }

    public String printLeastReviewedItems() {
        if (!items.isEmpty()){
            int numberOfAllReviews=0;
            for (Item currentItem : items){
                numberOfAllReviews+=getNumberOfReviews(currentItem.getID());
            }
            if (numberOfAllReviews!=0){
                String result="Least reviews: "+findItem(getLeastReviewedItems().get(0),items).reviewSize()+" review(s) each."+EOL;
                for (String currentItem : getLeastReviewedItems()){
                    result+= findItem(currentItem, items).toString()+EOL;
                }return result;
            }return "No items were reviewed yet.";
        }return "No items registered yet.";
    }

    public boolean containTransactionID(String ID){
        for (Transaction currentTransac : transactions){
            if (currentTransac.getID().equals(ID)){
                return true;
            }
        }
        return false;
    }

    public double getTotalProfit() {
        if (!transactions.isEmpty()){
            int numOfAllTransac = transactions.size();
            if (numOfAllTransac > 0) {
                double result = 0;
                for (Item currentItem : items) {
                    result += getProfit(currentItem.getID());
                }return decimal(result,2);
            }return 0;
        }return 0;
    }

    public String printItemTransactions(String itemID) {
        if (containsItem(itemID)){
            if (containTransactionID(itemID)){
                String result="Transactions for item: "+findItem(itemID, items).getID()+": "+findItem(itemID, items).getName()+". "+
                        findItem(itemID, items).getPrice()+" SEK"+EOL;
                for (Transaction currentTransac : transactions){
                    if (currentTransac.getID().equals(itemID)) {
                        result += currentTransac.toString() + EOL;
                    }
                }return result;
            }return "Transactions for item: "+findItem(itemID, items).getID()+
                    ": "+findItem(itemID, items).getName()+". "+String.format("%.2f", decimal(findItem(itemID,items).getPrice(),2))+
                    " SEK" +EOL
                    +"No transactions have been registered for item "+findItem(itemID, items).getID()+" yet.";
        }return "Item "+itemID+" was not registered yet.";
    }

    public int getTotalUnitsSold() {
        int numOfAllTransac = transactions.size();
        if (numOfAllTransac > 0) {
            int result = 0;
            for (Transaction currentTransac : transactions){
                result += currentTransac.getAmount();
            }return result;
        }return 0;
    }

    public int getTotalTransactions() {
        return transactions.size();
    }

    public double getProfit(String itemID) {
        if (containTransactionID(itemID)){
            double result =0;
            for (Transaction currentTransac : transactions){
                if (currentTransac.getID().equals(itemID)) {
                    result += currentTransac.getPurchasePrice();
                }
            }return decimal(result,2);
        }return 0;
    }

    public int getUnitsSolds(String itemID) {
        if (containTransactionID(itemID)){
            int result =0;
            for (Transaction currentTransac : transactions){
                if (currentTransac.getID().equals(itemID)) {
                    result += currentTransac.getAmount();
                }
            }
            return result;
        }return 0;
    }

    public String printAllTransactions() {
        if (!items.isEmpty()){
            int numOfAllTransac = transactions.size();
            if (numOfAllTransac>0){
                String result= "All purchases made: " +EOL+
                        "Total profit: "+String.format("%.2f", decimal(getTotalProfit(),2))+" SEK" +EOL+
                        "Total items sold: "+getTotalUnitsSold()+" units" +EOL+
                        "Total purchases made: "+getTotalTransactions()+" transactions"+EOL+
                        "------------------------------------"+EOL;
                for (Transaction currentTransac : transactions){
                    result += currentTransac.toString()+EOL;
                }
                result= result+"------------------------------------"+EOL;
                return result;
            }return "All purchases made: " +EOL+
                    "Total profit: 0.00 SEK" +EOL+
                    "Total items sold: 0 units" +EOL+
                    "Total purchases made: 0 transactions" +EOL+
                    "------------------------------------" +EOL+
                    "------------------------------------"+EOL;
        }return "All purchases made: " +EOL+
                "Total profit: 0.00 SEK" +EOL+
                "Total items sold: 0 units" +EOL+
                "Total purchases made: 0 transactions" +EOL+
                "------------------------------------" +EOL+
                "------------------------------------"+EOL;
    }

    public String printWorseReviewedItems() {
        if (!items.isEmpty()){
            if (!getWorseReviewedItems().isEmpty()){
                String result="Items with worst mean reviews:" +EOL+
                        "Grade: "+ getItemMeanGrade(getWorseReviewedItems().get(0))+EOL;
                for (String currentItem : getWorseReviewedItems()){
                    result+= findItem(currentItem,items).toString()+EOL;
                }return result;
            }return "No items were reviewed yet.";
        }
        return "No items registered yet.";
    }

    public String printBestReviewedItems() {
        if (!items.isEmpty()){
            if (!getBestReviewedItems().isEmpty()){
                String result="Items with best mean reviews:" +EOL+
                        "Grade: "+ getItemMeanGrade(getBestReviewedItems().get(0))+EOL;
                for (String currentItem : getBestReviewedItems()){
                    result+= findItem(currentItem,items).toString()+EOL;
                }return result;
            }return "No items were reviewed yet.";
        }return "No items registered yet.";
    }

    public List<String> getWorseReviewedItems() {
        ArrayList<String> worseReviewed = new ArrayList<>();
        if (getNumberOfAllReviews()!=0 && items.size()!=0) {
            double worstG = getItemMeanGrade(items.get(0).getID());
            for (Item currentItem : items) {
                if (getItemMeanGrade(currentItem.getID()) <= worstG && getItemMeanGrade(currentItem.getID()) != 0) {
                    worstG = getItemMeanGrade(currentItem.getID());
                }
            }
            for (Item currentItem : items) {
                if (getItemMeanGrade(currentItem.getID()) == worstG && getItemMeanGrade(currentItem.getID()) != 0) {
                    worseReviewed.add(currentItem.getID());
                }
            }
            return worseReviewed;
        }return worseReviewed;
    }

    public List<String> getBestReviewedItems() {
        ArrayList<String> bestReviewed = new ArrayList<>();
        if (getNumberOfAllReviews()!=0 && items.size()!=0) {
            double bestGrade = 0;
            for (Item currentItem : items) {
                if (getItemMeanGrade(currentItem.getID()) >= bestGrade && getItemMeanGrade(currentItem.getID()) != 0) {
                    bestGrade = getItemMeanGrade(currentItem.getID());
                }
            }
            for (Item currentItem : items) {
                if (getItemMeanGrade(currentItem.getID()) == bestGrade && getItemMeanGrade(currentItem.getID()) != 0) {
                    bestReviewed.add(currentItem.getID());
                }
            }
            return bestReviewed;
        }return bestReviewed;
    }

    public String printAllReviews() {
        if (!items.isEmpty()){
        String result="All registered reviews:"+EOL+"------------------------------------"+EOL;
        for (Item currentItem : items) {
            if (getNumberOfReviews(currentItem.getID()) > 0) {
                result += getPrintedReviews(currentItem.getID()) + "------------------------------------" + EOL;
            }
        }
        if (!result.equals("All registered reviews:"+EOL+"------------------------------------"+EOL)){
            return result;
        }
        return "No items were reviewed yet.";
        }
        return "No items registered yet.";
    }

    public String updateItemName(String itemID, String newName) {
        if (containsItem(itemID)){
            if (!newName.isEmpty()){
                findItem(itemID, items).setName(newName);
                return "Item "+itemID+" was updated successfully.";
            }else { return "Invalid data for item."; }
        }
        return "Item "+itemID+" was not registered yet.";
    }

    public String updateItemPrice(String itemID, double newPrice) {
        if (containsItem(itemID)){
            if (newPrice>0){
                findItem(itemID, items).setPrice(newPrice);
                return "Item "+itemID+" was updated successfully.";
            }else { return "Invalid data for item."; }
        }
        return "Item "+itemID+" was not registered yet.";
    }

    public String printAllItems() {
        if(!items.isEmpty()){
             String result = "All registered items:"+EOL;
            for (Item currentItem : items) {
                result += currentItem.toString()+EOL;
            }
            return result;
        }
        return "No items registered yet.";
    }

    public String printMostProfitableItems() {
        if (!items.isEmpty()) {
            int numOfAllTransac = transactions.size();
            if (numOfAllTransac>0) {
                double highPurchase = 0;
                for (Item currentItem : items) {
                    if (getProfit(currentItem.getID()) >= highPurchase) {
                        highPurchase = getProfit(currentItem.getID());
                    }
                }
                String result = "Most profitable items: " +EOL + "Total profit: " + String.format("%.2f", decimal(highPurchase,2)) + " SEK" +EOL;
                for (Item currentItem : items) {
                    if (getProfit(currentItem.getID()) == highPurchase) {
                        result += currentItem.toString()+EOL;
                    }
                }return result;
            }return "No items were bought yet.";
        }return "No items registered yet.";
    }

    public boolean containsEmployee(String ID){
        for (Employee currentEmp : employees){
            if (currentEmp.getID().equals(ID)){
                return true;
            }
        }return false;
    }

    public String createEmployee(String employeeID, String employeeName, double grossSalary) throws Exception {
        if (!containsEmployee(employeeID)) {
            employees.add(new Employee(employeeID, employeeName, grossSalary));
            return "Employee "+employeeID+" was registered successfully.";
        }return "An employee with this ID already exists.";
    }

    public String printEmployee(String employeeID) throws Exception {
        if (!containsEmployee(employeeID)) {
            throw new EmployeeNotFoundException(employeeID);
        }else if (findEmployee(employeeID, employees) == null) {
                throw new EmployeeNotFoundException(employeeID);
            } else {
                return findEmployee(employeeID, employees).toString();
            }
        }


    public String createEmployee(String employeeID, String employeeName, double grossSalary, String degree) throws Exception {
        if (!containsEmployee(employeeID)) {
            employees.add(new Manager(employeeID, employeeName, grossSalary, degree));
            return "Employee " + employeeID + " was registered successfully.";
        }return "An employee with this ID already exists.";
    }

    public String createEmployee(String employeeID, String employeeName, double grossSalary, int gpa) throws Exception {
        if (!containsEmployee(employeeID)) {
            employees.add(new Intern(employeeID, employeeName, grossSalary, gpa));
            return "Employee " + employeeID + " was registered successfully.";
        }return "An employee with this ID already exists.";
    }

    public double getNetSalary(String employeeID) throws Exception {
        if (!containsEmployee(employeeID)) {
            throw new EmployeeNotFoundException(employeeID);
        }else if (findEmployee(employeeID, employees) == null) {
                throw new EmployeeNotFoundException(employeeID);
        }else {
            return findEmployee(employeeID, employees).getNetSalary();
        }
    }

    public String createEmployee(String employeeID, String employeeName, double grossSalary, String degree, String dept) throws Exception {
        if (!containsEmployee(employeeID)) {
            employees.add(new Director(employeeID, employeeName, grossSalary, degree, dept));
            return "Employee " + employeeID + " was registered successfully.";
        }return "An employee with this ID already exists.";
    }

    public String removeEmployee(String empID) throws Exception {
        if (!containsEmployee(empID)) {
            throw new EmployeeNotFoundException(empID);
        }else if (findEmployee(empID, employees) == null) {
            throw new EmployeeNotFoundException(empID);
        }else {
            employees.remove(findEmployee(empID, employees));
            return "Employee "+empID+" was successfully removed.";
        }
    }

    public String printAllEmployees() throws Exception {
        String result="";
        if (employees.size()<=0) {
            throw new EmptyEmployeeListException();
        } else {
            result = "All registered employees:" + EOL;
            for (Employee currentEmp : employees) {
                result += currentEmp.toString() + EOL;
            }
        }
        return result;
    }

    public double getTotalNetSalary() throws Exception {
        double result=0.0;
        if (employees.size()<=0){
            throw new EmptyEmployeeListException();
        }else {
            for (Employee currentEmp : employees) {
                result += getNetSalary(currentEmp.getID());
            }
            return decimal(result,2);
        }
    }

    //https://dzone.com/articles/java-8-comparator-how-to-sort-a-list
    public String printSortedEmployees() throws Exception {
        if (employees.size()<=0) {
            throw new EmptyEmployeeListException();}
        else {
            String result = "Employees sorted by gross salary (ascending order):" + EOL;
            ArrayList<Employee> sortingEmployees =new ArrayList<>();
            sortingEmployees=employees;
            sortingEmployees.sort(Comparator.comparing(Employee::getGrossSalary));
            for (int i = 0; i < sortingEmployees.size(); i++) {
                result += sortingEmployees.get(i).toString() + EOL;
            }
            return result;
        }
    }

    public String updateEmployeeName(String empID, String newName) throws Exception {
        if (!containsEmployee(empID)) {
            throw new EmployeeNotFoundException(empID);
        }else if (findEmployee(empID, employees) == null) {
            throw new EmployeeNotFoundException(empID);
        }else if (newName.isBlank()) {
            throw new InvalidEmployeeDataException("Name cannot be blank.");
        }else {
            findEmployee(empID, employees).setName(newName);
            return "Employee " + empID + " was updated successfully";
        }
    }

    public String updateInternGPA(String empID, int newGPA) throws Exception {
        if (!containsEmployee(empID)) {
            throw new EmployeeNotFoundException(empID);
        }else if (findEmployee(empID, employees) == null) {
            throw new EmployeeNotFoundException(empID);
        }else if (newGPA<0 || newGPA>10) {
            throw new InvalidEmployeeDataException(newGPA+" outside range. Must be between 0-10.");
        }else {
            Intern currentIntern = (Intern) findEmployee(empID, employees);
            currentIntern.setGPA(newGPA);
            return "Employee " + empID + " was updated successfully";
        }
    }

    public String updateManagerDegree(String empID, String newDegree) throws Exception {
        if (!containsEmployee(empID)) {
            throw new EmployeeNotFoundException(empID);
        }else if (findEmployee(empID, employees) == null) {
            throw new EmployeeNotFoundException(empID);
        }else if (!newDegree.equals("BSc") && !newDegree.equals("MSc") && !newDegree.equals("PhD")) {
            throw new InvalidEmployeeDataException("Degree must be one of the options: BSc, MSc or PhD.");
        }else {
            Manager currentManager = (Manager) findEmployee(empID, employees);
            currentManager.setDegree(newDegree);
            return "Employee " + empID + " was updated successfully";
        }
    }

    public String updateDirectorDept(String empID, String newDepartment) throws Exception {
        if (!containsEmployee(empID)) {
            throw new EmployeeNotFoundException(empID);
        }else if (findEmployee(empID, employees) == null) {
            throw new EmployeeNotFoundException(empID);
        }else if (!newDepartment.equals("Business") && !newDepartment.equals("Human Resources") && !newDepartment.equals("Technical")) {
            throw new InvalidEmployeeDataException("Department must be one of the options: Business, Human Resources or Technical.");
        }else {
            Director currentDirector = (Director) findEmployee(empID, employees);
            currentDirector.setDepartment(newDepartment);
            return "Employee " + empID + " was updated successfully";
        }
    }

    public String updateGrossSalary(String empID, double newSalary) throws Exception {
        if (!containsEmployee(empID)) {
            throw new EmployeeNotFoundException(empID);
        }else if (findEmployee(empID, employees) == null) {
            throw new EmployeeNotFoundException(empID);
        }else if (newSalary<=0) {
            throw new InvalidEmployeeDataException("Salary must be greater than zero.");
        }else {
            findEmployee(empID, employees).setGrossSalary(newSalary);
            return "Employee " + empID + " was updated successfully";
        }
    }

    public Map<String, Integer> mapEachDegree() throws Exception {
        if (employees.isEmpty()) {
            throw new EmptyEmployeeListException();
        } else {
            int BScNum = 0;
            int MScNum = 0;
            int PhDNum = 0;
            for (Employee currentEmp : employees) {
                if (currentEmp instanceof Manager) {
                    switch (((Manager) currentEmp).getDegree()) {
                        case "BSc":
                            BScNum++;
                            break;
                        case "MSc":
                            MScNum++;
                            break;
                        case "PhD":
                            PhDNum++;
                            break; }
                }else if (currentEmp instanceof Director) {
                    switch (((Director) currentEmp).getDegree()) {
                        case "BSc":
                            BScNum++;
                            break;
                        case "MSc":
                            MScNum++;
                            break;
                        case "PhD":
                            PhDNum++;
                            break; }
                }
            }
            HashMap<String,Integer> mapDegrees= new HashMap<String, Integer>();
            if (BScNum>0){
                mapDegrees.put("BSc",BScNum);
            }
            if (MScNum>0){
                mapDegrees.put("MSc",MScNum);
            }
            if (PhDNum>0){
                mapDegrees.put("PhD",PhDNum);
            }
            return mapDegrees;
        }
    }

    public String promoteToManager(String empID, String degree) throws Exception {
        if (!containsEmployee(empID)){
            throw new EmployeeNotFoundException(empID);
        }else if (findEmployee(empID,employees) == null){
            throw new EmployeeNotFoundException(empID);
        }else {
            String theName=findEmployee(empID,employees).getName();
            double theGrossSalary=findEmployee(empID,employees).getRawSalary();
            employees.remove(findEmployee(empID,employees));
            employees.add(new Manager(empID,theName,theGrossSalary,degree));
            return empID+" promoted successfully to Manager.";
        }
    }

    public String promoteToDirector(String empID, String degree, String department) throws Exception {
        if (!containsEmployee(empID)){
            throw new EmployeeNotFoundException(empID);
        }else if (findEmployee(empID,employees) == null){
            throw new EmployeeNotFoundException(empID);
        }else {
            String theName=findEmployee(empID,employees).getName();
            double theGrossSalary=findEmployee(empID,employees).getRawSalary();
            employees.remove(findEmployee(empID,employees));
            employees.add(new Director(empID,theName,theGrossSalary,degree,department));
            return empID+" promoted successfully to Director.";
        }
    }

    public String promoteToIntern(String empID, int gpa) throws Exception {
        if (!containsEmployee(empID)){
            throw new EmployeeNotFoundException(empID);
        }else if (findEmployee(empID,employees) == null){
            throw new EmployeeNotFoundException(empID);
        }else {
            String theName=findEmployee(empID,employees).getName();
            double theGrossSalary=findEmployee(empID,employees).getRawSalary();
            employees.remove(findEmployee(empID,employees));
            employees.add(new Intern(empID,theName,theGrossSalary,gpa));
            return empID+" promoted successfully to Intern.";
        }
    }

}