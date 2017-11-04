package com.example.nolan.polipal;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class Quiz extends AppCompatActivity {

    CheckBox checkBox;
    ArrayList<String> topics;
    ArrayList<String> picked;
    LinearLayout checkBoxContainer;
    RadioGroup howLongGroup;
    RadioButton howLongButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        RelativeLayout r = (RelativeLayout) findViewById(R.id.activity_quiz);
        r.setBackgroundColor(Color.parseColor("#303030"));

        topics = getTopics();

        checkBoxContainer = (LinearLayout) findViewById(R.id.topicLayout);
        howLongGroup = (RadioGroup) findViewById(R.id.howLongRadioGroup);

        Button joinB = (Button) findViewById(R.id.joinB);

        for(int i = 0; i < topics.size(); i++) {
            checkBox = new CheckBox(this);
            checkBox.setText(topics.get(i));
            checkBox.setTextColor(Color.parseColor("#7E7E7E"));
            checkBox.setId(i);
            checkBoxContainer.addView(checkBox);
        }

        joinB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                joinConversation();
            }
        });


    }

    public ArrayList<String> getPicked() {

        ArrayList<String> addingToPicked = new ArrayList<String>();

        for(int i = 0; i < topics.size(); i++) {
            Object child = (Object) checkBoxContainer.getChildAt(i);
            if(child instanceof CheckBox) {
                CheckBox checkBoxChild = (CheckBox) child;
                if(checkBoxChild.isChecked()) {
                    addingToPicked.add(checkBoxChild.getText().toString());
                }
            }
        }

        return(addingToPicked);
    }

    public void joinConversation() {
        picked = getPicked();
        int radioX = howLongGroup.getCheckedRadioButtonId();
        howLongButton = (RadioButton) findViewById(radioX);
        int howLong = Integer.parseInt(howLongButton.getText().toString());

        //pass in picked and howLong

        Intent toConversation = new Intent(Quiz.this, Conversation.class);
        Quiz.this.startActivity(toConversation);
    }

    public ArrayList<String> getTopics() {
        ArrayList<String> hobbies = new ArrayList<String>();
        hobbies.add("Taxes");
        hobbies.add("Health Care");
        hobbies.add("Immigration");
        hobbies.add("Abortion");
        return(hobbies);
    }


}
