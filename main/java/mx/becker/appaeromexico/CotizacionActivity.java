package mx.becker.appaeromexico;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CotizacionActivity extends AppCompatActivity {

    private EditText nombreEditText, kilometrosEditText, pasajerosEditText;
    private Spinner temporadaSpinner;
    private RadioButton socioRadioButton, estudianteRadioButton;
    private Button cotizarButton;
    private TextView resultadoTextView;
    private double tarifa;

    // Aquí deberías definir tus tarifas por temporada y otros valores relevantes
    // ...

    private final double TARIFA1 = 0.8;
    private final double TARIFA2 = 1.05;
    private final double TARIFA3 = 1.45;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cotizacion);

        nombreEditText = findViewById(R.id.nombreEditText);
        kilometrosEditText = findViewById(R.id.kilometrosEditText);
        pasajerosEditText = findViewById(R.id.pasajerosEditText);
        temporadaSpinner = findViewById(R.id.temporadaSpinner);
        socioRadioButton = findViewById(R.id.socioRadioButton);
        estudianteRadioButton = findViewById(R.id.estudianteRadioButton);
        cotizarButton = findViewById(R.id.cotizarButton);
        resultadoTextView = findViewById(R.id.resultadoTextView);
        temporadaSpinner = findViewById(R.id.temporadaSpinner);

        // Crear un ArrayAdapter con las opciones deseadas
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.opciones_temporada, android.R.layout.simple_spinner_item);

        // Especificar el diseño del spinner desplegado
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Asignar el adaptador al Spinner
        temporadaSpinner.setAdapter(adapter);

        temporadaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Aquí se ejecutará cuando se seleccione una temporada
                String temporadaSeleccionada = parentView.getItemAtPosition(position).toString();

                // Llamar a una función para obtener la tarifa según la temporada seleccionada
                tarifa = obtenerTarifaPorTemporada(temporadaSeleccionada);

                // Realizar acciones adicionales si es necesario con la tarifa
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Aquí se ejecuta cuando no hay nada seleccionado
                Toast.makeText(CotizacionActivity.this, "Selecciona una temporada", Toast.LENGTH_SHORT).show();
            }
        });


        cotizarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = nombreEditText.getText().toString();
                double kilometros = Double.parseDouble(kilometrosEditText.getText().toString());
                int pasajeros = Integer.parseInt(pasajerosEditText.getText().toString());
                String temporada = temporadaSpinner.getSelectedItem().toString();
                double precioNeto = kilometros * tarifa * pasajeros;
                double descuentoMembresia = socioRadioButton.isChecked() ? 0.25 * precioNeto : 0;
                double descuentoEstudiante = estudianteRadioButton.isChecked() ? 0.1 * precioNeto : 0;
                double totalAPagar = precioNeto - descuentoMembresia - descuentoEstudiante;

                String resultado = "\n" +
                        "Cliente: " + nombre + "\n" +
                        "Kilómetros: " + kilometros + "\n" +
                        "Pasajeros: " + pasajeros + "\n" +
                        "Temporada: " + temporada + "\n" +
                        "Descuento Membresía: " + descuentoMembresia + "\n" +
                        "Descuento Estudiante: " + descuentoEstudiante + "\n" +
                        "Total a Pagar: " + totalAPagar;

                resultadoTextView.setText(resultado);
                Toast.makeText(CotizacionActivity.this, "Cotización calculada", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private double obtenerTarifaPorTemporada(String temporada) {
        double tarifa = 0.0; // Valor predeterminado o tarifa base
        switch (temporada) {
            case "Febrero-Marzo":
                tarifa = TARIFA1;
                break;
            case "Mayo-Junio":
                tarifa = TARIFA2;
                break;
            case "Septiembre-Octubre":
                tarifa = TARIFA3;
                break;
            // Agrega más casos para otras temporadas si es necesario
            default:
                // Valor predeterminado si no coincide con ninguna temporada conocida
                break;
        }
        return tarifa;
    }

}
