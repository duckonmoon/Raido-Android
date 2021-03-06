package ua.com.raido.raidoandroid.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.location.FusedLocationProviderClient;

import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.routing.OSRMRoadManager;
import org.osmdroid.bonuspack.routing.Road;
import org.osmdroid.bonuspack.routing.RoadManager;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.com.raido.raidoandroid.R;
import ua.com.raido.raidoandroid.constans.Constans;

public class DeleteMeActivity extends BaseActivity {
    @BindView(R.id.map)
    MapView mapView;


    FusedLocationProviderClient mFusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadOSMConfiguration();
        setContentView(R.layout.activity_delete_me);
        ButterKnife.bind(this);
        setMapConfiguration();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onPause();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    private void loadOSMConfiguration() {
        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
    }

    private void setMapConfiguration() {
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);

        IMapController mapController = mapView.getController();
        mapController.setZoom(Constans.ZOOM_OPTION);

        RotationGestureOverlay rotationGestureOverlay = new RotationGestureOverlay(mapView);
        rotationGestureOverlay.setEnabled(true);
        mapView.setMultiTouchControls(true);
        mapView.getOverlays().add(rotationGestureOverlay);


        /*mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION},
                    Constans.PERMISSION_LOCATION_READ);
            return;
        }

        setLocationInMap();*/
        ArrayList<GeoPoint> waypoints = new ArrayList<GeoPoint>();
        GeoPoint startPoint = new GeoPoint(49.8326679, 23.9421961);
        waypoints.add(startPoint);
        mapController.setCenter(startPoint);
        GeoPoint endPoint = new GeoPoint(50.4016991, 30.2525149);
        waypoints.add(endPoint);

        Marker m = new Marker(mapView);
        m.setTitle("Lviv");
        m.setIcon(ContextCompat.getDrawable(this,R.mipmap.ic_marker));
        m.setPosition(new GeoPoint(49.8326679,23.9421961));
        mapView.getOverlays().add(m);

        Handler handler = new Handler();

        new Thread(() -> {
            Road road = new OSRMRoadManager(this).getRoad(waypoints);
            Polyline roa = RoadManager.buildRoadOverlay(road);
            roa.setColor(R.color.colorAccent);
            handler.post(() -> {
                mapView.getOverlays().add(roa);
                mapView.invalidate();
            });
        }).start();
    }

    private void setLocationInMap() {
        try {
            /*mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, location -> {
                        if (location != null) {
                            GeoPoint startPoint = new GeoPoint(location.getLatitude(), location.getLongitude());
                            IMapController mapController = mapView.getController();
                            mapController.setCenter(startPoint);

                            Marker marker = new Marker(mapView);
                            marker.setPosition(new GeoPoint(location.getLatitude(),location.getLongitude()));
                            mapView.getOverlays().add(marker);
                        }
                    });*/


        } catch (SecurityException ignored) {
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == Constans.PERMISSION_LOCATION_READ) {
            setLocationInMap();
        }
    }
}
