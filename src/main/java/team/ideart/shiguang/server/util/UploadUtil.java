package team.ideart.shiguang.server.util;

import team.ideart.shiguang.server.persist.entity.User;

import java.util.Calendar;
import java.util.UUID;

/**
 * Description
 *
 * @author xccui
 *         Created on 11/21/15.
 */
public class UploadUtil {
    public static String generateFilePath(User user, String fileName) {
        Calendar calendar = Calendar.getInstance();
        StringBuilder builder = new StringBuilder(Constants.UPLOAD_BASE_PATH);
        builder.append("/" + user.getUserName());
        builder.append("/" + calendar.get(Calendar.YEAR));
        builder.append("/" + calendar.get(Calendar.MONTH));
        builder.append("/" + calendar.get(Calendar.DAY_OF_MONTH));
        builder.append("/" + fileName);
        return builder.toString();
    }

    public static String generateFilePath(User user) {
        return generateFilePath(user, UUID.randomUUID().toString());
    }
}
