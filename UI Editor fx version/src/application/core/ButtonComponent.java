package application.core;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import org.json.simple.JSONObject;

import application.core.utils.HelperMethods;

public class ButtonComponent implements IComponent, IBackgroundImage,
		IEventSettable, ISelectable {
	String id;
	Image bgImage = null;
	Image toggleImage = null;
	Color bgColor = null;
	float posX;
	float posY;
	float width;
	float height;
	String caption;
	ComponentType componentType = ComponentType.BUTTON;
	ButtonType buttonType;
	List<Event> events = new ArrayList<Event>();

	public ButtonComponent(float posX, float posY, float width, float height,
			String id, ButtonType buttonType) {
		this.posX = posX;
		this.posY = posY;
		this.height = height;
		this.width = width;
		this.id = id;
		this.buttonType = buttonType;
	}

	public ButtonType getButtonType() {
		return this.buttonType;
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
	public List<Event> getEvents() {
		// TODO Auto-generated method stub
		return this.events;
	}

	@Override
	public void addEvent(Event event) {
		this.events.add(event);
	}

	@Override
	public void removeEvent(Event event) {
		this.events.remove(event);
	}

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

	public void setToggleImage(Image image) {
		this.toggleImage = image;
	}

	public Image getToggleImage(Image image) {
		if (this.toggleImage != null)
			return this.toggleImage;
		return null;
	}

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

	@Override
	public JSONObject serialize() {
		// TODO Auto-generated method stub

		JSONObject obj = new JSONObject();
		JSONObject property = new JSONObject();
		JSONObject event = new JSONObject();

		obj.put("id", this.id);

		property.put("type",
				HelperMethods.ConvertButtonTypeToString(this.buttonType));
		property.put("pos_x", this.posX);
		property.put("pos_y", this.posY);
		property.put("width", this.width);
		property.put("height", this.height);

		if (!events.isEmpty()) {
			for (Event ev : events) {
				event.put(ev.eventName, ev.eventHandler);
			}
			obj.put("event", event);
		}

		obj.put("property", property);

		return obj;
	}

}
