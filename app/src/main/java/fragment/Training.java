package fragment;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AlertDialog;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class Training extends android.app.Fragment {

    private static final String TAG = "FragmentTraining";
    private Button button_practice;
    private Button button_back;
    private Button button_example;
    private Button button_set_difficulty;
    private Button button_1;
    private Button button_2;
    private Button button_3;
    private Button button_4;

    Button button_test_1[];
    Button button_test_2[];
    Button button_test_3[];
    Button button_test_4[];

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
    private Timer timer_click;
    private TimerTask timerTask_training;
    private TimerTask timerTask_countdown;
    private TimerTask timerTask_example;

    private Toast toast_countdown;
    View view_training;

    ConstraintLayout constraintLayout;

    int trackEasySize;
    int trackEasyData[];
    int trackEasyTime[];
    int trackNormalSize;
    int trackNormalData[];
    int trackNormalTime[];
    int trackSunnyDaySize;
    int trackSunnyDayData[];
    int trackSunnyDayTime[];

    int trackCount;
    int trackSetCount;

    int buttonCount_1;
    int buttonCount_2;
    int buttonCount_3;
    int buttonCount_4;

    boolean previousClick = false;
    boolean currentClick = false;

    int clickTiming_1 = 0;
    int clickTiming_2 = 0;
    int clickTiming_3 = 0;
    int clickTiming_4 = 0;

    int difficulty = 0;



    int streamPlay;
    private boolean isPlay = false;

    private DataBase dataBase_training;

    private long practiceTime;
    private long currentTime_1;
    private long currentTime_2;
    private long currentTime_3;
    private long currentTime_4;


    private void resetButtonTiming() {
        clickTiming_1 = 0;
        clickTiming_2 = 0;
        clickTiming_3 = 0;
        clickTiming_4 = 0;
    }
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
//                Log.d(TAG, "run: Timer Countdown");
                soundPool_training_1.play(soundID_5,(float)0.1,(float)0.1,1,0,1);
                count_down--;
//                Log.e(TAG, "run: Time\t" + System.currentTimeMillis() );
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
    private void playTrackExample(int difficulty) {
        if(difficulty == 0)
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
                if(i < 4)
                {
                    timer_example.schedule(timerTask_example, trackEasyTime[i]);
                }
                else
                {
                    timer_example.schedule(timerTask_example, trackEasyTime[i]-20);
                }
            }
        }
        else if(difficulty == 1)
        {
            for (int i = 0; i < trackNormalSize; i++) {
                if(trackNormalData[i] == 0)
                {
                    timerTask_example = new TimerTask() {
                        @Override
                        public void run() {
                            soundPool_training_1.play(soundID_5,(float)0.1,(float)0.1,1,0,1);
                            soundPool_training_1.stop(soundID_5);

                        }
                    };
                }
                if (trackNormalData[i] == 1) {
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
                if (trackNormalData[i] == 2) {
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
                if (trackNormalData[i] == 3) {
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
                if (trackNormalData[i] == 4) {
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
                if(i < 4)
                {
                    timer_example.schedule(timerTask_example, trackNormalTime[i]);
                }
                else
                {
                    timer_example.schedule(timerTask_example, trackNormalTime[i]-20);
                }

            }
        }
        else if(difficulty == 2)
        {
            for (int i = 0; i < trackSunnyDaySize; i++) {
                if(trackSunnyDayData[i] == 0)
                {
                    timerTask_example = new TimerTask() {
                        @Override
                        public void run() {
                            soundPool_training_1.play(soundID_5,(float)0.1,(float)0.1,1,0,1);
                            soundPool_training_1.stop(soundID_5);

                        }
                    };
                }
                if (trackSunnyDayData[i] == 1) {
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
                if (trackSunnyDayData[i] == 2) {
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
                if (trackSunnyDayData[i] == 3) {
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
                if (trackSunnyDayData[i] == 4) {
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
                if(i < 4)
                {
                    timer_example.schedule(timerTask_example, trackSunnyDayTime[i]);
                }
                else
                {
                    timer_example.schedule(timerTask_example, trackSunnyDayTime[i]-20);
                }

            }
        }
    }
    private void playTrackTraining() {
        buttonCount_1 = 0;
        buttonCount_2 = 0;
        buttonCount_3 = 0;
        buttonCount_4 = 0;
        if(difficulty == 0)
        {
            button_test_1 = new Button[trackEasySize+1];
            button_test_2 = new Button[trackEasySize+1];
            button_test_3 = new Button[trackEasySize+1];
            button_test_4 = new Button[trackEasySize+1];
            for(trackCount=0;trackCount<trackEasySize;trackCount++)
            {
                if (trackEasyData[trackCount] == 1)
                {
                    timerTask_training = new TimerTask() {
                        @Override
                        public void run() {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    button_test_1[buttonCount_1] = new Button(getActivity());
                                    button_test_1[buttonCount_1].setBackground(getResources().getDrawable(R.drawable.round_circle));
                                    Animation animation = AnimationUtils.loadAnimation(getActivity(),R.anim.translatex);
                                    animation.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            constraintLayout.removeView(button_test_1[buttonCount_1]);
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });
                                    button_test_1[buttonCount_1].setAnimation(animation);

                                    ConstraintLayout.LayoutParams layoutParams_1 = new ConstraintLayout.LayoutParams(dpToPx(106),dpToPx(106));
                                    layoutParams_1.leftToLeft = R.id.Button_1_training;
                                    layoutParams_1.rightToRight = R.id.Button_1_training;
                                    layoutParams_1.topToTop = R.id.Button_1_training;
                                    layoutParams_1.bottomToBottom = R.id.Button_1_training;

                                    constraintLayout.addView(button_test_1[buttonCount_1],-1,layoutParams_1);
                                    button_1.bringToFront();
                                    buttonCount_1++;
                                }
                            });
                        }
                    };
                }
                if (trackEasyData[trackCount] == 2)
                {
                    timerTask_training = new TimerTask() {
                        @Override
                        public void run() {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    button_test_2[buttonCount_2] = new Button(getActivity());
                                    button_test_2[buttonCount_2].setBackground(getResources().getDrawable(R.drawable.round_circle));
                                    Animation animation = AnimationUtils.loadAnimation(getActivity(),R.anim.translatex);
                                    animation.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            constraintLayout.removeView(button_test_2[buttonCount_2]);
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });
                                    button_test_2[buttonCount_2].setAnimation(animation);

                                    ConstraintLayout.LayoutParams layoutParams_2 = new ConstraintLayout.LayoutParams(dpToPx(106),dpToPx(106));
                                    layoutParams_2.leftToLeft = R.id.Button_2_training;
                                    layoutParams_2.rightToRight = R.id.Button_2_training;
                                    layoutParams_2.topToTop = R.id.Button_2_training;
                                    layoutParams_2.bottomToBottom = R.id.Button_2_training;
                                    constraintLayout.addView(button_test_2[buttonCount_2],-1,layoutParams_2);
                                    button_2.bringToFront();
                                    buttonCount_2++;
                                }
                            });
                        }
                    };
                }
                if (trackEasyData[trackCount] == 3)
                {
                    timerTask_training = new TimerTask() {
                        @Override
                        public void run() {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    button_test_3[buttonCount_3] = new Button(getActivity());
                                    button_test_3[buttonCount_3].setBackground(getResources().getDrawable(R.drawable.round_circle));
                                    Animation animation = AnimationUtils.loadAnimation(getActivity(),R.anim.translatex);
                                    animation.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            constraintLayout.removeView(button_test_3[buttonCount_3]);
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });
                                    button_test_3[buttonCount_3].setAnimation(animation);

                                    ConstraintLayout.LayoutParams layoutParams_3 = new ConstraintLayout.LayoutParams(dpToPx(106),dpToPx(106));
                                    layoutParams_3.leftToLeft = R.id.Button_3_training;
                                    layoutParams_3.rightToRight = R.id.Button_3_training;
                                    layoutParams_3.topToTop = R.id.Button_3_training;
                                    layoutParams_3.bottomToBottom = R.id.Button_3_training;
                                    constraintLayout.addView(button_test_3[buttonCount_3],-1,layoutParams_3);
                                    button_3.bringToFront();
                                    buttonCount_3++;
                                }
                            });
                        }
                    };
                }
                if (trackEasyData[trackCount] == 4)
                {
                    timerTask_training = new TimerTask() {
                        @Override
                        public void run() {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    button_test_4[buttonCount_4] = new Button(getActivity());
                                    button_test_4[buttonCount_4].setBackground(getResources().getDrawable(R.drawable.round_circle));
                                    Animation animation = AnimationUtils.loadAnimation(getActivity(),R.anim.translatex);
                                    animation.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            constraintLayout.removeView(button_test_4[buttonCount_4]);
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });
                                    button_test_4[buttonCount_4].setAnimation(animation);

                                    ConstraintLayout.LayoutParams layoutParams_4 = new ConstraintLayout.LayoutParams(dpToPx(106),dpToPx(106));
                                    layoutParams_4.leftToLeft = R.id.Button_4_training;
                                    layoutParams_4.rightToRight = R.id.Button_4_training;
                                    layoutParams_4.topToTop = R.id.Button_4_training;
                                    layoutParams_4.bottomToBottom = R.id.Button_4_training;
                                    constraintLayout.addView(button_test_4[buttonCount_4],-1,layoutParams_4);
                                    button_4.bringToFront();
                                    buttonCount_4++;
                                }
                            });
                        }
                    };
                }
