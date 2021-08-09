/** Репозиторий для работы в БД с сущностью Login */
package org.example.blindChessServer.repository;

import org.example.blindChessServer.model.Login;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LoginRepository extends CrudRepository<Login, Long> {
    Login findByEmail(String email);
}
