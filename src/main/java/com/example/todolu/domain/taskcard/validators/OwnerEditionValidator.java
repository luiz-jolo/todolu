package com.example.todolu.domain.taskcard.validators;

import com.example.todolu.domain.taskcard.TaskCard;
import com.example.todolu.domain.user.User;

public class OwnerEditionValidator {

    public static void validate(TaskCard taskCard, User user){
        if(!taskCard.getCreator().getId().equals(user.getId())){
            throw new IllegalArgumentException("This user cannot edit this taskcard");
        }
    }
}
