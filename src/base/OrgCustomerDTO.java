package base;

import java.util.Date;

/**
 * OrgCustomerDTO
 *
 * @author dengyu
 * @date 2019/10/30
 * @since 1.0.0
 */
public class OrgCustomerDTO {

    // 组织id
    private Integer orgId;

    private Date firstSourceTime;

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Date getFirstSourceTime() {
        return firstSourceTime;
    }

    public void setFirstSourceTime(Date firstSourceTime) {
        this.firstSourceTime = firstSourceTime;
    }
}
