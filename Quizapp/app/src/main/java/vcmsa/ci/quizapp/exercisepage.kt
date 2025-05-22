package vcmsa.ci.quizapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.TextView
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.view.get


class exercisepage : AppCompatActivity() {

    var questions: TextView? = null
    var choices: RadioGroup? = null
    var results: TextView? = null
    var Submit : Button?= null
    var questionindex = 0

    // incrementation of scores each time for each question
    var score = 0

    // The question array will be declared
    var questionsofarray = arrayOf(
        "Cape Town is the second Capital city of South Africa",
        "Where is the show 'Friends' based in ?",
        "You have to login into your android studio to use your virtual machine.",
        "Cyril Ramaphosa owns half McDonalds in South Africa",
        "Apple is the largest company with the largest net in the world"
    )

    // options to be displayed in the arrays
    val options = arrayOf(
        arrayOf("True","False"), arrayOf("Cape Town","New York City","Johannesburg","Nigeria"),
        arrayOf("True","False"), arrayOf("True","False"), arrayOf("True","False")
    )

    //Answers to the questions in array
    val answers = arrayOf(0,1,1,0,1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_exercisepage)

        questions = findViewById<View>(R.id.questions) as TextView
        choices = findViewById<View>(R.id.quizoptions) as RadioGroup
        results = findViewById<View>(R.id.results) as TextView
        Submit = findViewById<View>(R.id.nextbttn) as Button
myQuestions()
        Submit!!.setOnClickListener {
            val selectedID = choices!!.checkedRadioButtonId


                val selectedRadioButton = findViewById<RadioButton>(selectedID)
                val selectedAnswer = choices!!.indexOfChild(selectedRadioButton)

                if (selectedAnswer == answers[questionindex]) {
                    score++
                   Toast.makeText(this,"Corret Answer", Toast.LENGTH_LONG).show() //ext = "Correct Answer"
                } else {
                    //results!!.text = "Incorrect answer,Guess again "
                    Toast.makeText(this,"Incorrect answer,Guess again ", Toast.LENGTH_LONG).show()
                }

            questionindex++
            if (questionindex < questionsofarray.size) {
                myQuestions()
            }
            else{
                myScore()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

     fun myQuestions() {
        questions!!.text = questionsofarray[questionindex]
        choices!!.removeAllViews()
        for (choice in options[questionindex]){
            val radioButton = RadioButton(this)
            radioButton.text = choice
            choices!!.addView(radioButton)
        }
    }

    fun myScore(){
        val intent = Intent(this,ReviewScore::class.java)
        intent.putExtra("Score",score)
        startActivity(intent)
        finish()
    }
}

