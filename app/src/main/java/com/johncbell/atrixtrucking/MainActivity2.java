package com.johncbell.atrixtrucking;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

//Class for our main activity with OnClickListener
public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    //Declaring views
    private WebView webView;
    private EditText trucksID;
    private EditText tripReportNumber;
    private EditText enteredDate;
    private EditText emptyMilage;
    private EditText loadedMilage;
    private Button buttonRegister;
    private View btnclear;
    private String estTotal;
    private TextView estMilage;


    //This is our root url
    public static final String ROOT_URL = "http://www.paragonsofjustice.org/php/";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    Spinner spinner;
    String[] SPINNERVALUES = {"None Selected","MCIO","NYCN","NYCN","NYCN","NYCN","NYCN","NYCN"};

    Spinner spinner2;
    String[] SPINNERVALUES2 = {"None Selected","MCIO","NYCN","NYCN","NYCN","NYCN","NYCN","NYCN"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String spiller = null;

        spinner =(Spinner)findViewById(R.id.startLocation);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity2.this, android.R.layout.simple_list_item_1, SPINNERVALUES);
        spinner.setAdapter(adapter);

        spinner2 =(Spinner)findViewById(R.id.emptyLocation);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(MainActivity2.this, android.R.layout.simple_list_item_1, SPINNERVALUES2);
        spinner2.setAdapter(adapter);

        //Trying to get Spinner 1 and 2 Values
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {}

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });


        //Initialize Calendar View



        //Initializing Views
        trucksID = (EditText) findViewById(R.id.trucksID);
        tripReportNumber = (EditText) findViewById(R.id.tripReportNumber);
        enteredDate = (EditText) findViewById(R.id.enteredDate);
        emptyMilage = (EditText) findViewById(R.id.emptyMilage);
        loadedMilage = (EditText) findViewById(R.id.loadedMilage);
        estMilage = (TextView) findViewById(R.id.estMilage);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);


        //Reset Button for Form
        Button reset= (Button) findViewById(R.id.btnclear);

        assert reset != null;
        reset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                trucksID.setText("");
                tripReportNumber.setText("");
                enteredDate.setText("");
                emptyMilage.setText("");
                loadedMilage.setText("");
                estMilage.setText("");

            }
        });

        //Adding listener to button
        buttonRegister.setOnClickListener(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.openWebsite:
                Intent website = new Intent(this, ourWebsite.class);
                this.startActivity(website);
                return true;

            case R.id.goto2:
                Intent activity2 = new Intent(this, MainActivity2.class);
                this.startActivity(activity2);
                return true;

            case R.id.about_us:
                Intent about = new Intent(this, aboutUs.class);
                this.startActivity(about);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void insertUser() {
        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        RegisterAPI api = adapter.create(RegisterAPI.class);

        //Defining the method insertuser of our interface

        int value1 = Integer.parseInt(emptyMilage.getText().toString());
        int value2 = Integer.parseInt(loadedMilage.getText().toString());
        int estMilage = value2 + value1;

        api.insertUser(
                //Passing the values by getting it from editTexts
                trucksID.getText().toString(),
                tripReportNumber.getText().toString(),
                enteredDate.getText().toString(),
                emptyMilage.getText().toString(),
                loadedMilage.getText().toString(),
                //   String emptyLocation = spinner.getSelectedItem().toString(),

                //can take this out below
                Integer.toString(estMilage),


                //Creating an anonymous callback
                new Callback<Response>() {
                    @Override
                    public void success(Response result, Response response) {
                        //On success we will read the server's output using bufferedreader
                        //Creating a bufferedreader object
                        BufferedReader reader = null;

                        //An string to store output from the server
                        String output = "";

                        try {
                            //Initializing buffered reader
                            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));

                            //Reading the output in the string
                            output = reader.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        //Displaying the output as a toast
                        Toast.makeText(MainActivity2.this, output, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        //If any error occured displaying the error as toast
                        Toast.makeText(MainActivity2.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    //Overriding onclick method
    @Override
    public void onClick(View v) {
        //Calling insertUser on button click
        insertUser();

    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.johncbell.atrixtrucking/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.johncbell.atrixtrucking/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
