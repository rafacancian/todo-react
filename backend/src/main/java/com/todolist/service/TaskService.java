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

import java.time.LocalDate;
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

    public ResponseVO findByDescription(String description) {
        final List<Task> tasks = gateway.findByDescription(description);
        final List<TaskVO> taskVOS = TaskVOAdapter.create(tasks);
        return ResponseVOAdapter.create(taskVOS);
    }

    public ResponseVO findAll(int page, int size) {
        final Pageable pageable = PageRequest.of(page, size);
        final Page<Task> pageTickets = gateway.findAll(pageable);
        final List<TaskVO> taskVOS = TaskVOAdapter.create(pageTickets.getContent());
        return ResponseVOAdapter.create(taskVOS);
    }

    public TaskVO create(final TaskVO ticketVO) {
        ticketVO.setCreationDate(LocalDate.now());
        ticketVO.setDueDate(LocalDate.now().plusDays(2));
        final Task task = gateway.save(TaskAdapter.create(ticketVO));
        return TaskVOAdapter.create(task);
    }

    public void delete(Long id) {
        final Optional<Task> task = gateway.findById(id);
        task.ifPresent(gateway::delete);
    }

    public TaskVO update(TaskVO taskVO) {
        final Optional<Task> task = gateway.findById(taskVO.getId());
        if (task.isEmpty()) {
            throw new EntityNotFoundException("Erro1", "Erro2");
        }
        taskVO.setId(task.get().getId());
        return create(taskVO);
    }
}
