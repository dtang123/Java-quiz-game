package main.java;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.node.ArrayNode;

import javax.swing.*;

public class GameBackEnd {
    private Screen model;
    private GameFrontEnd view;

    private JsonNode questionBank;

    public GameBackEnd(Screen m, GameFrontEnd v) {
        model = m;
        view = v;
        initView();
    }

    public String apiCall(int category) {
        String strURL = "https://opentdb.com/api.php?amount=10&type=multiple&category=" + category;
        try {
            URL url = new URL(strURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                throw new RuntimeException("Response Error");
            } else {
                String responseString = "";
                Scanner scanner = new Scanner(url.openStream());

                while(scanner.hasNext()) {
                    responseString += scanner.nextLine();
                }
                scanner.close();
                System.out.println(responseString);
                ObjectMapper mapper = new ObjectMapper();

                JsonNode responseJson = mapper.readValue(responseString, JsonNode.class);
                questionBank = responseJson.get("results");
                model.setPoints(0);
                model.setQuestion(0);
                questionController();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "";
    }

    public void initView() {
        view.initialView();
    }

    public void initController() {
        view.getButton1().addActionListener(e -> apiCall(22));
        view.getButton2().addActionListener(e -> apiCall(18));
        view.getButton3().addActionListener(e -> apiCall(15));
        view.getButton4().addActionListener(e -> apiCall(21));

    }

    public void checkAnswer(JButton button, String answer) {
        System.out.println(answer);
        System.out.println(button.getText());
        if (button.getText().equals(answer)) {
            model.setPoints(model.getPoints() + 1);
            view.flash(true);
        } else {
            view.flash(false);
        }
        model.setQuestion(model.getQuestion() + 1);
        if (questionBank.has(model.getQuestion())) {
            questionController();
        } else {
            view.endPage(model.getPoints());
        }
    }

    public void questionActions(String correctAns) {
        view.getButton1().addActionListener(e -> checkAnswer(view.button1, correctAns));
        view.getButton2().addActionListener(e -> checkAnswer(view.button2, correctAns));
        view.getButton3().addActionListener(e -> checkAnswer(view.button3, correctAns));
        view.getButton4().addActionListener(e -> checkAnswer(view.button4, correctAns));
    }
    public void questionController() {
        view.questionView(model.getPoints());
        JsonNode question = questionBank.get(model.getQuestion());
        System.out.println(question);
        String correctAns = question.get("correct_answer").toString();
        ArrayList<String> answers = new ArrayList<String>(
                Arrays.asList(question.get("incorrect_answers").get(0).toString(),
                        question.get("incorrect_answers").get(1).toString(),
                        question.get("incorrect_answers").get(2).toString()));
        Random rand = new Random();
        int position = rand.nextInt(4);
        answers.add(position, correctAns);
        System.out.println(answers);
        view.changeQuestion(question.get("question").toString(), model.getPoints(), answers);
        questionActions(correctAns);
    }

    public static void main(String[] args) {
        // Assemble all the pieces of the MVC
        Screen m = new Screen();
        GameFrontEnd v = new GameFrontEnd();
        GameBackEnd c = new GameBackEnd(m, v);
        c.initController();
    }
}
