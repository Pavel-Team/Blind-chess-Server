/** Репозиторий для работы в БД с сущностью Chat */
package org.example.blindChessServer.repository;

import org.example.blindChessServer.model.Chat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ChatRepository extends CrudRepository<Chat, Integer> {
}
