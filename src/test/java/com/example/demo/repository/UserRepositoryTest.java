package com.example.demo.repository;

import com.example.demo.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setEmail("ha@gmail.com");
        user.setPassword("ggg");
        user.setFirstName("ggg");
        user.setLastName("ggg");

        User savedUser = userRepository.save(user);
        User existingUser = entityManager.find(User.class, savedUser.getId());

        Assertions.assertThat(existingUser.getEmail().equals(user.getEmail()));
    }

    @Test
    public void testFindUser() {
        System.out.println("اینه");
        System.out.println(userRepository.findByEmail("ha@gmail.com").getEmail());
        Assertions.assertThat(userRepository.findByEmail("ha@gmail.com")).isNotNull();

    }

}