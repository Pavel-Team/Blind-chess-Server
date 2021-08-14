/** Репозиторий для работы в БД с сущностью Achievements */
package org.example.blindChessServer.repository;

import org.example.blindChessServer.model.Achievements;
import org.example.blindChessServer.model.embeddable.AchievementsKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AchievementsRepository extends CrudRepository<Achievements, AchievementsKey> {
}
