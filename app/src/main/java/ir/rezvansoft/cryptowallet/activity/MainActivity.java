package ir.rezvansoft.cryptowallet.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import ir.rezvansoft.cryptowallet.R;
import ir.rezvansoft.cryptowallet.fragment.WalletFragment;
import ir.rezvansoft.cryptowallet.manager.SharedPreferenceManager;
import ir.rezvansoft.cryptowallet.service.CurrencyChangeName;
import ir.rezvansoft.cryptowallet.service.UpdateWalletsWorthService;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {


    private static final String FRAGMENT_WALLET = "wallet_fragment";
    WalletFragment walletFragment;
    SharedPreferenceManager sharedPreferenceManager;
    private MenuItem currency;
    private Toolbar toolbar;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_wallets:

                    getSupportActionBar().setTitle("کیف پول");
                    if (getSupportFragmentManager().findFragmentByTag(FRAGMENT_WALLET) == null)
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new WalletFragment(), FRAGMENT_WALLET).commit();

                    return true;

            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferenceManager = new SharedPreferenceManager(getApplicationContext());

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new WalletFragment(), FRAGMENT_WALLET).commit();

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int density = metrics.densityDpi;


        String densityString = "cannot determine";
        if (density == DisplayMetrics.DENSITY_XXHIGH) {

            densityString = "xxhdpi";
        } else if (density == DisplayMetrics.DENSITY_XHIGH) {
            densityString = "xhdpi";

        } else if (density == DisplayMetrics.DENSITY_HIGH) {
            densityString = "hdpi";

        } else if (density == DisplayMetrics.DENSITY_MEDIUM) {

            densityString = "mdpi";
        } else if (density == DisplayMetrics.DENSITY_LOW) {

            densityString = "ldpi";
        }


        Configuration config = getResources().getConfiguration();

        Log.d("dimens", "density= " + densityString);
        Log.d("dimens", "small width= " + String.valueOf(config.smallestScreenWidthDp));
        Log.d("dimens", "width x height " + String.valueOf(metrics.widthPixels) + " x " + String.valueOf(metrics.heightPixels));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.change_currency:
                showChangeCurrencyDialog();
                return true;
        }

        return false;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);


        currency = menu.findItem(R.id.change_currency);
        currency.setTitle(CurrencyChangeName.entopersiannamechanger(sharedPreferenceManager.getDefaultCurrency()));
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setQueryHint("جستجو...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {


                walletFragment = (WalletFragment) getSupportFragmentManager().findFragmentByTag(FRAGMENT_WALLET);
                if (walletFragment != null && walletFragment.isVisible())
                    walletFragment.onSearch(newText);


                return true;
            }
        });

        return true;
    }


    void showChangeCurrencyDialog() {
        final String currencies[] = getApplicationContext().getResources().getStringArray(R.array.currencies);
        Arrays.sort(currencies);
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("واحد پول")
                .setSingleChoiceItems(currencies, Arrays.binarySearch(currencies, sharedPreferenceManager.getDefaultCurrency()), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String nameofcurrency= CurrencyChangeName.entopersiannamechanger(currencies[i]);
                        sharedPreferenceManager.setDefaultCurrency(currencies[i]);
                        currency.setTitle(CurrencyChangeName.entopersiannamechanger(sharedPreferenceManager.getDefaultCurrency()));
                        dialogInterface.dismiss();
                        Intent intent = new Intent(MainActivity.this, UpdateWalletsWorthService.class);
                        startService(intent);

                    }
                });

        builder.setNegativeButton("انصراف", null)
                .create().show();
    }


}