//            Log.e(TAG, "time\t" + trackEasyTime[trackCount]);
                if(trackCount >= 4)
                {
                    timer_training.schedule(timerTask_training, trackEasyTime[trackCount]-3000);
                }
            }
        }
        else if(difficulty == 1)
        {
            button_test_1 = new Button[trackNormalSize];
            button_test_2 = new Button[trackNormalSize];
            button_test_3 = new Button[trackNormalSize];
            button_test_4 = new Button[trackNormalSize];
            for(trackCount=0;trackCount<trackNormalSize;trackCount++)
            {
                if (trackNormalData[trackCount] == 1)
                {
                    timerTask_training = new TimerTask() {
                        @Override
                        public void run() {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    button_test_1[buttonCount_1] = new Button(getActivity());
                                    button_test_1[buttonCount_1].setBackground(getResources().getDrawable(R.drawable.round_circle));
                                    Animation animation = AnimationUtils.loadAnimation(getActivity(),R.anim.translatex_normal);
                                    animation.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            constraintLayout.removeView(button_test_1[buttonCount_1]);
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });
                                    button_test_1[buttonCount_1].setAnimation(animation);

                                    ConstraintLayout.LayoutParams layoutParams_1 = new ConstraintLayout.LayoutParams(dpToPx(106),dpToPx(106));
                                    layoutParams_1.leftToLeft = R.id.Button_1_training;
                                    layoutParams_1.rightToRight = R.id.Button_1_training;
                                    layoutParams_1.topToTop = R.id.Button_1_training;
                                    layoutParams_1.bottomToBottom = R.id.Button_1_training;

                                    constraintLayout.addView(button_test_1[buttonCount_1],-1,layoutParams_1);
                                    button_1.bringToFront();
                                    buttonCount_1++;
                                }
                            });
                        }
                    };
                }
                if (trackNormalData[trackCount] == 2)
                {
                    timerTask_training = new TimerTask() {
                        @Override
                        public void run() {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    button_test_2[buttonCount_2] = new Button(getActivity());
                                    button_test_2[buttonCount_2].setBackground(getResources().getDrawable(R.drawable.round_circle));
                                    Animation animation = AnimationUtils.loadAnimation(getActivity(),R.anim.translatex_normal);
                                    animation.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            constraintLayout.removeView(button_test_2[buttonCount_2]);
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });
                                    button_test_2[buttonCount_2].setAnimation(animation);

                                    ConstraintLayout.LayoutParams layoutParams_2 = new ConstraintLayout.LayoutParams(dpToPx(106),dpToPx(106));
                                    layoutParams_2.leftToLeft = R.id.Button_2_training;
                                    layoutParams_2.rightToRight = R.id.Button_2_training;
                                    layoutParams_2.topToTop = R.id.Button_2_training;
                                    layoutParams_2.bottomToBottom = R.id.Button_2_training;
                                    constraintLayout.addView(button_test_2[buttonCount_2],-1,layoutParams_2);
                                    button_2.bringToFront();
                                    buttonCount_2++;
                                }
                            });
                        }
                    };
                }
                if (trackNormalData[trackCount] == 3)
                {
                    timerTask_training = new TimerTask() {
                        @Override
                        public void run() {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    button_test_3[buttonCount_3] = new Button(getActivity());
                                    button_test_3[buttonCount_3].setBackground(getResources().getDrawable(R.drawable.round_circle));
                                    Animation animation = AnimationUtils.loadAnimation(getActivity(),R.anim.translatex_normal);
                                    animation.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            constraintLayout.removeView(button_test_3[buttonCount_3]);
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });
                                    button_test_3[buttonCount_3].setAnimation(animation);

                                    ConstraintLayout.LayoutParams layoutParams_3 = new ConstraintLayout.LayoutParams(dpToPx(106),dpToPx(106));
                                    layoutParams_3.leftToLeft = R.id.Button_3_training;
                                    layoutParams_3.rightToRight = R.id.Button_3_training;
                                    layoutParams_3.topToTop = R.id.Button_3_training;
                                    layoutParams_3.bottomToBottom = R.id.Button_3_training;
                                    constraintLayout.addView(button_test_3[buttonCount_3],-1,layoutParams_3);
                                    button_3.bringToFront();
                                    buttonCount_3++;
                                }
                            });
                        }
                    };
                }
                if (trackNormalData[trackCount] == 4)
                {
                    timerTask_training = new TimerTask() {
                        @Override
                        public void run() {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    button_test_4[buttonCount_4] = new Button(getActivity());
                                    button_test_4[buttonCount_4].setBackground(getResources().getDrawable(R.drawable.round_circle));
                                    Animation animation = AnimationUtils.loadAnimation(getActivity(),R.anim.translatex_normal);
                                    animation.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            constraintLayout.removeView(button_test_4[buttonCount_4]);
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });
                                    button_test_4[buttonCount_4].setAnimation(animation);

                                    ConstraintLayout.LayoutParams layoutParams_4 = new ConstraintLayout.LayoutParams(dpToPx(106),dpToPx(106));
                                    layoutParams_4.leftToLeft = R.id.Button_4_training;
                                    layoutParams_4.rightToRight = R.id.Button_4_training;
                                    layoutParams_4.topToTop = R.id.Button_4_training;
                                    layoutParams_4.bottomToBottom = R.id.Button_4_training;
                                    constraintLayout.addView(button_test_4[buttonCount_4],-1,layoutParams_4);
                                    button_4.bringToFront();
                                    buttonCount_4++;
                                }
                            });
                        }
                    };
                }
