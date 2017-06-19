package pl.kolata.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kolata.entity.User;
import pl.kolata.repository.UserRepository;
import pl.kolata.service.UserService;

/**
 * Created by Aleksander on 2017-06-19.
 */
@Service
public class UserServiceImpl
implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveAndFlush(User user) {

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.saveAndFlush(user);

        return user;
    }
}
