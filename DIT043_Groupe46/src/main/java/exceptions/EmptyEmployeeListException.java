package exceptions;

public class EmptyEmployeeListException extends Exception{

    public EmptyEmployeeListException() throws Exception{
        super("No employees registered yet.");
    }
    public EmptyEmployeeListException(String message) throws Exception{
        super(message);
    }

}