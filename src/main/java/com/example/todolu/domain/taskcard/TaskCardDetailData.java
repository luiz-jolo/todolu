package com.example.todolu.domain.taskcard;

public record TaskCardDetailData(Long id, String title, String description, java.time.LocalDateTime createdDate,
                                 java.time.LocalDateTime updatedDate, java.util.UUID creatorId, String priority, TaskCardStatus status) {

    public TaskCardDetailData(TaskCard taskCard) {
        this(taskCard.getId(), taskCard.getTitle(), taskCard.getDescription(), taskCard.getCreatedDate(),
                taskCard.getUpdatedDate(), taskCard.getCreatorId(), taskCard.getPriority(), taskCard.getStatus());
    }
}
