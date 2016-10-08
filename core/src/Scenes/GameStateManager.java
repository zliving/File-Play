package Scenes;

import com.badlogic.gdx.Screen;

/**
 * Created by Chris on 10/8/2016.
 */

public class GameStateManager {
    private Screen currentScreen;
    public enum Screens{
        MAINMENU, SETTINGS, PLAY, LOBBY, LEADERBOARDS;
    }

    public GameStateManager(){
        setState(Screens.MAINMENU);
    }

    public void setState(Screens state){
        //Release the sources of the current state
        if(currentScreen != null){
            currentScreen.dispose();
        }

        //Swtich to the appropriate screen
        switch (state){
            case MAINMENU:
                currentScreen = new MainMenu();
                break;

            case SETTINGS:
                currentScreen = new SettingsScreen();
                break;

            case PLAY:

        }
    }

}
