package smallmoon.giftly;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import models.FriendDAO;
import models.FriendListDAO;
import models.GiftDAO;
import models.GiftListDAO;
import models.InterestDAO;
import models.InterestListDAO;
import models.UserDAO;
import modules.MobileDatabaseHandler;

public class GiftlyMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giftly_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MobileDatabaseHandler db = new MobileDatabaseHandler(this, null, null, 1);
        db.addUser(new UserDAO(1, 1, "Alec@gmail.com", "Alec", "Klein", "password"));
        db.addFriend(new FriendDAO(1, 1, "Alec Klein", "05/29/1993", 1));
        db.addFriend(new FriendDAO(2, 1, "Alec Klein2", "06/29/1993", 1));
        db.addFriend(new FriendDAO(3, 1, "Alec Klein3", "07/29/1993", 1));
        db.addGift(new GiftDAO("test", 2, "test"));
        db.addGift(new GiftDAO("also test", 3, "test2"));
        db.addInterest(new InterestDAO("Balls", 2));
        db.addInterest(new InterestDAO("Ding Dongs", 3));
        UserDAO test = db.getUser("Alec@gmail.com");
        FriendListDAO test2 = db.getFriends(1);
        InterestListDAO test3 = db.getInterests(2);
        GiftListDAO test4 = db.getGifts(3);
        for(FriendDAO friend : test2.getFriendList()) {
            System.out.println(friend.getName());
        }
        for(InterestDAO interest : test3.getInterestList()){
            System.out.println(interest.getInterestName());
        }
        for(GiftDAO gift : test4.getGiftList()){
            System.out.println(gift.getAsin());
        }
        System.out.println("Email = " + test.getEmail());
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
}
