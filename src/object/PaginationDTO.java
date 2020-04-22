package object;

import com.alibaba.fastjson.JSON;

public class PaginationDTO<T> {

    /**
     * 分页总数
     */
    private Long total;

    /**
     * 每一页的list
     */
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String json(){
        return JSON.toJSONString(this);
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "PaginationDTO{" +
                "total=" + total +
                ", name=" + name +
                '}';
    }
}
