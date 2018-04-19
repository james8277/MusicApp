package fragment;

import android.animation.Animator;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.james.musicapp.DataBase;
import com.example.james.musicapp.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class Record extends android.app.Fragment {


    private static final String TAG = "RecordFragment";

    private View view_record;

    private Button button_1_record;
    private Button button_2_record;
    private Button button_3_record;
    private Button button_4_record;
    private Button button_record;
    private Button button_back_record;
    private Button button_replay;
    private Button button_save;
    private Button button_data;
    private Button button_delete;

    private SoundPool soundPool_record;
    private int soundID_test;
    private int soundID_1;
    private int soundID_2;
    private int soundID_3;
    private int soundID_4;
    private int soundID_5;

    private long startTime;

    private int current_record;

    private boolean isRecord = false;
    private boolean isReplay = false;

    private ArrayList<Integer[]> arrayList_record_text;

    private Timer timer_replay;
    private TimerTask timerTask_replay;

    int count_replay;

    private Handler handler_replay;

    private LinearLayout linearLayout_recording;

    private DataBase dataBaseHelper;


    private String record_name = "";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handler_replay = new Handler();

        soundPool_record = new SoundPool.Builder().setMaxStreams(32).build();
        soundID_test = soundPool_record.load(getActivity(),R.raw.test,1);
        soundID_1 = soundPool_record.load(getActivity(),R.raw.hihat_1,1);
        soundID_2 = soundPool_record.load(getActivity(),R.raw.drum_2,1);
        soundID_3 = soundPool_record.load(getActivity(),R.raw.drum_1,1);
        soundID_4 = soundPool_record.load(getActivity(),R.raw.bass_drum,1);
        soundID_5 = soundPool_record.load(getActivity(),R.raw.metronome,1);
        arrayList_record_text = new ArrayList<Integer[]>();

        dataBaseHelper = new DataBase(getActivity(),"Database Record",null, 1);
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view_record = inflater.inflate(R.layout.fragment_record, container, false);

        linearLayout_recording = (LinearLayout)view_record.findViewById(R.id.linearLayout_record);


        button_1_record = (Button)view_record.findViewById(R.id.Button_1_record);
        button_2_record = (Button)view_record.findViewById(R.id.Button_2_record);
        button_3_record = (Button)view_record.findViewById(R.id.Button_3_record);
        button_4_record = (Button)view_record.findViewById(R.id.Button_4_record);
        button_record = (Button)view_record.findViewById(R.id.Button_Record);
        button_back_record = (Button)view_record.findViewById(R.id.Button_record_back);
        button_replay = (Button)view_record.findViewById(R.id.Button_replay);
        button_save = (Button)view_record.findViewById(R.id.Button_save);
        button_data = (Button)view_record.findViewById(R.id.Button_data);
        button_delete = (Button)view_record.findViewById(R.id.Button_delete);


        button_1_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool_record.play(soundID_1,(float)0.5,(float)0.5,1,0,1);
                button_1_record.setPressed(true);
                soundPool_record.stop(soundID_1);
                if(isRecord)
                {
                    Long temp_long = System.currentTimeMillis() - startTime;
                    Integer temp_int = temp_long.intValue();
                    Integer temp_int_array[] = new Integer[2];
                    temp_int_array[0] = temp_int;
                    temp_int_array[1] = 1;
                    arrayList_record_text.add(temp_int_array);
                }
            }
        });
        button_2_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool_record.play(soundID_2,1,1,1,0,1);
                button_2_record.setPressed(true);
                soundPool_record.stop(soundID_2);
                if(isRecord)
                {
                    Long temp_long = System.currentTimeMillis() - startTime;
                    Integer temp_int = temp_long.intValue();
                    Integer temp_int_array[] = new Integer[2];
                    temp_int_array[0] = temp_int;
                    temp_int_array[1] = 2;
                    arrayList_record_text.add(temp_int_array);
                }
            }
        });
        button_3_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool_record.play(soundID_3,1,1,1,0,1);
                button_3_record.setPressed(true);
                soundPool_record.stop(soundID_3);
                if(isRecord)
                {
                    Long temp_long = System.currentTimeMillis() - startTime;
                    Integer temp_int = temp_long.intValue();
                    Integer temp_int_array[] = new Integer[2];
                    temp_int_array[0] = temp_int;
                    temp_int_array[1] = 3;
                    arrayList_record_text.add(temp_int_array);
                }
            }
        });
        button_4_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool_record.play(soundID_4,1,1,1,0,1);
                button_4_record.setPressed(true);
                soundPool_record.stop(soundID_4);
                if(isRecord)
                {
                    Long temp_long = System.currentTimeMillis() - startTime;
                    Integer temp_int = temp_long.intValue();
                    Integer temp_int_array[] = new Integer[2];
                    temp_int_array[0] = temp_int;
                    temp_int_array[1] = 4;
                    arrayList_record_text.add(temp_int_array);
                }
            }
        });
        button_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isRecord)
                {
                    button_record.setText(R.string.stop);
                    linearLayout_recording.setVisibility(View.VISIBLE);
                    startTime = System.currentTimeMillis();
                    arrayList_record_text.clear();
                }
                else
                {
                    button_record.setText(R.string.record);
                    linearLayout_recording.setVisibility(View.INVISIBLE);
                }
                isRecord = !isRecord;
            }
        });

        button_back_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction mf = getFragmentManager().beginTransaction();
                android.app.Fragment fragment_main = new MainFragment();
                mf.replace(R.id.container_main,fragment_main);
                mf.commit();
            }
        });



        button_replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isReplay)
                {
                    if(arrayList_record_text.isEmpty())
                    {
                        Toast.makeText(getActivity(),"There is no recording",Toast.LENGTH_SHORT).show();
                        isReplay = !isReplay;
                    }
                    else
                    {
                        Log.e(TAG, "onClick: Array Size = " + arrayList_record_text.size() );

                        button_replay.setText(R.string.stop);

                        timer_replay = new Timer();
                        for(count_replay=0;count_replay<arrayList_record_text.size();count_replay++)
                        {
                            if(arrayList_record_text.get(count_replay)[1] == 1)
                            {
                                timerTask_replay = new TimerTask() {
                                    @Override
                                    public void run() {
                                        getActivity().runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                button_1_record.performClick();
                                                handler_replay.postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        button_1_record.setPressed(false);
                                                    }
                                                }, 100);
                                            }
                                        });
                                    }
                                };
                            }
                            if(arrayList_record_text.get(count_replay)[1] == 2)
                            {
                                timerTask_replay = new TimerTask() {
                                    @Override
                                    public void run() {
                                        getActivity().runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                button_2_record.performClick();
                                                handler_replay.postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        button_2_record.setPressed(false);
                                                    }
                                                }, 100);
                                            }
                                        });
                                    }
                                };
                            }
                            if(arrayList_record_text.get(count_replay)[1] == 3)
                            {
                                timerTask_replay = new TimerTask() {
                                    @Override
                                    public void run() {
                                        getActivity().runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                button_3_record.performClick();
                                                handler_replay.postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        button_3_record.setPressed(false);
                                                    }
                                                }, 100);
                                            }
                                        });
                                    }
                                };
                            }
                            if(arrayList_record_text.get(count_replay)[1] == 4)
                            {
                                timerTask_replay = new TimerTask() {
                                    @Override
                                    public void run() {
                                        getActivity().runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                button_4_record.performClick();
                                                handler_replay.postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        button_4_record.setPressed(false);
                                                    }
                                                }, 100);
                                            }
                                        });
                                    }
                                };
                            }

                            timer_replay.schedule(timerTask_replay,arrayList_record_text.get(count_replay)[0]);

