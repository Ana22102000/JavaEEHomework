package kma.topic2.junit.service;

import kma.topic2.junit.exceptions.ConstraintViolationException;
import kma.topic2.junit.exceptions.LoginExistsException;
import kma.topic2.junit.exceptions.UserNotFoundException;
import kma.topic2.junit.model.NewUser;
import kma.topic2.junit.model.User;
import kma.topic2.junit.repository.UserRepository;
import kma.topic2.junit.validation.UserValidator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;


@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @SpyBean
    private UserValidator userValidator;

    @Test
    void shoudCreateValidUser_userValidInput(){

        userService.createNewUser(createNewUser("new-login", "Full Name", "pass"));



        Mockito.verify(userValidator).validateNewUser(argThat(user -> {
            assertThat(user)
                    .returns("new-login", NewUser::getLogin);
            return user.getLogin().equals("new-login") && user.getFullName().equals("Full Name");
        }));

        assertThat(userRepository.getUserByLogin("new-login"))
            .isEqualTo(
                    User.builder()
                            .login("new-login")
                            .fullName("Full Name")
                            .password("pass")
                            .build()
            );

        //alternative
        assertThat(userRepository.getUserByLogin("new-login"))
                .returns("new-login", User::getLogin);

    }


    @Test
    void shoudThrowException_whehValidInput_butLoginExists(){
        userService.createNewUser(createNewUser("new-login1", "Full Name", "pass"));

       // Mockito.when(userValidator.validateNewUser().thenThrow(
        assertThatThrownBy(() -> userService.createNewUser(createNewUser("new-login1", "Full Namee", "pass2")))
                .isInstanceOf(LoginExistsException.class)
                .hasMessage("Login new-login1 already taken");

        Mockito.verify(userValidator, times(2)).validateNewUser(argThat(user -> {
            return true;
        }));

    }

    @Test
    void shoudThrowException_whehInvalidInput_password(){

        assertThatThrownBy(() -> userService.createNewUser(createNewUser("new-login2", "Full Name", "p")))
                .isInstanceOf(ConstraintViolationException.class);

        Mockito.verify(userValidator).validateNewUser(argThat(user -> {
            return true;
        }));
    }

    @Test
    void getUserByLogin_ValidInput(){

        userService.createNewUser(createNewUser("new-login3", "Full Name", "pass"));

        assertThat(userService.getUserByLogin("new-login3"))
                .isEqualTo(
                        User.builder()
                                .login("new-login3")
                                .fullName("Full Name")
                                .password("pass")
                                .build()
                );

    }

    @Test
    void getUserByLogin_InvalidInput(){
        userService.createNewUser(createNewUser("new-login4", "Full Namee", "pass2"));

        assertThatThrownBy(() -> userService.getUserByLogin("new-login10"))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessage("Can't find user by login: new-login10");
    }


    NewUser createNewUser(String login, String fullname, String pass){
        return NewUser.builder()
                .login(login)
                .fullName(fullname)
                .password(pass)
                .build();
    }


}