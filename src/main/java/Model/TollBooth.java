package Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TollBooth {
    private Integer boothNumber;
    private Integer vehiclesProcessed;
    private Integer collection;
}
