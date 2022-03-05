package iesluisvives.peluqueriadam.utils;

import android.net.Uri;

import androidx.room.TypeConverter;

public class UriConverters {
    //convertir un String a Uri
    @TypeConverter
    public static Uri fromString(String valor){

        return valor == null ? null : Uri.parse(valor);

       /* Uri myUri = null;
        if (valor != null ){
            myUri = Uri.parse(valor);
        }
        return myUri;*/
    }

    //convertir un Uri en String
    @TypeConverter
    public static String formUri (Uri valor){
        return valor.toString();
    }
}
