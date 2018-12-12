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
package com.zs.ina.search.master.currency;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyRemotePOJO {

    String base, date;
    Map<String, String> rates = new HashMap<String, String>();

    /**
     *
     * @return
     */
    public String getBase() {
        return base;
    }

    /**
     *
     * @param base
     */
    public void setBase(String base) {
        this.base = base;
    }

    /**
     *
     * @return
     */
    public String getDate() {
        return date;
    }

    /**
     *
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     *
     * @return
     */
    public Map<String, String> getRates() {
        return rates;
    }

    /**
     *
     * @param rates
     */
    public void setRates(Map<String, String> rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        return "CurrencyRemote{" + "base=" + base + ", date=" + date + ", rates=" + rates + '}';
    }

}
