package showcaseview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ganggongui.dalkomsoft02.com.myapplication.R;

/**
 * Created by ganggongui on 15. 1. 29..
 */
public class Fragment_fore extends Fragment {

    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_fore, container, false);

        return view;
    }
}
