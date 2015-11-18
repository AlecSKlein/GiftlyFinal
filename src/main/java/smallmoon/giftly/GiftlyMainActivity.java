package smallmoon.giftly;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;

import models.UserDAO;
import rest.RestClient;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class GiftlyMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giftly_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        RestClient.GiftlyApiInterface service = RestClient.getClient();
        UserDAO user = new UserDAO(15, 1, "test12giftly.us", "alec", "klein", "password");
        registerUser(service, user);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_giftly_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void registerUser(RestClient.GiftlyApiInterface service, UserDAO user)
    {
        final ProgressDialog dialog = ProgressDialog.show(this, "", "loading...");
        Call<UserDAO> userRegisterCall = service.registerUser(user);
        userRegisterCall.enqueue(new Callback<UserDAO>() {
            @Override
            public void onResponse(Response<UserDAO> response) {
                dialog.dismiss();
                System.out.println("Status Code = " + response.code());
                System.out.println("Dialog dismissed, success?");
                if (response.isSuccess()) {
                    System.out.println("Success");
                    UserDAO userResponse = response.body();
                    System.out.println("response = " + new Gson().toJson(userResponse));
                }
            }

            @Override
            public void onFailure(Throwable t) {
                dialog.dismiss();
                System.out.println("Dialog dismissed, failure");
                System.out.println(t.toString());
            }
        });
    }
}
