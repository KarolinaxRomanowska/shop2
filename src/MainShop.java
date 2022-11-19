import core.Engine;

import java.io.IOException;

public class MainShop {
    public static void main(String[] args) {
        try{
            Engine.start();
        }
        catch(IOException e){
            System.out.println("Nie działa wczytywanie z klawiatury!");
        }

    }
}
