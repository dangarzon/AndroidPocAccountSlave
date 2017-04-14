package arko.bones.com.accountslave;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // UI references.
    private TextView mAccountView;
    private TextView mUserView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Locate References
        mAccountView = (TextView) findViewById(R.id.account_text_view);
        mUserView = (TextView) findViewById(R.id.user_text_view);

        mAccountView.setText(R.string.info_no_account);
        mUserView.setText(R.string.info_no_user);
    }

}
