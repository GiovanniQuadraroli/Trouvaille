package gwc.com.trouvaille.Layout;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

import gwc.com.trouvaille.R;

public class WebPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_page);

        WebView webView = (WebView) findViewById(R.id.webview);
        webView.loadUrl("https://trouvaille-rails.herokuapp.com/user/sign_in");


    }

}
