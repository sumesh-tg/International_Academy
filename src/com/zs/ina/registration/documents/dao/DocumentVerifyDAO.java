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
package com.zs.ina.registration.documents.dao;

import com.zs.ina.common.email.MailSentPOJO;
import java.util.List;

/**
 *
 * @author SUMESH T.G <ZoftSolutions>
 */
public interface DocumentVerifyDAO {

    /**
     *
     * @param documentVerfiyBEAN
     * @return
     */
    public int insertDocumentVerfications(DocumentVerifyBEAN documentVerfiyBEAN);

    /**
     *
     * @param documentVerfiyBEAN
     * @return
     */
    public int updateDocumentVerfications(DocumentVerifyBEAN documentVerfiyBEAN);

    /**
     *
     * @param documentPhotoBEAN
     * @return
     */
    public int updatePhotoDocumentVerfication(DocumentPhotoBEAN documentPhotoBEAN);

    /**
     *
     * @param verifyId
     * @return
     */
    public int deleteDocumentVerfications(String verifyId);

    /**
     *
     * @param program
     * @param enquiryId
     * @return
     */
    public List<DocumentVerifyBEAN> retrieveDocumentVerfications(String program, String enquiryId);

    /**
     *
     * @param documentPhotoBEAN
     * @return
     */
    public int insertPhotoDocumentVerfication(DocumentPhotoBEAN documentPhotoBEAN);

    /**
     *
     * @param enquiryId
     * @return
     */
    public DocumentPhotoBEAN retrievePhotoDocumentVerfications(String enquiryId);

    /**
     *
     * @param enquiryId
     * @return
     */
    public List<MailSentPOJO> retrieveLogDetails(String enquiryId);

    /**
     *
     * @param enquiryId
     * @return
     */
    public int deletePhotoDocumentVerfication(String enquiryId);
    
   // public List<MailSentPOJO> retrieveLogDetails(String enquiryId);
    
    
    
    
  //  public int countNotifications(String enquiryId);

}
