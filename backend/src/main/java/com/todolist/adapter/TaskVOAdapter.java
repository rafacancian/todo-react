package com.todolist.adapter;

import com.todolist.entity.Task;
import com.todolist.model.TaskVO;
import org.modelmapper.ModelMapper;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

public class TaskVOAdapter {

    public static TaskVO create(final Task task) {
        if (!ObjectUtils.isEmpty(task)) {
            return new ModelMapper().map(task, TaskVO.class);
        }
        return TaskVO.builder().build();
    }

    public static List<TaskVO> create(final List<Task> tasks) {
        final List<TaskVO> ticketVOs = new ArrayList<>();
        if (!ObjectUtils.isEmpty(tasks)) {
            tasks.stream().forEach(t -> {
                ticketVOs.add(new ModelMapper().map(t, TaskVO.class));
            });
        }
        return ticketVOs;
    }
}
