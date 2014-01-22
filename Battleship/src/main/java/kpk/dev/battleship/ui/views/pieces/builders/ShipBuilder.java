package kpk.dev.battleship.ui.views.pieces.builders;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import kpk.dev.battleship.ui.views.pieces.ShipData;

/**
 * Created by krasimir.karamazov on 1/22/14.
 */
public class ShipBuilder {
    public enum Orientation{
        VERTICAL, HORIZONTAL;
    }
    public enum ShipType{
        Carrier(5, "Aircraft Carrier"), Battleship(4, "Battleship"), Cruiser(3, "Cruiser"), Destroyer(2, "Destroyer"), Submarine(1, "Submarine");
        private int numCells;
        private String shipName;
        ShipType(int num, String name) {
            numCells = num;
            shipName = name;
        }

        public int getNumCells() {
            return numCells;
        }

        public String getShipName() {
            return shipName;
        }
    }

    public static ShipData build(ShipType shipType, Orientation orientation) {
        final ShipData data = new ShipData();
        data.setName(shipType.getShipName());
        data.setNumSquares(shipType.getNumCells());
        data.setOrientation(orientation);
        return data;
    }

    public static Map<String, ShipData> buildFleet() {
        final ShipType[] types = ShipType.values();
        final Map<String, ShipData> fleet = new TreeMap<String, ShipData>();
        for(ShipType type : types) {
            int orientation=(Math.random()<0.5)?0:1;
            fleet.put(type.getShipName(), build(type, (orientation == 0)?Orientation.HORIZONTAL:Orientation.VERTICAL));
        }
        return fleet;
    }

}
