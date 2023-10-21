package br.com.pierremonteiro.todolist.task;


import br.com.pierremonteiro.todolist.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class taskController {
    @Autowired
    private ITaskRepository taskRepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody TaskModel taskmodel, HttpServletRequest request){
       var idUser = request.getAttribute("idUser");
       taskmodel.setIdUser((UUID) idUser);

       var currentDate = LocalDateTime.now();
       if(currentDate.isAfter(taskmodel.getStartAt()) || currentDate.isAfter(taskmodel.getEndAt())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de inicio deve ser maior que a data atual");
       }

       if((taskmodel.getStartAt()).isAfter(taskmodel.getEndAt())){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de inicio deve ser menor que a data final");
       }

       var task = this.taskRepository.save(taskmodel);
       return ResponseEntity.status(HttpStatus.OK).body(task);
    }


    @GetMapping("/")
    public List<TaskModel> list(HttpServletRequest request){
        var idUser = request.getAttribute("idUser");
        var tasks = this.taskRepository.findByIdUser((UUID) idUser);
        return tasks;
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody TaskModel taskModel, HttpServletRequest request, @PathVariable UUID id){

        var idUser = request.getAttribute("idUser");

        var task = this.taskRepository.findById(id).orElse(null);

        if(task == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("tarefa nao encontrada");
        }
        if(!task.getIdUser().equals(idUser)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario não tem permissão para alterar essa tarefa");
        }

        Utils.copyNonNullProperties(taskModel,task);
        var taskUptaded = this.taskRepository.save(task);

        return ResponseEntity.ok().body(taskUptaded);


    }

    @DeleteMapping("/{id}")
    public ResponseEntity remove(HttpServletRequest request , @PathVariable UUID id){

        var idUser = request.getAttribute("idUser");
        var task = this.taskRepository.findById(id).orElse(null);

        if(task == null){
            return ResponseEntity.badRequest().body("Esta tarefa não existe");
        }
        if(!task.getIdUser().equals(idUser)){
            return  ResponseEntity.badRequest().body("Usuario sem permissão para remover");
        }

        this.taskRepository.delete(task);

        return ResponseEntity.ok().body("Tarefa deletada com sucesso!");
    }
}





