/**Модель сущности Achievement (таблица с информацией о всех достижениях)*/
package org.example.blindChessServer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Achievement {

    //id достижения
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer achievement_id;

    //Название достижения
    @Column(nullable = false, length = 40)
    private String title;

    //Описание достижения
    @Column(nullable = false)
    private String description;

    //Название изображения достижения
    @Column(nullable = false, length = 100)
    private String image_name;

    //Прогресс, необходимый для получения достижения
    private Integer progress_max;

}
