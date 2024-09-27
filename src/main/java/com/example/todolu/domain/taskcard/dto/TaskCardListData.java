package com.example.todolu.domain.taskcard.dto;

import com.example.todolu.domain.taskcard.TaskCard;
import com.example.todolu.domain.taskcard.TaskCardStatus;
import com.example.todolu.domain.user.dto.UserDetailData;

import java.time.LocalDateTime;

public record TaskCardListData(Long id, String title, String description, UserDetailData userDetailData, LocalDateTime createdDate, TaskCardStatus status) {

    public TaskCardListData(TaskCard taskCard){
        this(taskCard.getId(), taskCard.getTitle(), taskCard.getDescription(), new UserDetailData(taskCard.getCreator()) ,taskCard.getCreatedDate(), taskCard.getStatus());
    }
}
