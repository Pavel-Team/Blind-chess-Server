/**DTO-объект для сущности User*/
package org.example.blindChessServer.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.blindChessServer.model.*;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    /**Конструктор класса, принимающий параметр модели User*/
    public UserDTO(User user) {
        this.user_id = user.getUser_id();
        this.name = user.getName();
        this.wins = user.getWins();
        this.defeats = user.getDefeats();
        this.best_league = user.getBest_league();
        this.league_max_in_this_season = user.getLeague_max_in_this_season();
        this.league_rating = user.getLeague_rating();
        this.status = user.getStatus();
        this.money = user.getMoney();
        this.foreground = user.getForeground();
        this.background = user.getBackground();
        this.skin = user.getSkin();
        this.achievements = user.getAchievements();
        this.products = user.getProducts();
        this.friends = Stream.concat(user.getFriendList1().stream(), user.getFriendList2().stream()).collect(Collectors.toList());
        this.chats = Stream.concat(user.getChatList1().stream(), user.getChatList2().stream()).collect(Collectors.toList());
    }

    //id пользователя
    private Integer user_id;

    //Имя пользователя
    private String name;

    //Число побед
    private Integer wins;

    //Число поражений
    private Integer defeats;

    //Лучшая лига в рейтинговых матчах
    private Integer best_league;

    //Максимальная лига в текущем сезоне
    private Integer league_max_in_this_season;

    //Рейтинг в текущем сезоне
    private Integer league_rating;

    //Текущий статус пользователя (online или offline)
    private String status;

    //Число монет
    private Integer money;

    //Фон автарки пользователя
    private Product background;

    //Название переднего плана аватарки пользователя
    private Product foreground;

    //Название набора шахмат пользователя
    private Product skin;

    //Достижения пользователя
    private List<Achievements> achievements;

    //Игровые предметы пользователя
    private List<Inventory> products;

    //Друзья пользователя
    private List<Friend> friends;

    //Чаты пользователя
    private List<Chat> chats;

}
