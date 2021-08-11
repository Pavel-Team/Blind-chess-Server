/** Репозиторий для работы в БД с сущностью GameRoom */
package org.example.blindChessServer.repository;

import org.example.blindChessServer.model.GameRoom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GameRoomRepository extends CrudRepository<GameRoom, Long> {
}
