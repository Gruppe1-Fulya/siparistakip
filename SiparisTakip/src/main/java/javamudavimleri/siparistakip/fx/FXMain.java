package javamudavimleri.siparistakip.fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class FXMain extends Application {
    Scene scene;
    FXMLLoader loader;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        loader = new FXMLLoader(getClass().getResource("Siparis_Takip.fxml"));
        //loader.setController(Main.this);
        scene = new Scene(loader.load());
        primaryStage.setTitle("SiparisTakip");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
}