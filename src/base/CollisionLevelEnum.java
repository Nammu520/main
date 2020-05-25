package base;

import java.util.HashMap;
import java.util.Map;

/**
 * @desc: 碰撞等级枚举
 * @author:yangzhou
 * @date:2019/11/26 14:00
 * @since:1.0.0
 */
public enum CollisionLevelEnum {
    UNKNOWN("未知"),
    A("轻微碰撞"),
    AA("中等碰撞"),
    AAA("较严重事故"),
    AAAA("严重碰撞事故");

    private String desc;

    CollisionLevelEnum(String desc) {
        this.desc = desc;
    }

    /**
     * @return map
     */
    public static Map<String, String> getStatusMap() {
        Map<String, String> map = new HashMap<>();
        for (CollisionLevelEnum status : CollisionLevelEnum.values()) {
            map.put(status.name(), status.desc());
        }
        return map;
    }

    /**
     * @return String
     */
    public String desc() {
        return desc;
    }
}
