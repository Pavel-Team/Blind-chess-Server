/** Сервис для работы с бизнес-логикой для сущности Achievements */
package org.example.blindChessServer.service;

import org.example.blindChessServer.model.Achievement;
import org.example.blindChessServer.model.Achievements;
import org.example.blindChessServer.model.embeddable.AchievementsKey;
import org.example.blindChessServer.repository.AchievementRepository;
import org.example.blindChessServer.repository.AchievementsRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AchievementsService {

    private final AchievementsRepository achievementsRepository; //Объект для работы в БД с сущностью Achievements
    private final AchievementRepository achievementRepository; //Объект для работы в БД с сущностью Achievement


    /**Конструктор класса
     * На вход принимает 2 параметра:
     * AchievementsRepository achievementsRepository - бин для работы в БД с сущностью Achievements
     * AchievementRepository achievementRepository - бин для работы в БД с сущностью Achievement*/
    public AchievementsService(AchievementsRepository achievementsRepository, AchievementRepository achievementRepository) {
        this.achievementsRepository = achievementsRepository;
        this.achievementRepository = achievementRepository;
    }


    /**Метод для изменения прогресса получаемых достижений
     * В запросе принимает 3 параметра:
     * Integer user_id - id пользователя, отправляющего заявку
     * Integer achievement_id - id, получаемого достижения
     * Integer progress - новый прогресс достижения
     * Если всё успешно - вернет CHANGE_ACHIEVEMENT, иначе - ERROR_CHANGE_ACHIEVEMENT*/
    public String changeAchievement(Integer user_id, Integer achievement_id, Integer progress) {
        AchievementsKey achievementsKey = new AchievementsKey();
        achievementsKey.setUser_id(user_id);
        achievementsKey.setAchievement_id(achievement_id);

        if(achievementsRepository.findById(achievementsKey).isEmpty())
            return "ERROR_CHANGE_ACHIEVEMENT";

        Achievements achievement = achievementsRepository.findById(achievementsKey).get();
        achievement.setProgress(progress);
        achievementsRepository.save(achievement);
        return "CHANGE_ACHIEVEMENT";
    }


    /**Метод для добавления записей для нового пользователя в таблицу Achievements
     * В запросе принимает 1 параметр:
     * Integer user_id - id пользователя, отправляющего заявку*/
    public void createAchievements(Integer user_id) {
        List<Achievement> achievementList = achievementRepository.findAll();

        AchievementsKey achievementsKey = new AchievementsKey();
        achievementsKey.setUser_id(user_id);
        Achievements achievements = new Achievements();

        for(Achievement achievement : achievementList) {
            achievementsKey.setAchievement_id(achievement.getAchievement_id());

            achievements.setProgress(0);
            achievements.setAchievementsKey(achievementsKey);
            achievementsRepository.save(achievements);
        }
    }

}
