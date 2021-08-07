/**Модель сущности Login (Таблица с логинами и паролями пользователей)*/
package org.example.blindChessServer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Login {

    //id пользователя
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;

    //Логин пользователя
    @Column(nullable = false, length = 30)
    private String login;

    //Пароль пользователя
    @Column(nullable = false, length = 30)
    private String password;


    //Связь с таблицей User
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

}
