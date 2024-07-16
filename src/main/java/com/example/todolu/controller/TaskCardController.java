package com.example.todolu.controller;

import com.example.todolu.taskcard.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("taskcard")
public class TaskCardController {

    @Autowired
    private TaskCardRepository taskCardRepository;

    @PostMapping
    public void createTaskCard(@RequestBody @Valid TaskCardData taskCardData){
        taskCardRepository.save(new TaskCard(
                null,
                taskCardData.title(),
                taskCardData.description(),
                taskCardData.createdDate(),
                taskCardData.updatedDate(),
                taskCardData.dueDate(),
                taskCardData.creatorId(),
                taskCardData.priority(),
                taskCardData.status()
        ));
    }

    @GetMapping
    public Page<TaskCardListData> listTaskCards(Pageable paginate){
        return taskCardRepository.findAllByActiveTrue(paginate).map(TaskCardListData::new);
    }

    @PutMapping
    @Transactional
    public void updateTaskCard(@RequestBody @Valid TaskCardUpdateData taskCardData){
        var taskCard = taskCardRepository.getReferenceById(taskCardData.id());
        taskCard.updateInfo(taskCardData);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteTaskCard(@PathVariable Long id) {
        var taskCard = taskCardRepository.getReferenceById(id);
        taskCard.disable();
    }

}
