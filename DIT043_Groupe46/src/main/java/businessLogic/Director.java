package businessLogic;

import exceptions.InvalidEmployeeDataException;
import static facade.Facade.decimal;

public class Director extends Manager{

    private String department;

    public Director(String ID, String name, double grossSalary, String degree, String department) throws Exception{
        super(ID,name,grossSalary,degree);
        if (!department.equals("Business") && !department.equals("Human Resources") && !department.equals("Technical")){
            throw new InvalidEmployeeDataException("Department must be one of the options: Business, Human Resources or Technical.");
        }else {
            this.department=department;
        }
    }

    public double getRawSalary() {
        return super.getRawSalary();
    }

    public double getGrossSalary(){
        final double additionalSalary=5000.0;
        return super.getGrossSalary()+additionalSalary;
    }

    public double getNetSalary(){
        final double lowRange=30000.0;
        final double highRange=50000.0;
        final double lowTax=0.1;
        final double mediumTax=0.2;
        final double highTax=0.4;
        double result=0.0;
        if (this.getGrossSalary()<lowRange){
            result = this.getGrossSalary()-this.getGrossSalary()*lowTax;
        }else if (this.getGrossSalary()>=lowRange && getGrossSalary()<highRange){
            result = this.getGrossSalary()-this.getGrossSalary()*mediumTax;
        }else if (this.getGrossSalary()>=highRange) {
            result = this.getGrossSalary()-(this.getGrossSalary()-lowRange)*highTax-(lowRange*mediumTax);
        }
        return result;
    }

    public String toString(){
        return super.getDegree()+" "+super.getName()+"'s gross salary is "+String.format("%.2f", decimal(this.getGrossSalary(),2))+" SEK per month. Dept: "+this.department;
    }

    public String getDepartment(){return this.department;}
    public void setDepartment(String newDepartment){this.department=newDepartment;}

}
