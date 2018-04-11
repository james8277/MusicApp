package fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.james.musicapp.R;



public class Training extends android.app.Fragment {

    private static final String TAG = "FragmentTraining";
    Button button_play;
    Button button_back;
    Button button_example;
    private Button button_1;
    private Button button_2;
    private Button button_3;
    private Button button_4;

    int streamPlay;
    private boolean isPlay = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_training, container, false);

        final SoundPool soundPool_training = new SoundPool.Builder().setMaxStreams(10).build();
        final int soundID_test = soundPool_training.load(getActivity(),R.raw.test,1);
        final int soundID_1 = soundPool_training.load(getActivity(),R.raw.hihat_1,1);
        final int soundID_2 = soundPool_training.load(getActivity(),R.raw.drum_1,1);
        final int soundID_3 = soundPool_training.load(getActivity(),R.raw.drum_2,1);
        final int soundID_4 = soundPool_training.load(getActivity(),R.raw.bassdrum_1,1);
        final int soundID_5 = soundPool_training.load(getActivity(),R.raw.hihat_2,1);


        button_1 = (Button)view.findViewById(R.id.Button_1_training);
        button_2 = (Button)view.findViewById(R.id.Button_2_training);
        button_3 = (Button)view.findViewById(R.id.Button_3_training);
        button_4 = (Button)view.findViewById(R.id.Button_4_training);


        final MediaPlayer mediaPlayer_track = MediaPlayer.create(getActivity(),R.raw.test);

        button_play = (Button)view.findViewById(R.id.Button_play_sound);
        button_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: play");
                if(!mediaPlayer_track.isPlaying())
                {
                    Log.d(TAG, "onClick: start");
                    mediaPlayer_track.start();

                }
                else
                {
                    Log.d(TAG, "onClick: stop");
                    mediaPlayer_track.stop();
                    mediaPlayer_track.prepareAsync();
                }
            }
        });

        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool_training.play(soundID_1,1,1,1,0,1);
            }
        });
        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool_training.play(soundID_2,1,1,1,0,1);
            }
        });
        button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool_training.play(soundID_3,1,1,1,0,1);
            }
        });
        button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool_training.play(soundID_4,1,1,1,0,1);
            }
        });

        final Handler handler_example = new Handler();
        final Runnable runnable_test = new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: time");
                button_1.performClick();
                handler_example.postDelayed(this, 1000*60/60);
            }

        };

        final MediaPlayer mediaPlayer_example = MediaPlayer.create(getActivity(),R.raw.aliez);
        int maxVolume = 50;
        int currentVolume = 25;
        float log_1 = (float)(Math.log(maxVolume-currentVolume)/Math.log(maxVolume));
        mediaPlayer_example.setVolume(1-log_1,1-log_1);

        button_example = (Button)view.findViewById(R.id.Button_example);
        button_example.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "onClick: example");

                if(!mediaPlayer_example.isPlaying())
                {
                    mediaPlayer_example.start();
                    Log.d(TAG, "onClick: play");
                    handler_example.post(runnable_test);
                }
                else
                {
                    Log.d(TAG, "onClick: stop");
                    mediaPlayer_example.stop();
                    mediaPlayer_example.prepareAsync();
                    handler_example.removeCallbacks(runnable_test);
                }

            }
        });

        button_back = (Button)view.findViewById(R.id.Button_back__training);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction mf = getFragmentManager().beginTransaction();
                Fragment fragment_main = new MainFragment();
                mf.replace(R.id.container_main,fragment_main);
                mf.commit();
            }
        });

        return view;
    }



}
