package team.ideart.shiguang.server.bean;

import team.ideart.shiguang.server.json.LoginResponse;
import team.ideart.shiguang.server.persist.entity.User;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description
 *
 * @author xccui
 *         Created on 11/20/15.
 */
public class UserContainer {
    private Map<String, User> tokenUserMap;

    public UserContainer() {
        tokenUserMap = new ConcurrentHashMap<>();
    }

    public boolean containsUser(User user) {
        return tokenUserMap.containsValue(user);
    }

    public User getUserByToken(String token) {
        return tokenUserMap.get(token);
    }

    public String generateRandomToken(String userName) {
        return userName + " " + UUID.randomUUID().toString();
    }

    public String getUserNameFromToken(String token) {
        if (null != token && token.indexOf(" ") > 0) {
            return token.substring(0, token.indexOf(" "));
        }
        return null;
    }

    public int addUser(User user, String token) {
        if (!tokenUserMap.containsKey(token) && !tokenUserMap.containsValue(user)) {
            synchronized (this) {
                if (tokenUserMap.containsKey(token) || tokenUserMap.containsValue(user)) {
                    tokenUserMap.put(token, user);
                } else {
                    return LoginResponse.ALREADY_LOGIN;
                }
            }
            return LoginResponse.SUCCESS;
        } else {
            return LoginResponse.ALREADY_LOGIN;
        }
    }
}
