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
package com.zs.ina.notice.dao;

import javafx.collections.ObservableList;

/**
 *
 * @author 100035
 */
public class NoticeSERVICE {
   NoticeDAO noticeDAO;

    /**
     *
     * @param noticeDAO
     */
    public NoticeSERVICE(NoticeIMPL noticeDAO) {
        this.noticeDAO=noticeDAO;
    }

    /**
     *
     * @param noticeBEAN
     * @return
     */
    public int insertNotice(NoticeBEAN noticeBEAN) {
       return noticeDAO.insertNotice(noticeBEAN);
    }

    /**
     *
     * @return
     */
    public ObservableList<BranchPOJO> retrieveBranches() {
        return noticeDAO.retrieveBranches();
    }

    /**
     *
     * @return
     */
    public ObservableList<NoticeBEAN> retrieveNotices() {
      return noticeDAO.retrieveNotices();
    }

    /**
     *
     * @param noticeBEAN
     * @return
     */
    public int updateNotice(NoticeBEAN noticeBEAN) {
       return noticeDAO.updateNotice(noticeBEAN);
    }

    /**
     *
     * @param noticeId
     * @return
     */
    public int deleteNotice(String noticeId) {
         return noticeDAO.deleteNotice(noticeId);
    }

   
}
