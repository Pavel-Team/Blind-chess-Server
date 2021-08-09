/** Сервис для работы с бизнес-логикой для сущности Login */
package org.example.blindChessServer.service;

import org.example.blindChessServer.DTO.UserDTO;
import org.example.blindChessServer.model.Login;
import org.example.blindChessServer.repository.LoginRepository;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;


@Service
public class LoginService {

    private final LoginRepository loginRepository; //Объект для работы с БД


    /**Конструктор класса
     * На вход принимает 1 параметр:
     * LoginRepository loginRepository - бин для работы в БД с сущностью Login*/
    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    /**Метод получения id пользователя, по его email и паролю
     * На вход принимает 2 параметра:
     * String email - email пользователя
     * String password - пароль пользователя
     * Если пользователь будет найден - вернет "OK" в ключе и объект UserDTO в значении, иначе - вернет ERROR_PASSWORD или NOT_FOUND в ключе и null в значении*/
    public AbstractMap.SimpleEntry<String, UserDTO> checkLoginUser(String email, String password) {
        Login login = loginRepository.findByEmail(email);

        //Отправка ответа
        if (login != null) {
            if (password.equals(login.getPassword()))
                return new AbstractMap.SimpleEntry<>("OK", new UserDTO(login.getUser()));
            else
                return new AbstractMap.SimpleEntry<>("ERROR_PASSWORD", null);
        } else {
            return new AbstractMap.SimpleEntry<>("NOT_FOUND", null);
        }

    }

}
