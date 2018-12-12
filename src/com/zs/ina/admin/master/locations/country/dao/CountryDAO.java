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
package com.zs.ina.admin.master.locations.country.dao;

import javafx.collections.ObservableList;

/**
 *
 * @author 100035
 */
public interface CountryDAO {
    
  /* ============== Country =========== */

    /**
     *
     * @param countryBEAN
     * @return
     */

  public int insertCountry(CountryBEAN countryBEAN);

    /**
     *
     * @param countryBEAN
     * @return
     */
    public int deleteCountry(CountryBEAN countryBEAN);
  
  
  /* ============== Commom methods for country combobox  =========== */

    /**
     *
     * @return
     */

  public ObservableList<String> loadcmbCountryCommon();
  
  
   /* ============== State =========== */

    /**
     *
     * @param countryBEAN
     * @return
     */

  public int insertState(CountryBEAN countryBEAN);

    /**
     *
     * @param countryBEAN
     * @return
     */
    public int deleteState(CountryBEAN countryBEAN);

    /**
     *
     * @return
     */
    public ObservableList<String> loadcmbStateDel();

    /**
     *
     * @param country
     * @return
     */
    public ObservableList<String> loadcmbStateChoose(String country);
  
 
  /* ============== District =========== */

    /**
     *
     * @param countryBEAN
     * @return
     */

  
  public int insertDistrict(CountryBEAN countryBEAN);

    /**
     *
     * @return
     */
    public ObservableList<String> loadcmbDistrictDel();

    /**
     *
     * @param countryBEAN
     * @return
     */
    public int deleteDistrict(CountryBEAN countryBEAN);
  
   
   /* ============== Location Table =========== */

    /**
     *
     * @return
     */

  
 
  public ObservableList<CountryBEAN> retrieveLocations();

}
