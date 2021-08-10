/** Сервис для работы с бизнес-логикой для сущности User */
package org.example.blindChessServer.service;

import org.example.blindChessServer.DTO.UserDTO;
import org.example.blindChessServer.model.Inventory;
import org.example.blindChessServer.model.League;
import org.example.blindChessServer.model.Login;
import org.example.blindChessServer.model.User;
import org.example.blindChessServer.model.embeddable.InventoryKey;
import org.example.blindChessServer.repository.*;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;           //Объект для работы в БД с сущностью User
    private final LoginRepository loginRepository;         //Объект для работы в БД с сущностью Login
    private final ProductRepository productRepository;     //Объект для работы в БД с сущностью Product
    private final LeagueRepository leagueRepository;       //Объект для работы в БД с сущностью League
    private final InventoryRepository inventoryRepository; //Объект для работы в БД с сущностью Inventory


    /**Конструктор класса
     * На вход принимает 5 параметров:
     * UserRepository userRepository - бин для работы в БД с сущностью User
     * LoginRepository loginRepository - бин для работы в БД с сущностью Login
     * ProductRepository productRepository - бин для работы в БД с сущностью Product
     * LeagueRepository leagueRepository - бин для работы в БД с сущностью League
     * InventoryRepository inventoryRepository - бин для работы в БД с сущностью Inventory*/
    public UserService(UserRepository userRepository, LoginRepository loginRepository, ProductRepository productRepository,
                       LeagueRepository leagueRepository, InventoryRepository inventoryRepository) {
        this.userRepository = userRepository;
        this.loginRepository = loginRepository;
        this.productRepository = productRepository;
        this.leagueRepository = leagueRepository;
        this.inventoryRepository = inventoryRepository;
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
     * В случае успеха вернет ACCOUNT_CREATED, если такой email есть - ERROR_EMAIL, если такое имя есть - ERROR_NAME*/
    public String createUser(String name, String email, String password) {

        //Проверка на существование пользователя с таким же email
        Login login = loginRepository.findByEmail(email);
        if (login != null)
            return "ERROR_EMAIL";

        //Проверка на существование пользователя с таким же именем
        User user = userRepository.findByName(name);
        if (user != null)
            return "ERROR_NAME";

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


    /**Метод для редактирования имени игрока
     * На вход принимает 2 параметра:
     * Integer user_id - id заданного пользвателя
     * String newName - новое имя пользователя
     * В случае успеха вернет NAME_UPDATE, если такое имя уже есть - ERROR_NAME*/
    public String updateName(Integer user_id, String newName) {

        //Проверка на существование пользователя с таким же именем
        User user = userRepository.findByName(newName);
        if (user != null)
            return "ERROR_NAME";

        User updateUser = userRepository.findByUserId(user_id);
        updateUser.setName(newName);
        userRepository.save(updateUser);
        return "NAME_UPDATE";
    }


    /**Метод для редактирования статистики пользователя после проведенного матча
     * На вход принимает 3 параметра:
     * Integer user_id - id заданного пользвателя
     * Integer isWin - победил ли польователя (0 или 1)
     * String typeGame - тип игры (QUICK_GAME, FRIEND_GAME или RATING_GAME)
     * В случае успеха вернет STATISTICS_UPDATE*/
    public String updateStatistics(Integer user_id, Integer isWin, String typeGame) {
        User user = userRepository.findByUserId(user_id);

        //Проверка на победу
        if (isWin.equals(1)) {
            //Если это была рейтинговая игра
            if (typeGame.equals("RATING_GAME")) {
                League league = user.getLeague();              //Текущая лига
                Integer league_id = league.getLeague_id();     //Текущий номер лиги
                Integer rating_plus = league.getRating_plus(); //Рейтинг, полученный за победу
                Integer rating = user.getLeague_rating();      //Текущий рейтинг

                //Устанавливаем при необходимости новую лигу (если необходимо - добавляем награду в инвентарь)
                if (league_id != 1) {
                    League nextLeague = leagueRepository.findByLeagueId(league_id - 1); //Следующая лига
                    if (rating + rating_plus >= nextLeague.getRating_min()) {
                        user.setLeague(nextLeague);

                        //Проверяем на максимальную лигу в этом сезоне
                        if (user.getLeague_max_in_this_season() > league_id - 1) {
                            user.setLeague_max_in_this_season(league_id - 1);

                            //Проверяем на получение предмета
                            if (nextLeague.getProduct() != null) {
                                Inventory prize = new Inventory();
                                prize.setInventoryKey(new InventoryKey(user_id, nextLeague.getProduct().getProduct_id()));
                                inventoryRepository.save(prize);
                            }
                        }

                        //Проверяем на лучшую лигу
                        if (user.getBest_league() > league_id - 1)
                            user.setBest_league(league_id - 1);
                    }
                }

                user.setLeague_rating(rating + rating_plus); //Увеличиваем рейтинг
            }
            user.setWins(user.getWins() + 1);
        } else {
            //Если это была рейтинговая игра
            if (typeGame.equals("RATING_GAME")) {
                League league = user.getLeague();                //Текущая лига
                Integer league_id = league.getLeague_id();       //Текущий номер лиги
                Integer rating_minus = league.getRating_minus(); //Рейтинг, отнимаемый за поражение
                Integer rating = user.getLeague_rating();        //Текущий рейтинг

                //Устанавливаем при необходимости новую лигу
                if (league_id != 15) {
                    League nextLeague = leagueRepository.findByLeagueId(league_id + 1); //Следующая лига
                    if (rating - rating_minus < league.getRating_min()) {
                        user.setLeague(nextLeague);
                    }
                }

                user.setLeague_rating(rating - rating_minus); //Отнимаем рейтинг
            }
            user.setDefeats(user.getDefeats() + 1);
        }

        userRepository.save(user);
        return "STATISTICS_UPDATE";
    }

}
