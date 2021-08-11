/** Сервис для работы с бизнес-логикой для сущности GameRoom */
package org.example.blindChessServer.service;

import org.example.blindChessServer.repository.GameRoomRepository;
import org.springframework.stereotype.Service;


@Service
public class GameRoomService {

    private final GameRoomRepository gameRoomRepository; //Объект для работы в БД с сущностью GameRoom


    /**Конструктор класса
     * На вход принимает 1 параметр:
     * GameRoomRepository gameRoomRepository - бин для работы в БД с сущностью GameRoom*/
    public GameRoomService(GameRoomRepository gameRoomRepository) {
        this.gameRoomRepository = gameRoomRepository;
    }

}
