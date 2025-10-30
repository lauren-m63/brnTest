package com.exam.brnquiz;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScoreFragment extends Fragment {

    //holds two text views which have the score
    private QuizViewModel sharedViewModel;
    TextView score;

    public ScoreFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_score, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(QuizViewModel.class);

        sharedViewModel.getAnswer().observe(getViewLifecycleOwner(), observer);
        score = view.findViewById(R.id.score_number);

    }


    Observer<Integer> observer = new Observer<Integer>() {
        @Override
        public void onChanged(Integer question) {
            // i want to make an obsever os when the get answer is changed it updates the text
            MutableLiveData<Integer> m = sharedViewModel.getAnswer();
            //int mo = sharedViewModel.getAnswerInt();
            //score.setText(mo);
            // i cant figure out how to update the score when my varibale is mutable live data

        }
    }; // end obserever

} // last bracket


//Observer<MutableLiveData<Question>> observer = new <MutableLiveData<Question>>() {
//@Override
//public void onChanged() {
//    Question currentQuestion = sharedViewModel.getCurrent();
//    assert tickers != null;
//    adapter = new ArrayAdapter<>(requireContext(),
//            android.R.layout.simple_list_item_1,
//            tickers);
//    listView.setAdapter(adapter);
//    // when this is changed I want to update the score
//}
//    }; //end observer