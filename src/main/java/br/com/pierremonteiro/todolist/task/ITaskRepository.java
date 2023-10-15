package br.com.pierremonteiro.todolist.task;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ITaskRepository extends JpaRepository<TaskModel, UUID> {
    List<TaskModel> findByIdUser(UUID id);
    TaskModel findByIdAndByIdUser(UUID id , UUID idUser);



}
