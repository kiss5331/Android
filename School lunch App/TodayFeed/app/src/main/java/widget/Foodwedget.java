package widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;

/**
 * Created by ganggongui on 14. 12. 15..
 */
public class Foodwedget extends AppWidgetProvider {

    private static final String TEG = "FoodWidget";

    public Foodwedget() {

    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
    }


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }
}
