package application.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import application.core.ComponentSelectedListener;
import application.core.ComponentType;
import application.core.IComponent;
import application.managers.CanvasManager;
import application.managers.ProjectManager;

public class MainWindowController implements Initializable {

	final DragContext dragContext = new DragContext();

	Map<String, Node> components = new HashMap<String, Node>();

	ProjectManager projectManager = new ProjectManager(
			String.valueOf((new Date()).getTime()));

	Rectangle rect = new Rectangle(0, 0, 0, 0);
	ComponentType componentType = null;

	// observer pattern
	// event listeners
	List<ComponentSelectedListener> listeners = new ArrayList<ComponentSelectedListener>();

	// add new listener
	public void addComponentSelectedListener(ComponentSelectedListener listener) {
		listeners.add(listener);
	}

	// remove listener
	public void removeComponentSelectedListener(
			ComponentSelectedListener listener) {
		listeners.remove(listener);
	}

	// button clicked listener
	
	@FXML
	private MenuItem menuSave;
	@FXML
	private HBox mainView;

	@FXML
	private VBox toolboxPanel;

	@FXML
	private VBox toolboxPanelHeader;

	@FXML
	private Button btnPanel;

	@FXML
	private Button btnButton;

	@FXML
	private Button btnTextbox;

	@FXML
	private Button btnTable;

	@FXML
	private Button btnImage;

	@FXML
	private VBox canvasPanel;

	@FXML
	private VBox canvasPanelHeader;

	@FXML
	private Pane cnvInterfaceDrawing;

	@FXML
	private VBox propertiesPanel;

	@FXML
	private VBox propertiesPanelHeader;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		rect.setStroke(Color.BLUE);
		rect.setStrokeWidth(1);
		rect.setStrokeLineCap(StrokeLineCap.ROUND);
		rect.setFill(Color.LIGHTBLUE.deriveColor(0, 1.2, 1, 0.6));
		EventHandler<ActionEvent> buttonClickedEventHandler = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Button source = (Button) event.getSource();
				switch (source.getId()) {
				case "btnButton":
					raiseEvents(ComponentType.BUTTON);
					componentType = ComponentType.BUTTON;
					break;
				case "btnImage":
					raiseEvents(ComponentType.IMAGE);
					componentType = ComponentType.IMAGE;
					break;
				case "btnTextbox":
					raiseEvents(ComponentType.TEXTFIELD);
					componentType = ComponentType.TEXTFIELD;
					break;
				case "btnTable":
					raiseEvents(ComponentType.TABLE);
					componentType = ComponentType.TABLE;
					break;
				case "btnPanel":
					raiseEvents(ComponentType.PANEL);
					componentType = ComponentType.PANEL;
					break;

				}

			}
		};

		// TODO Auto-generated method stub
		btnButton.setOnAction(buttonClickedEventHandler);
		btnImage.setOnAction(buttonClickedEventHandler);
		btnTextbox.setOnAction(buttonClickedEventHandler);
		btnTable.setOnAction(buttonClickedEventHandler);
		btnPanel.setOnAction(buttonClickedEventHandler);

		cnvInterfaceDrawing.addEventHandler(MouseEvent.MOUSE_PRESSED,
				onMousePreseedEventHandler);
		cnvInterfaceDrawing.addEventHandler(MouseEvent.MOUSE_DRAGGED,
				onMouseDraggedEventHandler);
		cnvInterfaceDrawing.addEventHandler(MouseEvent.MOUSE_RELEASED,
				onMouseReleasedEventHandler);
		menuSave.setOnAction(e -> {
			try {
				projectManager.saveProject();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

	}

	EventHandler<MouseEvent> onMouseReleasedEventHandler = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			double x = rect.getX();
			double y = rect.getY();
			double w = rect.getWidth();
			double h = rect.getHeight();

			// Rectangle node = new Rectangle(0, 0, w, h);
			// node.setStroke(Color.BLACK);
			// node.setFill((Color.BLACK.deriveColor(0, 0, 0, 0.3)));
			// node.setLayoutX(x);

			// node.setLayoutY(y);
			// cnvInterfaceDrawing.getChildren().add(node);

			if (componentType != null) {
				IComponent	iComponent = projectManager.createComponent(componentType, rect);
				Node component = CanvasManager.drawComponent(componentType, rect, iComponent);
				components.put(iComponent.getID(), component);
				cnvInterfaceDrawing.getChildren().add(component);
				componentType=null;
			}

			rect.setX(0);
			rect.setY(0);
			rect.setWidth(0);
			rect.setHeight(0);

			cnvInterfaceDrawing.getChildren().remove(rect);
		}
	};

	EventHandler<MouseEvent> onMouseDraggedEventHandler = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			double offsetX = event.getX();
			double offsetY = event.getY();

			System.out.println("getX: " + event.getX() + "    getY: "
					+ event.getY());
			System.out.println("getSceneX" + event.getSceneX()
					+ "     getSceneY" + event.getSceneY());

			// if(offsetX>0)
			rect.setX(dragContext.mouseAnchorX);
			rect.setY(dragContext.mouseAnchorY);
			rect.setWidth(offsetX - dragContext.mouseAnchorX);
			rect.setHeight(offsetY - dragContext.mouseAnchorY);
			if (rect.getWidth() < 0) {
				rect.setWidth(-rect.getWidth());
				rect.setX(rect.getX() - rect.getWidth());
			}
			// else{
			// rect.setX((event.getX()));
			// rect.setWidth(dragContext.mouseAnchorX-rect.getX());
			// }

			// if(offsetY>0)

			if (rect.getHeight() < 0) {
				rect.setHeight(-rect.getHeight());
				rect.setY(rect.getY() - rect.getHeight());
			}
			// else{
			// rect.setY(event.getY());
			// rect.setHeight(dragContext.mouseAnchorY-rect.getY());
			// }
		}
	};

	EventHandler<MouseEvent> onMousePreseedEventHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			// TODO Auto-generated method stub
			dragContext.mouseAnchorX = event.getX();
			dragContext.mouseAnchorY = event.getY();

			rect.setX(dragContext.mouseAnchorX);
			rect.setY(dragContext.mouseAnchorY);
			rect.setWidth(0);
			rect.setHeight(0);

			cnvInterfaceDrawing.getChildren().add(rect);
		}

	};

	private void raiseEvents(ComponentType component) {
		for (ComponentSelectedListener csl : listeners) {
			csl.componentSelected(component);
		}
	}

	private final class DragContext {
		public double mouseAnchorX;
		public double mouseAnchorY;
	}

}
