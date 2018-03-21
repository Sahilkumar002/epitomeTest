package devil.epitomecodetest.utils;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by devil on 3/21/18.
 */

public class GeneralFunctions {
    public static void addFragmentWithBackStack(FragmentManager manager, Fragment fragment,
                                                String tag, int container) {
        if (null == manager.findFragmentById(container)) {
            manager.beginTransaction().add(container, fragment, tag).addToBackStack(tag).commit();
        } else {
            if (!manager.findFragmentById(container).getTag().equals(tag)) {
                manager.beginTransaction().add(container, fragment, tag).addToBackStack(tag).commit();
            }
        }
    }

    public static ProgressDialog progressDialog(Context context) {
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setIndeterminate(false);
        dialog.setCancelable(false);
        dialog.setTitle("Loading");
        return dialog;
    }

}
