package tralafarlaw.miguel.button;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Main_Button extends AppCompatActivity {

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
    }
    public void conectar(){

    }

    public Location getLocaction() {
        return loc;
    }

    public void setLocation(Location loc) {
        this.loc = loc;
    }
}
