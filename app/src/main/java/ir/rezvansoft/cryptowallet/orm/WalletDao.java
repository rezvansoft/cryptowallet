package ir.rezvansoft.cryptowallet.orm;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import ir.rezvansoft.cryptowallet.model.Wallet;

import java.util.List;

/**
 * Created by Sreejith on 24-Nov-17.
 */
@Dao
public interface WalletDao {

    @Query("Select * from Wallet")
    LiveData<List<Wallet>> getAllWalletsLive();

    @Query("Select * from Wallet")
    List<Wallet> getAllWallets();

    @Insert
    long addNewWallet(Wallet wallets);

    @Query("Select * from Wallet where displayName=:displayName COLLATE NOCASE")
    Wallet checkIfNameDuplicate(String displayName);

    @Query("Select * from Wallet where walletAddress=:walletAddress")
    Wallet checkIfAddressDuplicate(String walletAddress);

    @Update
    int updateWallets(List<Wallet> wallets);

    @Delete
    int deleteWallet(Wallet wallet);

    @Query("Select * from Wallet where displayName=:displayName")
    Wallet getWalletByName(String displayName);

}
