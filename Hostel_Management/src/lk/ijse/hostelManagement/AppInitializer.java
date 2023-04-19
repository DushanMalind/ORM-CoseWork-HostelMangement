package lk.ijse.hostelManagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.hostelManagement.dao.custom.impl.QueryDAOImpl;
import lk.ijse.hostelManagement.projection.StudentDetailsDTO;
import lk.ijse.hostelManagement.util.SessionFactoryConfiguaration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AppInitializer extends Application {
    public static void main(String[] args) {
        launch(args);


    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    /*    Session session= SessionFactoryConfiguaration.getInstance().getSession();
        Transaction transaction= session.beginTransaction();

        QueryDAOImpl queryDAO=new QueryDAOImpl();

        List<StudentDetailsDTO>studentDetailsDTOS=queryDAO.getAllStudentProjection();
        for (StudentDetailsDTO s:studentDetailsDTOS){
            System.out.println(s.getStudentId());
            System.out.println(s.getResId());
            System.out.println(s.getRoom().getRoomTypeId());
        }*/

        Parent root= FXMLLoader.load(this.getClass().getResource("view/loadinProcessFrom.fxml"));
        Scene mainScene=new Scene(root);
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Hostel Management");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
