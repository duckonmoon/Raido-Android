package ua.com.raido.raidoandroid.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.com.raido.raidoandroid.R;

/**
 * Google destroyed all our hopes to use their api for free
 * @deprecated Do not use this class!
 */
@Deprecated
public class DeleteWebMapViewActivity extends AppCompatActivity {

    @BindView(R.id.web_view)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_web_map_view);
        ButterKnife.bind(this);
        webView.loadUrl("https://www.google.com.ua/maps/dir/%D0%9A%D0%B8%D1%97%D0%B2,+02000/%D0%9B%D1%8C%D0%B2%D1%96%D0%B2,+%D0%9B%D1%8C%D0%B2%D1%96%D0%B2%D1%81%D1%8C%D0%BA%D0%B0+%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%82%D1%8C,+79000/@50.1800199,26.131361,8z/data=!4m13!4m12!1m5!1m1!1s0x40d4cf4ee15a4505:0x764931d2170146fe!2m2!1d30.5234!2d50.4501!1m5!1m1!1s0x473add7c09109a57:0x4223c517012378e2!2m2!1d24.029717!2d49.839683");
        webView.clearCache(true);
        webView.clearHistory();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
    }
}
