package GameObjects;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by zach on 10/11/16.
 */

public class TriviaGame {


    public TriviaGame() {

    }

    public Array<TriviaQuestions> getTrivia() {

        try {
            // Build the URL for OpenDB API query.
            URL url = new URL("http://www.opentdb.com/api.php?amount=2");
            String as = "";

            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

            for (String line; (line = reader.readLine()) != null; ) {
                as += line;
            }
            //as = StringEscapeUtils.unescapeHtml4(as);
            reader.close();

            JsonValue root = new JsonReader().parse(as);
            JsonValue resultJson = root.get("results");
            Array<TriviaQuestions> questions = new Array<TriviaQuestions>();
            for (JsonValue resultsJson : resultJson.iterator()) {
                TriviaQuestions newQuestion = new TriviaQuestions();
                newQuestion.question = resultsJson.getString("question");
                newQuestion.correctAnswer = (resultsJson.getString("correct_answer"));
                newQuestion.incorrectAnswers = (resultsJson.get("incorrect_answers").asStringArray());
                questions.add(newQuestion);
            }
            for(int i = 0; i < questions.size; i++ ) {
                System.out.println(questions.get(i).question);
                for(int j = 0; j <questions.get(i).incorrectAnswers.length; j++) {
                    System.out.println(questions.get(i).incorrectAnswers[j]);
                }
                System.out.println(questions.get(i).correctAnswer);
            }


            return questions;

        } catch (Exception e) {
            Map <String, String> errorMap = new HashMap<String, String>();
            errorMap.put("Error", e.toString());

            //return errorMap;
        }
        return null;
    }

}
