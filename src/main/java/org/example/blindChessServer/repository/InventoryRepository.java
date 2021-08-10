/** Репозиторий для работы в БД с сущностью Inventory */
package org.example.blindChessServer.repository;

import org.example.blindChessServer.model.Inventory;
import org.example.blindChessServer.model.embeddable.InventoryKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InventoryRepository extends CrudRepository<Inventory, InventoryKey> {
}
