package com.todolist.service;


import com.todolist.adapter.ResponseVOAdapter;
import com.todolist.adapter.TaskAdapter;
import com.todolist.adapter.TaskVOAdapter;
import com.todolist.entity.Task;
import com.todolist.exception.EntityNotFoundException;
import com.todolist.external.TaskGateway;
import com.todolist.model.ResponseVO;
import com.todolist.model.TaskVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskGateway gateway;

    @Autowired
    private PagedResourcesAssembler<Task> pagedResourcesAssembler;

    public TaskVO findById(final Long id) {
        final Optional<Task> task = gateway.findById(id);
        if (task.isEmpty()) {
            throw new EntityNotFoundException("Erro1", "Erro2");
        }
        return TaskVOAdapter.create(task.get());
    }

    public ResponseVO findAll(int page, int size) {
        final Pageable pageable = PageRequest.of(page, size);
        final Page<Task> pageTickets = gateway.findAll(pageable);
        final List<TaskVO> taskVOS = TaskVOAdapter.create(pageTickets.getContent());
        return ResponseVOAdapter.create(taskVOS);
    }

    public TaskVO create(final TaskVO ticketVO) {
        final Task task = gateway.save(TaskAdapter.create(ticketVO));
        return TaskVOAdapter.create(task);
    }


}
