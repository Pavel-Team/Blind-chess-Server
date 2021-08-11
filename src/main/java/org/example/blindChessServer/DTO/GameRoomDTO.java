/**DTO-объект для сущности User*/
package org.example.blindChessServer.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameRoomDTO {

    private Integer game_room_id;
}
