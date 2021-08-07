/**Модель сущности Inventory (таблица со всеми игровыми предметами пользователей)*/
package org.example.blindChessServer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class League {

    //id лиги (совпадает с её номером)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer inventory_id;

    //Минимальный рейтинг для получения данной лиги
    private Integer rating_min;


    //Связь с таблицей Product
    //id продукта, который является призом за получение данной лиги
    @OneToOne()
    @JoinColumn(name="product_id")
    private Product product;
}
