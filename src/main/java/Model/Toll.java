package Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Toll {
    private String tollId;
    private List<TollBooth> boothList;
    private Map<TollPass, Integer> tollRateMap;
}
