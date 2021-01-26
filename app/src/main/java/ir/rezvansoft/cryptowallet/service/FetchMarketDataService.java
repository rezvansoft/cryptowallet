package ir.rezvansoft.cryptowallet.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import ir.rezvansoft.cryptowallet.R;
import ir.rezvansoft.cryptowallet.http.ApiClient;
import ir.rezvansoft.cryptowallet.http.MarketApi;
import ir.rezvansoft.cryptowallet.model.CoinPrices;
import ir.rezvansoft.cryptowallet.orm.AppDatabase;
import ir.rezvansoft.cryptowallet.orm.MarketDao;
import ir.rezvansoft.cryptowallet.util.UrlBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;


public class FetchMarketDataService extends IntentService {


    public FetchMarketDataService() {
        super("FetchMarketDataService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            Log.d("wallet", "fetch: ");
            updateDB(fetchDataFromServer());
            Log.d("wallet", "fetch: ignore="+intent.getBooleanExtra(BaseService.EXTRA_SHOULD_IGNORE_WALLET_REFRESH,false));

            if(!intent.getBooleanExtra(BaseService.EXTRA_SHOULD_IGNORE_WALLET_REFRESH,false))
            {
                Intent serviceIntent=new Intent(this,RefreshWalletService.class);
                startService(serviceIntent);
            }
            else
            {
                startService(new Intent(this,UpdateWalletsWorthService.class));
            }

        }
    }

    LinkedHashMap<String, HashMap<String, Double>>  fetchDataFromServer()
    {
        Retrofit retrofit= ApiClient.getInstance().getMarketClient();
        MarketApi marketApi=retrofit.create(MarketApi.class);

        Call<LinkedHashMap<String,HashMap<String,Double> >> call=marketApi.getAllCoinPrices(UrlBuilder.buildCoinList(),
                UrlBuilder.buildCurrencyList(getApplicationContext().getResources().getStringArray(R.array.currencies)));
        try {

            Response<LinkedHashMap<String, HashMap<String, Double>>> response=call.execute();
            if (response.isSuccessful())
            {
                return response.body();
            }
            else {

                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    void updateDB(LinkedHashMap<String, HashMap<String, Double>> prices)
    {
        if(prices==null) return;
        List<CoinPrices> coinPricesList=new ArrayList<>();
        for(Map.Entry<String, HashMap<String, Double>> entry : prices.entrySet()) {
            coinPricesList.add(new CoinPrices(entry.getKey(),entry.getValue()));

        }
        MarketDao dao= AppDatabase.getDatabase(getApplicationContext()).marketDao();
        dao.addCoinPrices(coinPricesList);



    }




}
