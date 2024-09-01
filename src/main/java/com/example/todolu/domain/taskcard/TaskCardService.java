package com.example.todolu.domain.taskcard;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.todolu.domain.user.AuthenticatedUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

@Service
public class TaskCardService {

    @Autowired
    private TaskCardRepository taskCardRepository;

    @Autowired
    private AuthenticatedUserService authenticatedUserService;

    public TaskCard create(TaskCardCreateData taskCardCreateData) {

        var creator = authenticatedUserService.getAuthenticatedUser();
        System.out.println("CREATOR");
        var teste = creator.toString();
        System.out.println(teste);
        var taskCard = new TaskCard(
                taskCardCreateData.title(),
                taskCardCreateData.description(),
                taskCardCreateData.dueDate(),
                creator,
                taskCardCreateData.priority()
        );
        System.out.println("TASKCARD CREATOR");
        var testedois = taskCard.getCreator().toString();
        System.out.println(testedois);
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
        taskCard.updateInfo(taskCardData);
        return new TaskCardDetailData(taskCard);
    }

    public void disableTaskCard(Long id) {
        var taskCard = taskCardRepository.getReferenceById(id);
        taskCard.disable();
    }
}
