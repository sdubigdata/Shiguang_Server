package team.ideart.shiguang.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import team.ideart.shiguang.server.persist.dao.PostDAO;
import team.ideart.shiguang.server.json.MultipartUploadResponse;
import team.ideart.shiguang.server.json.AResponse;
import team.ideart.shiguang.server.json.PostUploadResponse;
import team.ideart.shiguang.server.persist.entity.Label;
import team.ideart.shiguang.server.persist.entity.Post;
import team.ideart.shiguang.server.persist.entity.User;
import team.ideart.shiguang.server.util.Constants;
import team.ideart.shiguang.server.util.SecurityUtil;
import team.ideart.shiguang.server.util.UploadUtil;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Description
 *
 * @author xccui
 *         Created on 11/20/15.
 */
@Controller
public class UploadController {

    @Autowired
    private PostDAO postDAO;

    @RequestMapping(value = "/uploadMultipart", method = RequestMethod.POST)
    @ResponseBody
    public MultipartUploadResponse uploadMultipart(@RequestParam(name = "token") String token, @RequestParam(name = Constants.MULTIPART_UPLOAD_FILE) MultipartFile file) {
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        ServletContext servletContext = webApplicationContext.getServletContext();
        User user = SecurityUtil.getLoginUser(servletContext, token);
        MultipartUploadResponse response = new MultipartUploadResponse();
        if (null == user) {
            response.setCode(MultipartUploadResponse.LOGIN);
        } else {
            String filename = file.getOriginalFilename();
            try {
                File source = new File(UploadUtil.generateFilePath(user, filename));
                file.transferTo(source);
                response.setCode(MultipartUploadResponse.SUCCESS);
            } catch (IOException e) {
                e.printStackTrace();
                response.setCode(MultipartUploadResponse.ERROR);
            }
        }
        return response;
    }

    @RequestMapping(value = "/uploadPost", method = RequestMethod.POST)
    @ResponseBody
    public PostUploadResponse uploadPost(@RequestParam(name = "token") String token, @RequestParam(name = "path") String path, @RequestParam(name = "content") String content, @RequestParam(name = "date") Date date, @RequestParam(name = "color") int color, @RequestParam(name = "labelList") List<Label> labelList, @RequestParam(name = "weather") String weather) {
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        ServletContext servletContext = webApplicationContext.getServletContext();
        User user = SecurityUtil.getLoginUser(servletContext, token);
        PostUploadResponse response = new PostUploadResponse();
        if (null == user) {
            response.setCode(AResponse.LOGIN);
        } else {
            Post post = new Post();
            post.setColor(color);
            post.setContent(content);
            post.setDate(date);
            post.setLabelList(labelList);
            post.setPath(path);
            post.setUser(user);
            post.setWeather(weather);
            try {
                postDAO.save(post);
                response.setCode(AResponse.SUCCESS);
            } catch (Exception ex) {
                ex.printStackTrace();
                response.setCode(AResponse.ERROR);
            }
        }
        return response;
    }
}
