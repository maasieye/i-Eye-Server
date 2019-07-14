package kr.seoulmaas.ieye.service.utill.comparator;

import kr.seoulmaas.ieye.service.dto.busStop.body.BusItem;

import java.util.Comparator;

public class SizeComparator implements Comparator<BusItem> {

    @Override
    public int compare(BusItem o1, BusItem o2) {
        int size1 = o1.getPathList()
                .size();
        int size2 = o2.getPathList()
                .size();
        return Integer.compare(size1, size2);
    }

}
