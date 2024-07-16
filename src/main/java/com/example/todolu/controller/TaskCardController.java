package com.example.todolu.controller;

import com.example.todolu.taskcard.TaskCard;
import com.example.todolu.taskcard.TaskCardData;
import com.example.todolu.taskcard.TaskCardListData;
import com.example.todolu.taskcard.TaskCardRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("taskcard")
public class TaskCardController {

    @Autowired
    private TaskCardRepository taskCardRepository;

    @PostMapping
    public void createTaskCard(@RequestBody @Valid TaskCardData dados){
        taskCardRepository.save(new TaskCard(
                null,
                dados.title(),
                dados.description(),
                dados.createdDate(),
                dados.updatedDate(),
                dados.dueDate(),
                dados.creatorId(),
                dados.priority(),
                dados.status()
        ));
    }

    @GetMapping
    public List<TaskCardListData> listTaskCards(){
        return taskCardRepository.findAll().stream().map(TaskCardListData::new).toList();
    }

}
