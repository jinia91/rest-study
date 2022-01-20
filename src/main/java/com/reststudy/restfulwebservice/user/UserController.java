package com.reststudy.restfulwebservice.user;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.startup.Tomcat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.ControllerLinkRelationProvider;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.Servlet;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserDaoService userDaoService;

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userDaoService.findAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<EntityModel<User>> getUser(@PathVariable Long id){

        User user = userDaoService.findOne(id);

        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());
        entityModel.add(linkTo.withRel("all-users"));
        return ResponseEntity.ok(entityModel);

    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@Validated @RequestBody User user){
        User saveUser = userDaoService.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saveUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id){
        userDaoService.deleteById(id);
    }

}
