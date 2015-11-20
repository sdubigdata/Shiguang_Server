package team.ideart.shiguang.server.json;

/**
 * Description
 *
 * @author xccui
 *         Created on 11/21/15.
 */
public class AResponse {
    public static final int SUCCESS = 0;
    public static final int LOGIN = 1;
    public static final int ERROR = -1;
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
