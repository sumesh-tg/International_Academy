/*
 * Copyright (C) 2016 100035
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package test;

import com.zs.ina.search.colleges.dao.EduProviderBEAN;
import com.zs.ina.search.common.bean.CampusBEAN;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static javafx.application.Application.launch;

/**
 *
 * @author 100035
 */
public class EduProviderCampuses {

    private static void Dispaly() {
        EduProviderBEAN eduProviderBEAN_102 = new EduProviderBEAN();
        eduProviderBEAN_102.setCollegeId("eduProviderBEAN_102_id");
        eduProviderBEAN_102.setCollege("EduProvider_102");
        eduProviderBEAN_102.setCommission("commission_102");

        EduProviderBEAN eduProviderBEAN_103 = new EduProviderBEAN();
        eduProviderBEAN_103.setCollegeId("eduProviderBEAN_103_id");
        eduProviderBEAN_103.setCollege("EduProvider_103");
        eduProviderBEAN_103.setCommission("commission_103");

        CampusBEAN campusBEAN_1_1 = new CampusBEAN();
        campusBEAN_1_1.setRowId("101");
        campusBEAN_1_1.setCampus("Campus_101");
        campusBEAN_1_1.setCollegeId("EduProvider_101");

        CampusBEAN campusBEAN_2_1 = new CampusBEAN();
        campusBEAN_2_1.setRowId("102");
        campusBEAN_2_1.setCampus("Campus_102");
        campusBEAN_2_1.setCollegeId("EduProvider_102");

        CampusBEAN campusBEAN_3_1 = new CampusBEAN();
        campusBEAN_3_1.setRowId("103");
        campusBEAN_3_1.setCampus("Campus_103");
        campusBEAN_3_1.setCollegeId("EduProvider_103");

        CampusBEAN campusBEAN_2_2 = new CampusBEAN();
        campusBEAN_2_2.setRowId("104");
        campusBEAN_2_2.setCampus("Campus_104");
        campusBEAN_2_2.setCollegeId("EduProvider_102");

        CampusBEAN campusBEAN_3_2 = new CampusBEAN();
        campusBEAN_3_2.setRowId("105");
        campusBEAN_3_2.setCampus("Campus_105");
        campusBEAN_3_2.setCollegeId("EduProvider_103");
        List<CampusBEAN> campusDetailsList = new ArrayList<>();

        CampusBEAN campusBEAN_3_3 = new CampusBEAN();
        campusBEAN_3_3.setRowId("106");
        campusBEAN_3_3.setCampus("Campus_106");
        campusBEAN_3_3.setCollegeId("EduProvider_103");

        campusDetailsList.clear();
        campusDetailsList.add(campusBEAN_2_1);
        campusDetailsList.add(campusBEAN_2_2);

        Map<EduProviderBEAN, List<CampusBEAN>> mapEduCampuses = new HashMap<>();
        mapEduCampuses.clear();
        mapEduCampuses.put(eduProviderBEAN_102, campusDetailsList);
         List<CampusBEAN> campusDetailsList2 = new ArrayList<>();
        campusDetailsList2.clear();
        campusDetailsList2.add(campusBEAN_3_1);
        campusDetailsList2.add(campusBEAN_3_2);
        campusDetailsList2.add(campusBEAN_3_3);
        mapEduCampuses.put(eduProviderBEAN_103, campusDetailsList2);
        Map<String, Map<EduProviderBEAN, List<CampusBEAN>>> FinalMap = new HashMap<>();
        FinalMap.clear();
        FinalMap.put("Study", mapEduCampuses);
        FinalMap.put("Work", mapEduCampuses);

        for (Map.Entry<String, Map<EduProviderBEAN, List<CampusBEAN>>> entry : FinalMap.entrySet()) {
            System.out.println("");
            System.out.printf("Outer KEY : " + entry.getKey() + "\n Outer VALUE :" + entry.getValue());
            Map<EduProviderBEAN, List<CampusBEAN>> mapInner = entry.getValue();
            for (Map.Entry<EduProviderBEAN, List<CampusBEAN>> entryInner : mapInner.entrySet()) {
                System.out.printf("\n Iner KEY : " );
                List<CampusBEAN> listInner = entryInner.getValue();
                System.out.println("\n Inner List :: " + listInner.toString());
            }

        }

    }

    public static void main(String[] args) {
        Dispaly();
    }
}