//            Log.e(TAG, "time\t" + trackEasyTime[trackCount]);
                if(trackCount >= 4)
                {
                    timer_training.schedule(timerTask_training, trackNormalTime[trackCount]-2000);
                }
            }
        }
        else if(difficulty == 2)
        {
            button_test_1 = new Button[trackSunnyDaySize];
            button_test_2 = new Button[trackSunnyDaySize];
            button_test_3 = new Button[trackSunnyDaySize];
            button_test_4 = new Button[trackSunnyDaySize];
            for(trackCount=0;trackCount<trackSunnyDaySize;trackCount++)
            {
                if (trackSunnyDayData[trackCount] == 1)
                {
                    timerTask_training = new TimerTask() {
                        @Override
                        public void run() {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    button_test_1[buttonCount_1] = new Button(getActivity());
                                    button_test_1[buttonCount_1].setBackground(getResources().getDrawable(R.drawable.round_circle));
                                    Animation animation = AnimationUtils.loadAnimation(getActivity(),R.anim.translatex_sunnyday);
                                    animation.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            constraintLayout.removeView(button_test_1[buttonCount_1]);
                                            Log.e(TAG, "onAnimationEnd");
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });
                                    button_test_1[buttonCount_1].setAnimation(animation);

                                    ConstraintLayout.LayoutParams layoutParams_1 = new ConstraintLayout.LayoutParams(dpToPx(106),dpToPx(106));
                                    layoutParams_1.leftToLeft = R.id.Button_1_training;
                                    layoutParams_1.rightToRight = R.id.Button_1_training;
                                    layoutParams_1.topToTop = R.id.Button_1_training;
                                    layoutParams_1.bottomToBottom = R.id.Button_1_training;

                                    constraintLayout.addView(button_test_1[buttonCount_1],-1,layoutParams_1);
                                    button_1.bringToFront();
                                    buttonCount_1++;
                                }
                            });
                        }
                    };
                }
                if (trackSunnyDayData[trackCount] == 2)
                {
                    timerTask_training = new TimerTask() {
                        @Override
                        public void run() {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    button_test_2[buttonCount_2] = new Button(getActivity());
                                    button_test_2[buttonCount_2].setBackground(getResources().getDrawable(R.drawable.round_circle));
                                    Animation animation = AnimationUtils.loadAnimation(getActivity(),R.anim.translatex_sunnyday);
                                    animation.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            constraintLayout.removeView(button_test_2[buttonCount_2]);
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });
                                    button_test_2[buttonCount_2].setAnimation(animation);

                                    ConstraintLayout.LayoutParams layoutParams_2 = new ConstraintLayout.LayoutParams(dpToPx(106),dpToPx(106));
                                    layoutParams_2.leftToLeft = R.id.Button_2_training;
                                    layoutParams_2.rightToRight = R.id.Button_2_training;
                                    layoutParams_2.topToTop = R.id.Button_2_training;
                                    layoutParams_2.bottomToBottom = R.id.Button_2_training;
                                    constraintLayout.addView(button_test_2[buttonCount_2],-1,layoutParams_2);
                                    button_2.bringToFront();
                                    buttonCount_2++;
                                }
                            });
                        }
                    };
                }
                if (trackSunnyDayData[trackCount] == 3)
                {
                    timerTask_training = new TimerTask() {
                        @Override
                        public void run() {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    button_test_3[buttonCount_3] = new Button(getActivity());
                                    button_test_3[buttonCount_3].setBackground(getResources().getDrawable(R.drawable.round_circle));
                                    Animation animation = AnimationUtils.loadAnimation(getActivity(),R.anim.translatex_sunnyday);
                                    animation.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            constraintLayout.removeView(button_test_3[buttonCount_3]);
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });
                                    button_test_3[buttonCount_3].setAnimation(animation);

                                    ConstraintLayout.LayoutParams layoutParams_3 = new ConstraintLayout.LayoutParams(dpToPx(106),dpToPx(106));
                                    layoutParams_3.leftToLeft = R.id.Button_3_training;
                                    layoutParams_3.rightToRight = R.id.Button_3_training;
                                    layoutParams_3.topToTop = R.id.Button_3_training;
                                    layoutParams_3.bottomToBottom = R.id.Button_3_training;
                                    constraintLayout.addView(button_test_3[buttonCount_3],-1,layoutParams_3);
                                    button_3.bringToFront();
                                    buttonCount_3++;
                                }
                            });
                        }
                    };
                }
                if (trackSunnyDayData[trackCount] == 4)
                {
                    timerTask_training = new TimerTask() {
                        @Override
                        public void run() {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    button_test_4[buttonCount_4] = new Button(getActivity());
                                    button_test_4[buttonCount_4].setBackground(getResources().getDrawable(R.drawable.round_circle));
                                    Animation animation = AnimationUtils.loadAnimation(getActivity(),R.anim.translatex_sunnyday);
                                    animation.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            constraintLayout.removeView(button_test_4[buttonCount_4]);
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });
                                    button_test_4[buttonCount_4].setAnimation(animation);

                                    ConstraintLayout.LayoutParams layoutParams_4 = new ConstraintLayout.LayoutParams(dpToPx(106),dpToPx(106));
                                    layoutParams_4.leftToLeft = R.id.Button_4_training;
                                    layoutParams_4.rightToRight = R.id.Button_4_training;
                                    layoutParams_4.topToTop = R.id.Button_4_training;
                                    layoutParams_4.bottomToBottom = R.id.Button_4_training;
                                    constraintLayout.addView(button_test_4[buttonCount_4],-1,layoutParams_4);
                                    button_4.bringToFront();
                                    buttonCount_4++;
                                }
                            });
                        }
                    };
                }
