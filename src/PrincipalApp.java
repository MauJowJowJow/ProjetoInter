import java.awt.EventQueue;

import controller.PrincipalMenuController;
import javafx.application.Application;
import javafx.stage.Stage;

public class PrincipalApp{
	private static PrincipalMenuController principalMenuController;
	
	public static void main2(String[] args) {		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					principalMenuController = new PrincipalMenuController();
					principalMenuController.iniciaMenu(args);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
