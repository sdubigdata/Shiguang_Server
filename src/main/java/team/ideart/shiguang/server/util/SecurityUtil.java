package team.ideart.shiguang.server.util;

import team.ideart.shiguang.server.bean.UserContainer;
import team.ideart.shiguang.server.persist.entity.User;

import javax.servlet.ServletContext;

/**
 * Description
 *
 * @author xccui
 *         Created on 11/21/15.
 */
public class SecurityUtil {
    public static User getLoginUser(ServletContext context, String token) {
        UserContainer container = (UserContainer) context.getAttribute(Constants.USER_CONTAINER_KEY);
        return container.getUserByToken(token);
    }
}
