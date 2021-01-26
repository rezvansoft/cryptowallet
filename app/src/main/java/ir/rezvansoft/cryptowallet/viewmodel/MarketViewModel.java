package ir.rezvansoft.cryptowallet.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import ir.rezvansoft.cryptowallet.model.CoinPrices;
import ir.rezvansoft.cryptowallet.orm.AppDatabase;

import java.util.List;

/**
 * Created by Sreejith on 23-Nov-17.
 */

public class MarketViewModel extends AndroidViewModel {


    public LiveData<List<CoinPrices>> getAllCoinPrices() {
        return allCoinPrices;
    }

    private final LiveData<List<CoinPrices>> allCoinPrices;


    private AppDatabase appDatabase;

    public MarketViewModel(@NonNull Application application) {
        super(application);

        appDatabase = AppDatabase.getDatabase(this.getApplication());

        allCoinPrices = appDatabase.marketDao().getAllCoinPrices();


    }
}
