package team.ideart.shiguang.server.json;

/**
 * Description
 *
 * @author xccui
 *         Created on 11/20/15.
 */
public class LoginResponse {
    public static final int SUCCESS = 0;
    public static final int WRONG_INFO = 1;
    public static final int ALREADY_LOGIN = 2;
    private int code;
    private String token;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
