/**Модель сущности Achievements (таблица со всеми полученными и получаемыми пользователями достижениями)*/
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
public class Achievements {

    //Составной первичный ключ для достижений
    @Embeddable
    class AchievementsKey implements Serializable {
        //id пользователя
        @Column(name="user_id")
        private Integer user_id;

        //id получаемого достижения
        @Column(name="achievement_id")
        private Integer achievement_id;
    }

    //Первичный внешний ключ
    @EmbeddedId
    private AchievementsKey achievementsKey;

    //Текущий прогресс для данного достижения
    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer progress;

}
