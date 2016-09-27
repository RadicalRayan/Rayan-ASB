package applications.radical.rayanasb;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class activity_ASB_advice extends AppCompatActivity implements AdapterView.OnItemClickListener{

    android.support.v7.app.ActionBar ab;

    EditText txtFirst;
    CheckBox one;
    CheckBox two;
    CheckBox three;
    CheckBox four;
    CheckBox five;
    CheckBox six;
    CheckBox seven;
    CheckBox eight;
    CheckBox nine;
    EditText other1;
    EditText other2;
    Button but;

    private DrawerLayout drawerLayout;
    private ListView listView;
    private String[] objs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity__asb_advice);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        listView = (ListView) findViewById(R.id.left_drawer);
        objs = getResources().getStringArray(R.array.navOptions);

        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, objs));
        listView.setOnItemClickListener(this);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.asb_advice_toolbar);
        myToolbar.setTitle("ASB Suggestions");
        myToolbar.setTitleTextAppearance(this, R.style.MyTitleTextAppearance);
        setSupportActionBar(myToolbar);

        ab = getSupportActionBar();
        ab.setHomeButtonEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeAsUpIndicator(R.drawable.ic_action_draw_open);

        txtFirst = (EditText) findViewById(R.id.txtFirst);
        one = (CheckBox) findViewById(R.id.ck1);
        two = (CheckBox) findViewById(R.id.ck2);
        three = (CheckBox) findViewById(R.id.ck3);
        four = (CheckBox) findViewById(R.id.ck4);
        five = (CheckBox) findViewById(R.id.ck5);
        six = (CheckBox) findViewById(R.id.ck6);
        seven = (CheckBox) findViewById(R.id.ck7);
        eight = (CheckBox) findViewById(R.id.ck8);
        nine = (CheckBox) findViewById(R.id.ck9);
        other1 = (EditText) findViewById(R.id.txtIOther1);
        other2 = (EditText) findViewById(R.id.txtOther2);
        but = (Button) findViewById(R.id.btnSub);

        four.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    other1.setVisibility(View.VISIBLE);
                } else if (!isChecked) {
                    other1.setVisibility(View.INVISIBLE);
                }
            }
        });

        nine.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    other2.setVisibility(View.VISIBLE);
                } else if (!isChecked) {
                    other2.setVisibility(View.INVISIBLE);
                }
            }
        });

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtFirst.getText().toString().length() > 50) {
                    if (!one.isChecked() && !two.isChecked() && !three.isChecked() && !four.isChecked()) {
                        Toast.makeText(getApplicationContext(), "Please enter what your advice regards.", Toast.LENGTH_SHORT).show();
                    } else {
                        if (!five.isChecked() && !six.isChecked() && !seven.isChecked() && !seven.isChecked() && !eight.isChecked() && !nine.isChecked()) {
                            Toast.makeText(getApplicationContext(), "Please enter who needs your advice.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Thanks for your advice!", Toast.LENGTH_SHORT).show();
                            /*
                            String type = "asbAdvice";
                            BackgroundWorker bkgwork = new BackgroundWorker(this);
                            String advice = txtFirst.getText().toString();
                            String regarding = "";
                            if (one.isChecked()) {regarding = one.getText().toString();}
                            if (two.isChecked()) {regarding = regarding + " " + two.getText();}
                            if (three.isChecked()) {regarding = regarding + " " + three.getText();}
                            if (four.isChecked()) { regarding = regarding + " " + four.getText();}

                            String directed = "";
                            if (five.isChecked()) {directed = one.getText().toString();}
                            if (six.isChecked()) {directed = directed + " " + six.getText();}
                            if (seven.isChecked()) {directed = directed + " " + seven.getText();}
                            if (eight.isChecked()) {directed = directed + " " + eight.getText();}
                            if (nine.isChecked()) {directed = directed + " " + nine.getText();}

                            String nombre = "";
                            String correo = "";
                            if (other1.getText().toString().equals("")) {
                                nombre = "Not Available";
                            } else {
                                nombre = other1.getText().toString();
                            }
                            if (other2.getText().toString().equals("")) {
                                correo = "Not Available";
                            } else {
                                correo = other2.getText().toString();
                            }
                            */

                            txtFirst.setText("");
                            one.setChecked(false);
                            two.setChecked(false);
                            three.setChecked(false);
                            four.setChecked(false);
                            five.setChecked(false);
                            six.setChecked(false);
                            seven.setChecked(false);
                            eight.setChecked(false);
                            nine.setChecked(false);
                            other1.setText("");
                            other2.setText("");
                        }
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter more details at the top.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
/*
    @Override
    public boolean OnCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.basic_toolbar, menu);
        return true;
    }
*/
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
