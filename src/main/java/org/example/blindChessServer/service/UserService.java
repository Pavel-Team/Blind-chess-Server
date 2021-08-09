/** Сервис для работы с бизнес-логикой для сущности User */
package org.example.blindChessServer.service;

import org.example.blindChessServer.DTO.UserDTO;
import org.example.blindChessServer.model.Login;
import org.example.blindChessServer.model.User;
import org.example.blindChessServer.repository.LoginRepository;
import org.example.blindChessServer.repository.ProductRepository;
import org.example.blindChessServer.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;       //Объект для работы в БД с сущностью User
    private final LoginRepository loginRepository;     //Объект для работы в БД с сущностью Login
    private final ProductRepository productRepository; //Объект для работы в БД с сущностью Product


    /**Конструктор класса
     * На вход принимает 3 параметра:
     * UserRepository userRepository - бин для работы в БД с сущностью User
     * LoginRepository loginRepository - бин для работы в БД с сущностью Login
     * ProductRepository productRepository - бин для работы в БД с сущностью Product*/
    public UserService(UserRepository userRepository, LoginRepository loginRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.loginRepository = loginRepository;
        this.productRepository = productRepository;
    }


    /**Метод получений объекта пользователя UserDTO по его id
     * На вход принимает 1 параметр:
     * Integer user_id - id заданного пользователя
     * Если пользователь найден - вернет объект класса UserDTO, иначе - вернет null*/
    public UserDTO getUser(Integer user_id) {
        User user = userRepository.findByUserId(user_id);
        if (user != null)
            return new UserDTO(user);
        else
            return null;
    }


    /**Метод для создания нового аккаунта
     * На вход принимает 3 параметра:
     * String name - имя пользователя
     * String email - email пользователя
     * String password - пароль пользователя
     * В случае успеха вернет ACCOUNT_CREATED, если такой email есть - ERROR_EMAIL*/
    public String createUser(String name, String email, String password) {

        //Проверка на существование пользователя с таким же email
        Login login = loginRepository.findByEmail(email);
        if (login != null)
            return "ERROR_EMAIL";

        //Создание новой записи в таблице Login
        Login newLogin = new Login();
        newLogin.setEmail(email);
        newLogin.setPassword(password);

        //Создание новой записи в таблице User
        User newUser = new User();
        newUser.setName(name);
        newUser.setBest_league(15);
        newUser.setLeague_max_in_this_season(15);
        newUser.setLeague_rating(0);
        newUser.setDefeats(0);
        newUser.setWins(0);
        newUser.setMoney(0);
        newUser.setStatus("ONLINE");
        newUser.setBackground(productRepository.findProductById(13));
        newUser.setForeground(productRepository.findProductById(1));
        newUser.setSkin(productRepository.findProductById(14));
        //Добавить записи в Inventory и Achievements
        //...

        loginRepository.save(newLogin);
        userRepository.save(newUser);
        return "ACCOUNT_CREATED";
    }

}
