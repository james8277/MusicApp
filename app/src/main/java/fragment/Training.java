package fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.james.musicapp.R;



public class Training extends android.app.Fragment {

    private static final String TAG = "FragmentTraining";
    Button button_practice;
    Button button_back;
    Button button_example;
    private Button button_1;
    private Button button_2;
    private Button button_3;
    private Button button_4;
    private int drum_track[][];
    private int count;

    private SoundPool soundPool_training;
    private int soundID_test;
    private int soundID_1;
    private int soundID_2;
    private int soundID_3;
    private int soundID_4;
    private int soundID_5;

    private int count_down;

    private Handler handler_example;
    private Handler handler_countdown;

    private Runnable runnable_test;
    private Runnable runnable_countdown;

    private MediaPlayer mediaPlayer_example;
    private MediaPlayer mediaPlayer_track;

    private Toast toast_countdown;
    View view_test;

    int streamPlay;
    private boolean isPlay = false;

    private long previousTime;
    private long currentTime;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handler_example = new Handler();
        handler_countdown = new Handler();

        mediaPlayer_example = MediaPlayer.create(getActivity(),R.raw.basic_drum_track_1);
        mediaPlayer_track = MediaPlayer.create(getActivity(),R.raw.basic_drum_track_1_space);


        drum_track = new int[4][4];

        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4;j++)
            {
                drum_track[i][j] = 0;
            }
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

        view_test = inflater.inflate(R.layout.fragment_training, container, false);

        soundPool_training = new SoundPool.Builder().setMaxStreams(32).build();

        soundID_test = soundPool_training.load(getActivity(),R.raw.test,1);
        soundID_1 = soundPool_training.load(getActivity(),R.raw.hihat_1,1);
        soundID_2 = soundPool_training.load(getActivity(),R.raw.drum_2,1);
        soundID_3 = soundPool_training.load(getActivity(),R.raw.drum_1,1);
        soundID_4 = soundPool_training.load(getActivity(),R.raw.bass_drum,1);
        soundID_5 = soundPool_training.load(getActivity(),R.raw.hihat_2,1);

        button_1 = (Button)view_test.findViewById(R.id.Button_1_training);
        button_2 = (Button)view_test.findViewById(R.id.Button_2_training);
        button_3 = (Button)view_test.findViewById(R.id.Button_3_training);
        button_4 = (Button)view_test.findViewById(R.id.Button_4_training);

        button_example = (Button)view_test.findViewById(R.id.Button_example);
        button_back = (Button)view_test.findViewById(R.id.Button_back__training);
        button_practice = (Button)view_test.findViewById(R.id.Button_practice);



        count = 0;

        return view_test;
    }


    @Override
    public void onStart() {



        super.onStart();
    }


    @Override
    public void onResume() {

        count_down = 7;
        runnable_countdown = new Runnable() {
            @Override
            public void run() {
                if(count_down >= 0)
                {
                    Toast.makeText(getActivity(),Integer.toString(count_down),Toast.LENGTH_SHORT).show();
                    count_down--;
                }
                handler_countdown.postDelayed(this,500);
            }
        };

        mediaPlayer_track.setVolume(1,1);

        button_practice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: play");
                if(!mediaPlayer_track.isPlaying())
                {
                    Log.d(TAG, "onClick: start");
                    button_practice.setText(R.string.stop);

                    mediaPlayer_track.start();
                    handler_countdown.post(runnable_countdown);
                    if(count_down < 0)
                    {
                        handler_countdown.removeCallbacks(runnable_countdown);
                        count_down = 7;
                    }
                    mediaPlayer_track.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            Toast.makeText(getActivity(), "Practice end",Toast.LENGTH_SHORT).show();
                            mp.stop();
                            mp.prepareAsync();
                            button_practice.setText(R.string.button_practice);
                        }
                    });
                }
                else
                {
                    Log.d(TAG, "onClick: stop");
                    count_down = 7;
                    Toast.makeText(getActivity(), "Practice end",Toast.LENGTH_SHORT).show();
                    handler_countdown.removeCallbacks(runnable_countdown);
                    mediaPlayer_track.stop();
                    mediaPlayer_track.prepareAsync();
                    button_practice.setText(R.string.button_practice);
                }
            }
        });

        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool_training.play(soundID_1,(float)0.5,(float)0.5,1,0,1);
                button_1.setPressed(true);
                soundPool_training.stop(soundID_1);
            }
        });
        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool_training.play(soundID_2,1,1,1,0,1);
                button_2.setPressed(true);
                soundPool_training.stop(soundID_2);
            }
        });
        button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool_training.play(soundID_3,1,1,1,0,1);
                button_3.setPressed(true);
                soundPool_training.stop(soundID_3);
            }
        });
        button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool_training.play(soundID_4,1,1,1,0,1);
                button_4.setPressed(true);
                soundPool_training.stop(soundID_4);
            }
        });

        runnable_test = new Runnable() {
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
                Log.e(TAG, "\t" + System.currentTimeMillis());
//                previousTime = currentTime;
                handler_example.postDelayed(runnable_test,920*60/120);
            }
        };


        int maxVolume = 50;
        int currentVolume = 40;
        float log_1 = (float)(Math.log(maxVolume-currentVolume)/Math.log(maxVolume));
        mediaPlayer_example.setVolume(1-log_1,1-log_1);


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
                    handler_example.post(runnable_test);
                    mediaPlayer_example.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            Log.d(TAG, "onCompletion: track");
                            Toast.makeText(getActivity(), "Example end",Toast.LENGTH_SHORT).show();
                            mp.stop();
                            mp.prepareAsync();
                            handler_example.removeCallbacks(runnable_test);
                            button_example.setText(R.string.button_example);
                        }
                    });
                }
                else
                {
                    Log.d(TAG, "onClick: stop");
                    count = 0;
                    mediaPlayer_example.stop();
                    mediaPlayer_example.prepareAsync();
                    handler_example.removeCallbacks(runnable_test);
                    button_example.setText(R.string.button_example);
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

        soundPool_training.release();

        if(mediaPlayer_example.isPlaying())
        {
            mediaPlayer_example.stop();
            mediaPlayer_example.prepareAsync();
        }
        handler_countdown.removeCallbacks(runnable_countdown);

        if(mediaPlayer_track.isPlaying())
        {
            mediaPlayer_track.stop();
            mediaPlayer_track.prepareAsync();
        }
        handler_example.removeCallbacks(runnable_test);

        super.onPause();
    }

    @Override
    public void onStop() {

        super.onStop();
    }
}
