package com.atlas.application.user;

public record CreateUserCommand(
    String externalIdentity,
    String displayName) {

}
