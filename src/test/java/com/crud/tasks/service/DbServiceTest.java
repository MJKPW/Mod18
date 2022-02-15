package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class DbServiceTest {

    @Autowired
    private DbService service;

    @Test
    public void getTasks() {
        //Given
        //When
        List<Task> tasks = service.getAllTasks();
        //Then
        Assertions.assertEquals(5, tasks.size());
    }

    @Test
    public void getTask() {
        //Given
        //When
        //Then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
           service.getTaskById(2);
        });
        Assertions.assertThrows(IllegalArgumentException.class ,()->service.getTaskById(1));
        Assertions.assertThrows(TaskNotFoundException.class,
                                ()->service.getTask(2L));
    }

    @Test
    public void saveTaskTest() {
        //Given
        //Then
        Task task = service.saveTask(new Task(1L, "task", "content"));
        //When
        Assertions.assertEquals("task", task.getTitle());
        //CleanUp
        try {
            service.removeTask(task.getId());
        } catch (TaskNotFoundException e) {
            e.printStackTrace();
        }
    }

}