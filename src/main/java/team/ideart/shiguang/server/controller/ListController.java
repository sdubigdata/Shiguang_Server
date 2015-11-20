package team.ideart.shiguang.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import team.ideart.shiguang.server.persist.dao.PostDAO;
import team.ideart.shiguang.server.json.AResponse;
import team.ideart.shiguang.server.json.ListResponse;
import team.ideart.shiguang.server.persist.entity.Post;
import team.ideart.shiguang.server.persist.entity.User;
import team.ideart.shiguang.server.util.SecurityUtil;

import javax.servlet.ServletContext;
import java.util.List;

/**
 * Description
 *
 * @author xccui
 *         Created on 11/21/15.
 */
@Controller
public class ListController {
    @Autowired
    private PostDAO postDAO;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public ListResponse list(@RequestParam(name = "token") String token) {
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        ServletContext servletContext = webApplicationContext.getServletContext();
        User user = SecurityUtil.getLoginUser(servletContext, token);
        ListResponse response = new ListResponse();
        if (null == user) {
            response.setCode(AResponse.LOGIN);
        } else {
            List<Post> postList = postDAO.findByUser(user);
            response.setPostList(postList);
            response.setCode(AResponse.SUCCESS);
        }
        return response;
    }
}
