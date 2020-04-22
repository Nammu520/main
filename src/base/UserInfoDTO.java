package base;

import java.util.Date;

/**
 * 角色详情用户列表
 *
 * @author 王晗
 * @date 2019/10/21 14:51
 */
public class UserInfoDTO {

    private Long id;

    private String phone;

    private String username;

    private String name;

    private Boolean active;

    private String category;

    private Integer lastLoginOrg;

    private String ownOrgName;

    private Date createTime;

    private String roleName;

    private Long orgId;

    private Boolean superAdmin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getLastLoginOrg() {
        return lastLoginOrg;
    }

    public void setLastLoginOrg(Integer lastLoginOrg) {
        this.lastLoginOrg = lastLoginOrg;
    }

    public String getOwnOrgName() {
        return ownOrgName;
    }

    public void setOwnOrgName(String ownOrgName) {
        this.ownOrgName = ownOrgName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Boolean getSuperAdmin() {
        return superAdmin;
    }

    public void setSuperAdmin(Boolean superAdmin) {
        this.superAdmin = superAdmin;
    }
}
