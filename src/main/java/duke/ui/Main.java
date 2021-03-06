package duke.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for duke.main.Duke using FXML.
 */
public class Main extends Application {
    private Duke duke = new Duke();

    /**
     * Starts the duke.main.MainWindow view.
     * @param stage The stage for the MainWndow.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.setTitle("Larry the Cucumber");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
