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
package com.zs.ina.search.training.courses;

import com.zs.ina.search.common.bean.CampusBEAN;
import com.zs.ina.search.training.dao.TrainingBEAN;
import javafx.collections.ObservableList;

/**
 *
 * @author 100035
 */
public interface TrainReqmtBasicDAO {

    //  public boolean insertTrainingCourseReqmtBasics(TrainingBEAN trainingBEAN);
    public boolean updateTrainingCourseReqmtBasics(TrainingBEAN trainingBEAN);

    public boolean deleteTrainingCourseReqmtBasics(TrainingBEAN trainingBEAN);

    public ObservableList<TrainingBEAN> retrieveTrainingCourseReqmtBasics();

    public TrainingBEAN retrieveSelectedTrainingCourseReqmtBasics(String trainingId);

  //  public ObservableList<CampusBEAN> retrieveTrainCampuses(String trainingId);
    //  public ObservableList<TrainingBEAN> retrieveTrainingCourseReqmt(String trainingId);
}
