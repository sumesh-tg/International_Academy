/*
 * Copyright (C) 2016 100018
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

import com.zs.ina.counselor.dao.model.CounselorDetailsBEAN;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author 100018
 */
public class copyBean {

    /**
     *
     * @param args
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        CounselorDetailsBEAN counselorDetailsBEAN = new CounselorDetailsBEAN();
        counselorDetailsBEAN.setEnquiryID("123");
        counselorDetailsBEAN.setContactName("name");
        counselorDetailsBEAN.setContactEmail(null);
        CounselorDetailsBEAN counselorDetailsBEAN2 = new CounselorDetailsBEAN();
        counselorDetailsBEAN2.setEnquiryID("123");
        counselorDetailsBEAN2.setContactEmail(null);
        BeanUtils.copyProperties(counselorDetailsBEAN2, counselorDetailsBEAN);
        System.out.println("test :: " + counselorDetailsBEAN2.toString());
    }
}
