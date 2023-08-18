package hello.springmvc.basic.requestmapping;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mapping/users")
public class MappingClassController {
    @GetMapping//("/mapping/users")
    public String user(){
        return "get users";
    }
    @PostMapping//("/mapping/users")
    public String addUser(){
        return "post user";
    }
    @GetMapping("/{userId}")//("/mapping/users/{userId}")
    public String findUser(@PathVariable String userId){
        return "get userId= " + userId;
    }

    @PatchMapping("/{userId}")//("/mapping/users/{userId}")
    public String updateUser(@PathVariable String userId){
        return "update userId= " + userId;
    }
    @DeleteMapping("/{userId}")//("/mapping/users/{userId}")
    public String deleteUser(@PathVariable String userId){
        return "delete userId= " + userId;
    }
}
