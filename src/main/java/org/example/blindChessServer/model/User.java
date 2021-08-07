/**Модель сущности User (Таблица с данными о пользователе)*/
package org.example.blindChessServer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    //id пользователя
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;

    //Имя пользователя
    @Column(nullable = false, length = 30)
    private String name;

    //Число побед
    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer wins;

    //Число поражений
    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer defeats;

    //Лучшая лига в рейтинговых матчах
    @Column(nullable = false, columnDefinition = "int default 15") //ВРЕМЕННО
    private Integer best_league;

    //Максимальная лига в текущем сезоне
    @Column(nullable = false, columnDefinition = "int default 15") //ВРЕМЕННО
    private Integer league_max_in_this_season;

    //Рейтинг в текущем сезоне
    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer league_rating;

    //Текущий статус пользователя (online или offline)
    private String status;

    //Число монет
    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer money;

    //Фон автарки пользователя
    @OneToOne
    @JoinColumn(name="background_id")
    private Product background;

    //Название переднего плана аватарки пользователя
    @OneToOne
    @JoinColumn(name="foreground_id")
    private Product foreground;

    //Название набора шахмат пользователя
    @OneToOne
    @JoinColumn(name="skin_id")
    private Product skin;


    //Связь с таблицей Login
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private Login login;

    //Связь с таблицей Achievements
    @OneToMany()
    @JoinColumn(name="user_id")
    private List<Achievements> Achievements;

    //Связь с таблицей Inventory
    @OneToMany()
    @JoinColumn(name="user_id")
    private List<Inventory> products;

    //Связь с таблицей Friend
    @OneToMany()
    @JoinColumn(name="user_1_id")
    private List<Friend> friendList1;
    @OneToMany()
    @JoinColumn(name="user_2_id")
    private List<Friend> friendList2;

    //Связь с таблицей Chat
    @OneToMany()
    @JoinColumn(name="user_1_id")
    private List<Chat> chatList1;
    @OneToMany()
    @JoinColumn(name="user_2_id")
    private List<Chat> chatList2;
}
