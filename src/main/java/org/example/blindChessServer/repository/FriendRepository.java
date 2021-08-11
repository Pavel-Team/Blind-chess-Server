/** Репозиторий для работы в БД с сущностью Friend */
package org.example.blindChessServer.repository;

import org.example.blindChessServer.model.Friend;
import org.example.blindChessServer.model.embeddable.FriendKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FriendRepository extends CrudRepository<Friend, FriendKey> {
}
