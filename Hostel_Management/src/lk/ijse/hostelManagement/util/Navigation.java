package lk.ijse.hostelManagement.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    private static AnchorPane mealPackagesIdMain;
    public static void navigation(Routes routes,AnchorPane mealPackagesIdMain) throws IOException {
        Navigation.mealPackagesIdMain = mealPackagesIdMain;
        Navigation.mealPackagesIdMain.getChildren().clear();
        Stage window = (Stage) Navigation.mealPackagesIdMain.getScene().getWindow();

        switch (routes) {
            case MAINFROM:
                window.setTitle("Main From");
                initUI("main_from.fxml");
                break;
            case SINGNFROM:
                window.setTitle("Sign From");
                initUI("signUp_from.fxml");
                break;
            case LOGINFROM:
                window.setTitle("Login From");
                initUI("login_from.fxml");
                break;
            case SPACHSCREEN:
                window.setTitle("Spach From");
                initUI("loadinProcessFrom.fxml");
                break;
        }
    }
    private static void initUI(String location) throws IOException {
        Navigation.mealPackagesIdMain.getChildren().add(FXMLLoader.load(Navigation.class.getResource("/lk/ijse/hostelManagement/view/"+location)));
    }
}
