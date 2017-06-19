package pl.kolata.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.kolata.repository.UserRepository;
import pl.kolata.service.UserDetailService;

/**
 * Created by Aleksander on 2017-06-17.
 */
@Service
public class UserDetailServiceImpl
implements UserDetailService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDetails userDetails =  userRepository.findByLogin(username);

        if(userDetails == null){
            throw new UsernameNotFoundException("User " + username +  " not found!");
        }

        return userDetails;
    }
}
