package com.todolist.adapter;

import com.todolist.entity.Task;
import com.todolist.model.TaskVO;
import org.modelmapper.ModelMapper;

public class TaskAdapter {

    public static Task create(TaskVO taskVO) {
        return new ModelMapper().map(taskVO, Task.class);
    }

}
