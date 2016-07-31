package parko.parko;

import android.os.Handler;
import android.widget.Toast;
import android.os.StrictMode;

import org.json.*;

import java.net.*;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;



public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        mMap = googleMap;
        LatLng l1 = new LatLng(-37.558973, 143.855990);
        mMap.addMarker(new MarkerOptions().position(l1).title("Projected 35% Full, No parko data"));
        LatLng l2 = new LatLng(-37.560570, 143.859971);
        mMap.addMarker(new MarkerOptions().position(l2).title("Projected 2% Full, No parko data"));
        LatLng l3 = new LatLng(-37.561972, 143.863485);
        mMap.addMarker(new MarkerOptions().position(l3).title("Projected 0% Full, No parko data"));
        LatLng l4 = new LatLng(-37.557885, 143.852323);
        mMap.addMarker(new MarkerOptions().position(l4).title("Projected 77% Full, No parko data"));
        LatLng l5 = new LatLng(-37.558143, 143.850454);
        mMap.addMarker(new MarkerOptions().position(l5).title("Projected 0% Full, No parko data"));
        LatLng l6 = new LatLng(-37.561103, 143.860829);
        mMap.addMarker(new MarkerOptions().position(l6).title("Projected 0% Full, No parko data"));
        LatLng l7 = new LatLng(-37.561605, 143.867859);
        mMap.addMarker(new MarkerOptions().position(l7).title("Projected 45% Full, No parko data"));
        LatLng l8 = new LatLng(-37.559966, 143.860309);
        mMap.addMarker(new MarkerOptions().position(l8).title("Projected 27% Full, No parko data"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(l1));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(15));
        Integer tp1 = 0;
        String json = "";
        String parko1 = "";
        String parko2 = "";

        try{
            URL spreadsheet = new URL("https://spreadsheets.google.com/feeds/list/1mbTzkje20PEJ-JkWW0gclLfYrPPY4Xy1gaJHxwybKDw/od6/public/values?alt=json");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            spreadsheet.openStream()));
            String line;
            while ((line = in.readLine()) != null) {
                json += line;
            }
            in.close();

            JSONObject obj = new JSONObject(json);
            parko1 = obj.getJSONObject("feed").getJSONArray("entry").getJSONObject(0).getJSONObject("gsx$parko1").getString("$t").toString();
            parko2 = obj.getJSONObject("feed").getJSONArray("entry").getJSONObject(0).getJSONObject("gsx$parko2").getString("$t").toString();
        }
        catch (Exception e)
        {

        }
        if (parko1 == "TRUE") {
            tp1++;
        }
        if (parko2 == "TRUE") {
            tp1++;
        }

        LatLng t1 = new LatLng(-37.565411, 143.856870);
        mMap.addMarker(new MarkerOptions().position(t1).title("Projected " + Math.round(tp1 / 2 * 100) + "% Full, " + (2 - tp1) + " Free"));
        //return ("Projected " + Math.round(tp1 / 2 * 100) + "% Full, " + (2 - tp1) + " Free");
        LatLng t2 = new LatLng(-37.566411, 143.856870);
        mMap.addMarker(new MarkerOptions().position(t2).title("Projected " + Math.round(tp1 / 2 * 100) + "% Full, " + "Estimated " + ((1-tp1 / 2) * 32) + " Free, Atleast " + (2 - tp1) + " Free"));
        }

/*    Live updates, future feature
private void doSomethingRepeatedly() {
        Toast.makeText(MapsActivity.this, "2", Toast.LENGTH_SHORT).show();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate( new TimerTask() {
            public void run() {
                try{
                    Toast.makeText(MapsActivity.this, "3", Toast.LENGTH_SHORT).show();
                    new Update().execute();

                }
                catch (Exception e) {
                    // TODO: handle exception
                }

            }
        }, 0, 400);
   }*/

}