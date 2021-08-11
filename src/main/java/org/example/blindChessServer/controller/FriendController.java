/**Контроллер Friend, отвечающий за запросы, связанные с добавлением в друзья*/
package org.example.blindChessServer.controller;

import org.example.blindChessServer.service.FriendService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/friend")
public class FriendController {

    private FriendService friendService; //Объект для работы с бизнес-логикой в сущности Friend


    /**Конструктор класса
     * На вход принимает 1 параметр:
     * FriendService friendService - бин для работы с бизнес-логикой сущности Friend*/
    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }


    /**PostMapping для отправки заявки в друзья
     * В запросе принимает 2 параметра:
     * Integer user_id - id пользователя, отправляющего заявку
     * String name - имя пользователя, которого хотят добавить в друзья
     * Если всё успешно - вернет ADD_FRIEND_REQUEST, если пользователя с таким именем не найдено - ERROR_NAME,
     * если такая заявка уже была отправлена ранее - ERROR_REPEAT_REQUEST, иначе - ERROR_ADD_FRIEND_REQUEST*/
    @PostMapping("/{user_id}")
    public ResponseEntity addFriendRequest(@PathVariable Integer user_id, @RequestParam String name){
        String response = friendService.addFriendRequest(user_id, name);

        switch (response) {
            case "ADD_FRIEND_REQUEST":
                return new ResponseEntity("ADD_FRIEND_REQUEST", HttpStatus.OK);
            case "ERROR_NAME":
                return new ResponseEntity("ERROR_NAME", HttpStatus.BAD_REQUEST);
            case "ERROR_REPEAT_REQUEST":
                return new ResponseEntity("ERROR_REPEAT_REQUEST", HttpStatus.BAD_REQUEST);
            default:
                return new ResponseEntity("ERROR_ADD_FRIEND_REQUEST", HttpStatus.BAD_REQUEST);
        }
    }


    /**PatchMapping для ответа на заявку дружбы
     * В запросе принимает 3 параметра:
     * Integer user_id - id пользователя, отвечающего на заявку
     * String name - имя пользователя, на чью заявку отвечают
     * String answer - ответ на заявку (YES или NO)
     * Если всё успешно - вернет ADD_FRIEND, если отклонено - DONT_ADD_FRIEND (после удалится запись из таблицы FRIEND),
     * иначе - ERROR_ADD_FRIEND*/
    @PatchMapping("/{user_id}")
    public ResponseEntity addFriend(@PathVariable Integer user_id, @RequestParam String name, @RequestParam String answer){
        String response = friendService.addFriend(user_id, name, answer);

        switch (response) {
            case "ADD_FRIEND":
                return new ResponseEntity("ADD_FRIEND", HttpStatus.OK);
            case "DONT_ADD_FRIEND":
                return new ResponseEntity("DONT_ADD_FRIEND", HttpStatus.OK);
            default:
                return new ResponseEntity("ERROR_ADD_FRIEND", HttpStatus.BAD_REQUEST);
        }
    }


    /**DeleteMapping для удаления друга
     * В запросе принимает 2 параметра:
     * Integer user_id - id пользователя, который удаляет друга
     * String name - имя пользователя, которого удаляют
     * Если всё успешно - вернет DELETE_FRIEND, иначе - ERROR_DELETE_FRIEND*/
    @DeleteMapping("/{user_id}")
    public ResponseEntity deleteFriend(@PathVariable Integer user_id, @RequestParam String name){
        String response = friendService.deleteFriend(user_id, name);

        switch (response) {
            case "DELETE_FRIEND":
                return new ResponseEntity("DELETE_FRIEND", HttpStatus.OK);
            default:
                return new ResponseEntity("ERROR_DELETE_FRIEND", HttpStatus.BAD_REQUEST);
        }
    }

}
