package com.atlas.application.user;

import com.atlas.application.user.exception.UserNotFoundException;
import com.atlas.domain.user.User;
import com.atlas.domain.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class GetUserByIdQueryHandler {

    private final UserRepository userRepository;

    public GetUserByIdQueryHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserView handle(GetUserByIdQuery query) {

        User user = userRepository.findById(query.userId())
                .orElseThrow(() -> new UserNotFoundException(query.userId()));
        return mapToView(user);
    }

    private UserView mapToView(User user) {
        return new UserView(
                user.getId(),
                user.getExternalIdentity(),
                user.getDisplayName(),
                user.getStatus().name(),
                user.getCreatedAt()
        );
    }
}
