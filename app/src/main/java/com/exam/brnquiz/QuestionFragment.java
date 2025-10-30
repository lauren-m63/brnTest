package com.exam.brnquiz;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class QuestionFragment extends Fragment {

    private TextView questionText;
    private ListView list;

    private ArrayList<Question> questionList;
    private int currentIndex = 0;
    private int correctCount = 0;

    public QuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize question list
        questionList = new ArrayList<>();

        ArrayList<String> answer1List = new ArrayList<>();
        answer1List.add("red");
        answer1List.add("blue");
        answer1List.add("orange");
        questionList.add(new Question("What is my favorite color?", answer1List, 1)); // blue

        ArrayList<String> answer2List = new ArrayList<>();
        answer2List.add("hi");
        answer2List.add("hello");
        answer2List.add("hey");
        questionList.add(new Question("What is my favorite greeting?", answer2List, 2)); // hey
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_question, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        questionText = view.findViewById(R.id.questionTextView);
        list = view.findViewById(R.id.listView);

        displayQuestion();
    }

    private void displayQuestion() {
        if (currentIndex >= questionList.size()) {
            questionText.setText("Quiz Finished! Score: " + correctCount + "/" + questionList.size());
            list.setAdapter(null);
            return;
        }

        Question current = questionList.get(currentIndex);
        questionText.setText(current.questionText);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_list_item_1,
                current.options
        );
        list.setAdapter(adapter);

        //GRADING
        list.setOnItemClickListener((parent, itemView, position, id) -> {
            if (position == current.correctIndex) {
                correctCount++;
                Toast.makeText(requireContext(), "Correct!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireContext(), "Wrong!", Toast.LENGTH_SHORT).show();
            }

            // Move to next question
            currentIndex++;
            displayQuestion();
        });
    }
}
