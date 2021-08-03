package Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehiclePass extends TollPass{
    private Long timestampOfPurchase;


    public VehiclePass(PassType type, VehicleType type1, long currentTimeMillis) {
        super(type,type1);
        this.timestampOfPurchase = currentTimeMillis;
    }
}
