/**Модель сущности Friend (таблица с id друзей и их общего чата)*/
package org.example.blindChessServer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.blindChessServer.model.embeddable.FriendKey;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Friend {

    //Составной первичный ключ
    @EmbeddedId
    private FriendKey friendKey;

    //Статус приёма заявки дружбы (WAIT или FRIEND)
    @Column(nullable = false, length = 6)
    private String status;

    //id чата между друзьями
    @OneToOne
    @JoinColumn(name="chat_id")
    private Chat chat;

}