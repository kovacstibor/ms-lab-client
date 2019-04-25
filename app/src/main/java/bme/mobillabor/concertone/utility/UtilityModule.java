package bme.mobillabor.concertone.utility;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UtilityModule {
    private Context context;

    public UtilityModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public INetworkStateProvider provideNetworkStateProvider() {
        return new NetworkStateProvider(context);
    }
}
