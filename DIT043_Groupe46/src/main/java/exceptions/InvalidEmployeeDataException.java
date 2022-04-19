package exceptions;

public class InvalidEmployeeDataException extends Exception{

    public InvalidEmployeeDataException(){
        super();
    }

    public InvalidEmployeeDataException(String message) throws Exception{
        super(message);
    }
}
