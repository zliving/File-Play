package GameObjects;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.lang3.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by zach on 10/11/16.
 */

public class TriviaGame {

    Gson gson;

    public TriviaGame() {

    }

    public Map getTrivia() {

        try {
            // Build the URL for OpenDB API query.
            URL url = new URL("http://www.opentdb.com/api.php?amount=2");
            String as = "";

            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

            for (String line; (line = reader.readLine()) != null; ) {
                as += line;
            }
            //as = StringEscapeUtils.unescapeHtml4(as);
            gson = new Gson();

            Map<String,Object> triviaMap = new HashMap<String,Object>();
            triviaMap = (Map<String,Object>) gson.fromJson(as, triviaMap.getClass());
            ArrayList resultsArray = new ArrayList(((ArrayList) triviaMap.get("results")));
            System.out.println(resultsArray);

            return triviaMap;

        } catch (Exception e) {
            Map <String, String> errorMap = new HashMap<String, String>();
            errorMap.put("Error", e.toString());

            return errorMap;
        }

    }

}
