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
     * В случае успеха вернет ACCOUNT_CREATED, если такой email есть - ERROR_EMAIL, если такое имя есть - ERROR_NAME, иначе - ERROR_CREATION*/
    @PostMapping("/")
    public ResponseEntity create(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
        String response = userService.createUser(name, email, password);

        switch (response) {
            case "ACCOUNT_CREATED":
                return new ResponseEntity("ACCOUNT_CREATED", HttpStatus.OK);
            case "ERROR_EMAIL":
                return new ResponseEntity("ERROR_EMAIL", HttpStatus.BAD_REQUEST);
            case "ERROR_NAME":
                return new ResponseEntity("ERROR_NAME", HttpStatus.BAD_REQUEST);
            default:
                return new ResponseEntity("ERROR_CREATION", HttpStatus.BAD_REQUEST);
        }
    }


    /**PatchMapping для редактирования имени игрока
     * На вход принимает 2 параметра:
     * Integer user_id - id заданного пользвателя
     * String newName - новое имя пользователя
     * В случае успеха вернет NAME_UPDATE, если такое имя уже есть - ERROR_NAME, иначе - ERROR_UPDATE_NAME*/
    @PatchMapping("/editName/{user_id}")
    public ResponseEntity editName(@PathVariable Integer user_id, @RequestParam String newName) {
        String response = userService.updateName(user_id, newName);

        switch (response) {
            case "NAME_UPDATE":
                return new ResponseEntity("NAME_UPDATE", HttpStatus.OK);
            case "ERROR_NAME":
                return new ResponseEntity("ERROR_NAME", HttpStatus.BAD_REQUEST);
            default:
                return new ResponseEntity("ERROR_UPDATE_NAME", HttpStatus.BAD_REQUEST);
        }
    }


    /**PatchMapping для редактирования статистики пользователя после проведенного матча
     * На вход принимает 3 параметра:
     * Integer user_id - id заданного пользвателя
     * Integer isWin - победил ли польователя (0 или 1)
     * String typeGame - тип игры (QUICK_GAME, FRIEND_GAME или RATING_GAME)
     * В случае успеха вернет STATISTICS_UPDATE, иначе ERROR_UPDATE_STATISTICS*/
    @PatchMapping("/editStatistics/{user_id}")
    public ResponseEntity editStatistics(@PathVariable Integer user_id, @RequestParam Integer isWin, @RequestParam String typeGame) {
        String response = userService.updateStatistics(user_id, isWin, typeGame);

        switch (response) {
            case "STATISTICS_UPDATE":
                return new ResponseEntity("STATISTICS_UPDATE", HttpStatus.OK);
            default:
                return new ResponseEntity("ERROR_UPDATE_STATISTICS", HttpStatus.BAD_REQUEST);
        }
    }

}
