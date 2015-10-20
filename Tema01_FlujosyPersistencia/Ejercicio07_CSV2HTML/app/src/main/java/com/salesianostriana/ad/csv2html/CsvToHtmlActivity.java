package com.salesianostriana.ad.csv2html;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class CsvToHtmlActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csv_to_html);

        webView = (WebView) findViewById(R.id.webView);
        //almacenara la información obtenida del fichero
        String cadena = "";
        //servirá para formatear la cadena como se quierda
        StringBuilder sb = new StringBuilder(cadena);
        try {
            //Se crea el Buffered para leer el archivo
            BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput("ejemplo.csv")));
            String linea = "";
            sb = new StringBuilder(linea);
            //se lee el archivo
            while((linea = br.readLine()) != null) {
                //formatea la linea leida del archivo csv para convertirla en tabla html
                if(linea.contains(",")) {
                    linea = "<tr><td>" + linea.replace(",", "</td>\n<td>") + "</td></tr>\n";
                    sb = sb.append(linea);
                }else if(linea.contains(";")){
                    linea = "<tr><td>" + linea.replace(";", "</td>\n<td>") + "</td></tr>\n";
                    sb = sb.append(linea);
                }else if(linea.contains("\t")) {
                    linea = "<tr><td>" + linea.replace("\t", "</td>\n<td>") + "</td></tr>\n";
                    sb = sb.append(linea);
                }
                //para ver por el log lo que se va imprimiendo
                Log.i("CADENA", linea);
            }

    } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //carga el texto almacenado en el stringBuilder en el WebView
        muestraHTML("<html>\n" +
                "<head></head><style TYPE=\"text/css\">" +
                "table{border-collapse: collapse;}" +
                "td{font-size:10px; " +
                "border:1px solid;}" +
                "</style><body><table>"+sb+"</table></body></html>");

    }

    private void muestraHTML(String html) {
        webView.loadData(html,"text/html","utf-8");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_csv_to_html, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
