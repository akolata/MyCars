package pl.kolata.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.kolata.entity.User;
import pl.kolata.repository.UserRepository;

import javax.annotation.PostConstruct;

/**
 * Created by Aleksander on 2017-06-15.
 */
@Component
public class RunAtStart {

    private UserRepository userRepository;

    @Autowired
    public RunAtStart(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void initialize(){
        userRepository.saveAndFlush(generateSampleUser());
    }

    private User generateSampleUser(){
        User user = new User();
        user.setLogin("login");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Smith");
        user.setPhone("111-111-111");
        user.setEmail("sample@mail.com");
        return user;
    }
}
