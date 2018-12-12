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
package com.zs.ina.admin.master.payment.dao;

/**
 *
 * @author 100035
 */
public class PaymentPOJO {
    String head_id;
    String head;

    /**
     *
     * @return
     */
    public String getHead_id() {
        return head_id;
    }

    /**
     *
     * @param head_id
     */
    public void setHead_id(String head_id) {
        this.head_id = head_id;
    }

    /**
     *
     * @return
     */
    public String getHead() {
        return head;
    }

    /**
     *
     * @param head
     */
    public void setHead(String head) {
        this.head = head;
    }

    @Override
    public String toString() {
        return head;
    }
    
}
