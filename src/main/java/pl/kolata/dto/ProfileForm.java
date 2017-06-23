package pl.kolata.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import pl.kolata.entity.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Class used as data transfer object as user's profile form
 * Created by Aleksander on 2017-06-14.
 */
public class ProfileForm {

    @NotNull
    @NotEmpty
    @Size(min = 5,max = 20)
    private String  login;
    @NotNull
    @NotEmpty
    @Size(min = 5,max = 20)
    private String  password;
    @NotNull
    @NotEmpty
    @Size(min = 2,max = 20)
    private String  firstName;
    @NotNull
    @NotEmpty
    @Size(min = 3,max = 30)
    private String  lastName;
    @Email
    private String  email;
    private String  phone;

    public ProfileForm(){}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public static ProfileForm createProfileFormFromUser(User user){
        ProfileForm profileForm = new ProfileForm();
        profileForm.login = user.getLogin();
        profileForm.password = user.getPassword();
        profileForm.firstName = user.getFirstName();
        profileForm.lastName = user.getLastName();
        profileForm.email = user.getEmail();
        profileForm.phone = user.getPhone();
        return profileForm;
    }

    @Override
    public String toString() {
        return "ProfileForm{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
