package gift.makemoney;

import com.facebook.stetho.Stetho;

/**
 * Created by malk on 16/11/15.
 */

public class Application extends android.app.Application {

    private static Application mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        Stetho.initializeWithDefaults(this);

    }

    public static Application getInstance() {
        return mContext;
    }

}
