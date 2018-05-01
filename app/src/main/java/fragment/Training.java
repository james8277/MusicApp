package fragment;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.Toast;

import com.example.james.musicapp.DataBase;
import com.example.james.musicapp.R;

import java.util.Timer;
import java.util.TimerTask;


public class Training extends android.app.Fragment {

    private static final String TAG = "FragmentTraining";
    Button button_practice;
    Button button_back;
    Button button_example;
    private Button button_1;
    private Button button_2;
    private Button button_3;
    private Button button_4;

    Button button_test;

    private int drum_track[][];
    private int count;

    private SoundPool soundPool_training_1;
//    private SoundPool soundPool_training_2;
    private int soundID_test;
    private int soundID_1;
    private int soundID_2;
    private int soundID_3;
    private int soundID_4;
    private int soundID_5;

    private int count_down;

    private Handler handler_example;
    private Handler handler_countdown;
    private Handler handler_test;

    private Runnable runnable_test;
    private Runnable runnable_countdown;

    private MediaPlayer mediaPlayer_example;
    private MediaPlayer mediaPlayer_track;

    private Timer timer_training;
    private Timer timer_countdown;
    private Timer timer_example;
    private TimerTask timerTask_training;
    private TimerTask timerTask_countdown;
    private TimerTask timerTask_example;

    private Toast toast_countdown;
    View view_training;

    ConstraintLayout constraintLayout;

    int trackEasySize;
    int trackEasyData[];
    int trackEasyTime[];

    boolean previousClick = false;
    boolean currentClick = false;

    int difficulty = 1;


    int streamPlay;
    private boolean isPlay = false;

    private DataBase dataBase_training;

    private long practiceTime;
    private long currentTime;

    private TimerTask newTimerTask() {

         return new TimerTask() {
            @Override
            public void run() {
                if(count >= 0)
                {
                    if(drum_track[count%4][0] == 1)
                    {

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                button_1.performClick();
                                handler_test.postDelayed(new Runnable() {

                                    @Override
                                    public void run() {
//                                Log.d(TAG, "run: press = false");
                                        button_1.setPressed(false);
                                    }
                                }, 100);
                            }
                        });
                    }
                    if(drum_track[count%4][1] == 1)
                    {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                button_2.performClick();
                                handler_test.postDelayed(new Runnable() {

                                    @Override
                                    public void run() {
//                                Log.d(TAG, "run: press = false");
                                        button_2.setPressed(false);
                                    }
                                }, 100);
                            }
                        });
                    }
                    if(drum_track[count%4][2] == 1)
                    {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                button_3.performClick();
                                handler_test.postDelayed(new Runnable() {

                                    @Override
                                    public void run() {
//                                Log.d(TAG, "run: press = false");
                                        button_3.setPressed(false);
                                    }
                                }, 100);
                            }
                        });
                    }
                    if(drum_track[count%4][3] == 1)
                    {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                button_4.performClick();
                                handler_test.postDelayed(new Runnable() {

                                    @Override
                                    public void run() {
//                                Log.d(TAG, "run: press = false");
                                        button_4.setPressed(false);
                                    }
                                }, 100);
                            }
                        });
                    }
                }
                else
                {
                    soundPool_training_1.play(soundID_5,(float)0.1,(float)0.1,1,0,1);
                }
                count++;
                Log.e(TAG, "\t" + System.currentTimeMillis());
            }
        };
    }
    private Runnable newRunnable() {

        return new Runnable() {
            @Override
            public void run() {
                if(drum_track[count%4][0] == 1)
                {
                    button_1.performClick();
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
//                                Log.d(TAG, "run: press = false");
                            button_1.setPressed(false);
                        }
                    }, 100);
                }
                if(drum_track[count%4][1] == 1)
                {
                    button_2.performClick();
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
//                                Log.d(TAG, "run: press = false");
                            button_2.setPressed(false);
                        }
                    }, 100);
                }
                if(drum_track[count%4][2] == 1)
                {
                    button_3.performClick();
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
//                                Log.d(TAG, "run: press = false");
                            button_3.setPressed(false);
                        }
                    }, 100);
                }
                if(drum_track[count%4][3] == 1)
                {
                    button_4.performClick();
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
//                                Log.d(TAG, "run: press = false");
                            button_4.setPressed(false);
                        }
                    }, 100);
                }
                count++;
