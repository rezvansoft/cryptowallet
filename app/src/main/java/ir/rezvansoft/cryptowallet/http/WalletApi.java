package ir.rezvansoft.cryptowallet.http;

import ir.rezvansoft.cryptowallet.model.BCHAddressBalance;
import ir.rezvansoft.cryptowallet.model.CypherAddressBalance;
import ir.rezvansoft.cryptowallet.model.RippleBalance;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Sreejith on 24-Nov-17.
 */

public interface WalletApi {

    @GET
    Call<CypherAddressBalance> getCypherAddressBalance(@Url String url);

    @GET
    Call<BCHAddressBalance> getBCHAddressBalance(@Url String url);

    @GET
    Call<RippleBalance> getRippleBalance(@Url String url);


}
