package fragment;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.james.musicapp.R;

public class Welcome extends android.app.Fragment {


    Button button_free_mode;
    Button button_training_mode;
    Button button_record_mode;
    Button button_back;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_welcome, container, false);

        button_free_mode = (Button)view.findViewById(R.id.Button_welcome_free_mode);
        button_training_mode = (Button)view.findViewById(R.id.Button_welcome_training_mode);
        button_record_mode = (Button)view.findViewById(R.id.Button_welcome_record_mode);
        button_back = (Button)view.findViewById(R.id.Button_welcome_back);

        button_free_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction mf = getFragmentManager().beginTransaction();
                Fragment fragment_freeMode = new MainFragment();
                mf.replace(R.id.container_main,fragment_freeMode);
                mf.addToBackStack(null);
                mf.commit();
            }
        });

        button_training_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction mf = getFragmentManager().beginTransaction();
                Fragment fragment_training = new Training();
                mf.replace(R.id.container_main,fragment_training);
                mf.addToBackStack(null);
                mf.commit();
            }
        });

        button_record_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction mf = getFragmentManager().beginTransaction();
                Fragment fragment_record = new Record();
                mf.replace(R.id.container_main,fragment_record);
                mf.addToBackStack(null);
                mf.commit();
            }
        });

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage(R.string.exit)
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getActivity().finish();
                            }
                        });
                AlertDialog alertDialog_delete = builder.create();
                alertDialog_delete.show();
            }
        });


        return view;
    }
}