//                previousTime = currentTime;
                handler_example.postDelayed(this,920*60/120);
            }
        };
    }
    private TimerTask newTimerCountDown() {
        return new TimerTask() {
            @Override
            public void run() {
                Log.d(TAG, "run: Timer Countdown");
                soundPool_training_1.play(soundID_5,(float)0.1,(float)0.1,1,0,1);
                count_down--;
                Log.e(TAG, "run: Time\t" + System.currentTimeMillis() );
                if(count_down < 0)
                {
                    timerTask_countdown.cancel();
                }
            }
        };

    }
    private int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }
    private void playTrackEasyExample()
    {
        for (int i = 0; i < trackEasySize; i++) {
            if(trackEasyData[i] == 0)
            {
                timerTask_example = new TimerTask() {
                    @Override
                    public void run() {
                        soundPool_training_1.play(soundID_5,(float)0.1,(float)0.1,1,0,1);
                        soundPool_training_1.stop(soundID_5);

                    }
                };
            }
            if (trackEasyData[i] == 1) {
                timerTask_example = new TimerTask() {
                    @Override
                    public void run() {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                button_1.performClick();
                                handler_example.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        button_1.setPressed(false);
                                    }
                                }, 100);
                            }
                        });
                    }
                };
            }
            if (trackEasyData[i] == 2) {
                timerTask_example = new TimerTask() {
                    @Override
                    public void run() {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                button_2.performClick();
                                handler_example.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        button_2.setPressed(false);
                                    }
                                }, 100);
                            }
                        });
                    }
                };
            }
            if (trackEasyData[i] == 3) {
                timerTask_example = new TimerTask() {
                    @Override
                    public void run() {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                button_3.performClick();
                                handler_example.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        button_3.setPressed(false);
                                    }
                                }, 100);
                            }
                        });
                    }
                };
            }
            if (trackEasyData[i] == 4) {
                timerTask_example = new TimerTask() {
                    @Override
                    public void run() {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                button_4.performClick();
                                handler_example.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        button_4.setPressed(false);
                                    }
                                }, 100);
                            }
                        });
                    }
                };
            }
