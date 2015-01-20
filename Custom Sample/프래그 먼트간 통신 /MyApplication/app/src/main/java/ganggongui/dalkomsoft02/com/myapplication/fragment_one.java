package ganggongui.dalkomsoft02.com.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by ganggongui on 15. 1. 21..
 */
public class fragment_one extends Fragment {

    private View view;


    public onTextChangeListener changeListener;


    public interface onTextChangeListener {
        public void onTextChange(String text);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        changeListener = (onTextChangeListener) activity;


    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = this.view = inflater.inflate(R.layout.fragment_one, container, false);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        EditText editText = (EditText) view.findViewById(R.id.ET_input);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                changeListener.onTextChange(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
