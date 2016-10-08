package Scenes;

import com.badlogic.gdx.Screen;

public class GameStateManager {
    private Screen currentScreen;
    public enum Screens{
        MAINMENU, SETTINGS, PLAY, LOBBY, LEADERBOARDS, TEST;
    }

    public GameStateManager(){
        setState(Screens.MAINMENU);
    }

    public GameStateManager(Screens screen){
        setState(screen);
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
                currentScreen = new Settings();
                break;

            case PLAY:
                currentScreen = new Play();
                break;

            case LOBBY:
                currentScreen = new Lobby();
                break;

            case LEADERBOARDS:
                currentScreen = new Leaderboards();
                break;

            case TEST:
                currentScreen = new Test();
                break;

            default:
                currentScreen = new MainMenu();
                break;
        }
    }

    public void render(float delta){
        currentScreen.render(delta);
    }

    public void dispose(){
        currentScreen.dispose();
    }

}
