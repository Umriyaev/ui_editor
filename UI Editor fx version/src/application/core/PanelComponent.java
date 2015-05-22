package application.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import org.json.simple.JSONObject;

import application.core.utils.HelperMethods;

public class PanelComponent implements IComponent, ISelectable,
		IBackgroundImage, IComponentContainer {

	float posX;
	float posY;
	float width;
	float height;
	List<IComponent> components = new ArrayList<IComponent>();
	String id;
	Image bgImage = null;
	Color bgColor = null;
	ComponentType componentType = ComponentType.PANEL;

	public PanelComponent(float posX, float posY, float width, float height,
			String id) {
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		this.id = id;
	}

	/************** IComponentContainer interface *********************/
	@Override
	public List<IComponent> getComponents() {
		// TODO Auto-generated method stub
		return this.components;
	}

	@Override
	public void addComponent(IComponent component) {
		// TODO Auto-generated method stub
		this.components.add(component);
	}

	@Override
	public void removeComponent(IComponent component) {
		// TODO Auto-generated method stub
		this.components.remove(component);
	}

	/***************************************************************/

	@Override
	public Image getBGImage() {
		// TODO Auto-generated method stub
		if (this.bgImage != null)
			return this.bgImage;
		return null;
	}

	@Override
	public void setBGImage(Image image) {
		// TODO Auto-generated method stub
		this.bgImage = image;
	}

	@Override
	public JSONObject serialize() {
		// TODO Auto-generated method stub
		JSONObject obj = new JSONObject();
		JSONObject properties = new JSONObject();
		JSONObject objects = new JSONObject();

		obj.put("id", this.id);

		properties.put("pos_x", this.posX);
		properties.put("pos_y", this.posY);
		properties.put("width", this.width);
		properties.put("height", this.height);

		if (this.bgColor != null)
			properties.put("bg_color", this.bgColor.toString());

		if (this.bgImage != null) {
			try {
				properties.put("bg_image", HelperMethods
						.ConvertByteArrayToString(HelperMethods
								.ConvertImageToByteArray(this.bgImage)));
			} catch (IOException e) {
				e.printStackTrace();
			} finally {

			}
		}

		for (IComponent component : components) {
			objects.put(HelperMethods.ConvertComponentTypeToString(component
					.getComponentType()), component.serialize());
		}

		obj.put("property", properties);
		obj.put("objects", objects);

		return obj;
	}

	/*********** Observable pattern *************/
	// List for all the registered listeners
	List<ComponentSelectedOnCanvasListener> listeners = new ArrayList<ComponentSelectedOnCanvasListener>();

	// Notify all the listeners about selected component
	private void fireEvents(SelectedEventArgs e) {
		for (ComponentSelectedOnCanvasListener listener : listeners) {
			listener.selected(e);
		}
	}

	// when component is created, add selected event listener using this method
	public void addListener(ComponentSelectedOnCanvasListener listener) {
		listeners.add(listener);
	}

	// if listener is no longer needed, then use this method to remove it from
	// listeners list
	public void removeListener(ComponentSelectedOnCanvasListener listener) {
		listeners.remove(listener);
	}

	// Call this method when the component is selected on canvas
	// For example handle selected event on rectangle or do something else, but
	// this method
	// should be called in order to notify about selected event.
	@Override
	public void selected() {
		// TODO Auto-generated method stub
		fireEvents(new SelectedEventArgs(this));
	}

	/******************************************/
	@Override
	public float getPosX() {
		// TODO Auto-generated method stub
		return this.posX;
	}

	@Override
	public void setPosX(float posX) {
		// TODO Auto-generated method stub
		this.posX = posX;
	}

	@Override
	public float getPosY() {
		// TODO Auto-generated method stub
		return this.posY;
	}

	@Override
	public void setPosY(float posY) {
		// TODO Auto-generated method stub
		this.posY = posY;
	}

	@Override
	public float getWidth() {
		// TODO Auto-generated method stub
		return this.width;
	}

	@Override
	public void setWidth(float width) {
		// TODO Auto-generated method stub
		this.width = width;
	}

	@Override
	public float getHeight() {
		// TODO Auto-generated method stub
		return this.height;
	}

	@Override
	public void setHeight(float height) {
		// TODO Auto-generated method stub
		this.height = height;
	}

	@Override
	public Color getBGColor() {
		// TODO Auto-generated method stub
		if (this.bgColor != null)
			return this.bgColor;
		return null;
	}

	@Override
	public void setBGColor(Color color) {
		// TODO Auto-generated method stub
		this.bgColor = color;
	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public ComponentType getComponentType() {
		// TODO Auto-generated method stub
		return this.componentType;
	}

}
