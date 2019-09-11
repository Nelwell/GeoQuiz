package android.bignerdranch.com;

// Model class within the model layer to store all questions for the GeoQuiz
public class Question {


    private int mTextResId;
    private boolean mAnswerTrue;

    // Getters/setters for to be used throughout QuizActivity
    public Question(int textResId, boolean answerTrue) {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }
}
