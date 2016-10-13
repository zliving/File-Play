package GameObjects;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import org.apache.commons.lang3.StringEscapeUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by zach on 10/11/16.
 * TriviaGame class opens the connection to the OpenTDB, gets JSON results, and returns an array
 * of TriviaQuestions objects.
 */

public class TriviaGame {
    private String triviaUrl;

    public TriviaGame(String url) {
        triviaUrl = url;

    }

    public Array<TriviaQuestions> getTrivia() {

        try {
            // Build the URL for OpenDB API query using BufferedReader to get the url contents.
            URL url = new URL(triviaUrl);
            String as = "";

            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

            for (String line; (line = reader.readLine()) != null; ) {
                as += line;
            }
            //Close the buffered reader connection.
            reader.close();

            //Take the JSON retrieved from OpenTDB API and parse it into a JSON object.
            JsonValue root = new JsonReader().parse(as);
            JsonValue resultJson = root.get("results");
            Array<TriviaQuestions> questions = new Array<TriviaQuestions>();

            //Iterate through the resulting JSON and put each question into a TriviaQuestions object
            for (JsonValue resultsJson : resultJson.iterator()) {
                TriviaQuestions newQuestion = new TriviaQuestions();
                newQuestion.question = StringEscapeUtils.unescapeHtml4(resultsJson.getString("question"));
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
            
            //Send an error back to user if questions can't be retrieved.
            Array<TriviaQuestions> errorArray = new Array<TriviaQuestions>();
            TriviaQuestions errorQuestion = new TriviaQuestions();
            errorQuestion.errorMessage = e.toString();

            errorArray.add(errorQuestion);

            return errorArray;
        }
    }

}
