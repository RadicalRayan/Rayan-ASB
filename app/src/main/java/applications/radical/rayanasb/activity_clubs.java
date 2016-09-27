package applications.radical.rayanasb;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class activity_clubs extends AppCompatActivity implements AdapterView.OnItemClickListener{

    android.support.v7.app.ActionBar ab;
    private DrawerLayout drawerLayout;
    private ListView listView;
    private String[] objs;
    ListView clubList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_clubs);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        listView = (ListView) findViewById(R.id.left_drawer);
        objs = getResources().getStringArray(R.array.navOptions);

        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, objs));
        listView.setOnItemClickListener(this);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.clubs_toolbar);
        myToolbar.setTitle("STEM Clubs");
        myToolbar.setTitleTextAppearance(this, R.style.MyTitleTextAppearance);
        setSupportActionBar(myToolbar);

        ab = getSupportActionBar();
        ab.setHomeButtonEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeAsUpIndicator(R.drawable.ic_action_draw_open);

        clubList = (ListView) findViewById(R.id.listView);
        clubList.setAdapter(new ListAdapter(this));
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();
        if(res_id == 16908332) {
            drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawerLayout.openDrawer(Gravity.LEFT);
        }

        return true;
    }

    class SingleRow {
        String N;
        String P;
        String M;
        String C;
        String A;
        SingleRow (String N, String P, String M, String C, String A) {
            this.N = N;
            this.P = P;
            this.M = M;
            this.C = C;
            this.A = A;
        }
    }

    class ListAdapter extends BaseAdapter {
        ArrayList<SingleRow> list;
        Context context;
        ListAdapter(Context c) {
            context = c;
            list = new ArrayList<SingleRow>();
            Resources res = c.getResources();
            String[] Names = res.getStringArray(R.array.ClubNames);
            String[] Pres = res.getStringArray(R.array.ClubPresidents);
            String[] MeetInfo = res.getStringArray(R.array.ClubMeetings);
            String[] Contact = res.getStringArray(R.array.ClubContact);
            String[] Advisor = res.getStringArray(R.array.Advisor);

            for (int i = 0; i < Names.length; i++) {
                list.add(new SingleRow(Names[i], Pres[i], MeetInfo[i], Contact[i], Advisor[i]));
            }
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.club_template, parent, false);

            TextView one = (TextView) row.findViewById(R.id.txtOne);
            TextView two = (TextView) row.findViewById(R.id.txtTwo);
            TextView three = (TextView) row.findViewById(R.id.txtThree);
            TextView four = (TextView) row.findViewById(R.id.txtFour);
            TextView five = (TextView) row.findViewById(R.id.txtFive);

            SingleRow temp = list.get(position);

            one.setText(temp.N);
            two.setText(temp.P);
            three.setText(temp.C);
            four.setText(temp.M);
            five.setText(temp.A);

            return row;
        }
    }
}
