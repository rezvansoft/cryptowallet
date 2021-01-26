package ir.rezvansoft.cryptowallet.util;

import ir.rezvansoft.cryptowallet.model.Balance;

/**
 * Created by Sreejith on 24-Nov-17.
 */

public interface BalanceInterface {

    public Balance getWalletBalance(String coinCode);
}
