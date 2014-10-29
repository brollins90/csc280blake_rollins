package edu.neumont.csc280.lab4.user;

import edu.neumont.csc280.lab4.web.ModelAndView;

/**
 * Created by blakerollins on 10/27/14.
 */
public class UserGetController {

    private UserService userService;

    public UserGetController(UserService userService) {
        this.userService = userService;
    }

    public ModelAndView beginRegisterUserWorkflow(){
        return new ModelAndView(null, "/WEB-INF/register.jsp");
    }
}
