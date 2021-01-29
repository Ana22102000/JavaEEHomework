package kma.topic2.junit.validation;

import kma.topic2.junit.exceptions.ConstraintViolationException;
import kma.topic2.junit.exceptions.FullnameRegexException;
import kma.topic2.junit.exceptions.LoginExistsException;
import kma.topic2.junit.model.NewUser;
import kma.topic2.junit.repository.UserRepository;
import kma.topic2.junit.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class UserValidatorTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserValidator userValidator;

    @Test
    void validatePassword_validInput(){
        userValidator.validateNewUser(createNewUser("new-login", "Full Name", "pass"));
        Mockito.verify(userRepository).isLoginExists("new-login");

    }

    @ParameterizedTest
    @MethodSource("fullnameInvalidTestDataProvider")
    void shouldThrowException_whenFullnameInvalid(final String fullname){
        assertThatThrownBy(() -> userValidator.validateNewUser(createNewUser("login", fullname, "pass")
        )).isInstanceOf(FullnameRegexException.class)
                .hasMessage("Fullname doesnt match regex: "+fullname);

    }

    @ParameterizedTest
    @MethodSource("fullnameValidTestDataProvider")
    void validateFullname_whenFullnameValid(final String fullname){
        userValidator.validateNewUser(createNewUser("new-login", fullname, "pass"));

    }

    @Test
    void shouldThrowException_whenLoginExists(){
        Mockito.when(userRepository.isLoginExists("new-login")).thenReturn(true);
        assertThatThrownBy(() -> userValidator
                .validateNewUser(createNewUser("new-login", "Full Name", "pass"))
                ).isInstanceOf(LoginExistsException.class)
                .hasMessage("Login new-login already taken");;
    }

    @ParameterizedTest
    @MethodSource("passwordTestDataProvider")
    void shouldThrowException_whenPasswordIsInvalid(final String password, final List<String> expectedErrors){
        assertThatThrownBy(() -> userValidator.validateNewUser(createNewUser("login", "Full Name", password)
                )).isInstanceOfSatisfying(ConstraintViolationException.class, ex -> {
                    assertThat(ex.getErrors())
                            .containsExactlyInAnyOrderElementsOf(expectedErrors);
                })
                .hasMessage("You have errors in you object");

    }

    private static Stream<Arguments> passwordTestDataProvider(){
        return Stream.of(
                Arguments.of("pa", List.of("Password has invalid size")), //Password doesn't match regex
                Arguments.of("passwrddddddddddddddddddd", List.of("Password has invalid size")),
                Arguments.of("pa%%w@r", List.of("Password doesn't match regex")),
                Arguments.of("p!", List.of("Password has invalid size", "Password doesn't match regex")),
                Arguments.of("password!!!!!!!!!!!!!!!!!", List.of("Password has invalid size", "Password doesn't match regex"))

                );
    }

    private static Stream<Arguments> fullnameInvalidTestDataProvider(){
        return Stream.of(
                Arguments.of(""),
                Arguments.of("sd sd"),
                Arguments.of("Asd sdsss"),
                Arguments.of("dsdd Adssd"),
                Arguments.of("Hjsjsdjsd"),
                Arguments.of("asaashja"),
                Arguments.of("AdsddA Adssd"),
                Arguments.of("Ads1dd Adssd"),
                Arguments.of("Adsdd AAdssd"),
                Arguments.of("Adsdd 1Adssd")


                );
    }

    private static Stream<Arguments> fullnameValidTestDataProvider(){
        return Stream.of(
                Arguments.of("Fjdj Djjd"),
                Arguments.of("Jsksk Hakakakak"),
                Arguments.of("J H"),
                Arguments.of("Jkkka Y"),
                Arguments.of("H Jaskask"),
                Arguments.of("Iwuw Unsm")


        );
    }


    NewUser createNewUser(String login, String fullname, String pass){
        return NewUser.builder()
                .login(login)
                .fullName(fullname)
                .password(pass)
                .build();
    }

}