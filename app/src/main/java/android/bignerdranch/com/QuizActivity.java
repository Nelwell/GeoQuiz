package android.bignerdranch.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

// Controller layer, to manage event logic and connect with view/model layers
public class QuizActivity extends AppCompatActivity {

    // Sets TAG constant in variable
    private static final String TAG = "QuizActivity";
    // Key constant for re-creating the current view state during screen rotation
    private static final String KEY_INDEX = "index";

    // Member instance variables
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;

    // Question array object to hold all questions from quiz
    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };
    // Counter to reference question in array
    private int mCurrentIndex = 0;

    @Override // Ensures compiler checks that QuizActivity has the method to be overridden
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Debugging log statement to log messages
        Log.d(TAG, "onCreate(Bundle) called");
        // Inflates widgets and puts them on screen
        setContentView(R.layout.activity_quiz);

            // Tells onCreate to assign the 'saved' value of mCurrentIndex, otherwise set to 0
            // This will enable screen rotation WITHOUT losing the current view state
            if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        // References the string resource for each question and sets the TextView text to the
        // current question
        mQuestionTextView = findViewById(R.id.question_text_view);

        // Gets reference to each inflated widget and stores in variables
        mTrueButton = findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Calls method to check which answer what chosen
                checkAnswer(true);
            }
        });

        mFalseButton = findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Calls method to check which answer what chosen
                checkAnswer(false);
            }
        });

        mNextButton = findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        updateQuestion();
    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        // initialize variable to hold proper resource string ID
        int messageResId = 0;

        // Checks which answer is chosen to decide which Toast to display
        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }

        // Sets and displays Toast
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    // Logging methods for each lifecycle activity
    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    // Writes current mCurrentIndex value to the bundle object to maintain current view state during
    // screen rotation.
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    @Override
    // Will 'stash' current activity record in the OS when called to retrieve state even after app
    // is killed
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

}
