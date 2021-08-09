/**Составной первичный ключ для сущности Achievements*/
package org.example.blindChessServer.model.embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AchievementsKey implements Serializable {

    //id пользователя
    @Column(name="user_id")
    private Integer user_id;

    //id получаемого достижения
    @Column(name="achievement_id")
    private Integer achievement_id;

}
