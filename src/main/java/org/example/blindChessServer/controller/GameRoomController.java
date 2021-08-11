/**Контроллер Friend, отвечающий за запросы, связанные с созданием игровых комнат и их работой*/
package org.example.blindChessServer.controller;

import org.example.blindChessServer.service.GameRoomService;
import org.example.blindChessServer.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/gameRoom")
public class GameRoomController {

    private GameRoomService gameRoomService; //Объект для работы с бизнес-логикой в сущности GameRoom


    /**Конструктор класса
     * На вход принимает 1 параметр:
     * GameRoomService gameRoomService - бин для работы с бизнес-логикой сущности User*/
    public GameRoomController(GameRoomService gameRoomService) {
        this.gameRoomService = gameRoomService;
    }
}
