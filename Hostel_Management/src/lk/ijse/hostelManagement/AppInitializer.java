package lk.ijse.hostelManagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.hostelManagement.util.SessionFactoryConfiguaration;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AppInitializer extends Application {
    public static void main(String[] args) {
        launch(args);

        Session session= SessionFactoryConfiguaration.getInstance().getSession();
        Transaction transaction= session.beginTransaction();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root= FXMLLoader.load(this.getClass().getResource("view/main_from.fxml"));
        Scene mainScene=new Scene(root);
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Hostel Management");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
