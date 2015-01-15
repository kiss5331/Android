package Main_data;

import android.content.Context;

import com.dalkomsoft02.ganggongui.sound_balance_pross.R;

/**
 * Created by ganggongui on 15. 1. 7..
 */
public class StringResources {

    private Context context;

    public String ToolbarTitle;

    public String Start_Meg_false;

    public String Start_Meg_true;

    public String Start_Meg_stop;

    public String Start_Meg_stop_false;

    public String Start_Meg_start;

    public String Notif_Title;

    public String Notif_subTitle;

    public final static String SoundKey1 = "0Sound";

    public final static String SoundKey2 = "1Sound";

    public StringResources(Context context) {

        this.context = context;

        ToolbarTitle = context.getString(R.string.app_name);

        Start_Meg_false = context.getString(R.string.start_meg_false);

        Start_Meg_true = context.getString(R.string.start_meg_true);

        Start_Meg_stop = context.getString(R.string.start_meg_stop);

        Start_Meg_stop_false = context.getString(R.string.start_meg_stop_false);

        Start_Meg_start = context.getString(R.string.start_meg_start);

        Notif_Title = context.getString(R.string.notif_title);

        Notif_subTitle = context.getString(R.string.notif_subtitle);


    }


}
