/**Модель сущности Achievements (таблица со всеми полученными и получаемыми пользователями достижениями)*/
package org.example.blindChessServer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.blindChessServer.model.embeddable.AchievementsKey;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Achievements {

    //Первичный внешний ключ
    @EmbeddedId
    private AchievementsKey achievementsKey;

    //Текущий прогресс для данного достижения
    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer progress;

}
