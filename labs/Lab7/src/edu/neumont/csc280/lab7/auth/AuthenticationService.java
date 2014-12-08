//package edu.neumont.csc280.lab7.auth;
//
//import edu.neumont.csc280.lab7.user.User;
//
//import javax.ejb.LocalBean;
//import javax.ejb.Stateless;
//import javax.enterprise.inject.Any;
//import javax.enterprise.inject.Instance;
//import javax.inject.Inject;
//
//@Stateless
//@LocalBean
//public class AuthenticationService {
//
//    @Inject
//    @Any
//    Instance<AuthenticationProvider> providers;
//    @Inject
//    TokenRepository tokenRepository;
//
//    public User authenticate(Credentials credentials) {
//        for ( AuthenticationProvider provider : providers){
//            if (provider.support(credentials.getClass())) {
//                User user = provider.authenticate(credentials);
//                if (user != null) {
//                    tokenRepository.writeUser(user);
//                    return user;
//                }
//            }
//        }
//        return null;
//    }
//
//}
