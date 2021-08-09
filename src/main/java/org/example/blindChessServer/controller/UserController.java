/**Контроллер USER, отвечающий за запросы, связанные с изменением информации в аккаунтах пользователей*/
package org.example.blindChessServer.controller;

import org.example.blindChessServer.DTO.UserDTO;
import org.example.blindChessServer.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService; //Объект для работы с бизнес-логикой в сущности User


    /**Конструктор класса
     * На вход принимает 1 параметр:
     * UserService userService - бин для работы с бизнес-логикой сущности User*/
    public UserController(UserService userService) {
        this.userService = userService;
    }


    /**GetMapping для получения информации о пользователе
     * В запросе принимает 1 параметр:
     * Integer user_id - id данного пользователя
     * Если пользователь найден - вернет объект UserDTO, иначе - строку NOT_FOUND*/
    @GetMapping("/{user_id}")
    public ResponseEntity login(@PathVariable Integer user_id){
        UserDTO userDTO = userService.getUser(user_id);

        if (userDTO != null)
            return new ResponseEntity(userDTO, HttpStatus.OK);
        else
            return new ResponseEntity("NOT_FOUND", HttpStatus.NOT_FOUND);
    }


    /**PostMapping для создания нового аккаунта
     * На вход принимает 3 параметра:
     * String name - имя пользователя
     * String email - email пользователя
     * String password - пароль пользователя
     * В случае успеха вернет ACCOUNT_CREATED, если такой email есть - ERROR_EMAIL, иначе - ERROR_CREATION*/
    @PostMapping("/new")
    public ResponseEntity create(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
        String response = userService.createUser(name, email, password);

        switch (response) {
            case "ACCOUNT_CREATED":
                return new ResponseEntity("ACCOUNT_CREATED", HttpStatus.OK);
            case "ERROR_EMAIL":
                return new ResponseEntity("ERROR_EMAIL", HttpStatus.BAD_REQUEST);
            default:
                return new ResponseEntity("ERROR_CREATION", HttpStatus.BAD_REQUEST);
        }
    }

}
