package application.core;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import org.json.simple.JSONObject;

import application.core.utils.HelperMethods;

public class TableComponent implements IEventSettable, IComponent,
		IBackgroundImage, ISelectable {

	String id;
	float posX;
	float posY;
	float width;
	float height;
	List<Event> events = new ArrayList<Event>();
	int columnCount;
	int rowCount;
	float spacing;
	BorderType borderType;
	Color bgColor=null;
	Image bgImage=null;
	ComponentType componentType=ComponentType.TABLE;

	public TableComponent(float posX, float posY, float width, float height,
			String id, int columnCount, int rowCount, float spacing,
			BorderType borderType) {
		this.id = id;
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		this.borderType = borderType;
		this.spacing = spacing;
		this.rowCount = rowCount;
		this.columnCount = columnCount;
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
	
	public void setColumnCount(int columnCount){
		this.columnCount=columnCount;
	}
	
	public int getColumnCount(){
		return this.columnCount;
	}
	
	public void setRowCount(int rowCount){
		this.rowCount=rowCount;
	}
	
	public int getRowCount(){
		return this.rowCount;
	}
	
	public void setSpacing(float spacing){
		this.spacing=spacing;
	}
	
	public float getSpacing(){
		return this.spacing;
	}
	
	public void setBorderType(BorderType borderType){
		this.borderType=borderType;
	}
	
	public BorderType getBorderType(){
		return this.borderType;
	}

	@Override
	public Image getBGImage() {
		// TODO Auto-generated method stub
		if(this.bgImage!=null) return this.bgImage;
		return null;
	}

	@Override
	public void setBGImage(Image image) {
		// TODO Auto-generated method stub
		this.bgImage=image;
	}

	@Override
	public float getPosX() {
		// TODO Auto-generated method stub
		return this.posX;
	}

	@Override
	public void setPosX(float posX) {
		// TODO Auto-generated method stub
		this.posX=posX;
	}

	@Override
	public float getPosY() {
		// TODO Auto-generated method stub
		return this.posY;
	}

	@Override
	public void setPosY(float posY) {
		// TODO Auto-generated method stub
		this.posY=posY;
	}

	@Override
	public float getWidth() {
		// TODO Auto-generated method stub
		return this.width;
	}

	@Override
	public void setWidth(float width) {
		// TODO Auto-generated method stub
		this.width=width;
	}

	@Override
	public float getHeight() {
		// TODO Auto-generated method stub
		return this.height;
	}

	@Override
	public void setHeight(float height) {
		// TODO Auto-generated method stub
		this.height=height;
	}

	@Override
	public Color getBGColor() {
		// TODO Auto-generated method stub
		if(this.bgColor!=null) return this.bgColor;
		return null;
	}

	@Override
	public void setBGColor(Color color) {
		// TODO Auto-generated method stub
		this.bgColor=color;
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
		
		property.put("pos_x", this.posX);
		property.put("pos_y", this.posY);
		property.put("width", this.width);
		property.put("height", this.height);
		property.put("column", this.columnCount);
		property.put("row", this.rowCount);
		property.put("spacing", this.spacing);
		property.put("border", HelperMethods.ConvertBorderTypeToString(this.borderType));
		
		if(!this.events.isEmpty()){
			for(Event ev : this.events){
				event.put(ev.eventName, ev.eventHandler);
			}
			obj.put("event", event);
		}
		
		obj.put("property", property);
		
		return obj;
	}

	@Override
	public List<Event> getEvents() {
		// TODO Auto-generated method stub
		return this.events;
	}

	@Override
	public void addEvent(Event event) {
		// TODO Auto-generated method stub
		this.events.add(event);
	}

	@Override
	public void removeEvent(Event event) {
		// TODO Auto-generated method stub
		this.events.remove(event);
	}

}
