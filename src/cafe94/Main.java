package cafe94;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author DAVID
 */
public class Main extends Application {
    private AnchorPane rootPane;
    private Connection connection = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;

    /**
     * Starts the programme.
     * @param primaryStage Is an event:
     * "Used to get information in current scene.
     * @throws Exception Check if there is a fault on start.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        rootPane = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(rootPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Entry point to the systems
     * @param args Arguments passed to method.
     */
    public static void main(final String[] args) {
        launch(args);
    }
}
