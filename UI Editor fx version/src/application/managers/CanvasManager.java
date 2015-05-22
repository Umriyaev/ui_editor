package application.managers;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import application.core.ComponentType;
import application.core.IComponent;
import application.core.TextComponent;

public class CanvasManager {
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
			return text;
		case BUTTON:
			Button button = new Button("button");
			button.setTranslateX(rectangle.getX());
			button.setTranslateY(rectangle.getY());
			button.setPrefWidth(rectangle.getWidth());
			button.setPrefHeight(rectangle.getHeight());
			return button;
			

		}
		return null;
	}
}
