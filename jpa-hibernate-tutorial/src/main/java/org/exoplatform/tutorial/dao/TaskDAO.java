package org.exoplatform.tutorial.dao;

import org.exoplatform.tutorial.domains.Task;

import java.util.List;

/**
 * Created by TClement on 3/19/15.
 */
public interface TaskDAO extends GenericDAO<Task, Long> {

    public List<Task> findByProjectId(Long projectId);
}
