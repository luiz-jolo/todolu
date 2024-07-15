package com.example.todolu.taskcard;

import java.time.LocalDateTime;

public record TaskCardData(
        String title,
        String description,
        int creatorId,
        LocalDateTime createdDate,
        LocalDateTime updatedDate,
        LocalDateTime dueDate,
        TaskCardStatus status,
        String priority
) {

}
