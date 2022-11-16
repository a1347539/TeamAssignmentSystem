package comp3111G15;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 
 * The UIApplication starts the UI of the ATU system
 * @author SzeWingKwan
 *
 */
public class UIApplication extends Application {
	/**
	 * Application entry point
	 */
	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/requestUI.fxml"));
		VBox root = (VBox) loader.load();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Comp3111G15Project");
		stage.show();
	}

	/**
	 * UI entry point
	 * @param arg argument array
	 */
	public static void run(String arg[]) {
		Application.launch(arg);
	}
}