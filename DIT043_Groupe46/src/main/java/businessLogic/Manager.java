package businessLogic;

import exceptions.InvalidEmployeeDataException;
import static facade.Facade.decimal;

public class Manager extends Employee {

    private String degree;

    public Manager(String ID, String name, double grossSalary, String degree) throws Exception{
        super(ID,name,grossSalary);
        if (!degree.equals("BSc") && !degree.equals("MSc") && !degree.equals("PhD")) {
            throw new InvalidEmployeeDataException("Degree must be one of the options: BSc, MSc or PhD.");
        }else {
        this.degree=degree;
        }
    }

    public double getRawSalary() {
        return super.getRawSalary();
    }

    public double getGrossSalary(){
        final double BScBonus=0.1;
        final double MScBonus=0.2;
        final double PhdBonus=0.35;
        double result=0.0;
        switch (degree) {
            case "BSc":
                result = super.getGrossSalary() + super.getGrossSalary() * BScBonus;
                break;
            case "MSc":
                result = super.getGrossSalary() + super.getGrossSalary() * MScBonus;
                break;
            case "PhD":
                result = super.getGrossSalary() + super.getGrossSalary() * PhdBonus;
                break;
        }
        return result;
    }

    public double getNetSalary(){
        final double tax=0.1;
        return this.getGrossSalary()-this.getGrossSalary()*tax;
    }

    public String toString(){
        return this.degree+" "+super.getName()+"'s gross salary is "+String.format("%.2f", decimal(this.getGrossSalary(),2))+" SEK per month.";
    }

    public String getDegree(){return this.degree;}
    public void setDegree(String newDegree){this.degree=newDegree;}

}
