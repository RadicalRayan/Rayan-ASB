package applications.radical.rayanasb;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Rayan on 4/22/2016.
 */
public class BackgroundWorker extends AsyncTask<String, Void, String> {
    Context context;
    AlertDialog alertDialog;

    BackgroundWorker (Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String add_bully_url = "http://ec2-52-91-206-35.compute-1.amazonaws.com/register.php";
        if (type.equals("bullyReport")) {
            String description = params[1];
            String targetted = params[2];
            String location = params[3];
            String number = params[4];
            String severity = params[5];
            String name = params[6];
            String email = params[7];
            String dateTimeString = params[8];
            try {
                URL url = new URL(add_bully_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter buffWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("description", "UTF-8") + "=" + URLEncoder.encode(description, "UTF-8") + "&"
                        + URLEncoder.encode("targetted", "UTF-8") + "=" + URLEncoder.encode(targetted, "UTF-8") + "&"
                        + URLEncoder.encode("location", "UTF-8") + "=" + URLEncoder.encode(location, "UTF-8") + "&"
                        + URLEncoder.encode("number", "UTF-8") + "=" + URLEncoder.encode(number, "UTF-8") + "&"
                        + URLEncoder.encode("severity", "UTF-8") + "=" + URLEncoder.encode(severity, "UTF-8") + "&"
                        + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&"
                        + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&"
                        + URLEncoder.encode("dateTimeString", "UTF-8") + "=" + URLEncoder.encode("This is a test", "UTF-8");
                buffWriter.write(post_data);
                buffWriter.flush();
                buffWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader buffReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = buffReader.readLine()) != null) {
                    result += line;
                }
                buffReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (type.equals("asbAdvice")) {
            String description = params[1];
            String targetted = params[2];
            String location = params[3];
            String number = params[4];
            String severity = params[5];
            String name = params[6];
            String email = params[7];
            String dateTimeString = params[8];
            try {
                URL url = new URL(add_bully_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter buffWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("description", "UTF-8") + "=" + URLEncoder.encode(description, "UTF-8") + "&"
                        + URLEncoder.encode("targetted", "UTF-8") + "=" + URLEncoder.encode(targetted, "UTF-8") + "&"
                        + URLEncoder.encode("location", "UTF-8") + "=" + URLEncoder.encode(location, "UTF-8") + "&"
                        + URLEncoder.encode("number", "UTF-8") + "=" + URLEncoder.encode(number, "UTF-8") + "&"
                        + URLEncoder.encode("severity", "UTF-8") + "=" + URLEncoder.encode(severity, "UTF-8") + "&"
                        + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&"
                        + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&"
                        + URLEncoder.encode("dateTimeString", "UTF-8") + "=" + URLEncoder.encode("This is a test", "UTF-8");
                buffWriter.write(post_data);
                buffWriter.flush();
                buffWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader buffReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = buffReader.readLine()) != null) {
                    result += line;
                }
                buffReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Report Status");
    }

    @Override
    protected void onPostExecute(String aResult) {
        alertDialog.setMessage(aResult);
        alertDialog.show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
