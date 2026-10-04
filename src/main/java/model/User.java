package model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static exception.UserException.notNull;

public class User extends BaseEntity {
    private String userName;
    private String passwordHash; //TODO passwordEncoader.hash yada yada yada
    private String nameTag;
    private Set<MediaEntry> favourites = new HashSet<MediaEntry>();

    public User(String userName, String passwordHash, String nameTag) {
        super();
        setUserName(userName);
        setPasswordHash(passwordHash);
        setNameTag(nameTag);
    }

    //database constructor
    public User(UUID id, LocalDateTime createdAt, String userName, String passwordHash, String nameTag) {
        super(id, createdAt);

        setUserName(userName);
        setPasswordHash(passwordHash);
        setNameTag(nameTag);
    }

    //Methods / logic

    public void addToFavourites(MediaEntry mediaEntry) {
        notNull(mediaEntry, "Media entry");
        favourites.add(mediaEntry);
    }

    public void removeFromFavourites(MediaEntry mediaEntry) {
        notNull(mediaEntry, "Media entry");
        favourites.remove(mediaEntry);
    }


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
