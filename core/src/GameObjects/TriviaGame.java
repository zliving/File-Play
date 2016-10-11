package GameObjects;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;



/**
 * Created by zach on 10/11/16.
 */

public class TriviaGame {

    public TriviaGame() {

    }

    public String getTrivia() {

        try {
            URL url = new URL("http://www.opentdb.com/api.php?amount=10");
            String as = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            for (String line; (line = reader.readLine()) != null; ) {
                as += line;
            }

            return as;

        } catch (Exception e) {

        }

        return "Empty";
    }

}
