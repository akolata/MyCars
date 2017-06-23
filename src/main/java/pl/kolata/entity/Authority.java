package pl.kolata.entity;

import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;

/**
 * Entity used in project as user's authorities table
 * Created by Aleksander on 2017-06-16.
 */
@Entity
public
class Authority
implements GrantedAuthority{


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String authority;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Authority(){}

    public Authority(String authority){
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", authority='" + authority + '\'' +
                '}';
    }
}
