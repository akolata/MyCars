package pl.kolata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kolata.entity.Authority;

/**
 * Created by Aleksander on 2017-06-17.
 */
public interface AuthorityRepository
extends JpaRepository<Authority,Long>{

    Authority findByAuthority(String authority);
}
