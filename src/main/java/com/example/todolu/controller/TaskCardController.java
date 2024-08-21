package com.example.todolu.controller;

import com.example.todolu.domain.taskcard.*;
import com.example.todolu.domain.user.AuthenticatedUserService;
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

    @Autowired
    private AuthenticatedUserService authenticatedUserService;

    @Autowired
    private TaskCardService taskCardService;

    @PostMapping
    public ResponseEntity createTaskCard(@RequestBody @Valid TaskCardCreateData taskCardCreateData, UriComponentsBuilder uriComponentsBuilder){

        var taskCard = taskCardService.create(taskCardCreateData);
        var uri = uriComponentsBuilder.path("/taskcard/{id}").buildAndExpand(taskCard.getId()).toUri();

        return ResponseEntity.created(uri).body(new TaskCardDetailData(taskCard));
    }

    @GetMapping
    public ResponseEntity<Page<TaskCardListData>> listTaskCards(Pageable paginate){
        return ResponseEntity.ok(taskCardService.listTaskCards(paginate));
    }

    @GetMapping("/{id}")
    public ResponseEntity taskCardDetails(@PathVariable Long id){
        return ResponseEntity.ok(taskCardService.taskCardDetails(id));
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateTaskCard(@RequestBody @Valid TaskCardUpdateData taskCardData){
        var taskCard = taskCardService.updateTaskCard(taskCardData);
        return ResponseEntity.ok(taskCard);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteTaskCard(@PathVariable Long id) {
        taskCardService.disableTaskCard(id);
        return ResponseEntity.noContent().build();
    }

}
