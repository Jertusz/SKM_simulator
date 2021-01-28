package pl.edu.pjwstk.simulator.security;

import liquibase.pro.packaged.U;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.simulator.controller.CrudController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@RestController
@RequestMapping("/users")
public class UserController extends  CrudController<User> {
    UserService userService;

    protected UserController(UserService service) {
        super(service);
        this.userService = service;
    }

    // ALL AUTH MODIFICATION
    @PostMapping("/{id}/auth/{auth}")
    public ResponseEntity addAuth(@PathVariable Long id, @PathVariable String auth) {
        try {
            User user = userService.getById(id);
            user.addAuthority(() -> auth);
            userService.createOrUpdate(user);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}/auth/{auth}")
    public ResponseEntity setAuth(@PathVariable Long id, @PathVariable String auth) {
        try {
            User user = userService.getById(id);
            user.setStringAuthority(auth);
            userService.createOrUpdate(user);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{id}/auth/{auth}")
    public ResponseEntity deleteAuth(@PathVariable Long id, @PathVariable String auth) {
        try {
            User user = userService.getById(id);
            user.removeAuthority(() -> auth);
            userService.createOrUpdate(user);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Function<User, Map<String, Object>> transformToDTO() {
        return user -> {
            var payload = new LinkedHashMap<String, Object>();
            payload.put("id", user.getId());
            payload.put("username", user.getUsername());
            payload.put("authorities", user.getAuthorities().stream().map(GrantedAuthority::getAuthority));

            return payload;
        };
    }
}
