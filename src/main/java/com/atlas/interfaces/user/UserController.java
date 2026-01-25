package com.atlas.interfaces.user;

import com.atlas.application.user.CreateUserCommand;
import com.atlas.application.user.CreateUserUseCase;
import com.atlas.application.user.UserId;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final CreateUserUseCase createUserUseCase;
    public UserController(CreateUserUseCase createUserUseCase) {
        this.createUserUseCase = createUserUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserId createUser(@Valid @RequestBody CreateUserRequest request) {
        CreateUserCommand command = new CreateUserCommand(
                request.externalIdentity(),
                request.displayName()
        );

        return this.createUserUseCase.execute(command);
    }
}
