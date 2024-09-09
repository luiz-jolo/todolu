package com.example.todolu.domain.comment;

import com.example.todolu.domain.taskcard.TaskCard;
import com.example.todolu.domain.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Table(name="comments")
@Entity(name="Comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "taskcard_id", nullable = false)
    private TaskCard taskCard;

    public Comment(){}

    public Comment(String description, User user, TaskCard taskCard){
        this.description = description;
        this.createdAt = LocalDateTime.now();
        this.user = user;
        this.taskCard = taskCard;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TaskCard getTaskCard() {
        return taskCard;
    }

    public void setTaskCard(TaskCard taskCard) {
        this.taskCard = taskCard;
    }


}
