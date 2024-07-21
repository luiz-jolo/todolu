package com.example.todolu.domain.taskcard;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskCardRepository extends JpaRepository<TaskCard, Long> {
    Page<TaskCard> findAllByActiveTrue(Pageable paginate);
}
