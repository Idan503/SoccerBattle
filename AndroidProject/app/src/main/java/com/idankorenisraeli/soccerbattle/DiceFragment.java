package com.idankorenisraeli.soccerbattle;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.Objects;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DiceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiceFragment extends Fragment {

    private Button rollButton;
    private ImageView diceImage;
    private PlayerSide side;

    private DiceRolledListener rolledListener;

    // Roll animation properties
    private final static int CHANGE_COUNT = 10; // How many times dice will change before value shown determent
    private final static int CHANGE_TICK = 150; // (in ms) When dice changes, what is the delay before next change
    private final static int FADE_OUT_DURATION = 1500; // After both dice are rolled

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DiceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DiceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DiceFragment newInstance(String param1, String param2) {
        DiceFragment fragment = new DiceFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        System.out.println("Creating Dice");
        super.onCreateView(inflater,container,savedInstanceState);
        return inflater.inflate(R.layout.fragment_dice, container, false);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            rolledListener = (DiceRolledListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement DiceRolledListener");
        }
    }

    @Override
    public void onInflate(@NonNull Context context,@NonNull AttributeSet attrs, Bundle savedInstanceState) {
        super.onInflate(context, attrs, savedInstanceState);

        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.DiceFragment);

        int diceSideValue = a.getInt(R.styleable.DiceFragment_side, 2);
        this.side = PlayerSide.values()[diceSideValue];
        a.recycle();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        diceImage = view.findViewById(R.id.dice_image);
        rollButton = view.findViewById(R.id.roll_button);

        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roll();
                rollButton.setVisibility(View.GONE);
            }
        });
    }

    // Roll 'animation' is the dice changing its drawable 'changeCount' times, last drawable is the result
    private void roll(){
        final Handler handler = new Handler(Objects.requireNonNull(Looper.myLooper()));
        final Random rand = new Random();
        final int[] currentResult = new int[1];
        for(int changes = 0 ;changes<CHANGE_COUNT;changes++)
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // random number between 1 and 6
                    currentResult[0] = 1 + rand.nextInt(6);
                    setDiceImage(currentResult[0]);
                }
            }, changes*CHANGE_TICK);

        //
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                rolledListener.onDiceRolled(currentResult[0], side);
            }
        }, CHANGE_TICK*CHANGE_COUNT);
    }


    // Updating the drawable of the dice to match a certain value
    private void setDiceImage(int diceResult){
        if(diceResult < 1 || diceResult > 6)
            return;
        switch (diceResult) {
            case 1:
                CommonUtils.getInstance().setImageResource(diceImage, R.drawable.ic_dice_1);
                break;
            case 2:
                CommonUtils.getInstance().setImageResource(diceImage, R.drawable.ic_dice_2);
                break;
            case 3:
                CommonUtils.getInstance().setImageResource(diceImage, R.drawable.ic_dice_3);
                break;
            case 4:
                CommonUtils.getInstance().setImageResource(diceImage, R.drawable.ic_dice_4);
                break;
            case 5:
                CommonUtils.getInstance().setImageResource(diceImage, R.drawable.ic_dice_5);
                break;
            case 6:
                CommonUtils.getInstance().setImageResource(diceImage, R.drawable.ic_dice_6);
                break;
        }
    }

    public void setSide(PlayerSide side){
        this.side = side;
    }

    public void fadeOut(){
        YoYo.with(Techniques.FadeOut).duration(FADE_OUT_DURATION)
                .onEnd(new YoYo.AnimatorCallback() {
                    @Override
                    public void call(Animator animator) {
                        diceImage.setVisibility(View.GONE);
                        rollButton.setVisibility(View.GONE);
                    }
                }).playOn(diceImage);
    }
}