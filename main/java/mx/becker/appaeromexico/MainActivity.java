package mx.becker.appaeromexico;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView menuListView;
    private Button btnCotizacion, btnGoogle, btnAcercaDe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCotizacion = findViewById(R.id.btnCotizacion);
        btnGoogle = findViewById(R.id.btnGoogle);
        btnAcercaDe = findViewById(R.id.btnAcercaDe);

        // Manejar el clic en el botón de Cotización
        btnCotizacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirPantallaCotizacion();
            }
        });

        // Manejar el clic en el botón de Google
        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirGoogle();
            }
        });

        // Manejar el clic en el botón de Acerca de...
        btnAcercaDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarAcercaDe();
            }
        });
    }

    private void abrirPantallaCotizacion() {
        Intent intent = new Intent(this, CotizacionActivity.class);
        startActivity(intent);
    }

    private void abrirGoogle() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://aeromexico.com/"));
        startActivity(intent);
    }

    private void mostrarAcercaDe() {
        // Implementa la lógica para mostrar la pantalla "Acerca de..."
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

}






