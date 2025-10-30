package com.exam.brnquiz;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;

public class QuestionFragment extends Fragment {

    private TextView questionText;
    private ListView list;

    private ArrayList<Question> questionList;
    private int currentIndex = 0;
    private int correctCount = 0;
    private QuizViewModel sharedViewModel;

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
        answer2List.add("Medellin");
        answer2List.add("Madrid");
        answer2List.add("Paris");
        answer2List.add("Bogota");
        questionList.add(new Question("What is the capital of Colombia?", answer2List, 3)); // hey
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_question, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(QuizViewModel.class);

        sharedViewModel.getCurrent().observe(getViewLifecycleOwner(), observer);


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

        sharedViewModel.setAnswer(correctCount);
        sharedViewModel.addInt(correctCount);
    }


    Observer<Question> observer = new Observer<Question>() {
        @Override
        public void onChanged(Question question) {
            sharedViewModel.setCurrent(question); //updates current

        }
    }; // end obserever

} // last bracket
