package com.liqaa.server;

import com.liqaa.server.controllers.reposotories.implementations.AnnouncementRepoImpl;
import com.liqaa.server.util.FilePaths;
import com.liqaa.server.util.SceneManager;
import com.liqaa.shared.models.entities.Announcement;
import com.liqaa.server.controllers.reposotories.implementations.ConversationParticipantsRepoImpl;
import com.liqaa.shared.models.entities.ConversationParticipant;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Main extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(FilePaths.LOGIN_SCREEN_FXML));
//        Pane root = fxmlLoader.load();
//        Scene scene = new Scene(root, 890, 610);
//        SceneManager.initialize(stage);
//        stage.setTitle("Liqaa!");
//        stage.setScene(scene);
        //stage.show();
    }

    public static void main(String[] args) throws SQLException {
        //launch();
//        AnnouncementRepoImpl announcementRepo = new AnnouncementRepoImpl();
//        Announcement newAnnouncement = new Announcement("New Announcement", "This is the content of the new announcement.");
//        int id = announcementRepo.createAnnouncement(newAnnouncement);
//        System.out.println("New announcement added with ID: " + id);
        // Test adding a new announcement
//        try {
//            Announcement newAnnouncement = new Announcement("New Announcement", "This is the content of the new announcement.");
//            int id = announcementRepo.createAnnouncement(newAnnouncement);
//            System.out.println("New announcement added with ID: " + id);
//
//            // Test retrieving the announcement by ID
//            Announcement retrievedAnnouncement = announcementRepo.getAnnouncementById(id);
//            System.out.println("Retrieved Announcement: " + retrievedAnnouncement);
//
//            // Test getting all announcements
//            List<Announcement> allAnnouncements = announcementRepo.getAllAnnouncements();
//            System.out.println("All Announcements:");
//            for (Announcement announcement : allAnnouncements) {
//                System.out.println(announcement);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }
    }