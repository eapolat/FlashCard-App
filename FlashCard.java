// import java.util.ArrayList;

public class FlashCard {

private String question;
private String answer;
private int difficulty;

public FlashCard(String question, String answer, int difficulty) {
    
    this.question = question;
    this.answer = answer;
    this.setDifficulty(difficulty);

}

public String getQuestion() {
    return question;
}

public String getAnswer() {
    return answer;
}

public int getDifficulty() {
    return difficulty;
}

public void setQuestion(String question) {
    this.question = question;
}

public void setAnswer(String answer) {
    this.answer = answer;
}

public void setDifficulty(int difficulty) {

    if ((0 < difficulty) && (difficulty < 6)) {

        this.difficulty = difficulty;

    }
    else {
        this.difficulty = 0;
    }

}

public void reverseCard() {

    String saved_answer = this.answer;

    this.answer = this.question;
    this.question = saved_answer;

}

@Override
public boolean equals(Object flashCard) {
    if (this == flashCard) {
        return true;
    }
    if (flashCard == null || getClass() != flashCard.getClass()) {
        return false;
    }
    FlashCard other = (FlashCard) flashCard;
    return this.answer.equals(other.answer) && this.question.equals(other.question);
}

public String toString() {

    return question + "-" + answer + ": " + difficulty;
    
}

public String showFlashCardQuestion() {

    int question_length = this.question.length();

    int space = (15 - question_length) / 2;

    String formattedQuestion = String.format(" ****************%n *              *%n *              *%n *              *%n *%s%s%s*%n *              *%n *              *%n *              *%n ****************%n", " ".repeat(space), this.question, " ".repeat(space));    
    return formattedQuestion;
}

public String showFlashCardAnswer() {

    int answer_length = this.answer.length();

    int space = (15 - answer_length) / 2;

    String formattedAnswer = String.format(" ****************%n *              *%n *              *%n *              *%n *%s%s%s*%n *              *%n *              *%n *              *%n ****************%n", " ".repeat(space), this.answer, " ".repeat(space));    
    return formattedAnswer;
}

}
