package fragments;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ganggongui.dalkomsoft02.com.fibonachickenapp.R;

/**
 * Created by ganggongui on 15. 1. 23..
 */
public class Fragment_Checkin extends Fragment {

    private View view;

    private int Chicken_Num;

    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        this.view = inflater.inflate(R.layout.fragment_checkin_layout, container, false);

        Chicken_Num = getArguments().getInt("chicken", 0);

        TextView textView = (TextView) view.findViewById(R.id.tv);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.dalkomsoft02.ganggongui.soundbalancepro");
                PackageManager pm = v.getContext().getPackageManager();
                List<ResolveInfo> activityList = pm.queryIntentActivities(shareIntent, 0);
                for (final ResolveInfo app : activityList) {
                    if ((app.activityInfo.name).contains("facebook")) {
                        final ActivityInfo activity = app.activityInfo;
                        final ComponentName name = new ComponentName(activity.applicationInfo.packageName, activity.name);
                        shareIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                        shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                        shareIntent.setComponent(name);
                        v.getContext().startActivity(shareIntent);
                        break;
                    }
                }


            }
        });

        return this.view;


    }


    @Override
    public void onStart() {
        super.onStart();

        Toast.makeText(getActivity(), Chicken_Num + "", Toast.LENGTH_SHORT).show();

    }


}
