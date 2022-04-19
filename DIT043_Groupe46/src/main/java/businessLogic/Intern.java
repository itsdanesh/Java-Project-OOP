package businessLogic;

import exceptions.InvalidEmployeeDataException;
import static facade.Facade.decimal;

public class Intern extends Employee{

    private int GPA;

    public Intern(String ID, String name, double grossSalary, int GPA) throws Exception {
        super(ID, name, grossSalary);
        if (GPA < 0 || GPA > 10) {
            throw new InvalidEmployeeDataException(GPA + " outside range. Must be between 0-10.");
        } else {
            this.GPA = GPA;
        }
    }

    public double getRawSalary() {
        return super.getRawSalary();
    }

    public double getGrossSalary(){
        double result=0.0;
        if (this.GPA<=5){
            result=0.0;
        }else if (this.GPA>5 && this.GPA<8){
            result=super.getGrossSalary();
        }else if (this.GPA>=8){
            result=super.getGrossSalary()+1000.0;
        }
        return result;
    }

    public double getNetSalary(){
        return getGrossSalary();
    }

    public String toString(){
        return super.getName()+"'s gross salary is "+String.format("%.2f", decimal(this.getGrossSalary(),2))+" SEK per month. GPA: "+this.GPA;
    }
    public int getGPA() {return GPA;}
    public void setGPA(int newGPA) {this.GPA = newGPA;}

}
