package pl.kolata.service;

import pl.kolata.entity.User;

/**
 * Created by Aleksander on 2017-06-19.
 */
public interface UserService {

    User saveAndFlush(User user);
}
