package application.managers;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import application.core.ComponentType;
import application.core.IComponent;
import application.core.TextComponent;

public class CanvasManager {
	final static DragContext dragContext = new DragContext();

	public static Node drawComponent(ComponentType componentType,
			Rectangle rectangle, IComponent iComponent) {

		switch (componentType) {
		case TEXTFIELD:
			TextField text = new TextField();
			TextComponent component = (TextComponent) iComponent;
			text.setTranslateX(rectangle.getX());
			text.setTranslateY(rectangle.getY());
			text.setPrefWidth(rectangle.getWidth());
			text.setPrefHeight(rectangle.getHeight());
			text.setPromptText(component.getCaption());
			setDragEvents(text);
			return text;
		case BUTTON:
			Button button = new Button("button");
			button.setTranslateX(rectangle.getX());
			button.setTranslateY(rectangle.getY());
			button.setPrefWidth(rectangle.getWidth());
			button.setPrefHeight(rectangle.getHeight());
			setDragEvents(button);
			return button;

		case IMAGE:
			ImageView imageView = new ImageView();
			Image image = new Image("file:dummy-image.jpg");
			imageView.setImage(image);
			imageView.setFitHeight(rectangle.getHeight());
			imageView.setFitWidth(rectangle.getWidth());
			imageView.setX(rectangle.getX());
			imageView.setY(rectangle.getY());
			setDragEvents(imageView);
			return imageView;
			
		case PANEL:
			Pane panel = new Pane();
			panel.setTranslateX(rectangle.getX());
			panel.setTranslateY(rectangle.getY());
			panel.setPrefHeight(rectangle.getHeight());
			panel.setPrefWidth(rectangle.getWidth());
			setDragEvents(panel);
			panel.getStyleClass().add("panelComponent");
			return panel;

		}
		return null;
	}

	private static final class DragContext {
		public double mouseAnchorX;
		public double mouseAnchorY;
	}

	private static void setDragEvents(Node node) {
		setOnMousePressed(node);
		setOnMouseDragged(node);
		setOnMouseReleased(node);
		setOnMouseEntered(node);
	}

	private static void setOnMousePressed(Node node) {
		node.setOnMousePressed(e -> {
			dragContext.mouseAnchorX = e.getSceneX();
			dragContext.mouseAnchorY = e.getSceneY();
			node.setCursor(Cursor.MOVE);
			e.consume(); //for stopping event handling (bubbling)
		});
	}

	private static void setOnMouseReleased(Node node) {
		node.setOnMouseReleased(e -> {
			node.setCursor(Cursor.HAND);
			e.consume(); //for stopping event handling (bubbling)
		});
	}

	private static void setOnMouseDragged(Node node) {
		node.setOnMouseDragged(e -> {

			double dx = e.getSceneX() - dragContext.mouseAnchorX;
			double dy = e.getSceneY() - dragContext.mouseAnchorY;
			dragContext.mouseAnchorX = e.getSceneX();
			dragContext.mouseAnchorY = e.getSceneY();
			System.out.println("Drag context x,y: " + dragContext.mouseAnchorX
					+ ", " + dragContext.mouseAnchorY);
			System.out.println("Mouse x, y: " + e.getSceneX() + " , "
					+ e.getSceneY());
			System.out.println("Node x, y: " + node.getTranslateX() + ", "
					+ node.getTranslateY());
			node.setTranslateX(node.getTranslateX() + dx);
			node.setTranslateY(node.getTranslateY() + dy);
			e.consume(); //for stopping event handling (bubbling)

		});
	}

	private static void setOnMouseEntered(Node node) {
		node.setOnMouseEntered(e -> {
			node.setCursor(Cursor.HAND);
			e.consume(); //for stopping event handling (bubbling)
		});
	}

}
