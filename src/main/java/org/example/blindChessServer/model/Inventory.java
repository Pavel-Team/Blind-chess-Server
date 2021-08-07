/**Модель сущности Inventory (таблица со всеми игровыми предметами пользователей)*/
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
public class Inventory {

    //Составной первичный ключ
    @Embeddable
    class InventoryKey implements Serializable {
        @Column(nullable = false)
        private Integer user_id;

        @Column(nullable = false)
        private Integer product_id;
    }

    //Первичный ключ
    @EmbeddedId
    private InventoryKey inventoryKey;

}
