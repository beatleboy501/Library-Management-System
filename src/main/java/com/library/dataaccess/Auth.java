package com.library.dataaccess;

import java.util.Optional;

/**
 * Created by beatleboy501 on 8/10/2015.
 */
class Auth {
    static final String USERNAME = Optional.ofNullable(System.getenv("LMS_DB_USER")).orElse("root");
    static final String PASSWORD = Optional.ofNullable(System.getenv("LMS_DB_PW")).orElse("");
}
