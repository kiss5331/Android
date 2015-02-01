package colorset;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import applist.ListViewAdapter;
import ganggongui.dalkomsoft02.com.colorpad.R;

/**
 * Created by ganggongui on 15. 1. 28..
 */
public class AppList_Fragment extends android.app.Fragment {

    private View view;

    private ListView listView;

    private PackageManager packageManager;

    private List<ApplicationInfo> AppList = null;

    private ListViewAdapter listViewAdapter;

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

        listView = (ListView) view.findViewById(R.id.Lv_applist);

        packageManager = (PackageManager) getActivity().getPackageManager();

        AppList = packageManager.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);


        listViewAdapter = new ListViewAdapter(getActivity());


        for (ApplicationInfo info : AppList) {

            if ((info.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {

                listViewAdapter.addItem(info.loadIcon(packageManager), info.loadLabel(packageManager).toString());

            }
        }


        listViewAdapter.notifyDataSetChanged();

        listView.setVerticalScrollBarEnabled(false);
        listView.setAdapter(listViewAdapter);


    }

    /**
     * Called when the fragment is no longer in use.  This is called
     * after {@link #onStop()} and before {@link #onDetach()}.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();

        AppList = null;

        listViewAdapter = null;


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
