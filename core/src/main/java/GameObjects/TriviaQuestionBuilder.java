package GameObjects;


import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import org.apache.commons.lang3.StringEscapeUtils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


/**
 * Created by zach on 10/11/16. TriviaQuestionBuilder class takes in the desired API Url and opens
 * the connection to the Open Trivia Database (OpenTDB),gets JSON results, and returns an array
 */
public class TriviaQuestionBuilder {

  public TriviaQuestionBuilder() {
  }

  public Array<TriviaQuestion> getTriviaQuestions(String triviaApiUrl) {

    try {
      String questionJSON = "";
      String line;
      URL url;
      url = new URL(triviaApiUrl);
      HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
      // If we have an open connection get the trivia from OpenTDB
      if (con != null) {
        try {
          BufferedReader br =
                  new BufferedReader(
                          new InputStreamReader(con.getInputStream()));
          // Read through the OpenTDB response page.
          while ((line = br.readLine()) != null) {
            questionJSON += line;
          }
          br.close();
          // Take the JSON retrieved from OpenTDB API and parse it into a JSON object.
          JsonValue root = new JsonReader().parse(questionJSON);
          JsonValue resultJson = root.get("results");
          Array<TriviaQuestion> questions = new Array<TriviaQuestion>();
          if (root.get("response_code").asInt() != 0) {
            Array<TriviaQuestion> errorArray = new Array<TriviaQuestion>();
            TriviaQuestion errorQuestion = new TriviaQuestion();
            errorQuestion.errorMessage = "API Could not be reached";
            errorArray.add(errorQuestion);

            return errorArray;
          }
          // Iterate through the resulting JSON and put each question into a TriviaQuestion object.
          for (JsonValue resultsJson : resultJson.iterator()) {
            TriviaQuestion newQuestion = new TriviaQuestion();
            newQuestion.question = StringEscapeUtils.unescapeHtml4(resultsJson.getString("question"));
            newQuestion.correctAnswer = StringEscapeUtils.unescapeHtml4(resultsJson.getString("correct_answer"));
            newQuestion.incorrectAnswers = (resultsJson.get("incorrect_answers").asStringArray());
            questions.add(newQuestion);
          }
          // Iterate through each incorrect answer and unescape the HTML that might be in them.
          for (int i = 0; i < questions.size; i++) {
            for (int j = 0; j < questions.get(i).incorrectAnswers.length; j++) {
              questions.get(i).incorrectAnswers[j] =
                      StringEscapeUtils.unescapeHtml4(questions.get(i).incorrectAnswers[j]);
            }
          }

          return questions;

        } catch (IOException e) {
          e.printStackTrace();
        }
      } else {
        // There is no connection to the OpenTDB.
        // Send an error back to user if questions can't be retrieved.
        Array<TriviaQuestion> errorArray = new Array<TriviaQuestion>();
        TriviaQuestion errorQuestion = new TriviaQuestion();
        errorQuestion.errorMessage = "No connection can be made.";
        errorArray.add(errorQuestion);

        return errorArray;
      }
    } catch (MalformedURLException e) {
      // Send an error back to user if questions can't be retrieved.
      Array<TriviaQuestion> errorArray = new Array<TriviaQuestion>();
      TriviaQuestion errorQuestion = new TriviaQuestion();
      errorQuestion.errorMessage = "URL is not valid. " + e.toString();
      errorArray.add(errorQuestion);

      return errorArray;
    } catch (IOException e) {
      // Send an error back to user if questions can't be retrieved.
      Array<TriviaQuestion> errorArray = new Array<TriviaQuestion>();
      TriviaQuestion errorQuestion = new TriviaQuestion();
      errorQuestion.errorMessage = "IO Exception? " + e.toString();
      errorArray.add(errorQuestion);

      return errorArray;
    }

    return null;
  }

}
