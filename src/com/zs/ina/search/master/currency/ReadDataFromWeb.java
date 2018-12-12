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

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public class ReadDataFromWeb {

    /**
     *
     * @param base
     * @return
     */
    public static CurrencyRemotePOJO request(String base) {
        CurrencyRemotePOJO currencyRemote = new CurrencyRemotePOJO();
        try {
            ObjectMapper mapper = new ObjectMapper();
            URL RT = new URL("http://api.fixer.io/latest?base=" + base).toURI().toURL();
            currencyRemote = mapper.readValue(RT, CurrencyRemotePOJO.class);
            System.out.println("Data Fetched From Internet " + currencyRemote.toString());
        } catch (MalformedURLException ex) {
            Logger.getLogger(ReadDataFromWeb.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(ReadDataFromWeb.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } catch (URISyntaxException ex) {
            Logger.getLogger(ReadDataFromWeb.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return currencyRemote;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            URL RT = new URL("http://api.fixer.io/latest?base=USD").toURI().toURL();
            CurrencyRemotePOJO currencyRemote = mapper.readValue(RT, CurrencyRemotePOJO.class);
            System.out.println("" + currencyRemote.toString());
        } catch (IOException ex) {
            Logger.getLogger(ReadDataFromWeb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(ReadDataFromWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Test Method " + request("INR").toString());
    }

    /**
     *
     * @param site
     * @return
     */
    public static boolean testInet(String site) {
        Socket sock = new Socket();
        InetSocketAddress addr = new InetSocketAddress(site, 80);
        try {
            sock.connect(addr, 3000);
            return true;
        } catch (IOException e) {
            return false;
        } finally {
            try {
                sock.close();
            } catch (IOException e) {
            }
        }
    }
}
