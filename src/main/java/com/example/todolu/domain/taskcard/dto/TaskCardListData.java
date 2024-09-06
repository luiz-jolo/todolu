package com.example.todolu.domain.taskcard.dto;

import com.example.todolu.domain.taskcard.TaskCard;
import com.example.todolu.domain.taskcard.TaskCardStatus;
import com.example.todolu.domain.user.User;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskCardListData(Long id, String title, String description, User creator, LocalDateTime createdDate, TaskCardStatus status) {

    public TaskCardListData(TaskCard taskCard){
        this(taskCard.getId(), taskCard.getTitle(), taskCard.getDescription(), taskCard.getCreator() ,taskCard.getCreatedDate(), taskCard.getStatus());
    }
}
