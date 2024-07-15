package com.example.todolu.controller;

import com.example.todolu.taskcard.TaskCard;
import com.example.todolu.taskcard.TaskCardData;
import com.example.todolu.taskcard.TaskCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("taskcard")
public class TaskCardController {

    @Autowired
    private TaskCardRepository taskCardRepository;

    @PostMapping
    public void createTaskCard(@RequestBody TaskCardData dados){
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

}
