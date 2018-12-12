package tralafarlaw.miguel.button;

import android.content.Context;
import android.icu.util.Calendar;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.text.format.Time;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.OutputStreamWriter;

public class MyLocationListener implements LocationListener {
    private Main_Button mainActivity;
    private DatabaseReference databaseReference;


    public Main_Button getMainActivity() {
        return mainActivity;
    }

    public void setMainActivity(Main_Button mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void onLocationChanged(Location loc) {
        // Este mŽtodo se ejecuta cada vez que el GPS recibe nuevas coordenadas
        // debido a la detecci—n de un cambio de ubicacion

        this.mainActivity.setLocation(loc);

        //empezamos con firebase
        databaseReference = FirebaseDatabase.getInstance().getReference();
        FirebaseUser fbuser = FirebaseAuth.getInstance().getCurrentUser();
        if(fbuser != null) {
            //actualizar la ase de datos
            actualizar_ubicacion(fbuser.getDisplayName(),loc);
            //escribir_archivo(fbuser.getDisplayName(), loc);


        }
    }
    public void actualizar_ubicacion(String id, Location loc){
        databaseReference.child(mainActivity.getString(R.string.tabla_conductor)).child(id).child(mainActivity.getString(R.string.lat)).setValue(loc.getLatitude());
        databaseReference.child(mainActivity.getString(R.string.tabla_conductor)).child(id).child(mainActivity.getString(R.string.lon)).setValue(loc.getLongitude());
    }
    public void escribir_archivo(String Nombre, Location loc){
        OutputStreamWriter escritor=null;
        try{
            Time time = new Time();
            time.setToNow();
            escritor = new OutputStreamWriter(mainActivity.openFileOutput(Nombre, Context.MODE_APPEND));
            escritor.write(loc.getLatitude()+" "+
                    loc.getLongitude()+" "+
                    time.hour+" "+
                    time.minute+" "+
                    time.second);

        }catch (Exception e){}
        finally {
            try {
                if(escritor!= null)
                    escritor.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onProviderDisabled(String provider) {
        // Este mŽtodo se ejecuta cuando el GPS es desactivado
        // messageTextView.setText("GPS Desactivado");
    }

    @Override
    public void onProviderEnabled(String provider) {
        // Este mŽtodo se ejecuta cuando el GPS es activado
        // messageTextView.setText("GPS Activado");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // Este mŽtodo se ejecuta cada vez que se detecta un cambio en el
        // status del proveedor de localizaci—n (GPS)
        // Los diferentes Status son:
        // OUT_OF_SERVICE -> Si el proveedor esta fuera de servicio
        // TEMPORARILY_UNAVAILABLE -> Temp˜ralmente no disponible pero se
        // espera que este disponible en breve
        // AVAILABLE -> Disponible
        //poner aca los alerts
    }
}
