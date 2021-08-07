/**Модель сущности GameRoom (Таблица с данными о игровых комнатах)*/
package org.example.blindChessServer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameRoom {

    //id комнаты
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer game_room_id;

    //id первого игрока
    private Integer user_id_1;

    //id второго игрока
    private Integer user_id_2;

    //Тип игры (FRIEND, QUICK, RATING)
    @Column(nullable = false)
    private String type;

    //Текущий статус игры (объект доски)
    private String status;

    //Дата окончания текущего хода (с каждым ходом обновляется)
    @Column(nullable = false)
    private Date end_time;

    //id игрового чата
    @OneToOne
    @JoinColumn(name="chat_id")
    private Chat chat;

}
