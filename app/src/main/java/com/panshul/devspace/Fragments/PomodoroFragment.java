package com.panshul.devspace.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.panshul.devspace.R;

public class PomodoroFragment extends Fragment {

    View view;
    ImageView cancel,profile;
    TextView timer,state;
    CountDownTimer countDownTimer;
    private int quiztime=1500000;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_pomodoro, container, false);

        cancel = view.findViewById(R.id.clockCancelImageview);
        timer = view.findViewById(R.id.timerTextView);
        state = view.findViewById(R.id.timerTextView2);
        startTimer();

        return view;
    }
    public void startTimer(){
        countDownTimer=new CountDownTimer(quiztime,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateTextView(millisUntilFinished/1000);
                state.setText("Task Time");
            }

            @Override
            public void onFinish() {
                startTimerBreak();
                state.setText("Break Time");
            }
        }.start();
    }
    public void startTimerBreak(){
        countDownTimer=new CountDownTimer(300000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateTextView(millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }
    void updateTextView(long secondsLeft){
        int min = (int) (secondsLeft/60);
        int seconds = (int) (secondsLeft-(min*60));
        String secondString;
        secondString = Integer.toString(seconds);
        if(seconds<=9){
            secondString="0"+secondString;
        }
        if(min>=1) {
            timer.setText(Integer.toString(min) + ":" + secondString);
        }
        else{
            timer.setText(secondString);
        }
    }
}