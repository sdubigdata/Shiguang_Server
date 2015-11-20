package team.ideart.shiguang.server.persist.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import team.ideart.shiguang.server.persist.entity.User;

/**
 * Description
 *
 * @author xccui
 *         Created on 11/20/15.
 */
public interface UserDAO extends JpaRepository<User, String> {
    User findByUserNameAndPassword(String username, String password);
}
