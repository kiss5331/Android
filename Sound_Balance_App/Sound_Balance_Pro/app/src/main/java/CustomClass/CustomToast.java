package CustomClass;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by ganggongui on 14. 12. 31..
 */
public class CustomToast {

    private Context context;

    public CustomToast(Context context) {

        this.context = context;

    }


    public void Toast(String meg) {
        Toast.makeText(context, meg, Toast.LENGTH_SHORT).show();
    }

}
