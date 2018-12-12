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
package com.zs.ina.registration.documents.dao;

import java.io.InputStream;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author 100035
 */
public class DocumentPhotoBEAN {

    private final StringProperty photoId = new SimpleStringProperty();
    private final StringProperty photo = new SimpleStringProperty();
    private final StringProperty photoStatus = new SimpleStringProperty();
    private final StringProperty enquiryId = new SimpleStringProperty();
      InputStream inputStream=null;

    /**
     *
     * @return
     */
    public InputStream getInputStream() {
        return inputStream;
    }

    /**
     *
     * @param inputStream
     */
    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    /**
     *
     * @return
     */
    public String getPhotoStatus() {
        return photoStatus.get();
    }

    /**
     *
     * @param value
     */
    public void setPhotoStatus(String value) {
        photoStatus.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty photoStatusProperty() {
        return photoStatus;
    }

    /**
     *
     * @return
     */
    public String getEnquiryId() {
        return enquiryId.get();
    }

    /**
     *
     * @param value
     */
    public void setEnquiryId(String value) {
        enquiryId.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty enquiryIdProperty() {
        return enquiryId;
    }

    /**
     *
     * @return
     */
    public String getPhoto() {
        return photo.get();
    }

    /**
     *
     * @param value
     */
    public void setPhoto(String value) {
        photo.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty photoProperty() {
        return photo;
    }

    /**
     *
     * @return
     */
    public String getPhotoId() {
        return photoId.get();
    }

    /**
     *
     * @param value
     */
    public void setPhotoId(String value) {
        photoId.set(value);
    }

    /**
     *
     * @return
     */
    public StringProperty photoIdProperty() {
        return photoId;
    }

    @Override
    public String toString() {
        return "DocumentPhotoBEAN{" + "photoId=" + photoId + ", photo=" + photo + ", photoStatus=" + photoStatus + ", enquiryId=" + enquiryId + '}';
    }

}
