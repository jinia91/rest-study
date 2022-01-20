package com.reststudy.restfulwebservice.user;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDaoService {
    private final static List<User> users = new ArrayList<>();
    static {
        users.add(new User(1L,"ManA", LocalDateTime.now()));
        users.add(new User(2L,"ManB", LocalDateTime.now()));
        users.add(new User(3L,"ManC", LocalDateTime.now()));
    }

    public List<User> findAll(){
        return users;
    }

    public User findOne(Long id){
        for(User user : users) {
            if (id.equals(user.getId())) {
                return user;
            }
        }
        throw new UserNotFoundException(String.format("ID[%s] not found",id));
    }

    public User save(User user){
        if(user.getId() == null) {
            user.setId(users.size()+1L);
        }
        if(user.getJoinDate() == null){
            user.setJoinDate(LocalDateTime.now());
        }
        users.add(user);
        return user;
    }

    public void deleteById(Long id){
        if(users.removeIf(user -> id.equals(user.getId()))) return;
        throw new UserNotFoundException(String.format("ID[%s] not found",id));
    }

}
