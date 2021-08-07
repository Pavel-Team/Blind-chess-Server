/**Модель сущности Friend (таблица с id друзей и их общего чата)
 * P.S. Таблица не связана с User, так как все запросы, связанные с друзьями, написаны вручную*/
package org.example.blindChessServer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Friend {

    //Составной первичный ключ
    @Embeddable
    class FriendKey implements Serializable {
        //id первого друга
        private Integer user_1_id;

        //id второго друга
        private Integer user_2_id;
    }

    //Первичный ключ
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
