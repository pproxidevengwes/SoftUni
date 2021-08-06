package customException;

public class InvalidPersonNameException extends RuntimeException{
    public InvalidPersonNameException(String massage) {
        super(massage);
    }
}
