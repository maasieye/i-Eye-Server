package kr.seoulmaas.ieye.service.utill;

import kr.seoulmaas.ieye.service.dto.busStop.body.BusItem;

import java.util.Comparator;

public class DistanceComparator implements Comparator<BusItem> {
    @Override
    public int compare(BusItem b1, BusItem b2) {
        int distance1 = Integer.valueOf(b1.getDistance());
        int distance2 = Integer.valueOf(b2.getDistance());
        return Integer.compare(distance1, distance2);
    }
}
