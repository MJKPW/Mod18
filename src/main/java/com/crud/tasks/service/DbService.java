package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class DbService {

    private final TaskRepository repository;

    @Autowired
    public DbService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Task getTaskById(long id) {
        if(repository.findById(id).isPresent())
            return repository.findById(id).get();
        else
            throw new IllegalArgumentException("Id not found");
    }

    public Task saveTask(final Task task) {
        return repository.save(task);
    }

    public Task getTask(final Long id) throws TaskNotFoundException {
        return repository.findById(id).orElseThrow(TaskNotFoundException::new);
    }

    public void removeTask(final Long id) throws TaskNotFoundException {
        repository.deleteById(id);
    }
}
