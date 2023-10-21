package br.com.pierremonteiro.todolist.user;

import br.com.pierremonteiro.todolist.task.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IUserRepository extends JpaRepository<UserModel, UUID> {
    UserModel findByUsername(String username);
}