//            Log.e(TAG, "time\t" + trackEasyTime[trackCount]);
                if(trackCount >= 4)
                {
                    timer_training.schedule(timerTask_training, trackSunnyDayTime[trackCount]-1764);
                }
            }
        }
    }
    private void setButtonClickable(boolean input) {
        button_1.setClickable(input);
        button_2.setClickable(input);
        button_3.setClickable(input);
        button_4.setClickable(input);
    }
    private void changeColorEarly(final Button buttonEarly) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                buttonEarly.setBackground(getResources().getDrawable(R.drawable.round_shape_button_early));
                handler_example.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        buttonEarly.setBackground(getResources().getDrawable(R.drawable.round_shape_button));
                    }
                },500);

            }
        });
    }
    private void changeColorLate(final Button buttonLate) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                buttonLate.setBackground(getResources().getDrawable(R.drawable.round_shape_button_late));
                handler_example.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        buttonLate.setBackground(getResources().getDrawable(R.drawable.round_shape_button));
                    }
                },500);

            }
        });
    }
    private void setClickTime() {
        TimerTask timerTask_button_1_activate;
        TimerTask timerTask_button_1_deactivate;
        TimerTask timerTask_button_1_early;
        TimerTask timerTask_button_1_late;
        TimerTask timerTask_button_2_activate;
        TimerTask timerTask_button_2_deactivate;
        TimerTask timerTask_button_2_early;
        TimerTask timerTask_button_2_late;
        TimerTask timerTask_button_3_activate;
        TimerTask timerTask_button_3_deactivate;
        TimerTask timerTask_button_3_early;
        TimerTask timerTask_button_3_late;
        TimerTask timerTask_button_4_activate;
        TimerTask timerTask_button_4_deactivate;
        TimerTask timerTask_button_4_early;
        TimerTask timerTask_button_4_late;
        if(difficulty == 0)
        {
            for(trackSetCount=0;trackSetCount<trackEasySize;trackSetCount++)
            {
                if (trackEasyData[trackSetCount] == 1)
                {
                    timerTask_button_1_activate = new TimerTask() {
                        @Override
                        public void run() {
                            button_1.setClickable(true);
                            clickTiming_1 = 1;
                        }
                    };
                    timerTask_button_1_deactivate = new TimerTask() {
                        @Override
                        public void run() {
                            button_1.setClickable(false);
                            clickTiming_1 = 0;
                        }
                    };
                    timerTask_button_1_early = new TimerTask() {
                        @Override
                        public void run() {
                            clickTiming_1 = 2;
                        }
                    };
                    timerTask_button_1_late =  new TimerTask() {
                        @Override
                        public void run() {
                            clickTiming_1 = 3;
                        }
                    };
                    timer_click.schedule(timerTask_button_1_early,trackEasyTime[trackSetCount]-10);
                    timer_click.schedule(timerTask_button_1_late,trackEasyTime[trackSetCount]+110);
                    timer_click.schedule(timerTask_button_1_activate,trackEasyTime[trackSetCount]-500);

                    if(trackSetCount < trackEasySize-1)
                    {
                        if(trackEasyTime[trackSetCount]+500 < trackEasyTime[trackSetCount+1]-500)
                        {
                            timer_click.schedule(timerTask_button_1_deactivate,trackEasyTime[trackSetCount]+500);
                        }
                    }
                }
                if (trackEasyData[trackSetCount] == 2)
                {
                    timerTask_button_2_activate = new TimerTask() {
                        @Override
                        public void run() {
                            button_2.setClickable(true);
                            clickTiming_2 = 1;
                        }
                    };
                    timerTask_button_2_deactivate = new TimerTask() {
                        @Override
                        public void run() {
                            button_2.setClickable(false);
                            clickTiming_2 = 0;
                        }
                    };
                    timerTask_button_2_early = new TimerTask() {
                        @Override
                        public void run() {
                            clickTiming_2 = 2;
                        }
                    };
                    timerTask_button_2_late =  new TimerTask() {
                        @Override
                        public void run() {
                            clickTiming_2 = 3;
                        }
                    };
                    timer_click.schedule(timerTask_button_2_early,trackEasyTime[trackSetCount]-10);
                    timer_click.schedule(timerTask_button_2_late,trackEasyTime[trackSetCount]+110);
                    timer_click.schedule(timerTask_button_2_activate,trackEasyTime[trackSetCount]-500);

                    if(trackSetCount < trackEasySize-1)
                    {
                        if(trackEasyTime[trackSetCount]+500 < trackEasyTime[trackSetCount+1]-500)
                        {
                            timer_click.schedule(timerTask_button_2_deactivate,trackEasyTime[trackSetCount]+500);
                        }
                    }
                }
                if (trackEasyData[trackSetCount] == 3)
                {
                    timerTask_button_3_activate = new TimerTask() {
                        @Override
                        public void run() {
                            button_3.setClickable(true);
                            clickTiming_3 = 1;
                        }
                    };
                    timerTask_button_3_deactivate = new TimerTask() {
                        @Override
                        public void run() {
                            button_3.setClickable(false);
                            clickTiming_3 = 0;
//                        Log.e(TAG, "setClickTime: deactivate");
                        }
                    };
                    timerTask_button_3_early = new TimerTask() {
                        @Override
                        public void run() {
                            clickTiming_3 = 2;
                        }
                    };
                    timerTask_button_3_late =  new TimerTask() {
                        @Override
                        public void run() {
                            clickTiming_3 = 3;
                        }
                    };
                    timer_click.schedule(timerTask_button_3_early,trackEasyTime[trackSetCount]-10);
                    timer_click.schedule(timerTask_button_3_late,trackEasyTime[trackSetCount]+110);
                    timer_click.schedule(timerTask_button_3_activate,trackEasyTime[trackSetCount]-500);
                    if(trackSetCount < trackEasySize-4)
                    {
//                    Log.e(TAG, "button_3 = \t" + (trackEasyTime[trackSetCount]+500) + "\t" +
//                            (trackEasyTime[trackSetCount+1]-500));
                        if(trackEasyTime[trackSetCount]+500 < trackEasyTime[trackSetCount+4]-500)
                        {
                            timer_click.schedule(timerTask_button_3_deactivate,trackEasyTime[trackSetCount]+500);
                        }
                    }
                }
                if (trackEasyData[trackSetCount] == 4)
                {
                    timerTask_button_4_activate = new TimerTask() {
                        @Override
                        public void run() {
                            button_4.setClickable(true);
                            clickTiming_4 = 1;
                        }
                    };
                    timerTask_button_4_deactivate = new TimerTask() {
                        @Override
                        public void run() {
                            button_4.setClickable(false);
                            clickTiming_4 = 0;
                        }
                    };
                    timerTask_button_4_early = new TimerTask() {
                        @Override
                        public void run() {
                            clickTiming_4 = 2;
                        }
                    };
                    timerTask_button_4_late =  new TimerTask() {
                        @Override
                        public void run() {
                            clickTiming_4 = 3;
                        }
                    };
                    timer_click.schedule(timerTask_button_4_early,trackEasyTime[trackSetCount]-10);
                    timer_click.schedule(timerTask_button_4_late,trackEasyTime[trackSetCount]+110);
                    timer_click.schedule(timerTask_button_4_activate,trackEasyTime[trackSetCount]-500);
                    if(trackSetCount < trackEasySize-4)
                    {
                        if(trackEasyTime[trackSetCount]+500 < trackEasyTime[trackSetCount+4]-500)
                        {
                            timer_click.schedule(timerTask_button_4_deactivate,trackEasyTime[trackSetCount]+500);
                        }
                    }
                }
            }
        }
        else if(difficulty == 1)
        {
            for(trackSetCount=0;trackSetCount<trackNormalSize;trackSetCount++)
            {
                if (trackNormalData[trackSetCount] == 1)
                {
                    timerTask_button_1_activate = new TimerTask() {
                        @Override
                        public void run() {
                            button_1.setClickable(true);
                            clickTiming_1 = 1;
                        }
                    };
                    timerTask_button_1_deactivate = new TimerTask() {
                        @Override
                        public void run() {
                            button_1.setClickable(false);
                            clickTiming_1 = 0;
                        }
                    };
                    timerTask_button_1_early = new TimerTask() {
                        @Override
                        public void run() {
                            clickTiming_1 = 2;
                        }
                    };
                    timerTask_button_1_late =  new TimerTask() {
                        @Override
                        public void run() {
                            clickTiming_1 = 3;
                        }
                    };
                    timer_click.schedule(timerTask_button_1_early,trackNormalTime[trackSetCount]-10);
                    timer_click.schedule(timerTask_button_1_late,trackNormalTime[trackSetCount]+110);
                    timer_click.schedule(timerTask_button_1_activate,trackNormalTime[trackSetCount]-500);

                    if(trackSetCount < trackNormalSize-1)
                    {
                        if(trackNormalTime[trackSetCount]+500 < trackNormalTime[trackSetCount+1]-500)
                        {
                            timer_click.schedule(timerTask_button_1_deactivate,trackNormalTime[trackSetCount]+500);
                        }
                    }
                }
                if (trackNormalData[trackSetCount] == 2)
                {
                    timerTask_button_2_activate = new TimerTask() {
                        @Override
                        public void run() {
                            button_2.setClickable(true);
                            clickTiming_2 = 1;
                        }
                    };
                    timerTask_button_2_deactivate = new TimerTask() {
                        @Override
                        public void run() {
                            button_2.setClickable(false);
                            clickTiming_2 = 0;
                        }
                    };
                    timerTask_button_2_early = new TimerTask() {
                        @Override
                        public void run() {
                            clickTiming_2 = 2;
                        }
                    };
                    timerTask_button_2_late =  new TimerTask() {
                        @Override
                        public void run() {
                            clickTiming_2 = 3;
                        }
                    };
                    timer_click.schedule(timerTask_button_2_early,trackNormalTime[trackSetCount]-10);
                    timer_click.schedule(timerTask_button_2_late,trackNormalTime[trackSetCount]+110);
                    timer_click.schedule(timerTask_button_2_activate,trackNormalTime[trackSetCount]-500);

                    if(trackSetCount < trackNormalSize-1)
                    {
                        if(trackNormalTime[trackSetCount]+500 < trackNormalTime[trackSetCount+1]-500)
                        {
                            timer_click.schedule(timerTask_button_2_deactivate,trackNormalTime[trackSetCount]+500);
                        }
                    }
                }
                if (trackNormalData[trackSetCount] == 3)
                {
                    timerTask_button_3_activate = new TimerTask() {
                        @Override
                        public void run() {
                            button_3.setClickable(true);
                            clickTiming_3 = 1;
                        }
                    };
                    timerTask_button_3_deactivate = new TimerTask() {
                        @Override
                        public void run() {
                            button_3.setClickable(false);
                            clickTiming_3 = 0;
//                        Log.e(TAG, "setClickTime: deactivate");
                        }
                    };
                    timerTask_button_3_early = new TimerTask() {
                        @Override
                        public void run() {
                            clickTiming_3 = 2;
                        }
                    };
                    timerTask_button_3_late =  new TimerTask() {
                        @Override
                        public void run() {
                            clickTiming_3 = 3;
                        }
                    };
                    timer_click.schedule(timerTask_button_3_early,trackNormalTime[trackSetCount]-10);
                    timer_click.schedule(timerTask_button_3_late,trackNormalTime[trackSetCount]+110);
                    timer_click.schedule(timerTask_button_3_activate,trackNormalTime[trackSetCount]-500);
                    if(trackSetCount < trackNormalSize-4)
                    {
                        if(trackNormalTime[trackSetCount]+500 < trackNormalTime[trackSetCount+4]-500)
                        {
                            timer_click.schedule(timerTask_button_3_deactivate,trackNormalTime[trackSetCount]+500);
                        }
                    }
                }
                if (trackNormalData[trackSetCount] == 4)
                {
                    timerTask_button_4_activate = new TimerTask() {
                        @Override
                        public void run() {
                            button_4.setClickable(true);
                            clickTiming_4 = 1;
                        }
                    };
                    timerTask_button_4_deactivate = new TimerTask() {
                        @Override
                        public void run() {
                            button_4.setClickable(false);
                            clickTiming_4 = 0;
                        }
                    };
                    timerTask_button_4_early = new TimerTask() {
                        @Override
                        public void run() {
                            clickTiming_4 = 2;
                        }
                    };
                    timerTask_button_4_late =  new TimerTask() {
                        @Override
                        public void run() {
                            clickTiming_4 = 3;
                        }
                    };
                    timer_click.schedule(timerTask_button_4_early,trackNormalTime[trackSetCount]-10);
                    timer_click.schedule(timerTask_button_4_late,trackNormalTime[trackSetCount]+110);
                    timer_click.schedule(timerTask_button_4_activate,trackNormalTime[trackSetCount]-500);
                    if(trackSetCount < trackNormalSize-4)
                    {
                        if(trackNormalTime[trackSetCount]+500 < trackNormalTime[trackSetCount+4]-500)
                        {
                            timer_click.schedule(timerTask_button_4_deactivate,trackNormalTime[trackSetCount]+500);
                        }
                    }
                }
            }
        }
        else if(difficulty == 2)
        {
            for(trackSetCount=0;trackSetCount<trackSunnyDaySize;trackSetCount++)
            {
                if (trackSunnyDayData[trackSetCount] == 1)
                {
                    timerTask_button_1_activate = new TimerTask() {
                        @Override
                        public void run() {
                            button_1.setClickable(true);
                            clickTiming_1 = 1;
                        }
                    };
                    timerTask_button_1_deactivate = new TimerTask() {
                        @Override
                        public void run() {
                            button_1.setClickable(false);
                            clickTiming_1 = 0;
                        }
                    };
                    timerTask_button_1_early = new TimerTask() {
                        @Override
                        public void run() {
                            clickTiming_1 = 2;
                        }
                    };
                    timerTask_button_1_late =  new TimerTask() {
                        @Override
                        public void run() {
                            clickTiming_1 = 3;
                        }
                    };
                    timer_click.schedule(timerTask_button_1_early,trackSunnyDayTime[trackSetCount]-10);
                    timer_click.schedule(timerTask_button_1_late,trackSunnyDayTime[trackSetCount]+110);
                    timer_click.schedule(timerTask_button_1_activate,trackSunnyDayTime[trackSetCount]-500);

                    if(trackSetCount < trackSunnyDaySize-1)
                    {
                        if(trackSunnyDayTime[trackSetCount]+500 < trackSunnyDayTime[trackSetCount+1]-500)
                        {
                            timer_click.schedule(timerTask_button_1_deactivate,trackSunnyDayTime[trackSetCount]+500);
                        }
                    }
                }
                if (trackSunnyDayData[trackSetCount] == 2)
                {
                    timerTask_button_2_activate = new TimerTask() {
                        @Override
                        public void run() {
                            button_2.setClickable(true);
                            clickTiming_2 = 1;
                        }
                    };
                    timerTask_button_2_deactivate = new TimerTask() {
                        @Override
                        public void run() {
                            button_2.setClickable(false);
                            clickTiming_2 = 0;
                        }
                    };
                    timerTask_button_2_early = new TimerTask() {
                        @Override
                        public void run() {
                            clickTiming_2 = 2;
                        }
                    };
                    timerTask_button_2_late =  new TimerTask() {
                        @Override
                        public void run() {
                            clickTiming_2 = 3;
                        }
                    };
                    timer_click.schedule(timerTask_button_2_early,trackSunnyDayTime[trackSetCount]-10);
                    timer_click.schedule(timerTask_button_2_late,trackSunnyDayTime[trackSetCount]+110);
                    timer_click.schedule(timerTask_button_2_activate,trackSunnyDayTime[trackSetCount]-500);

                    if(trackSetCount < trackSunnyDaySize-1)
                    {
                        if(trackSunnyDayTime[trackSetCount]+500 < trackSunnyDayTime[trackSetCount+1]-500)
                        {
                            timer_click.schedule(timerTask_button_2_deactivate,trackSunnyDayTime[trackSetCount]+500);
                        }
                    }
                }
                if (trackSunnyDayData[trackSetCount] == 3)
                {
                    timerTask_button_3_activate = new TimerTask() {
                        @Override
                        public void run() {
                            button_3.setClickable(true);
                            clickTiming_3 = 1;
                        }
                    };
                    timerTask_button_3_deactivate = new TimerTask() {
                        @Override
                        public void run() {
                            button_3.setClickable(false);
                            clickTiming_3 = 0;
//                        Log.e(TAG, "setClickTime: deactivate");
                        }
                    };
                    timerTask_button_3_early = new TimerTask() {
                        @Override
                        public void run() {
                            clickTiming_3 = 2;
                        }
                    };
                    timerTask_button_3_late =  new TimerTask() {
                        @Override
                        public void run() {
                            clickTiming_3 = 3;
                        }
                    };
                    timer_click.schedule(timerTask_button_3_early,trackSunnyDayTime[trackSetCount]-10);
                    timer_click.schedule(timerTask_button_3_late,trackSunnyDayTime[trackSetCount]+110);
                    timer_click.schedule(timerTask_button_3_activate,trackSunnyDayTime[trackSetCount]-500);
                    if(trackSetCount < trackSunnyDaySize-4)
                    {
                        if(trackSunnyDayTime[trackSetCount]+500 < trackSunnyDayTime[trackSetCount+4]-500)
                        {
                            timer_click.schedule(timerTask_button_3_deactivate,trackSunnyDayTime[trackSetCount]+500);
                        }
                    }
                }
                if (trackSunnyDayData[trackSetCount] == 4)
                {
                    timerTask_button_4_activate = new TimerTask() {
                        @Override
                        public void run() {
                            button_4.setClickable(true);
                            clickTiming_4 = 1;
                        }
                    };
                    timerTask_button_4_deactivate = new TimerTask() {
                        @Override
                        public void run() {
                            button_4.setClickable(false);
                            clickTiming_4 = 0;
                        }
                    };
                    timerTask_button_4_early = new TimerTask() {
                        @Override
                        public void run() {
                            clickTiming_4 = 2;
                        }
                    };
                    timerTask_button_4_late =  new TimerTask() {
                        @Override
                        public void run() {
                            clickTiming_4 = 3;
                        }
                    };
                    timer_click.schedule(timerTask_button_4_early,trackSunnyDayTime[trackSetCount]-10);
                    timer_click.schedule(timerTask_button_4_late,trackSunnyDayTime[trackSetCount]+110);
                    timer_click.schedule(timerTask_button_4_activate,trackSunnyDayTime[trackSetCount]-500);
                    if(trackSetCount < trackSunnyDaySize-4)
                    {
                        if(trackSunnyDayTime[trackSetCount]+500 < trackSunnyDayTime[trackSetCount+4]-500)
                        {
                            timer_click.schedule(timerTask_button_4_deactivate,trackSunnyDayTime[trackSetCount]+500);
                        }
                    }
                }
            }
        }
    }
    private void clearButton() {
        for(int i=0;i<buttonCount_1;i++)
        {
            button_test_1[i].clearAnimation();
            constraintLayout.removeView(button_test_1[i]);
        }
        for(int i=0;i<buttonCount_2;i++)
        {
            button_test_2[i].clearAnimation();
            constraintLayout.removeView(button_test_2[i]);
        }
        for(int i=0;i<buttonCount_3;i++)
        {
            button_test_3[i].clearAnimation();
            constraintLayout.removeView(button_test_3[i]);
        }
        for(int i=0;i<buttonCount_4;i++)
        {
            button_test_4[i].clearAnimation();
            constraintLayout.removeView(button_test_4[i]);
        }
    }
    private void createSunnyDay() {
        ArrayList<Integer[]> arrayList_sunny_day = new ArrayList<Integer[]>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.getActivity().getAssets().open("sunnyday.txt")));

            String mLine = reader.readLine();
            while(mLine != null)
            {
                String temp[] = mLine.split("\t");
//                Log.e(TAG, "mLine = \t" + Integer.valueOf(temp[0]) + "\t" + Integer.valueOf(temp[1]));
                Integer temp_int_array_1[] = new Integer[2];
                temp_int_array_1[0] = 1000*(Integer.valueOf(temp[1]))*60/(68*2);
//                Log.e(TAG, "Time = \t" + temp_int_array_1[0]);
                temp_int_array_1[1] = Integer.valueOf(temp[0]);
                arrayList_sunny_day.add(temp_int_array_1);
                mLine = reader.readLine();

            }
            String temp[][] = new String[2][arrayList_sunny_day.size()];
            for(int i = 0; i< arrayList_sunny_day.size(); i++)
            {
                temp[0][i] = arrayList_sunny_day.get(i)[0].toString();
                temp[1][i] = arrayList_sunny_day.get(i)[1].toString();
            }
            dataBase_training.addSong(temp,"Sunny day");


