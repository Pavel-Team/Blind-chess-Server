/**Модель сущности Inventory (таблица со всеми игровыми предметами пользователей)*/
package org.example.blindChessServer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.blindChessServer.model.embeddable.InventoryKey;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {

    //Первичный ключ
    @EmbeddedId
    private InventoryKey inventoryKey;

}
