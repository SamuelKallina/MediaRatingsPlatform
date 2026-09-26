package Exception;

public class MediaEntryException extends RuntimeException{
    public MediaEntryException(String message){
        super(message);
    }

    public static void notNull(Object value, String fieldName) {
        if (value == null)  throw new NullPointerException(fieldName + " cannot be null");
    }
}
