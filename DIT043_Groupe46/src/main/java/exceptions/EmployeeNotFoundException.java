package exceptions;

public class EmployeeNotFoundException extends Exception{

    public EmployeeNotFoundException(String employeeID) throws Exception{
        super("Employee "+employeeID+" was not registered yet.");
    }
    public EmployeeNotFoundException(String message, String employeeID) throws Exception{
        super(message);
    }

}
