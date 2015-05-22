package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import application.controllers.MainWindowController;
import application.core.ComponentSelectedListener;
import application.core.ComponentType;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
//			BorderPane root = new BorderPane();
//			Scene scene = new Scene(root,400,400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.show();
			
			FXMLLoader fxmlLoader = new FXMLLoader();
			VBox root = (VBox) fxmlLoader.load(Main.class.getResource("MainWindow.fxml").openStream());
			MainWindowController controller = (MainWindowController) fxmlLoader.getController();
			
			controller.addComponentSelectedListener(new ComponentSelectedListener(){

				@Override
				public void componentSelected(ComponentType component) {
					// TODO Auto-generated method stub
					switch (component) {
					case BUTTON:
						System.out.println("Button component selected");
						break;
					case IMAGE:
						System.out.println("Image component selected");
						break;
					case TABLE:
						System.out.println("Table component selected");
						break;
					case PANEL:
						System.out.println("Panel component selected");
						break;
					case TEXTFIELD:
						System.out.println("Textfield component selected");
						break;
					}
				}
				
				
			});
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("UI Editor");
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
