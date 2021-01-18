package com.todolist.external;


import com.todolist.entity.Task;
import com.todolist.exception.GatewayException;
import com.todolist.external.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class TaskGatewayImpl implements TaskGateway {

    public static final String MSG_ERROR_FIND_BY_ID = "Internal error when try to find task by ID on database";
    public static final String MSG_ERROR_FIND_ALL = "Internal error when try to find all tasks on database";
    public static final String MSG_ERROR_SAVE = "Internal error when try to save task on database";


    private final TaskRepository repository;

    @Override
    public Optional<Task> findById(final Long id) {
        try {
            log.debug("TaskGatewayImpl find task by id");
            return repository.findById(id);
        } catch (final Exception e) {
            log.error(MSG_ERROR_FIND_BY_ID);
            throw new GatewayException(MSG_ERROR_FIND_BY_ID, e);
        }
    }

    @Override
    public Page<Task> findAll(Pageable pageable) {
        try {
            log.debug("TaskGatewayImpl find all tasks");
            return repository.findAll(pageable);
        } catch (final Exception e) {
            log.error(MSG_ERROR_FIND_ALL);
            throw new GatewayException(MSG_ERROR_FIND_ALL, e);
        }
    }

    @Override
    public Task save(Task task) {
        try {
            log.debug("TaskGatewayImpl save task");
            return repository.save(task);
        } catch (final Exception e) {
            log.error(MSG_ERROR_SAVE);
            throw new GatewayException(MSG_ERROR_SAVE, e);
        }
    }

}
