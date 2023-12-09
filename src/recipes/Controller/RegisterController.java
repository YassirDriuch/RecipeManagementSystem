package recipes.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import recipes.ExceptionHandler.BadRequestException;
import recipes.Model.User;
import recipes.Repository.UserRepository;

import javax.validation.Valid;

@RestController
public class RegisterController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/api/register")
    public ResponseEntity<String> doRegister(@RequestBody @Valid User user){
        if (userRepository.findByEmailIgnoreCase(user.getEmail()).isPresent()) throw new BadRequestException("User exists!");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
