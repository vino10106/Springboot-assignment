package user.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import user.assignment.entity.User;
import user.assignment.service.UserService;
import user.assignment.DTO.UserPatch;

import java.util.*;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;
   

    @RequestMapping(method = RequestMethod.GET,value = "/users")
    public ResponseEntity<Page<User>> getAllUsers(@RequestParam Optional<Integer> page,
                                                  @RequestParam Optional<Integer> limit){
        return status(HttpStatus.OK).body(userService.getAllUsers(page, limit));
    }

    @RequestMapping(method = RequestMethod.GET,value = "/users/{id}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable String id){
        return status(HttpStatus.OK).body(userService.getUser("id"));
    }
    @RequestMapping(method = RequestMethod.POST,value =  "/users")
    public ResponseEntity<User> addUser(@RequestBody User user){
        return status(HttpStatus.CREATED).body(userService.addUser(user));

    }
    @RequestMapping(method = RequestMethod.PUT,value =  "/users/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user,@PathVariable String id){
        return status(HttpStatus.ACCEPTED).body(userService.updateUser(user ,id));
    }
    @RequestMapping(method = RequestMethod.DELETE,value =  "/users/{id}")
    public ResponseEntity<Optional<User>> deleteUserById(@PathVariable String id) {
        return status(HttpStatus.OK).body(userService.deleteUserById(id));
    }
    @RequestMapping(value = "/users/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<String> userPatch(@RequestBody ArrayList<UserPatch> userPatch,
                                            @PathVariable String id){
        try {
            userService.updateUserByPatch(userPatch, id);
            return status(HttpStatus.OK).body("Patch updated");
        }
        catch (Exception e){
            return new ResponseEntity(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
}
}

