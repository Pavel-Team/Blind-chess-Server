/**Модель сущности Message (таблица со всеми отправленными сообщениями (как в чатах, так и в игровых комнатах))*/
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
public class Message {

    //id сообщения
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer message_id;

    //id пользователя, отправившего сообщение
    @Column(nullable = false)
    private Integer user_id;

    //Прочитано ли сообщение
    @Column(nullable = false)
    private Boolean isRead;

    //Текст сообщения
    private String message;

    //Время отправки сообщения
    @Column(nullable = false)
    private Date date_send;

    //id чата, в котором отправлено это сообщение
    @Column(nullable = false)
    private Integer chat_id;
}
