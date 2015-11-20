package team.ideart.shiguang.server;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import team.ideart.shiguang.server.config.MvcConfig;
import team.ideart.shiguang.server.listener.InitializeListener;

import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * WebAppInitializer
 *
 * @author Allen Jin
 * @date 2015-11-20
 */
public class WebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(javax.servlet.ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext dispatcherServlet = new AnnotationConfigWebApplicationContext();
        dispatcherServlet.register(MvcConfig.class);

        servletContext.addListener(new InitializeListener());
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet(
                "dispatcher", new DispatcherServlet(dispatcherServlet));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

    }

}
