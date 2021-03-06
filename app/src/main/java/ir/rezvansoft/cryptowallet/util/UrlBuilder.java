package ir.rezvansoft.cryptowallet.util;

import android.util.Log;

import java.util.Map;

/**
 * Created by Sreejith on 18-Feb-18.
 */

public class UrlBuilder {

    public static String buildCoinList()
    {
        StringBuffer coinList=new StringBuffer();
        for (Map.Entry<String,String> entry:Coin.getCoinsData().entrySet()) {
            coinList.append(entry.getKey()+",");
        }
        coinList.setLength(coinList.length()-1);
        Log.d("url", coinList.toString());
        return coinList.toString();
    }

    public static String buildCurrencyList(String currencyArray[])
    {
        Log.d("url", "buildCurrencyList: "+currencyArray.length);
        StringBuffer currencyList=new StringBuffer();
        for (String currency:currencyArray) {
            currencyList.append(currency+",");
        }
        currencyList.setLength(currencyList.length()-1);
        Log.d("url", currencyList.toString());
        return currencyList.toString();
    }
}
