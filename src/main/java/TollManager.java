import Model.*;

import java.util.List;
import java.util.Map;

public class TollManager {

    public boolean letVehicle(Vehicle vehicle, Toll toll, Integer boothNumber){
        TollBooth booth = toll.getBoothList().get(boothNumber);
        Integer numberOfVehicles = booth.getVehiclesProcessed() + 1;
        booth.setVehiclesProcessed(numberOfVehicles);
        toll.getBoothList().set(boothNumber, booth);
        VehiclePass tollPass = vehicle.getTollPassMap().get(toll);
        if(isPassValid(tollPass)) {
            System.out.println("Allow vehicle to pass");
            return true;
        } else {
            displayCharges(vehicle, toll);
            return false;
        }
    }

    private boolean isPassValid(VehiclePass tollPass){
        Long timeNow = System.currentTimeMillis();
        if(tollPass == null) return false;
        if(tollPass.getType().equals(PassType.SINGLE)){
            return false;
        } else if (tollPass.getType().equals(PassType.RETURN)){
            if(timeNow - tollPass.getTimestampOfPurchase() <= 86400000){
                return true;
            }
        } else if(tollPass.getType().equals(PassType.SEVEN_DAY)){
            if(timeNow - tollPass.getTimestampOfPurchase() <= 604800000){
                return true;
            }
        }
        return false;
    }

    private void displayCharges(Vehicle vehicle, Toll toll){
        for(Map.Entry<TollPass, Integer> tollRates : toll.getTollRateMap().entrySet()){
            if(tollRates.getKey().getVehicleType().equals(vehicle.getType())){
                System.out.println("Rate for pass : " + tollRates.getKey().getType() + " = " + tollRates.getValue());
            }
        }
    }

    public void chooseOnePass(Vehicle vehicle, PassType type, Integer boothId, Toll toll){
        VehiclePass vehiclePass = new VehiclePass(type, vehicle.getType(), System.currentTimeMillis());
        vehicle.getTollPassMap().put(toll, vehiclePass);
        TollBooth booth = toll.getBoothList().get(boothId);
        TollPass tollPass = null;
        for(Map.Entry<TollPass, Integer> tollRate : toll.getTollRateMap().entrySet()){
            if(tollRate.getKey().getVehicleType().equals(vehicle.getType()) && tollRate.getKey().getType().equals(type)){
                tollPass = tollRate.getKey();
            }
        }
        if(tollPass!=null) {
            Integer rate = toll.getTollRateMap().get(tollPass);
            booth.setCollection(booth.getCollection() + rate);
            toll.getBoothList().set(boothId, booth);
        } else {
            System.out.println("No toll pass exists for this vehicle type");
        }
    }

    public void printLeaderBoard(Toll toll){
        List<TollBooth> boothList = toll.getBoothList();
        boothList.sort((b1, b2) -> b2.getVehiclesProcessed() - b1.getVehiclesProcessed());
        for(TollBooth booth : boothList){
            System.out.println("booth " + booth.getBoothNumber() + " vehicles processed " + booth.getVehiclesProcessed() + " collection " + booth.getCollection());
        }
        boothList.sort((b1, b2) -> b2.getCollection() - b1.getCollection());
        for(TollBooth booth : boothList){
            System.out.println("booth " + booth.getBoothNumber() + " collection " + booth.getCollection() + " vehicles processed " + booth.getVehiclesProcessed());
        }
    }
}
