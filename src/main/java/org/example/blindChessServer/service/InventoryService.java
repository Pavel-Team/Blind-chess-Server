/** Сервис для работы с бизнес-логикой для сущности Inventory */
package org.example.blindChessServer.service;

import org.example.blindChessServer.model.Inventory;
import org.example.blindChessServer.model.embeddable.InventoryKey;
import org.example.blindChessServer.repository.InventoryRepository;
import org.springframework.stereotype.Service;


@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository; //Объект для работы в БД с сущностью Inventory


    /**Конструктор класса
     * На вход принимает 1 параметр:
     * InventoryRepository inventoryRepository - бин для работы в БД с сущностью Inventory*/
    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }


    /**Метод добавления первичных игровых предеметов новому пользователю
     * На вход принимает 1 параметр:
     * Integer user_id - id нового пользователя*/
    public void firstCreate(Integer user_id){
        InventoryKey inventoryKey = new InventoryKey();
        inventoryKey.setUser_id(user_id);
        Inventory inventory = new Inventory();

        //Foreground белой пешки
        inventoryKey.setProduct_id(1);
        inventory.setInventoryKey(inventoryKey);
        inventoryRepository.save(inventory);

        //Foreground черной пешки
        inventoryKey.setProduct_id(7);
        inventory.setInventoryKey(inventoryKey);
        inventoryRepository.save(inventory);

        //Background классической доски
        inventoryKey.setProduct_id(13);
        inventory.setInventoryKey(inventoryKey);
        inventoryRepository.save(inventory);

        //Skin стандартного набора шахмат
        inventoryKey.setProduct_id(14);
        inventory.setInventoryKey(inventoryKey);
        inventoryRepository.save(inventory);
    }

}
