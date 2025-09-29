package com.antony.todosimple.service;

import com.antony.todosimple.models.Task;
import com.antony.todosimple.models.User;
import com.antony.todosimple.respositories.TaskRepository;
import com.antony.todosimple.respositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    public Task findById(Long id){
        Optional<Task> task = taskRepository.findById(id);
        return task.orElseThrow(() -> new RuntimeException("Tarefa não foi encontrado! Id: " + id + ", Tipo: " + Task.class.getName()) );
    }

    @Transactional
    public Task create(Task obj){
        User user = this.userService.findById(obj.getUser().getId());
        obj.setId(null);
        obj.setUser(user);
        obj = this.taskRepository.save(obj);
        return obj;
    }

    @Transactional
     public Task update(Task obj) {
        Task newObj = findById(obj.getId());
        newObj.setDescrition(obj.getDescrition());
        return this.taskRepository.save(newObj);
    }

}
