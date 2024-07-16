package com.example.todolu.taskcard;

import java.time.LocalDateTime;

public record TaskCardListData(String title, String description, int creatorId, LocalDateTime createdDate, TaskCardStatus status) {

    public TaskCardListData(TaskCard taskCard){
        this(taskCard.getTitle(), taskCard.getDescription(), taskCard.getCreatorId(),taskCard.getCreatedDate(), taskCard.getStatus());
    }
}
