package com.atlas.domain.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void shouldCreateValidUser() {
        User user = User.create("email:rohit@example.com", "Rohit");

        assertNotNull(user.getId());
        assertEquals("email:rohit@example.com", user.getExternalIdentity());
        assertEquals("Rohit", user.getDisplayName());
        assertEquals(UserStatus.ACTIVE, user.getStatus());
        assertNotNull(user.getCreatedAt());
    }

    @Test
    void shouldRejectBlankExternalIdentity() {
        assertThrows(IllegalArgumentException.class,
                () -> User.create("", "Rohit")
        );
    }

    @Test
    void shouldRejectNullExternalIdentity() {
        assertThrows(IllegalArgumentException.class,
                () -> User.create(null, "Rohit")
        );
    }

    @Test
    void shouldRejectBlankDisplayName() {
        assertThrows(IllegalArgumentException.class,
                () -> User.create("email:rohit@example.com", "")
        );
    }

    @Test
    void shouldDisableUser() {
        User user = User.create("email:rohit@example.com", "Rohit");
        User disabled = user.disable();

        assertEquals(UserStatus.DISABLED, disabled.getStatus());
        assertEquals(user.getId(), disabled.getId());
    }

    @Test
    void disablingAlreadyDisabledUserShouldBeIdempotent() {
        User user = User.create("email:rohit@example.com", "Rohit");
        User disabledOnce = user.disable();
        User disabledTwice = disabledOnce.disable();

        assertSame(disabledOnce, disabledTwice);
    }
}