//            Log.e(TAG, "Array Length = \t" + arrayList_sunny_day.size());

        } catch (IOException e) {
            Log.e(TAG, "Open Fail");
            e.printStackTrace();
        }
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dataBase_training = new DataBase(getActivity(),"Database Record",null, 1);
        createSunnyDay();

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


        count = 3;

        DataBase dataBase_training = new DataBase(getActivity(),"Database Record",null, 1);

        trackEasySize = dataBase_training.getSongData(0).length;
        trackEasyData = dataBase_training.getSongData(0);
        trackEasyTime = dataBase_training.getSongTime(0);

        trackNormalSize = dataBase_training.getSongData(1).length;
        trackNormalData = dataBase_training.getSongData(1);
        trackNormalTime = dataBase_training.getSongTime(1);

        trackSunnyDaySize = dataBase_training.getSongData(2).length;
        trackSunnyDayData = dataBase_training.getSongData(2);
        trackSunnyDayTime = dataBase_training.getSongTime(2);


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
        button_set_difficulty = (Button)view_training.findViewById(R.id.Button_set_difficulty);


        return view_training;
    }


    @Override
    public void onStart() {



        super.onStart();
    }


    @Override
    public void onResume() {

        count_down = 3;
        int maxVolume = 50;
        int currentVolumeTraining = 20;
        float log_1 = (float)(Math.log(maxVolume-currentVolumeTraining)/Math.log(maxVolume));
        mediaPlayer_track.setVolume(1-log_1,1-log_1);


        constraintLayout = (ConstraintLayout)view_training.findViewById(R.id.constrainLayout_training);

        timerTask_countdown = newTimerCountDown();
        button_practice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: play");
                if(!mediaPlayer_track.isPlaying())
                {
//                if(!isPlay)
//                {
                    practiceTime = System.currentTimeMillis();

                    Log.d(TAG, "onClick: start");
                    button_practice.setText(R.string.stop);
                    mediaPlayer_track.start();
                    timer_countdown = new Timer();
                    if(difficulty == 0)
                    {
                        timer_countdown.schedule(timerTask_countdown,0,1000*60/80);
                    }
                    else if(difficulty == 1)
                    {
                        timer_countdown.schedule(timerTask_countdown,0,1000*60/120);
                    }
                    else if(difficulty == 2)
                    {
                        timer_countdown.schedule(timerTask_countdown,0,1000*60/68/2);
                    }

                    timer_click = new Timer();

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

                            timer_click.cancel();
                            timer_click.purge();
                            resetButtonTiming();
                            setButtonClickable(true);

                            timer_training.cancel();
                            timer_training.purge();
                        }
                    });

                    setButtonClickable(false);

                    timer_training = new Timer();
                    constraintLayout = (ConstraintLayout)view_training.findViewById(R.id.constrainLayout_training);
