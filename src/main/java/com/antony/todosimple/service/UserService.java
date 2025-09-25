package com.antony.todosimple.service;

import com.antony.todosimple.models.User;
import com.antony.todosimple.respositories.TaskRepository;
import com.antony.todosimple.respositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

}
