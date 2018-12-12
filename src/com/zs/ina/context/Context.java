/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zs.ina.context;

/**
 *
 * @author 100018
 */
public class Context {

    private final static Context instance = new Context();

    /**
     *
     * @return
     */
    public static Context getInstance() {
        return instance;
    }

    private CollectionPOJO collectionPOJO = new CollectionPOJO();

    /**
     *
     * @return
     */
    public CollectionPOJO currentProfile() {
        return collectionPOJO;
    }

}