//                        Log.e(TAG, "onClick: Array[" + count_replay + "][1] = " + arrayList_record_text.get(count_replay)[1] + "\tArray[" + count_replay + "][0] = " + arrayList_record_text.get(count_replay)[0]);

                        }
                        TimerTask text_reset = new TimerTask() {
                            @Override
                            public void run() {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        button_replay.setText(R.string.replay);
                                        isReplay = !isReplay;;
                                    }
                                });
                            }
                        };
                        timer_replay.schedule(text_reset,arrayList_record_text.get(arrayList_record_text.size()-1)[0] + 500);
                    }
                }
                else
                {
                    timer_replay.cancel();
                    timer_replay.purge();
                    button_replay.setText(R.string.replay);
                }
                isReplay = !isReplay;
            }
        });

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (arrayList_record_text.isEmpty()) {
                    Toast.makeText(getActivity(), "There is no recording", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                    LayoutInflater inflater_dialog = getActivity().getLayoutInflater();
//                    final View view_dialog = inflater_dialog.inflate(R.layout.dialog_save,null);
                    builder.setPositiveButton(R.string.enter, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    arrayList_record_text.clear();
                                }
                            })
                            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .setMessage(R.string.save_check);
                    AlertDialog dialog = builder.create();
                    dialog.show();

                    Log.e(TAG, "onClick: name = \t" +  record_name);
                    Log.e(TAG, "onClick: save data\t" + arrayList_record_text.size());
                    String temp[][] = new String[2][arrayList_record_text.size()];
                    for(int i=0;i<arrayList_record_text.size();i++)
                    {
                        temp[0][i] = arrayList_record_text.get(i)[0].toString();
                        temp[1][i] = arrayList_record_text.get(i)[1].toString();
                    }

                    dataBaseHelper.addRecord(temp,"Record Track " + dataBaseHelper.getRecordNumber());
                }

            }
        });


        button_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.e(TAG, "onClick: Data");

                if(dataBaseHelper.getRecordNumber() == 0)
                {
                    Toast.makeText(getActivity(),"There are no data in the database",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Log.e(TAG, "onClick: size = " + dataBaseHelper.getRecordName().length );
//                    Log.e(TAG, "onClick: 1 = " + dataBaseHelper.getRecordName()[0]);
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    String string_name_array[] = new String[dataBaseHelper.getRecordName().length];

                    for(int i=0;i<dataBaseHelper.getRecordName().length;i++)
                    {
                        string_name_array[i] = dataBaseHelper.getRecordName()[i];
                    }

                    builder.setTitle(R.string.chooseTrack)
                            .setItems(string_name_array, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Log.e(TAG, "onClick: which = \t" + which );
                                    int temp_data[];
                                    int temp_data_time[];
                                    int tempSize;
                                    tempSize = dataBaseHelper.getRecordData(which).length;
                                    temp_data = dataBaseHelper.getRecordData(which);
                                    temp_data_time = dataBaseHelper.getRecordTime(which);

                                    timer_replay = new Timer();
                                    for (int i = 0; i < tempSize; i++) {
                                        if (temp_data[i] == 1) {
                                            timerTask_replay = new TimerTask() {
                                                @Override
                                                public void run() {
                                                    getActivity().runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            button_1_record.performClick();
                                                            handler_replay.postDelayed(new Runnable() {
                                                                @Override
                                                                public void run() {
                                                                    button_1_record.setPressed(false);
                                                                }
                                                            }, 100);
                                                        }
                                                    });
                                                }
                                            };
                                        }
                                        if (temp_data[i] == 2) {
                                            timerTask_replay = new TimerTask() {
                                                @Override
                                                public void run() {
                                                    getActivity().runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            button_2_record.performClick();
                                                            handler_replay.postDelayed(new Runnable() {
                                                                @Override
                                                                public void run() {
                                                                    button_2_record.setPressed(false);
                                                                }
                                                            }, 100);
                                                        }
                                                    });
                                                }
                                            };
                                        }
                                        if (temp_data[i] == 3) {
                                            timerTask_replay = new TimerTask() {
                                                @Override
                                                public void run() {
                                                    getActivity().runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            button_3_record.performClick();
                                                            handler_replay.postDelayed(new Runnable() {
                                                                @Override
                                                                public void run() {
                                                                    button_3_record.setPressed(false);
                                                                }
                                                            }, 100);
                                                        }
                                                    });
                                                }
                                            };
                                        }
                                        if (temp_data[i] == 4) {
                                            timerTask_replay = new TimerTask() {
                                                @Override
                                                public void run() {
                                                    getActivity().runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            button_4_record.performClick();
                                                            handler_replay.postDelayed(new Runnable() {
                                                                @Override
                                                                public void run() {
                                                                    button_4_record.setPressed(false);
                                                                }
                                                            }, 100);
                                                        }
                                                    });
                                                }
                                            };
                                        }

                                        timer_replay.schedule(timerTask_replay, temp_data_time[i]);
                                    }
                                }
                            });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }

            }
        });

        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage(R.string.alert)
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dataBaseHelper.deleteTableRecord();
                            }
                        });
                AlertDialog alertDialog_delete = builder.create();
                alertDialog_delete.show();
            }
        });

        return view_record;
    }
}
