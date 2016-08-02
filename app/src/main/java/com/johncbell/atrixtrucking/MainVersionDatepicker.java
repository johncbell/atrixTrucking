package com.johncbell.atrixtrucking;

import android.app.DatePickerDialog;
import android.app.Dialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by johncbell on 7/27/2016.
 */

//Class for our main activity with OnClickListener
public class MainVersionDatepicker extends AppCompatActivity implements View.OnClickListener {

    //Declaring views
    private WebView webView;
    private EditText trucksID;
    private EditText tripReportNumber;
    private String enteredDate;
    private EditText emptyMilage;
    private EditText loadedMilage;
    public Button buttonRegister;
    private TextView estMilage;
    private String estTotal;

    //Var's to populate spinner options selections to pass to DB
    private String startState;
    private String startTerminal;
    private String endState;
    private String endTerminal;

    private GoogleApiClient client;
    private View btnclear;

    //Calendar Values:
    Button btn;
    int year_x,month_x,day_x;
    static final int DIALOG_ID=0;

    //This is our root url
    public static final String ROOT_URL = "http://www.atrixtrucking.com/wp-content/php/";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    Spinner s1,s2,s3,s4;

    String[] SPINNERVALUES2 = {"None Selected","MCIO","NYCN","NYCN","NYCN","NYCN","NYCN","NYCN"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_versiondatepicker);

        //Declaring Spinners
        s1 =(Spinner)findViewById(R.id.startState);
        s3 =(Spinner)findViewById(R.id.startTerminal);

        s2 =(Spinner)findViewById(R.id.endState);
        s4 =(Spinner)findViewById(R.id.endTerminal);

