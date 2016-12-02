import java.awt.*;

/**
 * Created by mgunes on 30.11.2016.
 *
 * Uygulamanın başladığı sınıf
 */
public class App  {
    public static void main(String[] args) {
        EventQueue.invokeLater (
            new Runnable() {
                public void run() {
                    try {
                        Screen screen = new Screen();
                        screen.showWindow();
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        );
    }

}
