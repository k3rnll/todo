package com.k3.todo.repo;

import com.k3.todo.models.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {
}
