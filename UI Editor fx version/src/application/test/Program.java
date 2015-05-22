package application.test;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import application.core.BorderType;
import application.core.ButtonComponent;
import application.core.ButtonType;
import application.core.Event;
import application.core.ImageComponent;
import application.core.PanelComponent;
import application.core.TableComponent;
import application.core.TextComponent;

public class Program extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		launch(args);
		
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		ImageComponent imageComponent = new ImageComponent(1,1,1,1, "img_111");
		imageComponent.setBGImage(new Image("file:soldiers.PNG"));
		System.out.println(imageComponent.serialize().toJSONString());
		
		TextComponent text = new TextComponent(1,1,1,1, "txt_123");
		System.out.println(text.serialize().toJSONString());
		
		PanelComponent panelComponent = new PanelComponent(1,1,1,1, "pnl_456");
		panelComponent.addComponent(imageComponent);
		panelComponent.addComponent(text);
		
		System.out.println(panelComponent.serialize().toJSONString());
		
		ButtonComponent button = new ButtonComponent(1,1,1,1, "btn_001", ButtonType.NORMAL);
		button.addEvent(new Event("event1", "connect_url()"));
		System.out.println(button.serialize().toJSONString());
		
		TableComponent table = new TableComponent(0, 0, 0, 0, "tbl_001", 0, 0, 0, BorderType.DOTTED);
		table.addEvent(new Event("click", "bla bla bla"));
		System.out.println(table.serialize().toJSONString());
	}

}
