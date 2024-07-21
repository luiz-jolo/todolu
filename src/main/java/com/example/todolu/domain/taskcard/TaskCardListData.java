package com.example.todolu.domain.taskcard;

import java.time.LocalDateTime;

public record TaskCardListData(Long id, String title, String description, int creatorId, LocalDateTime createdDate, TaskCardStatus status) {

    public TaskCardListData(TaskCard taskCard){
        this(taskCard.getId(), taskCard.getTitle(), taskCard.getDescription(), taskCard.getCreatorId(),taskCard.getCreatedDate(), taskCard.getStatus());
    }
}
