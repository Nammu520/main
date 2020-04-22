package base;

/**
 * 通用常量类
 *
 * @author dengyu
 * @date 2019/11/5
 * @since 1.0.0
 */
public final class CommonConstant {
    private CommonConstant() {

    }

    /**
     * 登录用户参数，@LoginUser注解使用
     */
    public static final String LOGIN_USER_PARAM = "Login-User-Encode";

    /**
     * Http Header中的 X-Trace-ID
     */
    public static final String HTTP_HEADER_KEY_TRACE_ID = "X-Trace-ID";

    /**
     *
     */
    public static class SpecialSymbol {
        public static final String UNDERLINE = "_";
        public static final String TRANSVERSE_LINE = "-";
        public static final String PLACEHOLDER = "";
        public static final String SPACE = " ";
        public static final String NULL = "null";
        public static final String QUOTES = "\"";
        public static final String SEMICOLON = ";";
        public static final String COLON = ":";
        public static final String DOT = "\\.";
        public static final String VERT = "|";
        public static final String VERTICAL = "\\|";
        public static final String COMMA = ",";
        public static final String SLASH = "/";
        public static final String UNKNOWN = "unknown";
        public static final String ELLIPSIS = "...";
        public static final String TABLE = "\t";
        public static final String STAR = "*";
        public static final String EQUAL = "=";
        public static final String AND = "&";
        public static final String NULL_LINE = "--";
        public static final String LEFT_PARENTHESES = "(";
        public static final String RIGHT_PARENTHESES = ")";
    }

    public static class HttpMethod {
        public static final String GET = "get";
        public static final String PUT = "put";
        public static final String POST = "post";
        public static final String DELETE = "delete";
        public static final String PATCH = "patch";
    }

    /**
     * 日期时间
     */
    public static final String[] DATETIME =
            new String[]{"yyyy-MM-dd'T'HH:mm:ssZ", "yyyy-MM-dd'T'HH:mm:ssXXX", "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
                    "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "yyyy-MM-dd'T'HH:mm:ss'Z'",
                    "yyyy-MM-dd", "yyyy-MM", "yyyy-MM-dd HH:mm", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.S"};
}
