package com.idankorenisraeli.soccerbattle.attack;

import android.animation.Animator;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.idankorenisraeli.soccerbattle.R;

// Using YoYo implemented package for animating attack notification.
public class AttackMessageAnimator {
    private static final int MESSAGE_TIME = 1800; // in ms

    private static AttackMessageAnimator single_instance = null;

    public static AttackMessageAnimator getInstance(){
        if(single_instance == null)
            single_instance = new AttackMessageAnimator();
        return single_instance;
    }


    // Showing and animating message of attack
    public void showMessage(TextView attackMessage, String playerName, String attackName, int attackPoints){
        if(attackMessage!=null) {
            String messageText = generateAttackString(attackMessage.getContext(), playerName, attackName, attackPoints);

            attackMessage.setText(messageText); // Updating text of message
            OnAnimationStart startCallback = new OnAnimationStart(attackMessage);
            OnAnimationEnd endCallback = new OnAnimationEnd(attackMessage);
            YoYo.with(Techniques.Flash).duration(MESSAGE_TIME).
                    onStart(startCallback).onEnd(endCallback).
                    playOn(attackMessage);
            // Flash animation techniques makes the turn on and off of the text rapidly
        }
    }

    private String generateAttackString(Context context, String playerName, String attackName, int attackPoints){
        return context.getString(R.string.attack_message,playerName, attackName.toLowerCase(), attackPoints);
        // Creating each text that should be shown at runtime
    }



}



// region Animation Callbacks

class OnAnimationStart implements YoYo.AnimatorCallback{
    View viewToEnable;
    private static final int FADE_IN_TIME = 300; // in ms


    public OnAnimationStart(View view){
        this.viewToEnable = view;
    }

    @Override
    public void call(Animator animator) {
        // Adding zoom fade in to the animation
        viewToEnable.setEnabled(true);
        YoYo.with(Techniques.ZoomIn).duration(FADE_IN_TIME)
                .playOn(viewToEnable);
    }
}

class OnAnimationEnd implements YoYo.AnimatorCallback{
    View viewToDisable;
    private static final int FADE_OUT_TIME = 550; // in ms
    private static final int DELAY = 300; // in ms

    public OnAnimationEnd(View view){
        this.viewToDisable = view;
    }

    @Override
    public void call(Animator animator) {

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Fading out the text view, disable after finished fading
                YoYo.with(Techniques.FadeOut).duration(FADE_OUT_TIME).onEnd
                        (new YoYo.AnimatorCallback() {
                            @Override
                            public void call(Animator animator) {
                                viewToDisable.setEnabled(false);
                                viewToDisable.setAlpha(0f);
                            }
                        }).playOn(viewToDisable);
            }
        }, DELAY);

    }
}

// endregion