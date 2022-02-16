package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService service;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void getTasksTest() throws Exception {
        //Given
        Task task = new Task(1L, "title", "content");
        List<Task> list = List.of(task);
        when(service.getAllTasks()).thenReturn(list);
        //Then
        //When
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/task/getTasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void getTest() throws Exception {
        //Given
        //Then
        //When
        mockMvc.perform(MockMvcRequestBuilders
               .get("/v1/task/get")
               .contentType(MediaType.APPLICATION_JSON)
               .param("taskId", "1"))
               .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getTaskTest() throws Exception {
        //Given
        //Then
        //When
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/task/{taskId}", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void updateTaskTest() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "title", "content");
        Task task = taskMapper.mapToTask(taskDto);
        when(service.saveTask(task)).thenReturn(task);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        //Then
        //When
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/v1/task/updateTask")
                        .content(jsonContent)
                        .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void createTaskTest() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "title", "content");
        Task task = taskMapper.mapToTask(taskDto);
        when(service.saveTask(task)).thenReturn(task);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        //Then
        //When
        mockMvc.perform(MockMvcRequestBuilders
               .post("/v1/task/createTask")
               .content(jsonContent)
               .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8"))
               .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deleteTaskTest() throws Exception {
        //Given
        //Then
        //When
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/v1/task/{taskId}", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk());
    }

}