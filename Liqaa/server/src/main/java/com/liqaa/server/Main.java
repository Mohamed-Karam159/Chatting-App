package com.liqaa.server;

import com.liqaa.server.controllers.reposotories.implementations.AnnouncementRepoImpl;
import com.liqaa.server.controllers.reposotories.implementations.CategoryRepoImpl;
import com.liqaa.server.controllers.reposotories.implementations.NotificationRepoImpl;
import com.liqaa.server.controllers.reposotories.implementations.UserContactCategoryRepoImpl;
import com.liqaa.server.controllers.services.implementations.NotificationServiceImpl;
import com.liqaa.shared.models.entities.Announcement;
import com.liqaa.shared.models.entities.Category;
import com.liqaa.shared.models.entities.Notification;
import com.liqaa.shared.models.entities.UserContactCategory;
import com.liqaa.shared.models.enums.NotificationType;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class Main extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        //stage.show();
    }

    public static void main(String[] args) throws SQLException {
//        // has problem !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//        NotificationRepoImpl repo = new NotificationRepoImpl();
//        Notification notification = repo.getNotificationById(10);
//        if (notification != null) {
//            notification.setRead(true);
//            repo.updateNotification(notification);
//        }

//        NotificationRepoImpl repo = new NotificationRepoImpl();
//        System.out.println(repo.getAnnouncementNotifications());

//        NotificationServiceImpl ser = new NotificationServiceImpl();
//        System.out.println(ser.getUnreadNotifications(1));

//        NotificationRepoImpl repo = new NotificationRepoImpl();
//        List<Notification> ret = repo.getUserNotifications(1);
//        if(ret != null) {
//            System.out.println(ret);
//        }
//        UserContactCategoryRepoImpl repo = new UserContactCategoryRepoImpl();
////       // Test updateUserContactCategory
////            UserContactCategory updatedCategory = new UserContactCategory(1, 3, LocalDateTime.now());
////            repo.updateUserContactCategory(updatedCategory);
//        // Test deleteUserContactCategory
//        boolean deleted = repo.deleteUserContactCategory(1, 3);
//        System.out.println("Delete Result: " + deleted);

        launch(args);
    }
}