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
package com.zs.ina.enquiry.followup;

import java.lang.reflect.Field;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TreeItem;

/**
 *
 * @author 100018
 * @param <T>
 */
public class FilterableTreeItem<T> extends TreeItem<T> {
    final private ObservableList<TreeItem<T>> sourceList;
    private FilteredList<TreeItem<T>> filteredList;
 
    /**
     *
     * @param value
     */
    public FilterableTreeItem(T value) {
        super(value);
        this.sourceList = FXCollections.observableArrayList();
        this.filteredList = new FilteredList<>(this.sourceList);
        setHiddenFieldChildren(this.filteredList); 
    }
 
    /**
     *
     * @param list
     */
    protected void setHiddenFieldChildren(ObservableList<TreeItem<T>> list) {
        try {
            Field childrenField = TreeItem.class.getDeclaredField("children"); //$NON-NLS-1$
            childrenField.setAccessible(true);
            childrenField.set(this, list);
 
            Field declaredField = TreeItem.class.getDeclaredField("childrenListener"); //$NON-NLS-1$
            declaredField.setAccessible(true);
            list.addListener((ListChangeListener<? super TreeItem<T>>) declaredField.get(this));
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException("Could not set TreeItem.children", e); //$NON-NLS-1$
        }
    }
 
    /**
     *
     * @return
     */
    public ObservableList<TreeItem<T>> getInternalChildren() {
        return this.sourceList;
    }
}