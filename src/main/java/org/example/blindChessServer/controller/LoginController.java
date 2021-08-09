/**Контроллер Login, отвечающий за запросы, связанные со входом в аккаунт или регистрацией*/
package org.example.blindChessServer.controller;

import org.example.blindChessServer.DTO.UserDTO;
import org.example.blindChessServer.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.AbstractMap;


@RestController
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;  //Объект для работы с бизнес-логикой для сущности Login


    /**Конструктор класса
     * На вход принимает 1 параметр:
     * LoginService loginService - бин для работы с бизнес-логикой сущности Login*/
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    /**PostMapping для проверки на вход пользователя
     * В запросе принимает 2 параметра:
     * String email - email пользователя
     * String password - пароль пользователя
     * Если пользователь найден - вернет объект User в виде строки, иначе - вернет ERROR_PASSWORD или NOT_FOUND*/
    @PostMapping("/checkUser")
    public ResponseEntity login(@RequestParam String email, @RequestParam String password){
        AbstractMap.SimpleEntry<String, UserDTO> serviceResponse = loginService.checkLoginUser(email, password);

        //Возврат ответа клиенту
        switch (serviceResponse.getKey()) {
            case "NOT_FOUND": {
                return new ResponseEntity("NOT_FOUND", HttpStatus.NOT_FOUND);
            }
            case "ERROR_PASSWORD": {
                return new ResponseEntity("ERROR_PASSWORD", HttpStatus.BAD_REQUEST);
            }
            default:
                return new ResponseEntity(serviceResponse.getValue(), HttpStatus.OK);
        }
    }

}
