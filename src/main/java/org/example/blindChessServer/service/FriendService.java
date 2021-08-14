/** Сервис для работы с бизнес-логикой для сущности Friend */
package org.example.blindChessServer.service;

import org.example.blindChessServer.model.Chat;
import org.example.blindChessServer.model.Friend;
import org.example.blindChessServer.model.User;
import org.example.blindChessServer.model.embeddable.FriendKey;
import org.example.blindChessServer.repository.ChatRepository;
import org.example.blindChessServer.repository.FriendRepository;
import org.example.blindChessServer.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class FriendService {

    private final FriendRepository friendRepository; //Объект для работы в БД с сущностью Friend
    private final UserRepository userRepository;     //Объект для работы в БД с сущностью User
    private final ChatRepository chatRepository;     //Объект для работы в БД с сущностью Chat


    /**Конструктор класса
     * На вход принимает 3 параметра:
     * FriendRepository friendRepository - бин для работы в БД с сущностью Friend
     * UserRepository userRepository - бин для работы в БД с сущностью User
     * ChatRepository chatRepository - бин для работы в дб с сущностью Chat*/
    public FriendService(FriendRepository friendRepository, UserRepository userRepository, ChatRepository chatRepository) {
        this.friendRepository = friendRepository;
        this.userRepository = userRepository;
        this.chatRepository = chatRepository;
    }


    /**Метод для отправки заявки в друзья
     * В запросе принимает 2 параметра:
     * Integer user_id - id пользователя, отправляющего заявку
     * String name - имя пользователя, которого хотят добавить в друзья
     * Если всё успешно - вернет ADD_FRIEND_REQUEST, если пользователя с таким именем не найдено - ERROR_NAME,
     * если такая заявка уже была отправлена ранее - ERROR_REPEAT_REQUEST*/
    public String addFriendRequest(Integer user_id, String name){
        User userFriend = userRepository.findByName(name);
        if (userFriend == null)
            return "ERROR_NAME";

        Integer userFriendId = userFriend.getUser_id();
        FriendKey friendKey = new FriendKey(user_id, userFriendId);
        if (!friendRepository.findById(friendKey).isEmpty()) {
            return "ERROR_REPEAT_REQUEST";
        } else {
            Friend friend = new Friend();
            friend.setFriendKey(friendKey);
            friend.setStatus("WAIT");

            friendRepository.save(friend);
        }

        return "ADD_FRIEND_REQUEST";
    }


    /**Метод для ответа на заявку дружбы
     * В запросе принимает 3 параметра:
     * Integer user_id - id пользователя, отвечающего на заявку
     * String name - имя пользователя, на чью заявку отвечают
     * String answer - ответ на заявку (YES или NO)
     * Если всё успешно - вернет ADD_FRIEND, если отклонено - DONT_ADD_FRIEND (после удалится запись из таблицы FRIEND)*/
    public String addFriend(Integer user_id, String name, String answer){
        FriendKey friendKey = new FriendKey();
        friendKey.setUser_2_id(user_id);
        friendKey.setUser_1_id(userRepository.findByName(name).getUser_id());
        Friend friend = friendRepository.findById(friendKey).get();

        if (answer.equals("YES")) {
            friend.setStatus("FRIEND");

            Chat newChat = new Chat();
            newChat.setUser_1_id(userRepository.findByName(name).getUser_id());
            newChat.setUser_2_id(user_id);
            chatRepository.save(newChat);

            friend.setChat(newChat);
            friendRepository.save(friend);
            return "ADD_FRIEND";
        } else {
            friendRepository.delete(friend);
            return "DONT_ADD_FRIEND";
        }
    }


    /**Метод для удаления друга
     * В запросе принимает 2 параметра:
     * Integer user_id - id пользователя, который удаляет друга
     * String name - имя пользователя, которого удаляют
     * Если всё успешно - вернет DELETE_FRIEND*/
    public String deleteFriend(Integer user_id, String name){
        Integer user_id_2 = userRepository.findByName(name).getUser_id();
        friendRepository.delete(friendRepository.findById(new FriendKey(user_id, user_id_2)).get());
        return "DELETE_FRIEND";
    }

}
