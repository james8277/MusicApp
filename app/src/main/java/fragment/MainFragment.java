package fragment;

import android.app.FragmentTransaction;
import android.content.Context;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.james.musicapp.MainActivity;
import com.example.james.musicapp.R;

import java.util.Timer;
import java.util.TimerTask;

public class MainFragment extends android.app.Fragment {

    private static final String TAG = "MainFragment";

    private Button button_1;
    private Button button_2;
    private Button button_3;
    private Button button_4;
    private Button Button_metronome;
    private Button button_training;
    private EditText editText_rate;

    private SoundPool soundPool_main;

    private int soundID_1;
    private int soundID_metronome;
    private int soundID_2;
    private int soundID_3 ;
    private int soundID_4;
    private int soundID_5;

    private Timer timer_test = null;
    private TimerTask timerTask_test;

    private long metronome_rate;


    private Runnable runnable_metronome;

    int streamMetronome;
    private boolean isMetronome = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        soundPool_main = new SoundPool.Builder().setMaxStreams(10).build();
        soundID_1 = soundPool_main.load(getActivity(),R.raw.hihat_1,1);
        soundID_metronome = soundPool_main.load(getActivity(),R.raw.metronome,1);
        soundID_2 = soundPool_main.load(getActivity(),R.raw.drum_2,1);
        soundID_3 = soundPool_main.load(getActivity(),R.raw.drum_1,1);
        soundID_4 = soundPool_main.load(getActivity(),R.raw.bass_drum,1);
        soundID_5 = soundPool_main.load(getActivity(),R.raw.hihat_2,1);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_main, container, false);


        button_1 = (Button)view.findViewById(R.id.Button_1);
        button_2 = (Button)view.findViewById(R.id.Button_2);
        button_3 = (Button)view.findViewById(R.id.Button_3);
        button_4 = (Button)view.findViewById(R.id.Button_4);
        button_training = (Button)view.findViewById(R.id.Button_training_mode);
        editText_rate = (EditText)view.findViewById(R.id.EditText_metronome_tempo);


        Button_metronome = (Button)view.findViewById(R.id.Button_metronome);

//        AudioAttributes test =
//                new AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).setUsage(AudioAttributes.USAGE_GAME).build();



        final Handler handler_delay = new Handler();
        runnable_metronome = new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "run: time\t" + System.currentTimeMillis());
                soundPool_main.play(soundID_metronome,(float)0.2,(float)0.2,1,0,1);
                handler_delay.postDelayed(this,988*60/metronome_rate);

            }
        };

        Button_metronome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: metronome");
                if(!isMetronome)
                {
                    Button_metronome.setText(R.string.stop);
                    Log.d(TAG, "onClick: start");
                    String value_metronome = editText_rate.getText().toString();

                    if(value_metronome.equals(""))
                    {
                        Log.d(TAG, "onClick: default");
                        metronome_rate = 60;
                    }
                    else
                    {
                        Log.d(TAG, "onClick: set_rate");
                        metronome_rate = Long.parseLong(value_metronome);
                    }


                    timer_test = new Timer();
                    timerTask_test = new TimerTask() {
                        @Override
                        public void run() {
                            Log.e(TAG, "run: timer\t" + System.currentTimeMillis() );
                            soundPool_main.play(soundID_metronome,(float)0.2,(float)0.2,1,0,1);
                        }
                    };

                    timer_test.schedule(timerTask_test,0,1000*60/metronome_rate);
//                    handler_delay.post(runnable_metronome);

                }
                else
                {
                    Button_metronome.setText(R.string.metronome);
                    Log.d(TAG, "onClick: stop");
//                    handler_delay.removeCallbacks(runnable_metronome);
                    timer_test.purge();
                    timer_test.cancel();

                }
                isMetronome = !isMetronome;
            }
        });

        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Button1");
                soundPool_main.play(soundID_1,(float)0.5,(float)0.5,1,0,1);

            }
        });

        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Button2");
                soundPool_main.play(soundID_2,1,1,1,0,1);

            }
        });

        button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Button3");
                soundPool_main.play(soundID_3,1,1,1,0,1);

            }
        });

        button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Button4");
                soundPool_main.play(soundID_4,1,1,1,0,1);

            }
        });

        button_training.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: training");
                FragmentTransaction mf = getFragmentManager().beginTransaction();
                android.app.Fragment fragment_training = new Training();
                mf.replace(R.id.container_main,fragment_training);
                mf.commit();
            }
        });
        return view;

    }


    @Override
    public void onPause() {

        soundPool_main.release();

        super.onPause();
    }


}
