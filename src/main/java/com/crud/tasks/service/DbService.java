package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class DbService {

    private final TaskRepository repository;

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
}
