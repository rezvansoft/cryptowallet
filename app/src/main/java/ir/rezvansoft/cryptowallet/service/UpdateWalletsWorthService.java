package ir.rezvansoft.cryptowallet.service;

import android.content.Intent;
import android.util.Log;

import ir.rezvansoft.cryptowallet.manager.SharedPreferenceManager;
import ir.rezvansoft.cryptowallet.model.Wallet;

import ir.rezvansoft.cryptowallet.util.Coin;

import java.util.List;


public class UpdateWalletsWorthService extends BaseService {

    private SharedPreferenceManager helper;
    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            helper=new SharedPreferenceManager(this);
            Log.d("wallet", "UpdateWalletsWorthService ");
            initializeDB();
            String currencyCode=helper.getDefaultCurrency();
            Boolean startNotificationService=intent.getBooleanExtra(EXTRA_SHOULD_START_NOTIFICATION,false);
            if(currencyCode!=null)
            {
                updateCoinsWorth(currencyCode);

                if(startNotificationService)
                {
                    startService(new Intent(this,NotificationService.class));
                }
            }
        }
    }


    void updateCoinsWorth(String currencyCode)
    {
        List<Wallet> wallets=getAllWallets();

        if (wallets!=null)
        {
            for (Wallet wallet:wallets) {
                wallet.setCoinWorth(Coin.calculateCoinWorth(wallet.getBalance().getCoinBalance(),getCoinRateFromDB(wallet.getCoinCode(), currencyCode),currencyCode));
            }

            updateWalletsDB(wallets);
        }
        else {
            Log.d("wallets", "updateCoinsWorth: wallets null");
        }
    }

}
