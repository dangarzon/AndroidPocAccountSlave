package arko.bones.com.accountslave;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private AccountManager accountManager;

    // UI references.
    private TextView mAccountView;
    private TextView mUserView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Store AccountManager
        accountManager = AccountManager.get(this);

        // Locate References
        mAccountView = (TextView) findViewById(R.id.account_text_view);
        mUserView = (TextView) findViewById(R.id.user_text_view);

        showAccountInfo();
    }

    /**
     * Get Usser Account
     *
     * @return user account
     */
    private Account getAccount() {
        Account[] accounts = accountManager.getAccountsByType(getString(R.string.account_type));

        Account account = null;
        for (Account item: accounts) {
            if (item.name.equals(getString(R.string.account_name))) {
                account = item;
            }
        }

        return account;
    }

    /**
     * Show Account Info
     */
    private void showAccountInfo() {
        Account account = getAccount();

        if (null != account) {
            mAccountView.setText(R.string.account_name);

            String info = accountManager.getUserData(account, "user")
                + ":"
                + accountManager.getUserData(account, "pass");

            mUserView.setText(info);
        } else {
            mAccountView.setText(R.string.info_no_account);
            mUserView.setText(R.string.info_no_user);
        }
    }

}
