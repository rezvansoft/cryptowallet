package ir.rezvansoft.cryptowallet.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import ir.rezvansoft.cryptowallet.model.Wallet;
import ir.rezvansoft.cryptowallet.orm.AppDatabase;

import java.util.List;

/**
 * Created by Sreejith on 25-Nov-17.
 */

public class WalletViewModel extends AndroidViewModel {

    private AppDatabase appDatabase;

    public LiveData<List<Wallet>> getAllWalletsLive() {
        return allWalletsLive;
    }

    private final LiveData<List<Wallet>> allWalletsLive;

    public WalletViewModel(@NonNull Application application) {
        super(application);
        appDatabase = AppDatabase.getDatabase(this.getApplication());
        allWalletsLive = appDatabase.walletDao().getAllWalletsLive();
    }

}
