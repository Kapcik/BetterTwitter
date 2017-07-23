package com.bettertwitter.exception;

import com.bettertwitter.data.User;
import com.bettertwitter.utils.UserTypes;

public class UserChecker {

    public static void checkIfUserNotNull(User user, UserTypes userType) {
        if (user == null) {
            switch (userType) {
                case USER:
                    throw new UserNotFoundException();
                case FOLLOWING:
                    throw new FollowingNotFoundException();
                case FOLLOWER:
                    throw new FollowerNotFoundException();
            }
        }
    }
}
