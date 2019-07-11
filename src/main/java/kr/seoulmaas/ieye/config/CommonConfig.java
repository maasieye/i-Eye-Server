package kr.seoulmaas.ieye.config;

import java.util.ArrayList;
import java.util.List;

public class CommonConfig {

    public static List getCheckedList(List maybeList) {
        if (maybeList == null) {
            return new ArrayList();
        }
        return maybeList;
    }
}
