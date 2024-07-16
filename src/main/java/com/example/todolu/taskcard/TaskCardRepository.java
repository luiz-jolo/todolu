package com.example.todolu.taskcard;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskCardRepository extends JpaRepository<TaskCard, Long> {
    Page<TaskCard> findAllByActiveTrue(Pageable paginate);
}
