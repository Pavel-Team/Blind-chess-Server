/**Модель сущности Chat (таблица со всеми чатами и их участниками)*/
package org.example.blindChessServer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chat {

    //id чата
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer chat_id;

    //id первого участника чата
    @Column(nullable = false)
    private Integer user_1_id;

    //id второго участника чата
    @Column(nullable = false)
    private Integer user_2_id;


    //Связь с таблицей Message
    @OneToMany
    @JoinColumn(name="chat_id")
    private List<Message> messages;

}
