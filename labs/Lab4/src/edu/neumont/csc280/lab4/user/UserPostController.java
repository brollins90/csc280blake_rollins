package edu.neumont.csc280.lab4.user;

import edu.neumont.csc280.lab4.web.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by blakerollins on 10/27/14.
 */
public class UserPostController {

    private UserService userService;

    public UserPostController(UserService userService) {
        this.userService = userService;
    }

    public ModelAndView commitRegisterUserWorkflow(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        RegisterUserModel model = new RegisterUserModel();

        if ( !password.equals(confirmPassword)) {
            model.setErrorMessage("No");
            return new ModelAndView(model, "/WEB-INF/register.jsp");
        }

        try {
            User user = userService.createUser(username,password);
            model.setUser(user);
            return new ModelAndView(model, "/WEB-INF/success.jsp");

        } catch (UsernameAlreadyExistsException e) {
            model.setErrorMessage("Username already exists.");
            return new ModelAndView(model, "/WEB-INF/register.jsp");
        }

    }
}
