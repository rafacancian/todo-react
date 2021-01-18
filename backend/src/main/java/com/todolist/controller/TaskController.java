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

import javax.websocket.server.PathParam;


@Slf4j
@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController extends ControllerException {

    private final TaskService taskService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") final Long id) {
        log.debug("Receiving parameters to find ticket by id: {}", id);
        final TaskVO taskVO = taskService.findById(id);
        return new ResponseEntity<>(taskVO, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> findAll(
            @RequestParam(value = "page", required = false, defaultValue = "0") final int page,
            @RequestParam(value = "page-size", required = false, defaultValue = "10") final int size) {
        final ResponseVO responseVO = taskService.findAll(page, size);
        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<?> search(@PathParam("description") final String description) {
        final ResponseVO responseVO = taskService.findByDescription(description);
        return new ResponseEntity<>(responseVO, HttpStatus.OK);
    }

    @PutMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> update(@RequestBody TaskVO taskVO) {
        final TaskVO taskVOUpdated = taskService.update(taskVO);
        return new ResponseEntity<>(taskVOUpdated, HttpStatus.CREATED);
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> create(@RequestBody TaskVO taskVO) {
        final TaskVO taskVOCreated = taskService.create(taskVO);
        return new ResponseEntity<>(taskVOCreated, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") final Long id) {
        taskService.delete(id);
        return ResponseEntity.ok().build();
    }
}
