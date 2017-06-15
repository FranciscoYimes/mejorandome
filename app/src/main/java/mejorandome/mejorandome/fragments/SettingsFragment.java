package mejorandome.mejorandome.fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.net.MalformedURLException;

import mejorandome.mejorandome.LoginActivity;
import mejorandome.mejorandome.R;
import mejorandome.mejorandome.adapters.Utils;

public class SettingsFragment extends Fragment {

    private View rootView;
    private Button logoutButton;
    private Button changepassOption;
    private EditText password1;
    private EditText password2;
    private TextView changePassMessage;
    private Button changePassButton;
    private LinearLayout changePassLayout;
    private Utils utils;
    private String pass1;
    private String pass2;
    private int dni;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        utils = new Utils();
        dni = getActivity().getIntent().getIntExtra("dni",0);
        logoutButton = (Button) rootView.findViewById(R.id.logout_button);
        changepassOption = (Button) rootView.findViewById(R.id.change_pass_option);
        changePassLayout = (LinearLayout) rootView.findViewById(R.id.change_pass_layout);
        password1 = (EditText) rootView.findViewById(R.id.password1);
        password2 = (EditText) rootView.findViewById(R.id.password2);
        changePassButton = (Button) rootView.findViewById(R.id.change_pass_button);
        changePassMessage = (TextView) rootView.findViewById(R.id.change_pass_message);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new logout().execute();

            }
        });

        changepassOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(changePassLayout.getVisibility() == View.VISIBLE)
                {
                    changePassLayout.setVisibility(View.GONE);
                }
                else
                {
                    changePassLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        changePassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendNewPassword();
            }
        });

        return rootView;
    }

    public void SendNewPassword()
    {
        pass1 = password1.toString();
        pass2 = password2.toString();

        if(password1.getText().toString().equals("") || password2.getText().toString().equals(""))
        {
            changePassMessage.setVisibility(View.VISIBLE);
            changePassMessage.setText("Debe completar los campos");
        }
        else
        {
            if(password1.getText().toString().equals(password2.getText().toString()))
            {
                changePassMessage.setVisibility(View.VISIBLE);
                changePassMessage.setText("Las contraseñas son iguales");
            }
            else new changePassword().execute();
        }
    }

    private class changePassword extends AsyncTask<Void,Void,Void>
    {
        SoapPrimitive resultado;
        @Override
        protected Void doInBackground(Void... params) {
            final String NAMESPACE = "http://tempuri.org/";
            final String URL = "http://www.mejorandome.com/servicio/Service1.svc";
            final String METHOD_NAME = "changePassword";
            final String SOAP_ACTION = "http://tempuri.org/IService1/changePassword";
            String Error;
            try {
                SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
                request.addProperty("rut",dni);
                request.addProperty("pass",pass1);
                request.addProperty("newPass",pass2);

                SoapSerializationEnvelope sobre = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                sobre.dotNet = true;
                sobre.setOutputSoapObject(request);

                HttpTransportSE transporte = new HttpTransportSE(URL);

                transporte.call(SOAP_ACTION, sobre);

                resultado = (SoapPrimitive) sobre.getResponse();


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
            int changePassResult;

            if(resultado!=null)
            {
                changePassResult = Integer.parseInt(resultado.toString());

                if(changePassResult==1)                     // cambio exitoso
                {
                    Toast toast = Toast.makeText(SettingsFragment.this.getActivity(), "La contraseña ha sido cambiada", Toast.LENGTH_SHORT);
                    toast.show();
                    changePassLayout.setVisibility(View.GONE);
                }
                if(changePassResult==-1)
                {
                    changePassMessage.setVisibility(View.VISIBLE);
                    changePassMessage.setText("Contraseña actual no corresponde");
                }
                if(changePassResult==0)
                {
                    changePassMessage.setVisibility(View.VISIBLE);
                    changePassMessage.setText("No se ha logrado cambiar la contraseña");
                }
            }
            else
            {
                changePassMessage.setVisibility(View.VISIBLE);
                changePassMessage.setText("Error al cambiar la contraseña");
            }
        }
    }

    private class logout extends AsyncTask<Void,Void,Void>
    {
        SoapPrimitive resultado;
        @Override
        protected Void doInBackground(Void... params) {
            final String NAMESPACE = "http://tempuri.org/";
            final String URL = "http://www.mejorandome.com/servicio/Service1.svc";
            final String METHOD_NAME = "logout";
            final String SOAP_ACTION = "http://tempuri.org/IService1/logout";
            String Error;
            try {
                SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
                request.addProperty("macAddress",utils.getMACAddress("wlan0"));

                SoapSerializationEnvelope sobre = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                sobre.dotNet = true;
                sobre.setOutputSoapObject(request);

                HttpTransportSE transporte = new HttpTransportSE(URL);

                transporte.call(SOAP_ACTION, sobre);

                resultado = (SoapPrimitive) sobre.getResponse();


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
            Intent i = new Intent(SettingsFragment.this.getActivity(), LoginActivity.class);
            startActivity(i);
            getActivity().finish();
        }
    }

}