//                    TimerTask timerTask_test[] = new TimerTask[tempSize];
                    playTrackTraining();
                    setClickTime();


                }
                else
                {
                    Log.e(TAG, "onClick: stop");
                    count_down = 3;
                    Toast.makeText(getActivity(), "Practice end",Toast.LENGTH_SHORT).show();
//                    handler_countdown.removeCallbacks(runnable_countdown);

                    clearButton();

                    timer_countdown.cancel();
                    timer_countdown.purge();
                    timerTask_countdown = newTimerCountDown();

                    timer_training.cancel();
                    timer_training.purge();

                    resetButtonTiming();
                    timer_click.cancel();
                    timer_click.purge();
                    setButtonClickable(true);

                    mediaPlayer_track.stop();
                    mediaPlayer_track.prepareAsync();
                    button_practice.setText(R.string.button_practice);
                }

//                isPlay = !isPlay;
            }
        });

        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mediaPlayer_example.isPlaying())
                {
                    soundPool_training_1.play(soundID_1,(float)0.3,(float)0.3,1,0,1);
                    soundPool_training_1.stop(soundID_1);
                }
                currentTime_1 = System.currentTimeMillis();
                currentTime_1 = currentTime_1-practiceTime;
//                Log.e(TAG, "onClick: button_1 Time = \t" + currentTime_1 );
                button_1.setPressed(true);
                if(clickTiming_1 == 1)
                {
                    changeColorEarly(button_1);
                }
                else if(clickTiming_1 == 3)
                {
                    changeColorLate(button_1);
                }
                else
                {
                    button_1.setBackground(getResources().getDrawable(R.drawable.round_shape_button));
                }
            }
        });
        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mediaPlayer_example.isPlaying())
                {
                    soundPool_training_1.play(soundID_2,(float)0.5,(float)0.5,1,0,1);
                    soundPool_training_1.stop(soundID_2);
                }

                currentTime_2 = System.currentTimeMillis();
                currentTime_2 = currentTime_2-practiceTime;
