package Model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static Exception.UserException.notNull;

public class User extends BaseEntity{
    private String userName;
    private String passwordHash; //TODO passwordEncoader.hash yada yada yada
    private String nameTag;
    private Set<MediaEntry> favourites= new HashSet<MediaEntry>();

    public User(String userName, String passwordHash, String nameTag) {
        super();
        setUserName(userName);
        setPasswordHash(passwordHash);
        setNameTag(nameTag);
    }

    //Methods / logic

    public void addToFavourites(MediaEntry mediaEntry) {
        favourites.add(mediaEntry);
    }

    public void viewProfile(UserProfile userProfile) {
        notNull(userProfile, "User profile");
        System.out.println(userProfile);
    }

    public void editProfile() {} //TODO

    public void createMedia(){} //TODO

    public void updateMedia(){} //TODO

    public void deleteMedia(MediaEntry mediaEntry){} //TODO

    public void rateMedia(MediaEntry mediaEntry, int stars){} //TODO

    public void editRating(){} //TODO

    public void deleteRating(){} //TODO

    public void likeRating(){} //TODO (only once per Rating

    public void viewRatingHistory(){} //TODO

    public void viewFavourites(){
        notNull(favourites, "User favourites");
        System.out.println(favourites);
    }

    public void viewStatistics(){} //TODO


    public void writeComment(String comment){} //TODO














    //getter & setter
    public String getUserName() {
        return userName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getNameTag() {
        return nameTag;
    }

    public Set<MediaEntry> getFavourites() {
        return favourites;
    }

    public void setUserName(String userName) {
        notNull(userName, "Username");
        this.userName = userName;
    }

    public void setNameTag(String nameTag) {
        notNull(nameTag, "Nametag");
        this.nameTag = nameTag;
    }

    public void setPasswordHash(String passwordHash) {
        notNull(passwordHash, "PasswordHash");
        this.passwordHash = passwordHash;
    }
}
