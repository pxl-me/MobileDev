package pxl.mobile.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//Вікно містить згорнутий список та кнопки «ОК» і «Cancel». Вивести обране
//значення зі списку
//Варіант 4. Боднар О. ІС-81

public class MainActivity extends AppCompatActivity {
    private String[] autoBrands = {"Lamborghini", "Aston Martin", "BMW", "Tesla",
            "Ferrari", "Porsche", "Mercedes-Benz", "McLaren", "Lexus", "Zhiguli"};
    TextView myTextView;
    Button buttonOk, buttonCancel;
    String selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    //создаем "деятельность" интерфейсы, связи данных и т.п.
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> autoBrandsAdapter =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, autoBrands);
        //адаптер (тут массив для подальшей связи "текствью" и элементов)
        //simple_spinner_item - представление элемента списка

        autoBrandsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //шаблон выпадающего списка
        Spinner spAutoBrands = (Spinner) findViewById(R.id.spAutoBrands);
        //выпадающий список
        //spAutoBrands - id из представления
        spAutoBrands.setAdapter(autoBrandsAdapter); //установили адаптер
        spAutoBrands.setPrompt("Автомобільні бренди"); //заголовок
        spAutoBrands.setOnItemSelectedListener(onItemSelectedListener());

        myTextView = (TextView) findViewById(R.id.myTextView);
        buttonOk = (Button) findViewById(R.id.buttonOk);
        buttonCancel = (Button) findViewById(R.id.buttonCancel);
        //текствью и 2 кнопочки, которые будут на экране

        View.OnClickListener obuttonOk = v -> myTextView.setText(selectedItem);
        buttonOk.setOnClickListener(obuttonOk);
        //2 метод взаемодействия с кнопочкой

        View.OnClickListener obuttonCancel = v -> myTextView.setText("Тикни у список..");
        buttonCancel.setOnClickListener(obuttonCancel);
    }

    AdapterView.OnItemSelectedListener onItemSelectedListener() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = parent.getSelectedItem().toString();
                Toast.makeText(getBaseContext(), "Нажмите ОК", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
    }

}