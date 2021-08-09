/** Репозиторий для работы в БД с сущностью User */
package org.example.blindChessServer.repository;

import org.example.blindChessServer.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT o FROM User o WHERE o.user_id = ?1")
    User findByUserId(Integer user_id);

}
