package application.managers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.shape.Rectangle;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import application.core.ButtonComponent;
import application.core.ButtonType;
import application.core.ComponentType;
import application.core.IComponent;
import application.core.ImageComponent;
import application.core.PanelComponent;
import application.core.TextComponent;
import application.core.utils.HelperMethods;

public class ProjectManager {
	int buttonCount;
	int textCount;
	int panelCount;
	int tableCount;
	int imageCount;
	String projectName;
	String deviceType;
	float width;
	float height;
	Map<String, IComponent> components = new HashMap<String, IComponent>();

	public ProjectManager(String projectName) {
		buttonCount = 0;
		textCount = 0;
		panelCount = 0;
		tableCount = 0;
		imageCount = 0;
		this.projectName = projectName;
	}

	public void saveProject() throws IOException {
		JSONObject project = new JSONObject();
		JSONArray buttons = new JSONArray();
		JSONArray texts = new JSONArray();
		for (Map.Entry<String, IComponent> component : components.entrySet()) {
			IComponent c = component.getValue();
			switch (c.getComponentType()) {
			case BUTTON:
				buttons.add(c.serialize());
				break;
			case TEXTFIELD:
				texts.add(c.serialize());
				break;
			}
		}

		if (!buttons.isEmpty()) {
			project.put(HelperMethods
					.ConvertComponentTypeToString(ComponentType.BUTTON),
					buttons);
		}

		if (!texts.isEmpty()) {
			project.put(HelperMethods
					.ConvertComponentTypeToString(ComponentType.TEXTFIELD),
					texts);
		}
		File myFile = new File(projectName + ".json");
		myFile.createNewFile();
		FileWriter file = new FileWriter(myFile);
		try {
			file.write(project.toJSONString());

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			file.flush();
			file.close();
		}

	}

	public IComponent createComponent(ComponentType componentType,
			Rectangle rectangle) {
		IComponent result = null;
		String id = "";
		switch (componentType) {
		case BUTTON:
			id = "btn_" + (buttonCount + 1);
			buttonCount++;
			ButtonComponent buttonComponent = new ButtonComponent(
					(float) rectangle.getX(), (float) rectangle.getY(),
					(float) rectangle.getWidth(),
					(float) rectangle.getHeight(), id, ButtonType.NORMAL);
			components.put(id, buttonComponent);
			result = buttonComponent;
			break;
		case TEXTFIELD:
			id = "txt_" + (textCount + 1);
			textCount++;
			TextComponent textComponent = new TextComponent(
					(float) rectangle.getX(), (float) rectangle.getY(),
					(float) rectangle.getWidth(),
					(float) rectangle.getHeight(), id);
			components.put(id, textComponent);
			result = textComponent;
			break;
		case IMAGE:
			id = "img_" + (imageCount + 1);
			imageCount++;
			ImageComponent imageComponent = new ImageComponent(
					(float) rectangle.getX(), (float) rectangle.getY(),
					(float) rectangle.getWidth(),
					(float) rectangle.getHeight(), id);
			components.put(id, imageComponent);
			result = imageComponent;
			break;
		case PANEL:
			id = "pnl_" + (panelCount + 1);
			panelCount++;
			PanelComponent panelComponent = new PanelComponent(
					(float) rectangle.getX(), (float) rectangle.getY(),
					(float) rectangle.getWidth(),
					(float) rectangle.getHeight(), id);
			components.put(id,panelComponent);
			result = panelComponent;			
			break;
		case TABLE:
			break;
		}
		return result;
	}

	public void updateComponent(IComponent component, String id,
			ComponentType componentType) {
		if (components.containsKey(id)) {
			components.put(id, component);
		}
	}

	public void removeComponent(String id) {
		components.remove(id);
	}

}
