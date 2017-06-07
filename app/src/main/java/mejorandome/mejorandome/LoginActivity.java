package mejorandome.mejorandome;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.net.MalformedURLException;

public class LoginActivity extends AppCompatActivity {

    Button loginButton;
    TextView forgotPassText;
    String passText;
    EditText dni;
    EditText pass;
    int dniText;
    int respuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = (Button) findViewById(R.id.login__button);
        forgotPassText = (TextView) findViewById(R.id.forgotPassText);

        dni = (EditText) findViewById(R.id.login__dni);
        pass = (EditText) findViewById(R.id.login__password);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(dni.getText().toString().equals(""))
                {
                    dniText = 0;
                }
                else
                {
                    dniText =   Integer.parseInt(dni.getText().toString());
                }

                passText = pass.getText().toString();

                new sendLogginInfo().execute();
                //Intent i = new Intent(LoginActivity.this,MainActivity.class);
                //startActivity(i);
            }
        });

        forgotPassText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,ForgotPasswordActivity.class);
                startActivity(i);
            }
        });
    }

    private class sendLogginInfo extends AsyncTask<Void,Void,Void>
    {
        SoapPrimitive resultado;
        @Override
        protected Void doInBackground(Void... params) {
            final String NAMESPACE = "http://tempuri.org/";
            final String URL = "http://www.mejorandome.com/servicio/Service1.svc";
            final String METHOD_NAME = "login";
            final String SOAP_ACTION = "http://tempuri.org/IService1/login";
            String Error;
            try {
                SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
                request.addProperty("rut", 18730368); // Paso parametros al WS
                request.addProperty("password", "1234"); // Paso parametros al WS


                SoapSerializationEnvelope sobre = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                sobre.dotNet = true;
                sobre.setOutputSoapObject(request);

                HttpTransportSE transporte = new HttpTransportSE(URL);

                transporte.call(SOAP_ACTION, sobre);

                resultado = (SoapPrimitive) sobre.getResponse();

                Log.i("Resultado", resultado.toString());

                respuesta = Integer.parseInt(resultado.toString());


            } catch (MalformedURLException e) {
                e.printStackTrace();
                Error = e.toString();
                respuesta = -2;

            } catch (SoapFault soapFault) {
                soapFault.printStackTrace();
                Error = soapFault.toString();
                respuesta = -2;

            } catch (XmlPullParserException e) {
                e.printStackTrace();
                Error = e.toString();
                respuesta = -2;

            } catch (IOException e) {
                e.printStackTrace();
                Error = e.toString();
                respuesta = -2;

            }

            return null;
        }
        protected void onPostExecute(Void result)
        {
            if(respuesta == 1)
            {
                Intent i = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(i);
            }
            else
            {

            }
            super.onPostExecute(result);
        }
    }
}
