package MainMenu;

import input.MVCInput;
import output.MVCOutput;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainMenuController {
    MainMenuView mainMenuView;

    public MainMenuController(MainMenuView mainMenuView) {
        this.mainMenuView = mainMenuView;
        mainMenuView.pinput.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                mainMenuView.frame.setVisible(false);
                new MVCInput();
            }
        });
        mainMenuView.poutput.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                mainMenuView.frame.setVisible(false);
                try {
                    new MVCOutput();
                } catch (InterruptedException ex) {
                    Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }


}
