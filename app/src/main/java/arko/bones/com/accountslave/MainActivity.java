package arko.bones.com.accountslave;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private AccountManager accountManager;

    // UI references.
    private TextView mAccountView;
    private TextView mUserView;
    private TextView mTokenView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Store AccountManager
        accountManager = AccountManager.get(this);

        // Locate References
        mAccountView = (TextView) findViewById(R.id.account_text_view);
        mUserView = (TextView) findViewById(R.id.user_text_view);
        mTokenView = (TextView) findViewById(R.id.token_text_view);

        showAccountInfo();

        // Bind token no button to action
        Button mRefreshButton = (Button) findViewById(R.id.button);
        mRefreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAccountView.setText("");
                mUserView.setText("");
                mTokenView.setText("");

                showAccountInfo();
            }
        });
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
            mTokenView.setText(accountManager.getUserData(account, "token"));
        } else {
            mAccountView.setText(R.string.info_no_account);
            mUserView.setText(R.string.info_no_user);
            mTokenView.setText(R.string.info_no_token);
        }
    }

}
