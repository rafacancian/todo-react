package com.todolist.external;

import com.todolist.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TaskGateway {

    Optional<Task> findById(Long id);

    Page<Task> findAll(Pageable pageable);

    Task save(Task task);
}