//                        Log.e(TAG, "time\t" + temp_data_time[i]);
            timer_example.schedule(timerTask_example, trackEasyTime[i]);

        }
    }
    private void playTrackEasyTraining()
    {
        for(int i=0;i<trackEasySize;i++)
        {
            if (trackEasyData[i] == 1)
            {
                timerTask_training = new TimerTask() {
                    @Override
                    public void run() {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Button button_test_1 = new Button(getActivity());
                                button_test_1.setBackground(getResources().getDrawable(R.drawable.round_circle));
                                Animation animation = AnimationUtils.loadAnimation(getActivity(),R.anim.translatex);
                                button_test_1.setAnimation(animation);

                                ConstraintLayout.LayoutParams layoutParams_1 = new ConstraintLayout.LayoutParams(dpToPx(106),dpToPx(106));
                                layoutParams_1.leftToLeft = R.id.Button_1_training;
                                layoutParams_1.rightToRight = R.id.Button_1_training;
                                layoutParams_1.topToTop = R.id.Button_1_training;
                                layoutParams_1.bottomToBottom = R.id.Button_1_training;

                                constraintLayout.addView(button_test_1,-1,layoutParams_1);
                                button_1.bringToFront();
                            }
                        });
                    }
                };
            }
            if (trackEasyData[i] == 2)
            {
                timerTask_training = new TimerTask() {
                    @Override
                    public void run() {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Button button_test_2 = new Button(getActivity());
                                button_test_2.setBackground(getResources().getDrawable(R.drawable.round_circle));
                                Animation animation = AnimationUtils.loadAnimation(getActivity(),R.anim.translatex);
                                button_test_2.setAnimation(animation);

                                ConstraintLayout.LayoutParams layoutParams_2 = new ConstraintLayout.LayoutParams(dpToPx(106),dpToPx(106));
                                layoutParams_2.leftToLeft = R.id.Button_2_training;
                                layoutParams_2.rightToRight = R.id.Button_2_training;
                                layoutParams_2.topToTop = R.id.Button_2_training;
                                layoutParams_2.bottomToBottom = R.id.Button_2_training;
                                constraintLayout.addView(button_test_2,-1,layoutParams_2);
                                button_2.bringToFront();
                            }
                        });
                    }
                };
            }
            if (trackEasyData[i] == 3)
            {
                timerTask_training = new TimerTask() {
                    @Override
                    public void run() {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Button button_test_3 = new Button(getActivity());
                                button_test_3.setBackground(getResources().getDrawable(R.drawable.round_circle));
                                Animation animation = AnimationUtils.loadAnimation(getActivity(),R.anim.translatex);
                                button_test_3.setAnimation(animation);

                                ConstraintLayout.LayoutParams layoutParams_3 = new ConstraintLayout.LayoutParams(dpToPx(106),dpToPx(106));
                                layoutParams_3.leftToLeft = R.id.Button_3_training;
                                layoutParams_3.rightToRight = R.id.Button_3_training;
                                layoutParams_3.topToTop = R.id.Button_3_training;
                                layoutParams_3.bottomToBottom = R.id.Button_3_training;
                                constraintLayout.addView(button_test_3,-1,layoutParams_3);
                                button_3.bringToFront();
                            }
                        });
                    }
                };
            }
            if (trackEasyData[i] == 4)
            {
                timerTask_training = new TimerTask() {
                    @Override
                    public void run() {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Button button_test_4 = new Button(getActivity());
                                button_test_4.setBackground(getResources().getDrawable(R.drawable.round_circle));
                                Animation animation = AnimationUtils.loadAnimation(getActivity(),R.anim.translatex);
                                button_test_4.setAnimation(animation);

                                ConstraintLayout.LayoutParams layoutParams_4 = new ConstraintLayout.LayoutParams(dpToPx(106),dpToPx(106));
                                layoutParams_4.leftToLeft = R.id.Button_4_training;
                                layoutParams_4.rightToRight = R.id.Button_4_training;
                                layoutParams_4.topToTop = R.id.Button_4_training;
                                layoutParams_4.bottomToBottom = R.id.Button_4_training;
                                constraintLayout.addView(button_test_4,-1,layoutParams_4);
                                button_4.bringToFront();
                            }
                        });
                    }
                };
            }
