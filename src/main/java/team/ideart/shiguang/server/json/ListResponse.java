package team.ideart.shiguang.server.json;

import team.ideart.shiguang.server.persist.entity.Post;

import java.util.List;

/**
 * Description
 *
 * @author xccui
 *         Created on 11/21/15.
 */
public class ListResponse extends AResponse {
    private List<Post> postList;

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }
}