        //Declaring Adapters for Start - End State
        final ArrayAdapter<String> spinnerSStatepickerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.startState));
        final ArrayAdapter<String> spinnerEStatepickerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.endState));

        // Delaring adapter for None Selected
        final ArrayAdapter<String> spinnerNoneSelectedAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.NoneSelected));

        // Declaring adapter for Start - End Terminals
        final ArrayAdapter<String> spinnerALArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.ALTerminalCodes));
        final ArrayAdapter<String> spinnerAZArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.AZTerminalCodes));
        final ArrayAdapter<String> spinnerCAArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.CATerminalCodes));
        final ArrayAdapter<String> spinnerCOArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.COTerminalCodes));
        final ArrayAdapter<String> spinnerFLArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.FLTerminalCodes));
        final ArrayAdapter<String> spinnerGAArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.GATerminalCodes));
        final ArrayAdapter<String> spinnerILArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.ILTerminalCodes));
        final ArrayAdapter<String> spinnerINArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.INTerminalCodes));
        final ArrayAdapter<String> spinnerKSArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.KSTerminalCodes));
        final ArrayAdapter<String> spinnerKYArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.KYTerminalCodes));
        final ArrayAdapter<String> spinnerLAArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.LATerminalCodes));
        final ArrayAdapter<String> spinnerMAArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.MATerminalCodes));
        final ArrayAdapter<String> spinnerMDArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.MDTerminalCodes));
        final ArrayAdapter<String> spinnerMIArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.MITerminalCodes));
        final ArrayAdapter<String> spinnerMNArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.MNTerminalCodes));
        final ArrayAdapter<String> spinnerMOArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.MOTerminalCodes));
        final ArrayAdapter<String> spinnerMSArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.MSTerminalCodes));
        final ArrayAdapter<String> spinnerNCArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.NCTerminalCodes));
        final ArrayAdapter<String> spinnerNEArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.NETerminalCodes));
        final ArrayAdapter<String> spinnerNJArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.NJTerminalCodes));
        final ArrayAdapter<String> spinnerNMArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.NMTerminalCodes));
        final ArrayAdapter<String> spinnerNVArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.NVTerminalCodes));
        final ArrayAdapter<String> spinnerNYArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.NYTerminalCodes));
        final ArrayAdapter<String> spinnerOHArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.OHTerminalCodes));
        final ArrayAdapter<String> spinnerOKArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.OKTerminalCodes));
        final ArrayAdapter<String> spinnerORArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.ORTerminalCodes));
        final ArrayAdapter<String> spinnerPAArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.PATerminalCodes));
        final ArrayAdapter<String> spinnerPA_CLEArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.PA_CLETerminalCodes));
        final ArrayAdapter<String> spinnerPA_CUMArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.PA_CUMTerminalCodes));
        final ArrayAdapter<String> spinnerPA_MONArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.PA_MONTerminalCodes));
        final ArrayAdapter<String> spinnerRIArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.RITerminalCodes));
        final ArrayAdapter<String> spinnerSCArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.SCTerminalCodes));
        final ArrayAdapter<String> spinnerTNArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.TNTerminalCodes));
        final ArrayAdapter<String> spinnerTN_SHEArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.TN_SHETerminalCodes));
        final ArrayAdapter<String> spinnerTXArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.TXTerminalCodes));
        final ArrayAdapter<String> spinnerUTArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.UTTerminalCodes));
        final ArrayAdapter<String> spinnerVAArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.VATerminalCodes));
        final ArrayAdapter<String> spinnerVA_CHEArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.VA_CHETerminalCodes));
        final ArrayAdapter<String> spinnerWAArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.WA_TerminalCodes));
        final ArrayAdapter<String> spinnerWIArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.WI_TerminalCodes));

        //Declaring Dropdowns
        s1.setAdapter(spinnerSStatepickerArrayAdapter);
        s3.setAdapter(spinnerEStatepickerArrayAdapter);


        //Trying to get Spinner 1 and 2 Values
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View selectedItemview,
                                       int position, long id) {
                Toast.makeText(parent.getContext(),
                        "Starting State Selected: " + parent.getItemAtPosition(position).toString(),
                        Toast.LENGTH_SHORT).show();
                String text = s1.getSelectedItem().toString();

                if (text.contentEquals("None")){
                    s3.setAdapter(spinnerNoneSelectedAdapter2);
                }else
                if (text.contentEquals("AL")){
                    s3.setAdapter(spinnerALArrayAdapter2);
                }else
                if (text.contentEquals("AZ")){
                    s3.setAdapter(spinnerAZArrayAdapter2);
                }else
                if (text.contentEquals("CA")){
                    s3.setAdapter(spinnerCAArrayAdapter2);
                }else
                if (text.contentEquals("CO")){
                    s3.setAdapter(spinnerCOArrayAdapter2);
                }else
                if (text.contentEquals("FL")){
                    s3.setAdapter(spinnerFLArrayAdapter2);
                }else
                if (text.contentEquals("GA")){
                    s3.setAdapter(spinnerGAArrayAdapter2);
                }else
                if (text.contentEquals("IL")){
                    s3.setAdapter(spinnerILArrayAdapter2);
                }else
                if (text.contentEquals("IN")){
                    s3.setAdapter(spinnerINArrayAdapter2);
                }else
                if (text.contentEquals("KS")){
                    s3.setAdapter(spinnerKSArrayAdapter2);
                }else
                if (text.contentEquals("KY")){
                    s3.setAdapter(spinnerKYArrayAdapter2);
                }else
                if (text.contentEquals("LA")){
                    s3.setAdapter(spinnerLAArrayAdapter2);
                }else
                if (text.contentEquals("MA")){
                    s3.setAdapter(spinnerMAArrayAdapter2);
                }else
                if (text.contentEquals("MD")){
                    s3.setAdapter(spinnerMDArrayAdapter2);
                }else
                if (text.contentEquals("MI")){
                    s3.setAdapter(spinnerMIArrayAdapter2);
                }else
                if (text.contentEquals("MN")){
                    s3.setAdapter(spinnerMNArrayAdapter2);
                }else
                if (text.contentEquals("MO")){
                    s3.setAdapter(spinnerMOArrayAdapter2);
                }else
                if (text.contentEquals("MS")){
                    s3.setAdapter(spinnerMSArrayAdapter2);
                }else
                if (text.contentEquals("NC")){
                    s3.setAdapter(spinnerNCArrayAdapter2);
                }else
                if (text.contentEquals("NE")){
                    s3.setAdapter(spinnerNEArrayAdapter2);
                }else
                if (text.contentEquals("NJ")){
                    s3.setAdapter(spinnerNJArrayAdapter2);
                }else
                if (text.contentEquals("NM")){
                    s3.setAdapter(spinnerNMArrayAdapter2);
                }else
                if (text.contentEquals("NV")){
                    s3.setAdapter(spinnerNVArrayAdapter2);
                }else
                if (text.contentEquals("NY")){
                    s3.setAdapter(spinnerNYArrayAdapter2);
                }else
                if (text.contentEquals("OH")){
                    s3.setAdapter(spinnerOHArrayAdapter2);
                }else
                if (text.contentEquals("OK")){
                    s3.setAdapter(spinnerOKArrayAdapter2);
                }else
                if (text.contentEquals("OR")){
                    s3.setAdapter(spinnerORArrayAdapter2);
                }else
                if (text.contentEquals("PA")){
                    s3.setAdapter(spinnerPAArrayAdapter2);
                }else
                if (text.contentEquals("PA/CLE")){
                   s3.setAdapter(spinnerPA_CLEArrayAdapter2);
                }else
                if (text.contentEquals("PA/CUM")){
                   s3.setAdapter(spinnerPA_CUMArrayAdapter2);
                }else
                if (text.contentEquals("PA/MON")){
                    s3.setAdapter(spinnerPA_MONArrayAdapter2);
                }else
                if (text.contentEquals("RI")){
                    s3.setAdapter(spinnerRIArrayAdapter2);
                }else
                if (text.contentEquals("SC")){
                    s3.setAdapter(spinnerSCArrayAdapter2);
                }else
                if (text.contentEquals("TN")){
                    s3.setAdapter(spinnerTNArrayAdapter2);
                }else
                if (text.contentEquals("TN/SHE")){
                    s3.setAdapter(spinnerTN_SHEArrayAdapter2);
                }else
                if (text.contentEquals("TX")){
                    s3.setAdapter(spinnerTXArrayAdapter2);
                }else
                if (text.contentEquals("UT")){
                    s3.setAdapter(spinnerUTArrayAdapter2);
                }else
                if (text.contentEquals("VA")){
                    s3.setAdapter(spinnerVAArrayAdapter2);
                }else
                if (text.contentEquals("VA/CHE")){
                    s3.setAdapter(spinnerVA_CHEArrayAdapter2);
                }else
                if (text.contentEquals("WA")){
                    s3.setAdapter(spinnerWAArrayAdapter2);
                }else
                if (text.contentEquals("WI")){
                    s3.setAdapter(spinnerWIArrayAdapter2);
                }
                startState = s1.getSelectedItem().toString();
                startTerminal = s3.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        //Second Set of Arrays
        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View selectedItemview,
                                       int position, long id) {
                Toast.makeText(parent.getContext(),
                        "Ending State Selected: " + parent.getItemAtPosition(position).toString(),
                        Toast.LENGTH_SHORT).show();
                String text2 = s2.getSelectedItem().toString();
                if (text2.contentEquals("None")){
                    s4.setAdapter(spinnerNoneSelectedAdapter2);
                }else
                if (text2.contentEquals("AL")){
                    s4.setAdapter(spinnerALArrayAdapter2);
                }else
                if (text2.contentEquals("AZ")){
                    s4.setAdapter(spinnerAZArrayAdapter2);
                }else
                if (text2.contentEquals("CA")){
                    s4.setAdapter(spinnerCAArrayAdapter2);
                }else
                if (text2.contentEquals("CO")){
                    s4.setAdapter(spinnerCOArrayAdapter2);
                }else
                if (text2.contentEquals("FL")){
                    s4.setAdapter(spinnerFLArrayAdapter2);
                }else
                if (text2.contentEquals("GA")){
                    s4.setAdapter(spinnerGAArrayAdapter2);
                }else
                if (text2.contentEquals("IL")){
                    s4.setAdapter(spinnerILArrayAdapter2);
                }else
                if (text2.contentEquals("IN")){
                    s4.setAdapter(spinnerINArrayAdapter2);
                }else
                if (text2.contentEquals("KS")){
                    s4.setAdapter(spinnerKSArrayAdapter2);
                }else
                if (text2.contentEquals("KY")){
                    s4.setAdapter(spinnerKYArrayAdapter2);
                }else
                if (text2.contentEquals("LA")){
                    s4.setAdapter(spinnerLAArrayAdapter2);
                }else
                if (text2.contentEquals("MA")){
                    s4.setAdapter(spinnerMAArrayAdapter2);
                }else
                if (text2.contentEquals("MD")){
                    s4.setAdapter(spinnerMDArrayAdapter2);
                }else
                if (text2.contentEquals("MI")){
                    s4.setAdapter(spinnerMIArrayAdapter2);
                }else
                if (text2.contentEquals("MN")){
                    s4.setAdapter(spinnerMNArrayAdapter2);
                }else
                if (text2.contentEquals("MO")){
                    s4.setAdapter(spinnerMOArrayAdapter2);
                }else
                if (text2.contentEquals("MS")){
                    s4.setAdapter(spinnerMSArrayAdapter2);
                }else
                if (text2.contentEquals("NC")){
                    s4.setAdapter(spinnerNCArrayAdapter2);
                }else
                if (text2.contentEquals("NE")){
                    s4.setAdapter(spinnerNEArrayAdapter2);
                }else
                if (text2.contentEquals("NJ")){
                    s4.setAdapter(spinnerNJArrayAdapter2);
                }else
                if (text2.contentEquals("NM")){
                    s4.setAdapter(spinnerNMArrayAdapter2);
                }else
                if (text2.contentEquals("NV")){
                    s4.setAdapter(spinnerNVArrayAdapter2);
                }else
                if (text2.contentEquals("NY")){
                    s4.setAdapter(spinnerNYArrayAdapter2);
                }else
                if (text2.contentEquals("OH")){
                    s4.setAdapter(spinnerOHArrayAdapter2);
                }else
                if (text2.contentEquals("OK")){
                    s4.setAdapter(spinnerOKArrayAdapter2);
                }else
                if (text2.contentEquals("OR")){
                    s4.setAdapter(spinnerORArrayAdapter2);
                }else
                if (text2.contentEquals("PA")){
                    s4.setAdapter(spinnerPAArrayAdapter2);
                }else
                if (text2.contentEquals("PA/CLE")){
                    s4.setAdapter(spinnerPA_CLEArrayAdapter2);
                }else
                if (text2.contentEquals("PA/CUM")){
                    s4.setAdapter(spinnerPA_CUMArrayAdapter2);
                }else
                if (text2.contentEquals("PA/MON")){
                    s4.setAdapter(spinnerPA_MONArrayAdapter2);
                }else
                if (text2.contentEquals("RI")){
                    s4.setAdapter(spinnerRIArrayAdapter2);
                }else
                if (text2.contentEquals("SC")){
                    s4.setAdapter(spinnerSCArrayAdapter2);
                }else
                if (text2.contentEquals("TN")){
                    s4.setAdapter(spinnerTNArrayAdapter2);
                }else
                if (text2.contentEquals("TN/SHE")){
                    s4.setAdapter(spinnerTN_SHEArrayAdapter2);
                }else
                if (text2.contentEquals("TX")){
                    s4.setAdapter(spinnerTXArrayAdapter2);
                }else
                if (text2.contentEquals("UT")){
                    s4.setAdapter(spinnerUTArrayAdapter2);
                }else
                if (text2.contentEquals("VA")){
                    s4.setAdapter(spinnerVAArrayAdapter2);
                }else
                if (text2.contentEquals("VA/CHE")){
                    s4.setAdapter(spinnerVA_CHEArrayAdapter2);
                }else
                if (text2.contentEquals("WA")){
                    s4.setAdapter(spinnerWAArrayAdapter2);
                }else
                if (text2.contentEquals("WI")){
                    s4.setAdapter(spinnerWIArrayAdapter2);
                }
                endState = s2.getSelectedItem().toString();
                endTerminal = s4.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        //Initializing Views
        trucksID = (EditText) findViewById(R.id.trucksID);
        tripReportNumber = (EditText) findViewById(R.id.tripReportNumber);
        emptyMilage = (EditText) findViewById(R.id.emptyMilage);
        loadedMilage = (EditText) findViewById(R.id.loadedMilage);
        estMilage = (TextView) findViewById(R.id.estMilage);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        //Calendar Stuff
        final Calendar cal = Calendar.getInstance();
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH);
        day_x = cal.get(Calendar.DAY_OF_MONTH);

        showDialogOnButtonClick();
        //End of Calendar Stuff

        //Reset Button for Form
        Button reset= (Button) findViewById(R.id.btnclear);

        assert reset != null;
        reset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                trucksID.setText("");
                tripReportNumber.setText("");
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

    //Calendar from Beginners #27
    public void showDialogOnButtonClick() {
        btn = (Button) findViewById(R.id.button);

        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(DIALOG_ID);
                    }
                }
        );
    }
        @Override
        protected Dialog onCreateDialog(int id){
            if (id == DIALOG_ID)
                    return new DatePickerDialog(this,dpickerListener,year_x,month_x,day_x);
            return null;
        }
    private DatePickerDialog.OnDateSetListener dpickerListener
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            year_x = year;
            month_x = monthOfYear +1;
            day_x = dayOfMonth;
            Toast myToast = Toast.makeText(MainVersionDatepicker.this,+month_x+"/"+day_x+"/"+year_x,Toast.LENGTH_SHORT);
            myToast.show();

            //Now the statement below gets the text displayed
            enteredDate = ((TextView)((LinearLayout)myToast.getView()).getChildAt(0)).getText().toString();
      }
    };

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

            case R.id.about_us:
                Intent about = new Intent(this, aboutUs.class);
                this.startActivity(about);
                return true;

            case R.id.goto4:
                Intent actDate = new Intent(this, MainActivity.class);
                this.startActivity(actDate);
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
        RegisterAPITest api = adapter.create(RegisterAPITest.class);

        //Defining the method insertuser of our interface

        int value1 = Integer.parseInt(emptyMilage.getText().toString());
        int value2 = Integer.parseInt(loadedMilage.getText().toString());
        int estMilage = value2 + value1;

        api.insertUser(
                //Passing the values by getting it from editTexts
                trucksID.getText().toString(),
                tripReportNumber.getText().toString(),
                enteredDate,
                emptyMilage.getText().toString(),
                loadedMilage.getText().toString(),

                //can take this out below
                Integer.toString(estMilage),

                //NewVairiable
                startState,
                startTerminal,
                endState,
                endTerminal,

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
                        Toast.makeText(MainVersionDatepicker.this, output, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        //If any error occured displaying the error as toast
                        Toast.makeText(MainVersionDatepicker.this, error.toString(), Toast.LENGTH_LONG).show();
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
