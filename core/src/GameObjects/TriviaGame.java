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
import java.util.HashMap;
import java.util.Map;


/**
 * Created by zach on 10/11/16.
 */

public class TriviaGame {

    public TriviaGame() {

    }

    public Map getTrivia() {

        try {
            URL url = new URL("http://www.opentdb.com/api.php?amount=10");
            String as = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            for (String line; (line = reader.readLine()) != null; ) {
                as += line;
            }
            //as = StringEscapeUtils.unescapeHtml4(as);
            Gson gson = new Gson();
            Map<String,Object> map = new HashMap<String,Object>();
            map = (Map<String,Object>) gson.fromJson(as, map.getClass());

            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                System.out.println("key = "+key+" value= "+value+"\n");
            }

            return map;

        } catch (Exception e) {
            Map <String, String> errorMap = new HashMap<String, String>();
            errorMap.put("Error","No connection could be made to trivia database.");
            
            return errorMap;
        }
    }

}
