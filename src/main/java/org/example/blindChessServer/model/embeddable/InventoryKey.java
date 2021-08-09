/**Составной первичный ключ для сущности Inventory*/
package org.example.blindChessServer.model.embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;


@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryKey implements Serializable {

    @Column(nullable = false)
    private Integer user_id;

    @Column(nullable = false)
    private Integer product_id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryKey that = (InventoryKey) o;
        return Objects.equals(user_id, that.user_id) &&
                Objects.equals(product_id, that.product_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, product_id);
    }
}
