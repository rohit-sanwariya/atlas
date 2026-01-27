package com.atlas.interfaces.user;

import com.atlas.application.user.GetUserByIdQuery;
import com.atlas.application.user.GetUserByIdQueryHandler;
import com.atlas.application.user.UserView;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserQueryController {

    private final GetUserByIdQueryHandler handler;

    public UserQueryController(GetUserByIdQueryHandler handler) {
        this.handler = handler;
    }

    @GetMapping("/{id}")
    public UserView getUserById(@PathVariable UUID id) {
        return handler.handle(new GetUserByIdQuery(id));
    }
}