//                        Log.e(TAG, "time\t" + temp_data_time[i]);
            if(i >= 4)
            {
                timer_training.schedule(timerTask_training, trackEasyTime[i]-3000);
            }
        }
    }
    private void setButtonClickable(boolean input)
    {
        button_1.setClickable(input);
        button_2.setClickable(input);
        button_3.setClickable(input);
        button_4.setClickable(input);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handler_example = new Handler();
        handler_countdown = new Handler();
        handler_test = new Handler();

        mediaPlayer_example = MediaPlayer.create(getActivity(),R.raw.example_easy_space);
        mediaPlayer_track = MediaPlayer.create(getActivity(),R.raw.example_easy_space);

        soundPool_training_1 = new SoundPool.Builder().setMaxStreams(32).build();
        soundID_test = soundPool_training_1.load(getActivity(),R.raw.test,1);
        soundID_1 = soundPool_training_1.load(getActivity(),R.raw.hihat_1,1);
        soundID_2 = soundPool_training_1.load(getActivity(),R.raw.drum_2,1);

//        soundPool_training_2 = new SoundPool.Builder().setMaxStreams(32).build();
        soundID_3 = soundPool_training_1.load(getActivity(),R.raw.drum_1,1);
        soundID_4 = soundPool_training_1.load(getActivity(),R.raw.bass_drum,1);
        soundID_5 = soundPool_training_1.load(getActivity(),R.raw.metronome,1);

        dataBase_training = new DataBase(getActivity(),"Database Record",null, 1);

        count = -8;

        DataBase dataBase_training = new DataBase(getActivity(),"Database Record",null, 1);



        trackEasySize = dataBase_training.getSongData(0).length;
        trackEasyData = dataBase_training.getSongData(0);
        trackEasyTime = dataBase_training.getSongTime(0);



        drum_track = new int[4][5];

        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4;j++)
            {
                drum_track[i][j] = 0;
            }
            drum_track[i][4] = 240;
        }
        drum_track[0][0] = 1;
        drum_track[1][0] = 1;
        drum_track[2][0] = 1;
        drum_track[3][0] = 1;

        drum_track[0][3] = 1;
        drum_track[2][2] = 1;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view_training = inflater.inflate(R.layout.fragment_training, container, false);

        button_1 = (Button)view_training.findViewById(R.id.Button_1_training);
        button_2 = (Button)view_training.findViewById(R.id.Button_2_training);
        button_3 = (Button)view_training.findViewById(R.id.Button_3_training);
        button_4 = (Button)view_training.findViewById(R.id.Button_4_training);

        button_example = (Button)view_training.findViewById(R.id.Button_example);
        button_back = (Button)view_training.findViewById(R.id.Button_back__training);
        button_practice = (Button)view_training.findViewById(R.id.Button_practice);



        return view_training;
    }


    @Override
    public void onStart() {



        super.onStart();
    }


    @Override
    public void onResume() {

        count_down = 3;
//        runnable_countdown = new Runnable() {
//            @Override
//            public void run() {
//                if(count_down >= 0)
//                {
//                    Toast.makeText(getActivity(),Integer.toString(count_down),Toast.LENGTH_SHORT).show();
//                    count_down--;
//                }
//                handler_countdown.postDelayed(this,500);
//            }
//        };

        mediaPlayer_track.setVolume(1,1);

        timerTask_countdown = newTimerCountDown();
        button_practice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: play");
                if(!mediaPlayer_track.isPlaying())
                {
                    practiceTime = System.currentTimeMillis();

                    Log.d(TAG, "onClick: start");
                    button_practice.setText(R.string.stop);
                    mediaPlayer_track.start();
//                    handler_countdown.post(runnable_countdown);
                    timer_countdown = new Timer();
                    timer_countdown.schedule(timerTask_countdown,0,1000*60/80);

//                    if(count_down < 0)
//                    {
//                        handler_countdown.removeCallbacks(runnable_countdown);
//                        Log.d(TAG, "countdown < 0");
//                        timer_countdown.cancel();
//                        timer_countdown.purge();
//
//                        count_down = 7;

                    mediaPlayer_track.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            Toast.makeText(getActivity(), "Practice end",Toast.LENGTH_SHORT).show();
                            mp.stop();
                            mp.prepareAsync();
                            button_practice.setText(R.string.button_practice);
                            count_down = 3;
                            timer_countdown.cancel();
                            timer_countdown.purge();
                            timerTask_countdown = newTimerCountDown();

                            setButtonClickable(true);
                            timer_training.cancel();
                            timer_training.purge();
                        }
                    });

                    setButtonClickable(false);

                    timer_training = new Timer();
                    constraintLayout = (ConstraintLayout)view_training.findViewById(R.id.constrainLayout_training);
