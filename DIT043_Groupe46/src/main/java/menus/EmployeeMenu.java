package menus;

import exceptions.*;
import facade.Facade;
import input.Input;

public class EmployeeMenu {

    static final String EOL= System.lineSeparator();
    static final Facade facade= MainMenu.facade;

    public static void doEmployeeMenu() throws Exception{

        System.out.println("Employee options menu:" +EOL+
                "0. Return to Main Menu." +EOL+
                "1. Create an employee (Regular Employee)." +EOL+
                "2. Create an employee (Manager)." +EOL+
                "3. Create an employee (Director)." +EOL+
                "4. Create an employee (Intern)." +EOL+
                "5. Remove an employee." +EOL+
                "6. Print specific employee." +EOL+
                "7. Print all registered employees." +EOL+
                "8. Print the total expense with net salary." +EOL+
                "9. Print all employees sorted by gross salary." +EOL+
                EOL +
                "Type an option number:");

        int menuOption=Input.readAndVerifyOptionInput(0,9);

        switch (menuOption){
            case 0: MainMenu.doMainMenu();
                break;
            case 1:
                createRegularEmployee();
                break;
            case 2:
                createManagerEmployee();
                break;
            case 3:
                createDirectorEmployee();
                break;
            case 4:
                createInternEmployee();
                break;
            case 5:
                removeStoredEmployees();
                break;
            case 6:
                PrintSpecificEmployee();
                break;
            case 7:
                try{
                    facade.printAllEmployees();
                }catch (EmptyEmployeeListException exception){
                    System.out.println(exception.getMessage());
                }finally {
                    doEmployeeMenu();
                }
                break;
            case 8:
                try{
                    facade.getTotalNetSalary();
                }catch (EmptyEmployeeListException exception){
                    System.out.println(exception.getMessage());
                }finally {
                    doEmployeeMenu();
                }
                break;
            case 9:
                try{
                    facade.printSortedEmployees();
                }catch (EmptyEmployeeListException exception){
                    System.out.println(exception.getMessage());
                }finally {
                    doEmployeeMenu();
                }
                break;
        }
    }

    public static void createRegularEmployee() throws Exception{
        String ID=Input.readString("Type the ID of the person : ");
        String name=Input.readString("Type the name of the person : ");
        double grossSalary=Input.readDouble("Type the gross salary of the person : ");
        try {
            System.out.println(facade.createEmployee(ID,name,grossSalary));
        }catch (InvalidEmployeeDataException exception){
            System.out.println(exception.getMessage());
        }finally {
            doEmployeeMenu();
        }
    }

    public static void createManagerEmployee() throws Exception{
        String ID=Input.readString("Type the ID of the person : ");
        String name=Input.readString("Type the name of the person : ");
        double grossSalary=Input.readDouble("Type the gross salary of the person : ");
        String degree=Input.readString("Type the degree of the person (BSc,MSc,PhD) : ");
        try {
            System.out.println(facade.createEmployee(ID,name,grossSalary,degree));
        }catch (InvalidEmployeeDataException exception){
            System.out.println(exception.getMessage());
        }finally {
            doEmployeeMenu();
        }
    }

    public static void createDirectorEmployee() throws Exception{
        String ID=Input.readString("Type the ID of the person : ");
        String name=Input.readString("Type the name of the person : ");
        double grossSalary=Input.readDouble("Type the gross salary of the person : ");
        String degree=Input.readString("Type the degree of the person (BSc,MSc,PhD) : ");
        String department=Input.readString("Type the name of the department of the employee : ");
        try {
            System.out.println(facade.createEmployee(ID,name,grossSalary,degree,department));
        }catch (InvalidEmployeeDataException exception){
            System.out.println(exception.getMessage());
        }finally {
            doEmployeeMenu();
        }
    }

    public static void createInternEmployee() throws Exception{
        String ID=Input.readString("Type the ID of the person : ");
        String name=Input.readString("Type the name of the person : ");
        double grossSalary=Input.readDouble("Type the gross salary of the person : ");
        int GPA=Input.readInt("Type the GPA of the employee : ");
        try {
            System.out.println(facade.createEmployee(ID,name,grossSalary,GPA));
        }catch (InvalidEmployeeDataException exception){
            System.out.println(exception.getMessage());
        }finally {
            doEmployeeMenu();
        }
    }

    public static void PrintSpecificEmployee() throws Exception{
        String ID=Input.readString("Type the ID of the employee : ");
        try {
            System.out.println(facade.printEmployee(ID));
        }catch (EmployeeNotFoundException exception){
            System.out.println(exception.getMessage());
        }finally {
            doEmployeeMenu();
        }
    }

    public static void removeStoredEmployees() throws Exception{
        String ID=Input.readString("Type the ID of the employee : ");
        try{
            System.out.println(facade.removeEmployee(ID));
        }catch (EmployeeNotFoundException exception){
            System.out.println(exception.getMessage());
        }finally {
            doEmployeeMenu();
        }
    }

}