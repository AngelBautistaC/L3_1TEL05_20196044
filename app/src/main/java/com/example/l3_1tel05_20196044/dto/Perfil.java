// Perfil.java
package com.example.l3_1tel05_20196044.dto;

import java.util.List;

public class Perfil {

    private List<Result> results;

    public List<Result> getResults() {
        return results;
    }

    public static class Result {
        private Name name;
        private String email;
        private Login login;

        public Name getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public Login getLogin() {
            return login;
        }
    }

    public static class Name {
        private String first;
        private String last;

        public String getFirst() {
            return first;
        }

        public String getLast() {
            return last;
        }
    }

    public static class Login {
        private String password;

        public String getPassword() {
            return password;
        }
    }
}