//                    TimerTask timerTask_test[] = new TimerTask[tempSize];

                    if(difficulty == 1)
                    {
                        playTrackEasyTraining();
                    }

                }
                else
                {
                    Log.d(TAG, "onClick: stop");
                    count_down = 3;
                    Toast.makeText(getActivity(), "Practice end",Toast.LENGTH_SHORT).show();
//                    handler_countdown.removeCallbacks(runnable_countdown);


                    setButtonClickable(true);

                    timer_countdown.cancel();
                    timer_countdown.purge();
                    timerTask_countdown = newTimerCountDown();

                    timer_training.cancel();
                    timer_training.purge();

                    mediaPlayer_track.stop();
                    mediaPlayer_track.prepareAsync();
                    button_practice.setText(R.string.button_practice);
                }
            }
        });

        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool_training_1.play(soundID_1,(float)0.5,(float)0.5,1,0,1);
                button_1.setPressed(true);
                soundPool_training_1.stop(soundID_1);
            }
        });
        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool_training_1.play(soundID_2,1,1,1,0,1);
                button_2.setPressed(true);
                soundPool_training_1.stop(soundID_2);
            }
        });
        button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool_training_1.play(soundID_3,1,1,1,0,1);
                button_3.setPressed(true);
                soundPool_training_1.stop(soundID_3);
            }
        });
        button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool_training_1.play(soundID_4,1,1,1,0,1);
                button_4.setPressed(true);
                soundPool_training_1.stop(soundID_4);
            }
        });



        int maxVolume = 50;
        int currentVolume = 50;
        float log_1 = (float)(Math.log(maxVolume-currentVolume)/Math.log(maxVolume));
        mediaPlayer_example.setVolume(1-log_1,1-log_1);

//        runnable_test = newRunnable();


        button_example.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "onClick: example");
                button_example.setText(R.string.stop);
                if(!mediaPlayer_example.isPlaying())
                {
                    Log.d(TAG, "onClick: play");

                    mediaPlayer_example.start();
//                    previousTime = System.currentTimeMillis();
//                    handler_example.post(runnable_test);
                    mediaPlayer_example.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            Log.d(TAG, "onCompletion: track");
                            Toast.makeText(getActivity(), "Example end",Toast.LENGTH_SHORT).show();
                            mp.stop();
                            mp.prepareAsync();
//                            handler_example.removeCallbacks(runnable_test);
                            button_example.setText(R.string.button_example);
//                            timer_training.cancel();
//                            timer_training.purge();
                            timer_example.cancel();
                            timer_example.purge();
//                            count = -8;
                        }
                    });
                    timer_example = new Timer();

                    if(difficulty == 1)
                    {
                        playTrackEasyExample();
                    }
//                    timer_training = new Timer();
//                    timerTask_training = newTimerTask();
//                    timer_training.schedule(timerTask_training,0,1000*68/120);
                }
                else
                {
                    Log.d(TAG, "onClick: stop");
//                    count = -8;
                    mediaPlayer_example.stop();
                    mediaPlayer_example.prepareAsync();
                    handler_example.removeCallbacks(runnable_test);
                    button_example.setText(R.string.button_example);

                    timer_example.cancel();
                    timer_example.purge();
//                    timer_training.cancel();
//                    timer_training.purge();
                }

            }
        });

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction mf = getFragmentManager().beginTransaction();
                Fragment fragment_main = new MainFragment();
                mf.replace(R.id.container_main,fragment_main);
                mf.commit();
            }
        });

        super.onResume();
    }

    @Override
    public void onPause() {

        soundPool_training_1.release();
//        soundPool_training_2.release();

        if(mediaPlayer_example.isPlaying())
        {
            mediaPlayer_example.stop();
            mediaPlayer_example.prepareAsync();
        }
//        handler_countdown.removeCallbacks(runnable_countdown);



        if(mediaPlayer_track.isPlaying())
        {
            mediaPlayer_track.stop();
            mediaPlayer_track.prepareAsync();
        }

        if(timer_training != null)
        {
            timer_training.cancel();
            timer_training.purge();
        }

//        handler_example.removeCallbacks(runnable_test);

        super.onPause();
    }

    @Override
    public void onStop() {

        super.onStop();
    }
}

