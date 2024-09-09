package com.example.todolu.domain.taskcard;

import com.example.todolu.domain.comment.Comment;
import com.example.todolu.domain.taskcard.dto.TaskCardUpdateData;
import com.example.todolu.domain.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Table(name = "taskcards")
@Entity(name = "TaskCard")
public class TaskCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private LocalDateTime dueDate;

    @OneToMany(mappedBy = "taskCard")
    private Set<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    public TaskCard(){}

    public TaskCard(String title, String description, LocalDateTime dueDate, User creator, String priority) {
        this.title = title;
        this.description = description;
        this.createdDate = LocalDateTime.now();
        this.dueDate = dueDate;
        this.creator = creator;
        this.priority = priority;
        this.status = TaskCardStatus.BACKLOG;
        this.active = true;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    private String priority;

    @Enumerated(EnumType.STRING)
    private TaskCardStatus status;

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public TaskCardStatus getStatus() {
        return status;
    }

    public void setStatus(TaskCardStatus status) {
        this.status = status;
    }


    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    private Boolean active;

    public void updateInfo(TaskCardUpdateData taskCardData) {
        if (taskCardData.title() != null) {
            this.title = taskCardData.title();
        }
        if (taskCardData.description() != null) {
            this.description = taskCardData.description();
        }
        if (taskCardData.dueDate() != null) {
            this.dueDate = taskCardData.dueDate();
        }
        if (taskCardData.status() != null) {
            this.status = taskCardData.status();
        }
        if (taskCardData.priority() != null) {
            this.priority = taskCardData.priority();
        }
        this.updatedDate = LocalDateTime.now();
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskCard taskCard)) return false;
        return getId().equals(taskCard.getId());
    }

    public void disable() {
        this.active = false;
    }

}
