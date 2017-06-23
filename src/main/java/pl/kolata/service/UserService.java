package pl.kolata.service;

import pl.kolata.entity.User;

/**
 * User's entities service used with Spring Security with Db login
 * Created by Aleksander on 2017-06-19.
 */
public interface UserService {

    /**
     *  Method encodes password and calls default repository implementation
     * @param user user to be saved
     * @return saved user
     */
    User saveAndFlush(User user);
}
