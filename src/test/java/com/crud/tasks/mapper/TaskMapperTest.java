package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TaskMapperTest {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void taskMappingTest() {
        //Given
        Task task = new Task(1L, "task", "content");
        List<Task> taskList = List.of(task);
        //Then
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);
        Task taskFromDto = taskMapper.mapToTask(taskDto);
        //When
        Assertions.assertEquals(1L, taskDto.getId());
        Assertions.assertEquals("task", taskDto.getTitle());
        Assertions.assertEquals("content", taskDto.getContent());
        Assertions.assertEquals(1L, taskFromDto.getId());
        Assertions.assertEquals("task", taskFromDto.getTitle());
        Assertions.assertEquals("content", taskFromDto.getContent());
        taskDtoList.forEach(_task -> {
            Assertions.assertEquals(1L, _task.getId());
            Assertions.assertEquals("task", _task.getTitle());
            Assertions.assertEquals("content", _task.getContent());
        });
    }
}
