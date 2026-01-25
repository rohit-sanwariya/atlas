package com.atlas.application.user;

import com.atlas.domain.user.User;
import com.atlas.domain.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Application-level workflow for creating a user.
 * Coordinates domain + persistence.
 */
@Service
public class CreateUserUseCase {

    private final UserRepository userRepository;

    public CreateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserId execute(CreateUserCommand command) {

        // 1) Enforce uniqueness (system-wide rule)
        userRepository.findByExternalIdentity(command.externalIdentity())
                .ifPresent(u -> {
                    throw new IllegalStateException(
                            "User with external identity already exists"
                    );
                });

        // 2) Create domain object (domain enforces validity)
        User user = User.create(
                command.externalIdentity(),
                command.displayName()
        );

        // 3) Persist atomically
        userRepository.save(user);

        // 4) Return minimal result
        return new UserId(user.getId());
    }
}
