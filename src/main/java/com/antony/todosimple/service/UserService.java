package com.antony.todosimple.service;

import com.antony.todosimple.models.User;
import com.antony.todosimple.respositories.TaskRepository;
import com.antony.todosimple.respositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;


    public User findById(Long id) {
       Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(()-> new RuntimeException
                ("Usuário não foi encontrado! Id: " + id + ", Tipo: " + User.class.getName()));
    }


    @Transactional
    public User create(User obj) {
        obj.setId(null); //cria um novo usuário
        obj =  this.userRepository.save(obj);
        this.taskRepository.saveAll(obj.getTasks());
        return obj;
    }

    @Transactional
    public User update(User obj) {
        User newObj = this.findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.userRepository.save(newObj);
    }

    public void delete(Long id) {
        findById(id);
        try {
            this.userRepository.deleteById(id);
        }catch (Exception ex){
            throw  new RuntimeException("Não é possivel excluir há entidades relacionadas");
        }
    }

}
