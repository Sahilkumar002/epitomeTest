package devil.epitomecodetest.utils;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by devil on 3/20/18.
 */

public class ApplicationGlobal extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
