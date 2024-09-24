package com.example.todolu.domain.taskcard;

import com.example.todolu.domain.taskcard.dto.TaskCardCreateData;
import com.example.todolu.domain.taskcard.dto.TaskCardDetailData;
import com.example.todolu.domain.taskcard.dto.TaskCardListData;
import com.example.todolu.domain.taskcard.dto.TaskCardUpdateData;
import com.example.todolu.domain.taskcard.validators.CreationValidator;
import com.example.todolu.domain.taskcard.validators.OwnerEditionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.todolu.domain.user.AuthenticatedUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class TaskCardService {

    @Autowired
    private TaskCardRepository taskCardRepository;

    @Autowired
    private AuthenticatedUserService authenticatedUserService;

    public TaskCard create(TaskCardCreateData taskCardCreateData) {

        CreationValidator.validate(taskCardCreateData);

        var creator = authenticatedUserService.getAuthenticatedUser();
        var taskCard = new TaskCard(
                taskCardCreateData.title(),
                taskCardCreateData.description(),
                taskCardCreateData.dueDate(),
                creator,
                taskCardCreateData.priority()
        );
        return taskCardRepository.save(taskCard);
    }

    public Page<TaskCardListData> listTaskCards(Pageable pageable){
        return taskCardRepository.findAllByActiveTrue(pageable).map(TaskCardListData::new);
    }

    public TaskCardDetailData taskCardDetails(Long id){
        var taskCard = taskCardRepository.getReferenceById(id);
        return new TaskCardDetailData(taskCard);
    }

    public TaskCardDetailData updateTaskCard(TaskCardUpdateData taskCardData){
        var taskCard = taskCardRepository.getReferenceById(taskCardData.id());
        var user = authenticatedUserService.getAuthenticatedUser();
        //aply design pattern for this validation
        OwnerEditionValidator.validate(taskCard, user);
        taskCard.updateInfo(taskCardData);
        return new TaskCardDetailData(taskCard);
    }

    public void disableTaskCard(Long id) {
        var taskCard = taskCardRepository.getReferenceById(id);
        taskCard.disable();
    }
}
