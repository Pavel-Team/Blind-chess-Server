/** Репозиторий для работы в БД с сущностью Achievement */
package org.example.blindChessServer.repository;

import org.example.blindChessServer.model.Achievement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AchievementRepository extends CrudRepository<Achievement, Integer> {
    List<Achievement> findAll();
}
