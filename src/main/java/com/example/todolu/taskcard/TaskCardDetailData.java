package com.example.todolu.taskcard;

public record TaskCardDetailData(Long id, String title, String description) {

    public TaskCardDetailData(TaskCard taskCard){
        this(taskCard.getId(), taskCard.getTitle(), taskCard.getDescription());
    }
}
