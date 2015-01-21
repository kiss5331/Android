package showcaseview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ganggongui.dalkomsoft02.com.myapplication.R;

/**
 * Created by ganggongui on 15. 1. 21..
 */
public class Fragment_twe extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        this.view = inflater.inflate(R.layout.fragment_twe, container, false);

        return this.view;
    }

}
