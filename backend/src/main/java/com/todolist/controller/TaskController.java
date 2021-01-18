package com.todolist.controller;


import com.todolist.exception.ControllerException;
import com.todolist.model.ResponseVO;
import com.todolist.model.TaskVO;
import com.todolist.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController extends ControllerException {

    private final TaskService taskService;

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> findById(@PathVariable("id") final Long id) {
        log.debug("Receiving parameters to find ticket by id: {}", id);
        final TaskVO taskVO = taskService.findById(id);
        return new ResponseEntity<>(taskVO, HttpStatus.OK);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> findAll(
            @RequestParam(value = "page", required = false, defaultValue = "0") final int page,
            @RequestParam(value = "page-size", required = false, defaultValue = "10") final int size) {
        final ResponseVO responseVO = taskService.findAll(page, size);
        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }

    //TODO creation with mock. Not implemented yet
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> create(@RequestBody TaskVO taskVO) {
        taskVO = TaskVO.builder().description("create by controller").isDone(false).username("rafacancian").build();
        final TaskVO taskVOCreated = taskService.create(taskVO);
        return new ResponseEntity<>(taskVOCreated, HttpStatus.CREATED);
    }
}
