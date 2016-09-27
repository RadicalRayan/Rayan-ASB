package applications.radical.rayanasb;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.format.Time;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    android.support.v7.app.ActionBar ab;
    private DrawerLayout drawerLayout;
    private ListView listView;
    private String[] objs;
    CheckBox checkOther;
    EditText txtOther;
    TextView txtNumOccur;
    TextView txtSeverityVal;
    SeekBar scrlNum;
    SeekBar scrlSev;
    Button btnSubmit;
    EditText txtFirst;
    CheckBox one;
    CheckBox two;
    CheckBox three;
    CheckBox targetted;
    EditText name;
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        if (!checkFirstTime(getApplicationContext(), "First Open.txt")) {
            dialogMaker();
            try {
                FileOutputStream fos = openFileOutput("First Open.txt", MODE_PRIVATE);
                String passed = "pass";
                fos.write(passed.getBytes());
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        listView = (ListView) findViewById(R.id.left_drawer);
        objs = getResources().getStringArray(R.array.navOptions);

        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, objs));
        listView.setOnItemClickListener(this);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.clubs_toolbar);
        myToolbar.setTitle("Report Bullying");
        myToolbar.setTitleTextAppearance(this, R.style.MyTitleTextAppearance);
        setSupportActionBar(myToolbar);

        ab = getSupportActionBar();
        ab.setHomeButtonEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeAsUpIndicator(R.drawable.ic_action_draw_open);

        checkOther = (CheckBox) findViewById(R.id.checkOther);
        txtOther = (EditText) findViewById(R.id.editTxtOther);
        txtNumOccur = (TextView) findViewById(R.id.txtNumber);
        txtSeverityVal = (TextView) findViewById(R.id.txtSeverity);
        scrlNum = (SeekBar) findViewById(R.id.scrlNum);
        scrlSev = (SeekBar) findViewById(R.id.scrlSeverity);
        btnSubmit = (Button) findViewById(R.id.btnSub);
        txtFirst = (EditText) findViewById(R.id.txtFirst);
        one = (CheckBox) findViewById(R.id.chck1);
        two = (CheckBox) findViewById(R.id.chck2);
        three = (CheckBox) findViewById(R.id.chck3);
        targetted = (CheckBox) findViewById(R.id.checkBox);
        name = (EditText) findViewById(R.id.txtName);
        email = (EditText) findViewById(R.id.txtEmail);

        if (isNetworkConnected()) {
            btnSubmit.setEnabled(true);
            btnSubmit.setText("Submit Report");
        } else {
            btnSubmit.setEnabled(false);
            btnSubmit.setText("Please connect to Wi-Fi before submitting");
        }

        /*
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtFirst.getText().toString().length() > 50) {
                    if (!one.isChecked() && !two.isChecked() && !three.isChecked() && !checkOther.isChecked()) {
                        Toast.makeText(getApplicationContext(), "Please enter where the event occurred.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Your Report has been Submitted", Toast.LENGTH_SHORT).show();
                        BackgroundWorker bgkwork = new BackgroundWorker(this);
                        bgkwork.execute()
                        txtFirst.setText("");
                        one.setChecked(false);
                        two.setChecked(false);
                        three.setChecked(false);
                        checkOther.setChecked(false);
                        scrlSev.setProgress(0);
                        scrlNum.setProgress(0);
                        txtOther.setText("");
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter more details at the top.", Toast.LENGTH_SHORT).show();
                }
            }
        });
*/
        checkOther.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    txtOther.setVisibility(View.VISIBLE);
                } else if (!isChecked) {
                    txtOther.setVisibility(View.INVISIBLE);
                }
            }
        });

        scrlNum.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float floatingProgress = (float) scrlNum.getProgress();
                float dividing = (float) 100;
                float multiplying = (float) 5;
                float percentaging = floatingProgress / dividing;
                float floatingPictures = percentaging * multiplying;
                int pictures = Math.round(floatingPictures);
                int numOccur = pictures + 1;
                if (numOccur != 6) {
                    String newText = Integer.toString(numOccur);
                    txtNumOccur.setText(newText);
                } else {
                    txtNumOccur.setText("More than 5");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        scrlSev.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float floatingProgress = (float) scrlSev.getProgress();
                float dividing = (float) 100;
                float multiplying = (float) 5;
                float percentaging = floatingProgress / dividing;
                float floatingPictures = percentaging * multiplying;
                int pictures = Math.round(floatingPictures);

                if (pictures == 0) {
                    txtSeverityVal.setText("Annoying");
                } else if (pictures == 1) {
                    txtSeverityVal.setText("Cuts and Bruises");
                } else if (pictures == 2) {
                    txtSeverityVal.setText("Verbal Abuse");
                } else if (pictures == 3) {
                    txtSeverityVal.setText("Broken Bones");
                } else if (pictures == 4) {
                    txtSeverityVal.setText("Emotional and Physical Pain");
                } else if (pictures == 5) {
                    txtSeverityVal.setText("Extreme");
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();
        if (res_id == 16908332) {
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

    public boolean checkFirstTime(Context c, String fileName) {
        File file = c.getFileStreamPath(fileName);
        return file.exists();
    }

    public void dialogMaker() {
        final String[] labels = new String[]{"0", "1", "2", "3", "4", "5", "More than 6"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, labels);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("STEM Student Check: How many graduating classes has STEM had?");
        builder.setCancelable(false);
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 1) {
                    Toast.makeText(getApplicationContext(), "Correct, continue to the app!", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Incorrent try again later.", Toast.LENGTH_LONG).show();
                    //android.os.SystemClock.sleep(5000); //Millisecs
                    android.os.Process.killProcess(android.os.Process.myPid());
                }
            }
        });

        final AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void btnPressed(View v) {
        if (txtFirst.getText().toString().length() > 50) {
            if (!one.isChecked() && !two.isChecked() && !three.isChecked() && !checkOther.isChecked()) {
                Toast.makeText(getApplicationContext(), "Please enter where the event occurred.", Toast.LENGTH_SHORT).show();
            } else {
                String type = "bullyReport";
                BackgroundWorker bgkwork = new BackgroundWorker(this);
                String target = "No";
                if (targetted.isChecked()) {
                    target = "Yes";
                }
                String targetString = target;
                String location = "";
                if (one.isChecked()) {
                    location = location + " " + one.getText();
                }
                if (two.isChecked()) {
                    location = location + " " + two.getText();
                }
                if (three.isChecked()) {
                    location = location + " " + three.getText();
                }
                if (checkOther.isChecked()) {
                    location = location + " " + txtOther.getText().toString();
                }
                String number = txtNumOccur.getText().toString();
                String sev = txtSeverityVal.getText().toString();
                String nombre = "";
                String correo = "";
                if (name.getText().toString().equals("")) {
                    nombre = "Not Available";
                } else {
                    nombre = name.getText().toString();
                }
                if (email.getText().toString().equals("")) {
                    correo = "Not Available";
                } else {
                    correo = email.getText().toString();
                }
                Time today = new Time(Time.getCurrentTimezone());
                String time = today.format("%k:%M:%S");
                String date = today.format(today.monthDay + "-" + today.month + "-" + today.year);
                String dateTime = date + " " + time;
                bgkwork.execute(type, txtFirst.getText().toString(), targetString, location, number,  sev, nombre, correo, dateTime);
                txtFirst.setText("");
                one.setChecked(false);
                two.setChecked(false);
                three.setChecked(false);
                checkOther.setChecked(false);
                scrlSev.setProgress(0);
                scrlNum.setProgress(0);
                txtOther.setText("");
                targetted.setChecked(false);
            }
        } else {
            Toast.makeText(getApplicationContext(), "Please enter more details at the top.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
}