package ganggongui.dalkomsoft02.com.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by ganggongui on 15. 1. 21..
 */
public class fragment_twe extends Fragment {


    private TextView textView;

    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = this.view = inflater.inflate(R.layout.fragment_two, container, false);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        textView = (TextView) view.findViewById(R.id.TV_output);

    }

    public void setText(String text) {

        textView.setText(text);

    }


}
