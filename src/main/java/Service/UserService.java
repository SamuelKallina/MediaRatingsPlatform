package Service;

import Model.UserProfile;

import static Exception.UserException.notNull;

public class UserService {

    public void viewProfile(UserProfile userProfile) {
        notNull(userProfile, "User profile");
        System.out.println(userProfile);
    }

    public void editProfile() {} //TODO

    public void viewRatingHistory(){} //TODO

//    public void viewFavourites(){ //TODO
//        notNull(favourites, "User favourites");
//        System.out.println(favourites);
//    }

    public void viewStatistics(){} //TODO
}
