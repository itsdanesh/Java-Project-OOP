package businessLogic;

import exceptions.InvalidEmployeeDataException;
import java.util.Objects;
import static facade.Facade.decimal;

public class Employee implements Comparable<Employee> {

    private final String ID;
    private String name;
    private double grossSalary;

    public Employee(String ID, String name, double grossSalary) throws Exception{
        if (ID.isBlank()){
            throw new InvalidEmployeeDataException("ID cannot be blank.");
        }else if (name.isBlank()){
            throw new InvalidEmployeeDataException("Name cannot be blank.");
        }else if (grossSalary<=0){
            throw new InvalidEmployeeDataException("Salary must be greater than zero.");
        }else{
        this.ID=ID;
        this.name=name;
        this.grossSalary=decimal(grossSalary,2);
        }
    }

    public String getID(){return this.ID;}
    public String getName(){return this.name;}
    public void setName(String name){this.name=name;}
    public double getGrossSalary(){return this.grossSalary;}
    public void setGrossSalary(double salary){this.grossSalary=salary;}

    public double getRawSalary(){return this.grossSalary;}

    public String toString(){
        return this.name+"'s gross salary is "+String.format("%.2f", decimal(this.grossSalary,2))+" SEK per month.";
    }

    public boolean equals(Object anotherObject) {
        if (anotherObject==this){
            return true;
        }if (anotherObject==null){
            return false;
        }if (anotherObject.getClass()==Employee.class){
            Employee anotherEmployee= (Employee) anotherObject;
            return getID().equals(anotherEmployee.getID());
        }else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(ID);
    }

    public double getNetSalary(){
        final double tax=0.1;
        return this.grossSalary-this.grossSalary*tax;
    }

    public int compareTo(Employee anotherEmployee) {
        if (this.getGrossSalary() > anotherEmployee.getGrossSalary()) {
            return 1;
        } else if (this.getGrossSalary() == anotherEmployee.getGrossSalary()) {
            return 0;
        } else {
            return -1;
        }
    }

}
