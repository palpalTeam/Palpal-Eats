package com.sparta.palpaleats.domain.user.entity;

import lombok.Getter;

@Getter
public enum UserRoleEnum {

    BUYER(Authority.BUYER),
    SELLER(Authority.SELLER),
    ADMIN(Authority.ADMIN);

    private final String authority;

    UserRoleEnum(String authority) {
        this.authority = authority;
    }

    public static class Authority {

        public static final String BUYER = "ROLE_BUYER";
        public static final String SELLER = "ROLE_SELLER";
        public static final String ADMIN = "ROLE_ADMIN";
    }
}
