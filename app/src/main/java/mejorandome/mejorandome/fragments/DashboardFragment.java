package mejorandome.mejorandome.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;
import mejorandome.mejorandome.CheckinActivity;
import mejorandome.mejorandome.MoodActivity;
import mejorandome.mejorandome.R;

public class DashboardFragment extends Fragment {

    private PieChartView pieChart;
    private PieChartData data;

    private View rootView;
    private Button moodButton;
    private Button checkinButton;
    private int dni;
    private int positiveEmotions;
    private int negativeEmotions;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);

        dni = getActivity().getIntent().getIntExtra("dni",0);

        new getMoodStatus().execute();

        pieChart = (PieChartView) rootView.findViewById(R.id.pie_chart);
        pieChart.setInteractive(true);

        moodButton = (Button) rootView.findViewById(R.id.mood_button);
        checkinButton = (Button) rootView.findViewById(R.id.checkin_button);
        moodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardFragment.this.getActivity(), MoodActivity.class);
                i.putExtra("dni",dni);
                startActivity(i);
            }
        });

        checkinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardFragment.this.getActivity(), CheckinActivity.class);
                i.putExtra("dni",dni);
                startActivity(i);
            }
        });

        pieChart.setChartRotationEnabled(false);

        pieChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        GetEmotionsData();

        return rootView;
    }

    private void generateData() {

        List<SliceValue> values = new ArrayList<>();

        SliceValue sliceValueBuenas = new SliceValue((float) positiveEmotions, ContextCompat.getColor(rootView.getContext(),R.color.color_green));
        values.add(sliceValueBuenas);

        SliceValue sliceValueMalas = new SliceValue((float) negativeEmotions, ContextCompat.getColor(rootView.getContext(),R.color.color_red));
        values.add(sliceValueMalas);

        data = new PieChartData(values);

        pieChart.setPieChartData(data);
    }

    private void GetPositiveNegativeData()
    {
        positiveEmotions = 78;
        negativeEmotions = 22;
    }

    private void GetEmotionsData()
    {
        GetPositiveNegativeData();
        generateData();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private class getMoodStatus extends AsyncTask<Void,Void,Void>
    {
        SoapPrimitive resultado;
        @Override
        protected Void doInBackground(Void... params) {
            final String NAMESPACE = "http://tempuri.org/";
            final String URL = "http://www.mejorandome.com/servicio/Service1.svc";
            final String METHOD_NAME = "getMoodStatus";
            final String SOAP_ACTION = "http://tempuri.org/IService1/getMoodStatus";
            String Error;
            try {
                SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
                request.addProperty("rut", dni); // Paso parametros al WS

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
                if(Boolean.parseBoolean(resultado.toString()))
                {
                    moodButton.setVisibility(View.VISIBLE);
                }
                else
                {
                    moodButton.setVisibility(View.INVISIBLE);
                }
            }
            else
            {
                moodButton.setVisibility(View.INVISIBLE);
            }

            super.onPostExecute(result);
        }
    }
}
