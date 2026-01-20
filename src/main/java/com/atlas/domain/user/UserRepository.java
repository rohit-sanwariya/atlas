package com.atlas.domain.user;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    void save(User user);

    Optional<User> findById(UUID id);

    Optional<User> findByExternalIdentity(String externalIdentity);
}
