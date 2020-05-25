package base;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 大数据推送里程消息DTO
 *
 * @author dengyu
 * @date 2020/3/4
 * @since 1.0.0
 */
public class MileageMessageDTO {

    @JsonProperty("vehicle_id")
    private Long vehicleId;

    private Long mileage;

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getMileage() {
        return mileage;
    }

    public void setMileage(Long mileage) {
        this.mileage = mileage;
    }
}
