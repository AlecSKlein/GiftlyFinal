package smallmoon.giftly;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.util.ArrayList;

import models.FriendDAO;
import models.FriendListDAO;
import models.UserDAO;
import modules.MobileDatabaseHandler;
import rest.RestClient;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class GiftlyMainActivity extends AppCompatActivity {

    ArrayList<UserDAO> userList;
    MobileDatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giftly_main);
        System.out.println("test");
        dbHandler = new MobileDatabaseHandler(this,"giftly",null,1);
        final RestClient.GiftlyApiInterface service = RestClient.getClient();
        //UserDAO userFull = new UserDAO(0, 1, "Alec@giftly.us", "Alec", "Klein", "password");
        //registerUser(service, userFull);
        //getUser(service, userFull);
        final UserDAO user = dbHandler.getUser("Alec@giftly.us");
        final FriendDAO friend = new FriendDAO(0, 0, "Frank", "05/29/1993");
        Button button = (Button) findViewById(R.id.button);
        final int counter = 0;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(user.getEmail());
                System.out.println(user.getFname());
                System.out.println(user.getLname());
                System.out.println(user.getUserid());
                addFriend(service, user, friend);

                //getAllFriends(service, user);
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

    public void registerUser(RestClient.GiftlyApiInterface service, UserDAO user) {
        final ProgressDialog dialog = ProgressDialog.show(this, "", "loading...");
        Call<UserDAO> userRegisterCall = service.registerUser(user);
        System.out.println("Regtest");
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
                    dbHandler.addUser(userResponse);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                dialog.dismiss();
                System.out.println("Dialog dismissed, failure");
                System.out.println(t.toString());
            }
        });
        System.out.println("Regtest2");
    }

    public void loginUser(RestClient.GiftlyApiInterface service, UserDAO user) {
        final ProgressDialog dialog = ProgressDialog.show(this, "", "loading...");
        Call<UserDAO> userLoginCall = service.loginUser(user);
        userLoginCall.enqueue(new Callback<UserDAO>() {
            @Override
            public void onResponse(Response<UserDAO> response) {
                dialog.dismiss();
                System.out.println("Status Code = " + response.code());
                System.out.println("Dialog dismissed, success?");
                if (response.isSuccess()) {
                    System.out.println("Success");
                    UserDAO user = response.body();
                    System.out.println("response = " + new Gson().toJson(user));
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

    public void getUser(RestClient.GiftlyApiInterface service, UserDAO user){
        final ProgressDialog dialog = ProgressDialog.show(this, "", "loading...");
        Call<UserDAO> getUserCall = service.getUser(user);
        getUserCall.enqueue(new Callback<UserDAO>() {
            @Override
            public void onResponse(Response<UserDAO> response) {

                System.out.println("Status Code = " + response.code());
                System.out.println("Dialog dismissed, success?");
                if (response.isSuccess()) {
                    System.out.println("Success");
                    UserDAO userResponse = response.body();
                    System.out.println("response = " + new Gson().toJson(userResponse));
                    dbHandler.getUser(userResponse.getEmail());
                }
                dialog.dismiss();
            }

            @Override
            public void onFailure(Throwable t) {
                dialog.dismiss();
                System.out.println("Dialog dismissed, failure");
                System.out.println(t.toString());
            }
        });
    }

    public void addFriend(RestClient.GiftlyApiInterface service, UserDAO user, FriendDAO friend) {
        final ProgressDialog dialog = ProgressDialog.show(this, "", "loading...");
        Call<FriendDAO> addFriendCall = service.addFriend(user, friend);
        System.out.println("test add friend");
        addFriendCall.enqueue(new Callback<FriendDAO>() {

            @Override
            public void onResponse(Response<FriendDAO> response) {
                dialog.dismiss();
                System.out.println("Status Code = " + response.code());
                System.out.println("Dialog dismissed, success?");
                if (response.isSuccess()) {
                    System.out.println("Success");
                    FriendDAO addFriendResponse = response.body();
                    System.out.println("response = " + new Gson().toJson(addFriendResponse));
                    dbHandler.addFriend(addFriendResponse);
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

    public void getAllFriends(RestClient.GiftlyApiInterface service, UserDAO user){
        final ProgressDialog dialog = ProgressDialog.show(this, "", "loading...");
        System.out.println(user.getUserid());
        Call<FriendListDAO> userGetAllFriendsCall = service.getAllFriends(user);
        userGetAllFriendsCall.enqueue(new Callback<FriendListDAO>() {
            @Override
            public void onResponse(Response<FriendListDAO> response) {
                dialog.dismiss();
                System.out.println("Status Code = " + response.code());
                System.out.println("Dialog dismissed, success?");
                if (response.isSuccess()) {
                    System.out.println("Success");
                    FriendListDAO allFriendsResponse = response.body();
                    System.out.println("response = " + new Gson().toJson(allFriendsResponse));
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
