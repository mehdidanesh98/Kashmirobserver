package com.kashmirobserver.news.view.expanListview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> live = new ArrayList<String>();


        List<String> popular = new ArrayList<String>();
//        popular.add("Sport");
//        popular.add("Politic");
//        popular.add("Science");

        List<String> feature = new ArrayList<String>();
//        feature.add("United States");
//        feature.add("Spain");
//        feature.add("Argentina");
//        feature.add("France");
//        feature.add("Russia");

        expandableListDetail.put("About", feature);
        expandableListDetail.put("Category", popular);
        expandableListDetail.put("My News", live);
        return expandableListDetail;
    }
}
