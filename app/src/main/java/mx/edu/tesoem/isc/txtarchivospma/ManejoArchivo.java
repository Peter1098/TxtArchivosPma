package mx.edu.tesoem.isc.txtarchivospma;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class ManejoArchivo {

    private ArrayList<String> TextoCompleto = new ArrayList<String>();

    public boolean verificArch(Context context,String nomarch){
        String[]  archivos = context.fileList();
        for (String verifica:archivos){
            if (verifica.equals(nomarch))return true;
        }
        return false;

    }
    public boolean grabar(Context context, ArrayList<String> dato, String nomarch){
        try{
            OutputStreamWriter archivo;
            archivo = new OutputStreamWriter(context.openFileOutput(nomarch, Activity.MODE_PRIVATE));
            for(String cadena : dato)
                archivo.write(cadena+"\n");
            archivo.flush();
            archivo.close();
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }
    public boolean leer(Context context , String nomarch){
        try{
            InputStreamReader archivo = new InputStreamReader(context.openFileInput(nomarch));
            BufferedReader br = new BufferedReader(archivo);
            String cadena =  br.readLine();
            while (cadena!=null){
                TextoCompleto.add(cadena);
                cadena=br.readLine();

            }


        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public ArrayList<String> getContenido(){
        return TextoCompleto;
    }
    public void agrega(String dato,ArrayList<String>TextoCompleto){
        this.TextoCompleto = TextoCompleto;
        this.TextoCompleto.add(dato);



    }

}
