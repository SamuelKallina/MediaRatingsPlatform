package Exception;

public class MediaEntryException extends RuntimeException{
    public MediaEntryException(String message){
        super(message);
    }

    public static void notNull(Object value, String fieldName) {
        if (value == null)  throw new NullPointerException(fieldName + " cannot be null");
    }

    public static void movieLengthValid(int lengthInMinutes) {
        if (lengthInMinutes <= 0) {
            throw new MediaEntryException("Movie length must be greater than 0 minutes");
        }
    }

    public static void gamePlayTimeValid(int playTimeInHours) {
        if (playTimeInHours <= 0) {
            throw new MediaEntryException("Game play time must be greater than 0 hours");
        }
    }

    public static void seriesEpisodesValid(int episodes) {
        if (episodes <= 0) {
            throw new MediaEntryException("Series must have at least 1 episode");
        }
    }

}
