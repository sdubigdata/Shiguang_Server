package team.ideart.shiguang.server.listener;

import org.springframework.beans.factory.annotation.Autowired;
import team.ideart.shiguang.server.bean.UserContainer;
import team.ideart.shiguang.server.persist.dao.UserDAO;
import team.ideart.shiguang.server.persist.entity.User;
import team.ideart.shiguang.server.util.Constants;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Description
 *
 * @author xccui
 *         Created on 11/20/15.
 */
public class InitializeListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        UserContainer container = new UserContainer();
        context.setAttribute(Constants.USER_CONTAINER_KEY, container);
        System.out.println("Initialize User Container");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
