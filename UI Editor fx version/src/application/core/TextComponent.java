package application.core;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

import org.json.simple.JSONObject;

public class TextComponent implements IComponent, ISelectable {

	float posX;
	float posY;
	float width;
	float height;
	String caption;
	String id;
	Color bgColor=null;
	ComponentType componentType = ComponentType.TEXTFIELD;
	//Map<PropertyType, String> properties = new HashMap<PropertyType, String>();

	/*********** Observable pattern *************/
	// List for all the registered listeners
	List<ComponentSelectedOnCanvasListener> listeners = new ArrayList<ComponentSelectedOnCanvasListener>();

	// Notify all the listeners about selected component
	private void fireEvents(SelectedEventArgs e) {
		for (ComponentSelectedOnCanvasListener listener : listeners) {
			listener.selected(e);
		}
	}
	
	public void setCaption(String caption){
		this.caption = caption;
	}
	
	public String getCaption(){
		return this.caption;
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

	// method of IJsonSerializer interface
	// TO DO
	
	//serializirovat' v ProjectManager klasse
	//v fayl proekta zapisivat' sleduyushim obrazom:
	//JSONObject project = new JSONObject();
	//for(IComponent component : components){
	//	switch(component.getComponentType())
	//		case "TEXT_FIELD": project.put("text", component.serialize()); break;
	//		case "BUTTON": project.put("button", component.serialize()); break;
	//		case "IMAGE": project.put("image", component.serialize()); break;
	//		case "PANEL": project.put("panel", component.serialize()); break;
	//		case "TABLE": project.put("table", component.serialize()); break;
	//}
	@Override
	public JSONObject serialize() {
		// TODO Auto-generated method stub
	
		JSONObject obj = new JSONObject();
		obj.put("id", this.id);
		JSONObject property = new JSONObject();
		property.put("pos_x", this.posX);
		property.put("pos_y", this.posY);
		property.put("caption", this.caption);
		property.put("width", this.width);
		property.put("height", this.height);
		if(this.bgColor!=null) property.put("bg_color", this.bgColor.toString());
		obj.put("property", property);
		
		return obj;
	}

//	@Override
//	public Map<PropertyType, String> getProperties() {
//		// TODO Auto-generated method stub
//		return this.properties;
//	}

	@Override
	public float getPosX() {
		// TODO Auto-generated method stub
		return this.posX;
	}

	@Override
	public float getPosY() {
		// TODO Auto-generated method stub
		return this.posY;
	}

	@Override
	public float getWidth() {
		// TODO Auto-generated method stub
		return this.width;
	}

	@Override
	public float getHeight() {
		// TODO Auto-generated method stub
		return this.height;
	}

	@Override
	public Color getBGColor() {
		// TODO Auto-generated method stub
		if(this.bgColor!=null) return this.bgColor;
		return null;
	}

	@Override
	public ComponentType getComponentType() {
		// TODO Auto-generated method stub
		return this.componentType;
	}

	public TextComponent(float posX, float posY, float width, float height,
			String id) {
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		this.id = id;
		this.caption = "text";
	}

	@Override
	public void setPosX(float posX) {
		// TODO Auto-generated method stub
		this.posX = posX;
	}

	@Override
	public void setPosY(float posY) {
		// TODO Auto-generated method stub
		this.posY = posY;
	}

	@Override
	public void setWidth(float width) {
		// TODO Auto-generated method stub
		this.width = width;
	}

	@Override
	public void setHeight(float height) {
		// TODO Auto-generated method stub
		this.height = height;
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

}
