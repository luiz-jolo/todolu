package com.example.todolu.domain.taskcard.validators;

import com.example.todolu.domain.taskcard.dto.TaskCardCreateData;

import java.time.LocalDateTime;

public class CreationValidator {

    public static void validate(TaskCardCreateData taskCardCreateData){
        var now = LocalDateTime.now();
        var dueDate = taskCardCreateData.dueDate();
        if(dueDate.isBefore(now)){
            throw new IllegalArgumentException("The due_date does not be before today");
        }
    }
}
