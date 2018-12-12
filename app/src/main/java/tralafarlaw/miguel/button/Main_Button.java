package tralafarlaw.miguel.button;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main_Button extends AppCompatActivity {
    FirebaseUser usr;
    DatabaseReference dbreference;
    LocationManager manager;
    LocationListener locationListener;
    Context ctx;
    Location yo;


    private Location loc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__button);
        ImageButton btn = findViewById(R.id.conected_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conectar();
            }
        });
        usr = FirebaseAuth.getInstance().getCurrentUser();
        dbreference = FirebaseDatabase.getInstance().getReference();
        ctx = getApplicationContext();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

    }
    public void conectar(){
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        if(ActivityCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            Toast.makeText(getApplicationContext(),"No se dieron Permisos",Toast.LENGTH_SHORT).show();
        }


        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        MyLocationListener mlocListener = new MyLocationListener();
        mlocListener.setMainActivity(this);
        if(LocationManager.GPS_PROVIDER != null) {

            mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 10, (LocationListener) mlocListener);
        }else{
            mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10, 10, (LocationListener) mlocListener);
        }
        locationListener = mlocListener;
        //locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

    }

    public Location getLocaction() {
        return loc;
    }

    public void setLocation(Location loc) {
        this.loc = loc;
    }
}
