package com.example.todolu.domain.taskcard;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskCardListData(Long id, String title, String description, UUID creatorId, LocalDateTime createdDate, TaskCardStatus status) {

    public TaskCardListData(TaskCard taskCard){
        this(taskCard.getId(), taskCard.getTitle(), taskCard.getDescription(), taskCard.getCreatorId(),taskCard.getCreatedDate(), taskCard.getStatus());
    }
}
