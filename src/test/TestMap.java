/*
 * Copyright ZoftSolutions(C) 2016 SUMESH T.G <ZoftSolutions>
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
 *  Developed by ZoftSolutions (2015) Company.
 */
package test;

import com.zs.ina.counselor.dao.model.CounselorDetailsBEAN;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class TestMap {

    public static void main(String[] args) {
        Map<String, Object> mapTest = new HashMap<>();
        CounselorDetailsBEAN detailsBEAN = new CounselorDetailsBEAN();
        detailsBEAN.setEnquiryID("id_haiiiii");
        mapTest.put("test1", detailsBEAN);
        mapTest.put("test2", "hii");
        mapTest.put("test3", "2");
        CounselorDetailsBEAN outputBEAN = (CounselorDetailsBEAN) mapTest.get("test1");
        System.out.println("test class ::" + outputBEAN.toString());
        String s = (String) mapTest.get("test2");
        System.out.println("test string ::" + s);
        int val = Integer.parseInt( mapTest.get("test3").toString());
        System.out.println("test int ::" + val);
    }

}
