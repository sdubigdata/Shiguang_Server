package team.ideart.shiguang.server.persist.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import team.ideart.shiguang.server.persist.entity.Post;
import team.ideart.shiguang.server.persist.entity.User;

import java.util.List;

/**
 * Description
 *
 * @author xccui
 *         Created on 11/21/15.
 */
public interface PostDAO extends JpaRepository<Post, String> {
    List<Post> findByUser(User user);
}
