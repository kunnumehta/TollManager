
import Model.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Driver {

    public static void main(String[] args) throws Exception{
        Toll toll1 = new Toll("T1",new ArrayList<>(), new HashMap<>());

        TollBooth booth1 = new TollBooth(0, 1,20);
        TollBooth booth2 = new TollBooth(1, 2,10);
        TollBooth booth3 = new TollBooth(2, 3,30);
        toll1.getBoothList().add(booth1);
        toll1.getBoothList().add(booth2);
        toll1.getBoothList().add(booth3);

        TollPass tp1 = new TollPass(PassType.SINGLE, VehicleType.TWO_WHEELER);
        TollPass tp2 = new TollPass(PassType.SINGLE, VehicleType.FOUR_WHEELER);
        TollPass tp3 = new TollPass(PassType.RETURN, VehicleType.TWO_WHEELER);
        TollPass tp4 = new TollPass(PassType.RETURN, VehicleType.FOUR_WHEELER);
        TollPass tp5 = new TollPass(PassType.SEVEN_DAY, VehicleType.TWO_WHEELER);
        TollPass tp6 = new TollPass(PassType.SEVEN_DAY, VehicleType.FOUR_WHEELER);
        toll1.getTollRateMap().put(tp1, 10);
        toll1.getTollRateMap().put(tp2, 30);
        toll1.getTollRateMap().put(tp3, 20);
        toll1.getTollRateMap().put(tp4, 60);
        toll1.getTollRateMap().put(tp5, 100);
        toll1.getTollRateMap().put(tp6, 200);

        Vehicle v1 = new Vehicle("V1", VehicleType.TWO_WHEELER,new HashMap<>());
        Vehicle v2 = new Vehicle("V2", VehicleType.FOUR_WHEELER, new HashMap<>());

        VehiclePass vp1 = new VehiclePass(PassType.RETURN, VehicleType.TWO_WHEELER, System.currentTimeMillis() - 85400000);
        v1.getTollPassMap().put(toll1,vp1);

        TollManager tollManager = new TollManager();
        tollManager.printLeaderBoard(toll1);

        System.out.println();

        tollManager.letVehicle(v1,toll1,2);

        System.out.println();

        tollManager.printLeaderBoard(toll1);

        tollManager.letVehicle(v1,toll1,1);
        System.out.println();

        if(!tollManager.letVehicle(v2, toll1,0)){
            tollManager.chooseOnePass(v2, PassType.SINGLE,0,toll1);
            tollManager.chooseOnePass(v2, PassType.SINGLE,0,toll1);
        }

        tollManager.letVehicle(v2,toll1,1);
        System.out.println();
        tollManager.printLeaderBoard(toll1);
    }
}
