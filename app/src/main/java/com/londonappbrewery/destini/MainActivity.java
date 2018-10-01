package com.londonappbrewery.destini;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextView mStoryTextView;
    private Button mButtonTop;
    private Button mButtonBottom;
    private int mStoryIndex = 1;
    private MediaPlayer mMediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mMediaPlayer = new MediaPlayer();
        mMediaPlayer = MediaPlayer.create(this, R.raw.darkshadow);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();



        mStoryTextView = (TextView) findViewById(R.id.storyTextView);
        mButtonTop = (Button) findViewById(R.id.buttonTop);
        mButtonBottom = (Button) findViewById(R.id.buttonBottom);


        mButtonTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mStoryIndex==1) {
                    mStoryTextView.setText(R.string.T3_Story);
                    mButtonTop.setText(R.string.T3_Ans1);
                    mButtonBottom.setText(R.string.T3_Ans2);
                    mStoryIndex=3;
                }
                else if(mStoryIndex==2){
                    mStoryTextView.setText(R.string.T3_Story);
                    mButtonTop.setText(R.string.T3_Ans1);
                    mButtonBottom.setText(R.string.T3_Ans2);
                    mStoryIndex=3;
                }
                else if(mStoryIndex==3){
                    mStoryTextView.setText(R.string.T6_End);
                    mButtonBottom.setText("Exit");
                    mButtonTop.setText("Restart!");
                    mStoryIndex = 6;
                    //alertMessage();
                    mButtonTop.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i=new Intent(getApplicationContext(), MainActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                            mMediaPlayer.reset();
                        }
                    });
                    mButtonBottom.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mMediaPlayer.stop();
                            finish();
                        }
                    });
                }
            }
        });

        mButtonBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mStoryIndex==1) {
                    mStoryTextView.setText(R.string.T2_Story);
                    mButtonTop.setText(R.string.T2_Ans1);
                    mButtonBottom.setText(R.string.T2_Ans2);
                    mStoryIndex=2;
                }
                else if(mStoryIndex==2){
                    mStoryTextView.setText(R.string.T4_End);
                    mButtonBottom.setVisibility(View.GONE);
                    mButtonTop.setVisibility(View.GONE);
                    mStoryIndex=4;
                    alertMessage();
                }
                else if (mStoryIndex==3){
                    mStoryTextView.setText(R.string.T5_End);
                    mButtonBottom.setVisibility(View.GONE);
                    mButtonTop.setVisibility(View.GONE);
                    mStoryIndex=5;
                    alertMessage();

                }

            }
        });



    }
    private void alertMessage(){
        if (mStoryIndex == 4 || mStoryIndex == 5 || mStoryIndex == 6) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Game Over");
            alert.setCancelable(false);
            alert.setMessage("Restart with different decisions ?");
            alert.setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent i=new Intent(getApplicationContext(), MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                    mMediaPlayer.reset();

                }
            });
            alert.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                    mMediaPlayer.stop();
                }
            });
            alert.show();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        mMediaPlayer.pause();

    }

    @Override
    protected void onRestart(){
        super.onRestart();
        mMediaPlayer.start()
        ;
    }
}
