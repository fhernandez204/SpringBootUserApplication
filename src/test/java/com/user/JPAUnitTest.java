package com.user;

import com.user.model.User;
import com.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.user.mock.UserMock.getNewUser;
import static com.user.mock.UserMock.getUserResponse;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class JPAUnitTest {
    //@Autowired
    //private TestEntityManager entityManager;

    @Autowired
    UserRepository repository;

    private final String EMAIL  = "fhernandez204@gmail.com";
    private final Long ID       = Long.valueOf(1);
    private final Long ID_DELETE       = Long.valueOf(6);

    @Test
    public void getAllUsers() {
        List<User> users = new ArrayList<User>();

        repository.findAll().forEach(users::add);
        assertThat(users).isNotNull();
        assertThat(users).hasSizeGreaterThan(0);
    }
    @Test
    public void getUserById() {
        Optional<User> userData = repository.findById(ID);
        assertThat(userData).isNotNull();
        assertThat(userData).isEqualTo(userData.get());
    }
    @Test
    public void findByEmail() {
        Optional<User> userData = repository.findByEmail(EMAIL);
        User user = getUserResponse();
        assertThat(userData).isNotNull();
        assertThat(userData).isEqualTo(user);
    }
    @Test
    public void createUser() {
        User user = getNewUser();
        User _user = repository
                .save(new User(user.getName(), user.getUserName(), user.getEmail(), user.getPhone()));
        Optional<User> userData = repository.findByEmail(user.getEmail());
        assertThat(userData).isNotNull();
        assertThat(_user.toString()).isEqualTo(user.toString());
    }
    @Test
    public void deleteTutorial() {
        repository.deleteById(ID_DELETE);
        Optional<User> userData = repository.findById(ID_DELETE);
        assertThat(userData).isEmpty();
    }

}
