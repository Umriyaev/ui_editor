package application.core;

import org.json.simple.JSONObject;

import javafx.scene.paint.Color;

public interface IComponent {
	// public Map<PropertyType, String> getProperties();
	public float getPosX();

	public void setPosX(float posX);

	public float getPosY();

	public void setPosY(float posY);

	public float getWidth();

	public void setWidth(float width);

	public float getHeight();

	public void setHeight(float height);

	public Color getBGColor();

	public void setBGColor(Color color);

	public String getID();

	public ComponentType getComponentType();

	public JSONObject serialize();
}
