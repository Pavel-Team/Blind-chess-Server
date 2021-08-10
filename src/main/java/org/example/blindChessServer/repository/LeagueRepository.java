/** Репозиторий для работы в БД с сущностью League */
package org.example.blindChessServer.repository;

import org.example.blindChessServer.model.League;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LeagueRepository extends CrudRepository<League, Long> {

    @Query("SELECT o FROM League o WHERE o.league_id = ?1")
    League findByLeagueId(Integer league_id);

}
