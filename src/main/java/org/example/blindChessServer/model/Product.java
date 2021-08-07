/**Модель сущности Product (Таблица со всеми игровыми предметами: фоны, аватарки, скины для шахмат и т.д.)*/
package org.example.blindChessServer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    //id товара
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer product_id;

    //Название товара
    @Column(nullable = false)
    private String title;

    //Тип товара (фон, передний план, скин для шахмат)
    @Column(nullable = false)
    private String type;

    //Стоимость
    @Column(nullable = false)
    private Integer price;

    //Название картинки товара
    @Column(nullable = false)
    private String image_name;

    //Продается ли в магазине (0 или 1)
    @Column(nullable = false)
    private Boolean isBuy;


    //Связь с таблицей Inventory
    @OneToMany
    @JoinColumn(name="product_id")
    private List<Inventory> inventoryList;

}
