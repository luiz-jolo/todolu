package com.example.todolu.domain.taskcard;

import com.example.todolu.domain.user.User;

public record TaskCardDetailData(Long id, String title, String description, java.time.LocalDateTime createdDate,
                                 java.time.LocalDateTime updatedDate, User creator, String priority, TaskCardStatus status) {

    public TaskCardDetailData(TaskCard taskCard) {
        this(taskCard.getId(), taskCard.getTitle(), taskCard.getDescription(), taskCard.getCreatedDate(),
                taskCard.getUpdatedDate(), taskCard.getCreator() , taskCard.getPriority(), taskCard.getStatus());
    }
}
