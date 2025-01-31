package app;
import java.net.URI;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Comerciapp extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        //Carga la ventana login
        URI login = Paths.get("src/view/login_window.fxml").toAbsolutePath().toUri(); //ruta para la vista de login
        
        LocalDateTime DateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = DateTime.format(dateTimeFormatter);
        System.out.println("Fecha y Hora formateadas: " + formattedDateTime);
        
        Parent root = FXMLLoader.load(login.toURL() );
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