//                Log.e(TAG, "onClick: button_2 Time = \t" + currentTime_2 );
                button_2.setPressed(true);
                if(clickTiming_2 == 1)
                {
                    changeColorEarly(button_2);
                }
                else if(clickTiming_2 == 3)
                {
                    changeColorLate(button_2);
                }
                else
                {
                    button_2.setBackground(getResources().getDrawable(R.drawable.round_shape_button));
                }
            }
        });
        button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mediaPlayer_example.isPlaying())
                {
                    soundPool_training_1.play(soundID_3,(float)0.5,(float)0.5,1,0,1);
                    soundPool_training_1.stop(soundID_3);
                }
                currentTime_3 = System.currentTimeMillis();
                currentTime_3 = currentTime_3-practiceTime;
//                Log.e(TAG, "onClick: button_3 Time = \t" + currentTime_3 );
                button_3.setPressed(true);

//                Log.e(TAG, "onClick: clickTiming_3 = \t" + clickTiming_3 );
                if(clickTiming_3 == 1)
                {
                    changeColorEarly(button_3);
                }
                else if(clickTiming_3 == 3)
                {
                    changeColorLate(button_3);
                }
                else
                {
                    button_3.setBackground(getResources().getDrawable(R.drawable.round_shape_button));
                }
            }
        });
        button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mediaPlayer_example.isPlaying())
                {
                    soundPool_training_1.play(soundID_4,(float)0.5,(float)0.5,1,0,1);
                    soundPool_training_1.stop(soundID_4);
                }
                currentTime_4 = System.currentTimeMillis();
                currentTime_4 = currentTime_4-practiceTime;
