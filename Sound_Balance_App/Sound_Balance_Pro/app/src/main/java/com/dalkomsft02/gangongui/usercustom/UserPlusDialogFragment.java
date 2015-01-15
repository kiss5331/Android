package com.dalkomsft02.gangongui.usercustom;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.dalkomsoft02.ganggongui.sound_balance_pross.MainActivity;
import com.dalkomsoft02.ganggongui.sound_balance_pross.R;

import CustomClass.CustomToast;

/**
 * Created by ganggongui on 15. 1. 11..
 */
public class UserPlusDialogFragment extends DialogFragment implements View.OnClickListener {

    private RadioButton[] radioButton = new RadioButton[2];

    private Button okBtn;

    private Button plusBtn;

    private RadioGroup radioGroup;

    private View v;


    public UserPlusDialogFragment() {


    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int style = DialogFragment.STYLE_NO_TITLE;

        int themo = android.R.style.ThemeOverlay_Material_Dark;

        setStyle(style, themo);


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = v = inflater.inflate(R.layout.dialogfragment, container, false);

        okBtn = (Button) v.findViewById(R.id.okBtn);

        plusBtn = (Button) v.findViewById(R.id.plusBtn);

        radioGroup = (RadioGroup) v.findViewById(R.id.ridogrop);

        radioButton[0] = (RadioButton) v.findViewById(R.id.base_radio_btn);

        radioButton[1] = (RadioButton) v.findViewById(R.id.custom_radio_btn);

        okBtn.setOnClickListener(this);

        plusBtn.setOnClickListener(this);


        return v;
    }


    @Override
    public void onStart() {
        super.onStart();

        setWindowSize();

        setdftRadioBtn();


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {


                switch (checkedId) {
                    case R.id.base_radio_btn:

                        Radio(true, false, 0);

                        break;

                    case R.id.custom_radio_btn:

                        Radio(false, true, 1);

                        break;

                    default:

                        break;

                }

                Log.d("check ID", String.valueOf(checkedId));
            }
        });

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()) {


            case R.id.okBtn:

                MainActivity.editor.commit();

                for (int i = 0; i < UserCustomActivity.TubSeekbar.length; i++) {
                    UserCustomActivity.TubSeekbar[i].setProgress(MainActivity.preferences.getInt(MainActivity.preferences.getInt("MODE", 0) + "Sound" + i, UserCustomActivity.Sound[i]));
                }
                dismiss();

                break;
            case R.id.plusBtn:

                CustomToast customToast = new CustomToast(getActivity());

                customToast.Toast(getString(R.string.ProText));

                break;

            default:

                break;

        }

    }

    private void setWindowSize() {

        int width = getDialog().getWindow().getWindowManager().getDefaultDisplay().getWidth() - 230;

        int height = getDialog().getWindow().getWindowManager().getDefaultDisplay().getHeight() - 560;

        getDialog().getWindow().setLayout(width, height);
    }

    private void Radio(boolean num1, boolean num2, int modenum) {

        radioButton[0].setChecked(num1);
        radioButton[1].setChecked(num2);

        MainActivity.editor.putBoolean("radio1", num1);
        MainActivity.editor.putBoolean("radio2", num2);
        MainActivity.editor.putInt("MODE", modenum);


    }

    private void setdftRadioBtn() {

        radioButton[0].setText(getString(R.string.radiodef));
        radioButton[0].setTextSize(23);
        radioButton[0].setChecked(MainActivity.preferences.getBoolean("radio1", false));
        radioButton[1].setText(getString(R.string.radiodef2));
        radioButton[1].setTextSize(23);
        radioButton[1].setChecked(MainActivity.preferences.getBoolean("radio2", false));


    }


}
