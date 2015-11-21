package team.ideart.shiguang.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import team.ideart.shiguang.server.persist.dao.PostDAO;
import team.ideart.shiguang.server.json.AResponse;
import team.ideart.shiguang.server.json.ListResponse;
import team.ideart.shiguang.server.persist.entity.Post;
import team.ideart.shiguang.server.persist.entity.User;
import team.ideart.shiguang.server.util.SecurityUtil;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

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

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ListResponse list(@RequestParam(name = "token") String token, HttpServletRequest request) {
        ServletContext servletContext = request.getServletContext();
        User user = SecurityUtil.getLoginUser(servletContext, token);
        ListResponse response = new ListResponse();
        if (null == user) {
            response.setCode(AResponse.LOGIN);
        } else {
            List<Post> postList = postDAO.findByUser(user);
            Collections.sort(postList, new Comparator<Post>() {
                public int compare(Post o1, Post o2) {
                    return o1.getDate().getTime() > o2.getDate().getTime() ? 1 : -1;
                }
            });
            //TODO reorderlist
//            List<Post> newPostList = new ArrayList<>(postList.size());
//            for (int i = 0; i < postList.size(); i++) {
//                newPostList.set(postList.size() - 1, postList.get(i));
//            }
            for (Post p : postList) {
                //TODO remove set null achievement here
                p.getUser().setAchievementList(null);
                p.getUser().setPostList(null);
            }
            response.setPostList(postList);
            response.setCode(AResponse.SUCCESS);
        }
        return response;
    }
}
