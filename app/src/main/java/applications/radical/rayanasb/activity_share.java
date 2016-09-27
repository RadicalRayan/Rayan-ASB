package applications.radical.rayanasb;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class activity_share extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private DrawerLayout drawerLayout;
    private ListView listView;
    private String[] objs;
    android.support.v7.app.ActionBar ab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_share);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        listView = (ListView) findViewById(R.id.left_drawer);
        objs = getResources().getStringArray(R.array.navOptions);

        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, objs));
        listView.setOnItemClickListener(this);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.clubs_toolbar);
        myToolbar.setTitle("Share");
        myToolbar.setTitleTextAppearance(this, R.style.MyTitleTextAppearance);
        setSupportActionBar(myToolbar);

        ab = getSupportActionBar();
        ab.setHomeButtonEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeAsUpIndicator(R.drawable.ic_action_draw_open);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();
        if(res_id == 16908332) {
            drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawerLayout.openDrawer(Gravity.LEFT);
        }

        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 3) {
            Intent transfer = new Intent(this, activity_vote_rayan.class);
            startActivity(transfer);
        } else if (position == 0) {
            Intent transfer = new Intent(this, MainActivity.class);
            startActivity(transfer);
        } else if (position == 1) {
            Intent transfer = new Intent(this, activity_ASB_advice.class);
            startActivity(transfer);
        } else if (position == 2) {
            Intent transfer = new Intent(this, activity_clubs.class);
            startActivity(transfer);
        } else if (position == 4) {
            Intent transfer = new Intent(this, activity_share.class);
            startActivity(transfer);
        }
    }
}
