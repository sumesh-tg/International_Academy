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
package com.zs.ina.common.jsons;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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
/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class JsonParsing {

    /**
     *
     */
    public static Properties properties = null;

    /**
     *
     */
    public static JSONObject jsonObject = null;

    static {
        properties = new Properties();
    }

    /**
     *
     */
    public static void parseJsonToMap() {

        try {

            JSONParser jsonParser = new JSONParser();

            File file = new File("src/newjson.json");

            Object object = jsonParser.parse(new FileReader(file));

            jsonObject = (JSONObject) object;
            /* ======================== Mapp To Hash Map ==================== */
            Map<String, Map<String, List<String>>> map = new HashMap<String, Map<String, List<String>>>();
            ObjectMapper mapper = new ObjectMapper();

            map = mapper.readValue(file, new TypeReference<HashMap>() {
            });

            System.out.println("Map :: " + map);
            for (String key : map.keySet()) {
                Map<String, List<String>> mapInner = map.get(key);
                for (String keyInner : mapInner.keySet()) {
                    List<String> listTag = mapInner.get(keyInner);
                    for (String s : listTag) {
                        System.out.println("Test Single Data :: " + s);
                    }
                    System.out.println("Test Inner Data :: " + mapInner.get(keyInner));
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /**
     *
     * @param filePath
     * @return
     */
    public static Map<String, Map<String, List<String>>> parseJsonToMap(String filePath) {
        Map<String, Map<String, List<String>>> map = new HashMap<String, Map<String, List<String>>>();

        try {

            JSONParser jsonParser = new JSONParser();

            File file = new File(filePath);

            Object object = jsonParser.parse(new FileReader(file));

            jsonObject = (JSONObject) object;
            /* ======================== Mapp To Hash Map ==================== */
            ObjectMapper mapper = new ObjectMapper();

            map = mapper.readValue(file, new TypeReference<HashMap>() {
            });

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return map;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        parseJsonToMap("src/email_heads.json");
    }
}
