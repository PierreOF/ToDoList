package br.com.pierremonteiro.todolist.user;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserRepository userRepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel userModel){
        var user = this.userRepository.findByUsername(userModel.getUsername());
        if(user != null){
            System.out.println("Usuário já existe");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("usuario já existe");
        }

        var passwordHashared = BCrypt.withDefaults().hashToString(12,userModel.getPassword().toCharArray());

        userModel.setPassword(passwordHashared);

        var userCreated = this.userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }

}
