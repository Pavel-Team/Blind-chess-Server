/**Составной первичный ключ для сущности Friend*/
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
public class FriendKey implements Serializable {

    //id первого друга
    @Column(name="user_1_id")
    private Integer user_1_id;

    //id второго друга
    @Column(name="user_2_id")
    private Integer user_2_id;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FriendKey friendKey = (FriendKey) o;
        return Objects.equals(user_1_id, friendKey.user_1_id) &&
                Objects.equals(user_2_id, friendKey.user_2_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_1_id, user_2_id);
    }
}
