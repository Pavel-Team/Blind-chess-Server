/**Контроллер Achievements, отвечающий за запросы, связанные с изменением прогресса получаемых достижений*/
package org.example.blindChessServer.controller;

import org.example.blindChessServer.service.AchievementsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/achievements")
public class AchievementsController {

    private AchievementsService achievementsService; //Объект для работы с бизнес-логикой в сущности Achievements


    /**Конструктор класса
     * На вход принимает 1 параметр:
     * AchievementsService achievementsService - бин для работы с бизнес-логикой сущности Achievements*/
    public AchievementsController(AchievementsService achievementsService) {
        this.achievementsService = achievementsService;
    }


    /**PatchMapping для изменения прогресса получаемых достижений
     * В запросе принимает 3 параметра:
     * Integer user_id - id пользователя, отправляющего заявку
     * Integer achievement_id - id, получаемого достижения
     * Integer progress - новый прогресс достижения
     * Если всё успешно - вернет CHANGE_ACHIEVEMENT, иначе - ERROR_CHANGE_ACHIEVEMENT*/
    @PatchMapping("/{user_id}")
    public ResponseEntity changeAchievement(@PathVariable Integer user_id, @RequestParam Integer achievement_id,
                                            @RequestParam Integer progress) {
        String response = achievementsService.changeAchievement(user_id, achievement_id, progress);

        if (response.equals("CHANGE_ACHIEVEMENT"))
            return new ResponseEntity("CHANGE_ACHIEVEMENT", HttpStatus.OK);
        else
            return new ResponseEntity("ERROR_CHANGE_ACHIEVEMENT", HttpStatus.BAD_REQUEST);
    }

}
