//package edu.neumont.csc280.lab6.auth;
//
///**
// * Created by blakerollins on 11/10/14.
// */
//public class UserBuilder {
//
//    private String firstName;
//    private String suffix;
//
//    public UserBuilder() {
//    }
//
//    public static UserBuilder getInstance() {
//        return new UserBuilder();
//    }
//
//    public UserBuilder firstName(String firstName) {
//        this.firstName = firstName;
//        return this;
//    }
//
//    public UserBuilder suffix(String suffix) {
//        this.suffix = suffix;
//        return this;
//    }
//
//    public User build() {
//        return new User(null, null, firstName, null, suffix);
//    }
//
//    public static void main(String[] args) {
//        User user = UserBuilder.getInstance().firstName("John").suffix("jr").build();
//    }
//
//}
