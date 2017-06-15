package mejorandome.mejorandome;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.net.MalformedURLException;

import mejorandome.mejorandome.SeekBarClasses.Mood;
import mejorandome.mejorandome.SeekBarClasses.RangeSliderView;

public class MoodActivity extends AppCompatActivity {


    private Button saveMoodButton;
    private RangeSliderView rangeSliderView1;
    private RangeSliderView rangeSliderView2;
    private RangeSliderView rangeSliderView3;
    private RangeSliderView rangeSliderView4;
    private RangeSliderView rangeSliderView5;
    private RangeSliderView rangeSliderView6;
    private Switch medicineCheckBox;
    private EditText peso;
    private Mood mood;
    private int dni;
    private boolean response;
    private int medicineCheck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);

        dni = getIntent().getIntExtra("dni",0);

        mood = new Mood();

        rangeSliderView1 = (RangeSliderView) findViewById(R.id.seekbar_1);
        rangeSliderView2 = (RangeSliderView) findViewById(R.id.seekbar_2);
        rangeSliderView3 = (RangeSliderView) findViewById(R.id.seekbar_3);
        rangeSliderView4 = (RangeSliderView) findViewById(R.id.seekbar_4);
        rangeSliderView5 = (RangeSliderView) findViewById(R.id.seekbar_5);
        rangeSliderView6 = (RangeSliderView) findViewById(R.id.seekbar_6);
        saveMoodButton = (Button) findViewById(R.id.save_mood_button);
        peso = (EditText) findViewById(R.id.peso);
        medicineCheckBox = (Switch) findViewById(R.id.medicineCheck);

        rangeSliderView1.setInitialIndex(3);
        rangeSliderView2.setInitialIndex(1);
        rangeSliderView3.setInitialIndex(2);
        rangeSliderView4.setInitialIndex(4);
        rangeSliderView5.setInitialIndex(2);
        rangeSliderView6.setInitialIndex(3);
        medicineCheck = 0;


        saveMoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(medicineCheckBox.isChecked()) medicineCheck = 1;

                mood.setFelicidad(rangeSliderView1.getIndex());
                mood.setPaz(rangeSliderView2.getIndex());
                mood.setSatisfaccion(rangeSliderView3.getIndex());
                mood.setAngustia(rangeSliderView4.getIndex());
                mood.setCulpa(rangeSliderView5.getIndex());
                mood.setAnsiedad(rangeSliderView6.getIndex());
                mood.setPeso(Double.parseDouble(peso.getText().toString()));

                if(mood.AllReady())
                {
                    new sendMoodData().execute();
                }
                else
                {
                    //mensaje
                }
            }
        });
    }



    private class sendMoodData extends AsyncTask<Void,Void,Void>
    {
        SoapPrimitive resultado;
        @Override
        protected Void doInBackground(Void... params) {
            final String NAMESPACE = "http://tempuri.org/";
            final String URL = "http://www.mejorandome.com/servicio/Service1.svc";
            final String METHOD_NAME = "sendMoodData";
            final String SOAP_ACTION = "http://tempuri.org/IService1/sendMoodData";
            String Error;
            try {
                SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
                request.addProperty("rut",dni);

                request.addProperty("miedo",mood.getCulpa());
                request.addProperty("ansiedad",mood.getAnsiedad());
                request.addProperty("alegria",mood.getFelicidad());
                request.addProperty("paz_interior",mood.getPaz());
                request.addProperty("angustia",mood.getAngustia());
                request.addProperty("satisfaccion",mood.getSatisfaccion());
                request.addProperty("peso",mood.getPeso2());
                request.addProperty("medicamento",medicineCheck);
                request.addProperty("recaida",false);

                SoapSerializationEnvelope sobre = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                sobre.dotNet = true;
                sobre.setOutputSoapObject(request);

                HttpTransportSE transporte = new HttpTransportSE(URL);

                transporte.call(SOAP_ACTION, sobre);

                resultado = (SoapPrimitive) sobre.getResponse();

                Log.i("Resultado", resultado.toString());


            } catch (MalformedURLException e) {
                e.printStackTrace();
                Error = e.toString();

            } catch (SoapFault soapFault) {
                soapFault.printStackTrace();
                Error = soapFault.toString();

            } catch (XmlPullParserException e) {
                e.printStackTrace();
                Error = e.toString();

            } catch (IOException e) {
                e.printStackTrace();
                Error = e.toString();
            }

            return null;
        }
        protected void onPostExecute(Void result)
        {
            if(resultado!=null)
            {
                response = Boolean.parseBoolean(resultado.toString());
            }
            else response = false;

            if(response)
            {
                finish();
            }
            else
            {
                //mensaje de error
            }
        }
    }
}
