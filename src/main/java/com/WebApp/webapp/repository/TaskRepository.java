package com.WebApp.webapp.repository;

import com.WebApp.webapp.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByTask(String task);
    @Query("SELECT t FROM Task t WHERE t.task = ?1")
    boolean existByTask(String task);
}
