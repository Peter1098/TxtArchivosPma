package mx.edu.tesoem.isc.txtarchivospma;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText txtnombre;
    TextView lblcontenido;
    private final String nomarch="peter1098.txt";
    private ArrayList<String> TextoCompleto = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtnombre = findViewById(R.id.TxtNombre);
        lblcontenido = findViewById(R.id.lblcontenido);
        llamadatos();
    }
    public void recargar(View v){
        ManejoArchivo objmanar = new ManejoArchivo();
        objmanar.agrega(txtnombre.getText().toString(),TextoCompleto);
        TextoCompleto = objmanar.getContenido();

        if (objmanar.grabar(this,TextoCompleto,nomarch)){
            Toast.makeText(this, "Se grabo correctamente",Toast.LENGTH_SHORT).show();
            llamadatos();
        } else {
            Toast.makeText(this,"Error no se pudo grabar",Toast.LENGTH_SHORT).show();
            llamadatos();
        }
    }
    public void cargardatos(View v){
        llamadatos();
    }
    private  void llamadatos(){
        ManejoArchivo informacion = new ManejoArchivo();
        if (informacion.verificArch(this,nomarch)) {
            Toast.makeText(this, "Si existe", Toast.LENGTH_LONG).show();
            if (informacion.leer(this,nomarch)){
                TextoCompleto = informacion.getContenido();

                String temp="";
                for (String cadena :TextoCompleto)temp+="\n"+cadena;
                lblcontenido.setText(temp);

            }
        }else {
            Toast.makeText(this, "No existe", Toast.LENGTH_LONG).show();
        }
    }
}
