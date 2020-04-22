package base;

/**
 * CommonUtil
 *
 * @author dengyu
 * @date 2019/11/11
 * @since 1.0.0
 */
public class StringUtil {
    /**
     * @param objects objects
     * @return StringBuilder
     */
    public static StringBuilder stringAppend(Object... objects) {
        StringBuilder stringBuilder = new StringBuilder();
        if (objects != null) {
            int length = objects.length;
            for (int i = 0; i < length; i++) {
                stringBuilder.append(objects[i]);
            }
        }
        return stringBuilder;
    }
}
