package edu.neumont.csc280.lab7.user;

import edu.neumont.csc280.lab7.item.AuctionService;
import edu.neumont.csc280.lab7.web.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by blakerollins on 12/3/14.
 */
public class UserController {

    @Inject
    UserService userService;
    @Inject
    HttpServletRequest request;


    public ModelAndView getUserLoginForm() {
        return new ModelAndView(null, "/user/login.jsp");
    }

    public ModelAndView exectureUserLoginForm() {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);//passwordEncoder.encode(password));

        userService.updateUser(user);

        return new ModelAndView(null, "redirect:" + request.getServletContext().getContextPath() + "/item");
    }

    public ModelAndView getUserLogoutForm() {
        return new ModelAndView(null, "/user/logout.jsp");
    }

    public ModelAndView exectureUserLogoutForm() {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(password);//passwordEncoder.encode(password));
//
//        userService.updateUser(user);

        return new ModelAndView(null, "redirect:" + request.getServletContext().getContextPath() + "/item");
    }

}
