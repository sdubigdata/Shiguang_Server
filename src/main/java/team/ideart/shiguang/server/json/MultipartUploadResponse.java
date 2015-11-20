package team.ideart.shiguang.server.json;

/**
 * Description
 *
 * @author xccui
 *         Created on 11/21/15.
 */
public class MultipartUploadResponse extends AResponse {
    private String MultipartPath;

    public String getMultipartPath() {
        return MultipartPath;
    }

    public void setMultipartPath(String multipartPath) {
        MultipartPath = multipartPath;
    }

}
