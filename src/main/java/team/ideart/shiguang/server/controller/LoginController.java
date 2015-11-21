package team.ideart.shiguang.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import team.ideart.shiguang.server.bean.UserContainer;
import team.ideart.shiguang.server.persist.dao.UserDAO;
import team.ideart.shiguang.server.json.LoginResponse;
import team.ideart.shiguang.server.persist.entity.User;
import team.ideart.shiguang.server.util.Constants;
import team.ideart.shiguang.server.util.Encrypt;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Description
 *
 * @author xccui
 *         Created on 11/20/15.
 */

@Controller
public class LoginController {

    @Autowired
    private UserDAO userDAO;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public LoginResponse login(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password, HttpServletRequest request) {
        String passwordSHA = Encrypt.SHA(password);
        LoginResponse response = new LoginResponse();
        int code = LoginResponse.WRONG_INFO;
        //User user = userDAO.findByUserNameAndPassword(username, passwordSHA);
        User user = userDAO.findAll().get(0);
        if (null != user) {
            code = LoginResponse.ALREADY_LOGIN;
            ServletContext servletContext = request.getServletContext();
            UserContainer userContainer = (UserContainer) servletContext.getAttribute(Constants.USER_CONTAINER_KEY);
            if (!userContainer.containsUser(user)) {
                String token = userContainer.generateRandomToken(user.getUserName());
                response.setToken(token);
                code = userContainer.addUser(user, token);
            }
        }
        response.setCode(code);
        return response;
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String addUser() {
        User user1 = new User();
        user1.setUserName("Test1");
        user1.setPassword(Encrypt.SHA("12345"));
        userDAO.save(user1);
        return "SUCCESS";
    }
}
