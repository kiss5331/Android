package colorset;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ganggongui.dalkomsoft02.com.colorpad.R;

/**
 * Created by ganggongui on 15. 1. 28..
 */
public class AppList_Fragment extends android.app.Fragment {

    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_plist, container, false);

        return view;
    }

    /**
     * Called when the Fragment is visible to the user.  This is generally
     * tied to {@link Activity#onStart() Activity.onStart} of the containing
     * Activity's lifecycle.
     */
    @Override
    public void onStart() {
        super.onStart();
        setToolBar();
    }

    private void setToolBar() {


        Toolbar toolbar;
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        toolbar.setTitleTextColor(getResources().getColor(R.color.DeepWhite));

        toolbar.setSubtitleTextColor(getResources().getColor(R.color.DeepWhite));

        toolbar.setTitle(getString(R.string.Toolbar_Title2));

        toolbar.setSubtitle(getString(R.string.Toolbar_subTitle2));


    }
}
