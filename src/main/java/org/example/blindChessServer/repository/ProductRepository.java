/** Репозиторий для работы в БД с сущностью Product (игровые предметы) */
package org.example.blindChessServer.repository;

import org.example.blindChessServer.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    @Query("SELECT o FROM Product o WHERE o.product_id = ?1")
    Product findProductById(Integer product_id);

}
