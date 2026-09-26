package Exception;

public class UserException extends RuntimeException{
    public UserException(String message){
        super(message);
    }

    public static void notNull(Object value, String fieldName) {
       if (value == null)  throw new NullPointerException(fieldName + " cannot be null");
    }
}
