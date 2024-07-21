package com.example.todolu.controller;

import com.example.todolu.domain.taskcard.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("taskcard")
public class TaskCardController {

    @Autowired
    private TaskCardRepository taskCardRepository;

    @PostMapping
    public ResponseEntity createTaskCard(@RequestBody @Valid TaskCardData taskCardData, UriComponentsBuilder uriComponentsBuilder){
        var taskCard = new TaskCard(
                null,
                taskCardData.title(),
                taskCardData.description(),
                taskCardData.createdDate(),
                taskCardData.updatedDate(),
                taskCardData.dueDate(),
                taskCardData.creatorId(),
                taskCardData.priority(),
                taskCardData.status()
        );
        taskCardRepository.save(taskCard);
        var uri = uriComponentsBuilder.path("/taskcard/{id}").buildAndExpand(taskCard.getId()).toUri();

        return ResponseEntity.created(uri).body(new TaskCardDetailData(taskCard));
    }

    @GetMapping
    public ResponseEntity<Page<TaskCardListData>> listTaskCards(Pageable paginate){
        var page = taskCardRepository.findAllByActiveTrue(paginate).map(TaskCardListData::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity taskCardDetails(@PathVariable Long id){
        var taskCard = taskCardRepository.getReferenceById(id);
        return ResponseEntity.ok(new TaskCardDetailData(taskCard));
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateTaskCard(@RequestBody @Valid TaskCardUpdateData taskCardData){
        var taskCard = taskCardRepository.getReferenceById(taskCardData.id());
        taskCard.updateInfo(taskCardData);

        return ResponseEntity.ok(new TaskCardDetailData(taskCard));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteTaskCard(@PathVariable Long id) {
        var taskCard = taskCardRepository.getReferenceById(id);
        taskCard.disable();
        return ResponseEntity.noContent().build();

    }

}
