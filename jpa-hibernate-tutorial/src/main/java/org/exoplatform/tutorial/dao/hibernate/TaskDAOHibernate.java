/* 
* Copyright (C) 2003-2015 eXo Platform SAS.
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU Affero General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU Affero General Public License for more details.
*
* You should have received a copy of the GNU Affero General Public License
* along with this program. If not, see http://www.gnu.org/licenses/ .
*/
package org.exoplatform.tutorial.dao.hibernate;

import org.exoplatform.tutorial.dao.TaskDAO;
import org.exoplatform.tutorial.domains.Task;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by The eXo Platform SAS
 * Author : Thibault Clement
 * tclement@exoplatform.com
 * 3/19/15
 */
public class TaskDAOHibernate extends GenericDAOHibernate<Task, Long> implements TaskDAO {

    public TaskDAOHibernate(EntityManager em) {
        super(em);
    }

    @Override
    public List<Task> findByProjectId(Long projectId) {
        return null;
    }
}

