package pl.kolata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import pl.kolata.entity.User;

/**
 * Created by Aleksander on 2017-06-15.
 */
public interface UserRepository extends JpaRepository<User,Long> {

    User findByLogin(String login);
}
