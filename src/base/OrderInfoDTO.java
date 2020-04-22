package base;

import java.util.Date;

/**
 * 订单信息
 *
 * @author dengyu
 * @date 2019/11/26
 * @since 1.0.0
 */
public class OrderInfoDTO {

    private Integer id;

    private Date serviceEndTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getServiceEndTime() {
        return serviceEndTime;
    }

    public void setServiceEndTime(Date serviceEndTime) {
        this.serviceEndTime = serviceEndTime;
    }
}
