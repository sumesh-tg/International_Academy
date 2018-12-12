/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.common;

import java.util.UUID;

/**
 *
 * @author sumesh
 */
public class UiiDGenerator {

    /**
     *
     * @return
     */
    public static final String getUIID() {
        // creating UUID      
        UUID uuid = UUID.randomUUID();
        uuid.getLeastSignificantBits();
        String randomUUIDString = uuid.toString();
        return randomUUIDString;
    }

    /**
     *
     * @return
     */
    public static final String getUIID8() {
        // creating UUID      
        UUID uuid = UUID.randomUUID();
        uuid.getLeastSignificantBits();
        String randomUUIDString = uuid.toString();
        randomUUIDString = randomUUIDString.substring(randomUUIDString.lastIndexOf('-') + 1);
        return randomUUIDString;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("djfdg" + UiiDGenerator.getUIID());
        String s = getUIID8();
      //  s = s.substring(s.lastIndexOf('-') + 1);
        System.out.println("test" + s);
    }

}
