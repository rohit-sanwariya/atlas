package com.atlas.infrastructure.persistence.user;

import com.atlas.domain.user.User;

public final class UserMapper {

    private UserMapper() {}

    public static UserEntity toEntity(User user) {
        return new UserEntity(
                user.getId(),
                user.getExternalIdentity(),
                user.getDisplayName(),
                user.getStatus(),
                user.getCreatedAt()
        );
    }

    public static User toDomain(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getExternalIdentity(),
                entity.getDisplayName(),
                entity.getStatus(),
                entity.getCreatedAt()
        );
    }
}