//                Log.e(TAG, "onClick: button_4 Time = \t" + currentTime_4 );
                button_4.setPressed(true);

                if(clickTiming_4 == 1)
                {
                    changeColorEarly(button_4);
                }
                else if(clickTiming_4 == 3)
                {
                    changeColorLate(button_4);
                }
                else
                {
                    button_4.setBackground(getResources().getDrawable(R.drawable.round_shape_button));
                }
            }
        });


        int currentVolumeExample = 50;
        float log_2 = (float)(Math.log(maxVolume-currentVolumeExample)/Math.log(maxVolume));
        mediaPlayer_example.setVolume(1-log_2,1-log_2);

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
                    playTrackExample(difficulty);

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

        button_set_difficulty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                String string_difficulty[] = dataBase_training.getSongName();
//                Log.e(TAG, "Example:\t" + string_difficulty[0] + "\t" + string_difficulty[1] );

                builder.setTitle("Choose difficulty")
                        .setItems(string_difficulty, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                difficulty = which;
                                if(which == 0)
                                {
                                    button_set_difficulty.setText(R.string.easy);
                                    mediaPlayer_example = MediaPlayer.create(getActivity(),R.raw.example_easy_space);
                                    mediaPlayer_track = MediaPlayer.create(getActivity(),R.raw.example_easy_space);
                                }
                                else if(which == 1)
                                {
                                    button_set_difficulty.setText(R.string.normal);
                                    mediaPlayer_example = MediaPlayer.create(getActivity(),R.raw.example_normal_space);
                                    mediaPlayer_track = MediaPlayer.create(getActivity(),R.raw.example_normal_space);
                                }
                                else if(which == 2)
                                {
                                    button_set_difficulty.setText(R.string.sunnyday);
                                    mediaPlayer_example = MediaPlayer.create(getActivity(),R.raw.sunnyday);
                                    mediaPlayer_track = MediaPlayer.create(getActivity(),R.raw.sunnyday_nodrum);
                                }
                                Log.e(TAG, "Difficulty = \t" + difficulty);
                            }
                        });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
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

