package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class TaskMapperTest {
//
//    @Autowired
//    TaskMapper taskMapper;
//
//    @Test
//    public void testMapToTask(){
//        TaskDto taskDto = new TaskDto(1l, "Task 1","ABC123");
//
//        Task task = taskMapper.mapToTask(taskDto);
//
//
//        Assert.assertTrue(task.getId().equals(taskDto.getId()));
//        Assert.assertTrue(task.getTitle().equals(taskDto.getTitle()));
//        Assert.assertTrue(task.getContent().equals(taskDto.getContent()));
//    }
//    @Test
//    public void TestMapToTaskDto() {
//        TaskMapper taskMapper = new TaskMapper();
//        Task task = new Task(1l, "Task 1", "ABC123");
//
//        TaskDto taskDto = taskMapper.mapToTaskDto(task);
//
//
//        assertEquals("Task 1", taskDto.getTitle());
//        assertEquals("ABC123", taskDto.getContent());
//    }
//
//    @Test
//    public void TestMapToTaskDtoList() {
//        TaskMapper taskMapper = new TaskMapper();
//        Task task1 = new Task(1l, "Task 1", "ABC123");
//        Task task2 = new Task(2l, "Task 2", "123ABC");
//        List<Task> taskList = new ArrayList<>();
//        taskList.add(task1);
//        taskList.add(task2);
//
//        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);
//
//        assertEquals(2, taskDtoList.size());
//        assertEquals("Task 1", taskDtoList.get(0).getTitle());
//    }
//}

