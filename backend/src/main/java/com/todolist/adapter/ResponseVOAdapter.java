package com.todolist.adapter;


import com.todolist.model.ResponseVO;
import com.todolist.model.TaskVO;

import java.util.List;

public class ResponseVOAdapter {

    public static ResponseVO create(List<TaskVO> tasks) {
        return ResponseVO.builder().taskVOS(tasks).build();
    }
}
