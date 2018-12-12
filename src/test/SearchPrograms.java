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
import com.zs.ina.search.training.dao.TrainingBEAN;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 100035
 */
public class SearchPrograms {

    private static void Dispaly() {

        Map<String, Object> GeneralMap = new HashMap<>();

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
        GeneralMap.put("Study", mapEduCampuses);

        TrainingBEAN trainingBEAN_102 = new TrainingBEAN();
        trainingBEAN_102.setTrainingId("trainingBEAN_102_id");
        trainingBEAN_102.setTrainingCourse("Training_102");
        trainingBEAN_102.setCommission("T_commission_102");

        TrainingBEAN trainingBEAN_103 = new TrainingBEAN();
        trainingBEAN_103.setTrainingId("trainingBEAN_103_id");
        trainingBEAN_103.setTrainingCourse("Training_103");
        trainingBEAN_103.setCommission("T_commission_103");

        CampusBEAN TcampusBEAN_1_1 = new CampusBEAN();
        TcampusBEAN_1_1.setRowId("T_101");
        TcampusBEAN_1_1.setCampus("T_Campus_101");
        TcampusBEAN_1_1.setCollegeId("T_EduProvider_101");

        CampusBEAN TcampusBEAN_2_1 = new CampusBEAN();
        TcampusBEAN_2_1.setRowId("T_102");
        TcampusBEAN_2_1.setCampus("T_Campus_102");
        TcampusBEAN_2_1.setCollegeId("T_EduProvider_102");

        CampusBEAN TcampusBEAN_3_1 = new CampusBEAN();
        TcampusBEAN_3_1.setRowId("T_103");
        TcampusBEAN_3_1.setCampus("T_Campus_103");
        TcampusBEAN_3_1.setCollegeId("T_EduProvider_103");

        CampusBEAN TcampusBEAN_2_2 = new CampusBEAN();
        TcampusBEAN_2_2.setRowId("T_104");
        TcampusBEAN_2_2.setCampus("T_Campus_104");
        TcampusBEAN_2_2.setCollegeId("T_EduProvider_102");

        CampusBEAN TcampusBEAN_3_2 = new CampusBEAN();
        TcampusBEAN_3_2.setRowId("T_105");
        TcampusBEAN_3_2.setCampus("T_Campus_105");
        TcampusBEAN_3_2.setCollegeId("T_EduProvider_103");
        List<CampusBEAN> campusDetailsList_ONE_TRAIN = new ArrayList<>();

        CampusBEAN TcampusBEAN_3_3 = new CampusBEAN();
        TcampusBEAN_3_3.setRowId("T_106");
        TcampusBEAN_3_3.setCampus("T_Campus_106");
        TcampusBEAN_3_3.setCollegeId("T_EduProvider_103");

        campusDetailsList_ONE_TRAIN.clear();
        campusDetailsList_ONE_TRAIN.add(TcampusBEAN_2_1);
        campusDetailsList_ONE_TRAIN.add(TcampusBEAN_2_2);

        Map<TrainingBEAN, List<CampusBEAN>> mapTrainCampuses = new HashMap<>();
        mapTrainCampuses.clear();
        mapTrainCampuses.put(trainingBEAN_102, campusDetailsList_ONE_TRAIN);
        List<CampusBEAN> campusDetailsList_TWO_TRAIN = new ArrayList<>();
        campusDetailsList_TWO_TRAIN.clear();
        campusDetailsList_TWO_TRAIN.add(TcampusBEAN_3_1);
        campusDetailsList_TWO_TRAIN.add(TcampusBEAN_3_2);
        campusDetailsList_TWO_TRAIN.add(TcampusBEAN_3_3);
        mapTrainCampuses.put(trainingBEAN_103, campusDetailsList_TWO_TRAIN);
        GeneralMap.put("Training", mapTrainCampuses);

        Map<EduProviderBEAN, List<CampusBEAN>> mapObjStudyCampuses = (Map<EduProviderBEAN, List<CampusBEAN>>) GeneralMap.get("Study");
        for (Map.Entry<EduProviderBEAN, List<CampusBEAN>> entry : mapObjStudyCampuses.entrySet()) {
            System.out.printf("\n STUDY Inner KEY : "+entry.getKey());
            List<CampusBEAN> listInner = entry.getValue();
            System.out.println("\n STUDY Inner List :: " + listInner.toString());
        }
        Map<TrainingBEAN, List<CampusBEAN>> mapObjTrainCampuses = (Map<TrainingBEAN, List<CampusBEAN>>) GeneralMap.get("Training");
        for (Map.Entry<TrainingBEAN, List<CampusBEAN>> entry2 : mapObjTrainCampuses.entrySet()) {
            System.out.printf("\n TRAIN Inner KEY : "+entry2.getKey());
            List<CampusBEAN> listInner = entry2.getValue();
            System.out.println("\n TRAIN Inner List :: " + listInner.toString());
        }

    }

    public static void main(String[] args) {
        Dispaly();
    }
}
