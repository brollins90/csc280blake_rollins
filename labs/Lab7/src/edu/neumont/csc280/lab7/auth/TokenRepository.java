//package edu.neumont.csc280.lab7.auth;
//
//import edu.neumont.csc280.lab7.user.User;
//
////import java.util.Base64;
//import javax.crypto.Cipher;
//import javax.ejb.LocalBean;
//import javax.ejb.Stateless;
//import javax.servlet.http.Cookie;
//import java.io.*;
//
//@Stateless
//@LocalBean
//public class TokenRepository {
//
//    public User readUser() {
//        for(Cookie c : InjectionFilter.requestHolder.get().getCookies() ) {
//            if ("_auth".equals(c.getName())) {
//                String value = c.getValue();
//                byte[] inBytes = Base64.getDecoder().decode(value);
//                return decrypt(inBytes);
//            }
//        }
//        return null;
//    }
//
//    public void writeUser(User u) {
//        Cookie c = new Cookie("_auth", Base64.getEncoder().encodeToString(encrypt(u)));
//        c.setHttpOnly(true);
//        c.setMaxAge(-1);
//        c.setDomain(InjectionFilter.requestHolder.get().xxxx);
//        InjectionFilter.responseHolder.get().addCookie(C);
//    }
//
//    private User decrypt(byte[] b) {
//
//
//        ///
////        byte[] userInBytes;
////        try (ObjectOutputStream oos = new ObjectOutputStream())
//
//        try (
//            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(b))) {
//            return (User)ois.readObject();
//
//            }catch (IOException | ClassNotFoundException e) {
//            throw new IllegalArgumentException("Cannot encrypt user object.");
//        }
//
//    }
//
//    private String encrypt(User u) {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//
//        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
//            oos.writeObject(u);
//        } catch (IOException e) {
//        throw new IllegalArgumentException("Cannot encrypt user object.");}
//
//
//
//        try {
//            Cipher c = Cipher.getInstance("AES>>>>SAKDJSAJKDK");
//
//        }
//
//    }
//}
