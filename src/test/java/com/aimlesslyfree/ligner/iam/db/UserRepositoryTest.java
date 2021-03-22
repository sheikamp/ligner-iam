package com.aimlesslyfree.ligner.iam.db;

import java.util.Collections;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldCreateUser() {
        var user = new User(null, "foo@bar.com", "Foo Bar", Collections.emptyList());
        userRepository.save(user);

        var users = userRepository.findAll();

        assertThat(users).asList().hasSize(1);
        var createdUser = users.iterator().next();
        assertThat(createdUser.getId()).isNotNull();
    }

    @Test
    void shouldCreateUserWithFriendship() {
        var friend = userRepository.save(new User(null, "bar@foo.com", "Bar Foo", Collections.emptyList()));
        var user = new User(null, "foo@bar.com", "Foo Bar", Collections.emptyList());
        user.addFriend(friend);
        var userId = userRepository.save(user).getId();

        var createdUser = userRepository.findById(userId).orElse(null);

        assertThat(createdUser.getId()).isNotNull();

        assertThat(createdUser.getFriendships()).asList().hasSize(1);
        var createdFriendship = createdUser.getFriendships().iterator().next();
        assertThat(createdFriendship.getId()).isNotNull();
        assertThat(createdFriendship.getStatus()).isEqualTo(Friendship.FriendshipStatus.REQUESTED);
        assertThat(createdFriendship.getFriend()).isNotNull();
    }
}
