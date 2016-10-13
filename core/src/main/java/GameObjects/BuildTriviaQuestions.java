package GameObjects;


import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import org.apache.commons.lang3.StringEscapeUtils;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;




/**
 * Created by zach on 10/11/16.
 * BuildTriviaQuestions class takes in the desired API Url and opens the connection to the
 * Open Trivia Database (OpenTDB),gets JSON results, and returns an array
 */

public class BuildTriviaQuestions {
    private String triviaApiUrl;

    public BuildTriviaQuestions(String apiUrl) {
        triviaApiUrl = apiUrl;
    }

    public Array<TriviaQuestion> getTriviaQuestions() {

        try {
            // Build the URL for OpenTDB API query using BufferedReader to get the url contents.
            URL url = new URL(triviaApiUrl);
            String html = "";

            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

            for (String line; (line = reader.readLine()) != null; ) {
                html += line;
            }
            // Close the buffered reader connection.
            reader.close();

            // Take the JSON retrieved from OpenTDB API and parse it into a JSON object.
            JsonValue root = new JsonReader().parse(html);
            JsonValue resultJson = root.get("results");
            Array<TriviaQuestion> questions = new Array<TriviaQuestion>();

            // Iterate through the resulting JSON and put each question into a TriviaQuestion object.
            for (JsonValue resultsJson : resultJson.iterator()) {
                TriviaQuestion newQuestion = new TriviaQuestion();
                newQuestion.question = StringEscapeUtils.unescapeHtml4(resultsJson.getString("question"));
                newQuestion.correctAnswer = (resultsJson.getString("correct_answer"));
                newQuestion.incorrectAnswers = (resultsJson.get("incorrect_answers").asStringArray());
                questions.add(newQuestion);
            }
            // Iterate through each incorrect answer and unescape the HTML that might be in them.
            for(int i = 0; i < questions.size; i++ ) {
                for(int j = 0; j <questions.get(i).incorrectAnswers.length; j++) {
                    questions.get(i).incorrectAnswers[j] = StringEscapeUtils.unescapeHtml4(questions.get(i).incorrectAnswers[j]);
                }
            }


            return questions;

        } catch (Exception e) {

            // Send an error back to user if questions can't be retrieved.
            Array<TriviaQuestion> errorArray = new Array<TriviaQuestion>();
            TriviaQuestion errorQuestion = new TriviaQuestion();
            errorQuestion.errorMessage = e.toString();

            errorArray.add(errorQuestion);

            return errorArray;
        }
    }

}
