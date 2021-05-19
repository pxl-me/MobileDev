package pxl.mobile.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;

//Вікно містить згорнутий список та кнопки «ОК» і «Cancel». Вивести обране
//значення зі списку + робота з файлом по лр№3
//Варіант 4. Боднар О. ІС-81

public class MainActivity extends AppCompatActivity {
    private final String[] autoBrands = {"Lamborghini ", "Aston Martin ", "BMW ", "Tesla ",
            "Ferrari ", "Porsche ", "Mercedes-Benz ", "McLaren ", "Lexus ", "Zhiguli "};
    final static String filename = "somefile";
    final static String defline = "Тикни у список..";
    final static File file = new File(filename);

    TextView myTextView;
    Button buttonOk, buttonCancel, saveBtn, switchBtn;
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
        Spinner spAutoBrands = findViewById(R.id.spAutoBrands);
        //выпадающий список
        //spAutoBrands - id из представления
        spAutoBrands.setAdapter(autoBrandsAdapter); //установили адаптер
        spAutoBrands.setPrompt("Автомобільні бренди"); //заголовок
        spAutoBrands.setOnItemSelectedListener(onItemSelectedListener());

        myTextView = findViewById(R.id.myTextView);
        buttonOk = findViewById(R.id.buttonOk);
        buttonCancel = findViewById(R.id.buttonCancel);
        switchBtn = findViewById(R.id.switchBtn);
        saveBtn = findViewById(R.id.saveBtn);

        //текствью и кнопочки, которые будут на экране

        View.OnClickListener obuttonOk = v -> myTextView.setText(selectedItem);
        buttonOk.setOnClickListener(obuttonOk);
        //2 метод взаемодействия с кнопочкой

        View.OnClickListener obuttonCancel = v -> myTextView.setText(defline);
        buttonCancel.setOnClickListener(obuttonCancel);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myTextView.getText().toString() != defline) {
                    saveData(file, myTextView.getText().toString());
                    Log.d("LOG", "Файл обновлен!");
                    Toast.makeText(getBaseContext(), "Файл обновлен!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        switchBtn.setOnClickListener(this::onClick);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.switchBtn:
                Intent intent = new Intent(this, ReadActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    public void saveData(File f, String data)
    {
        try {
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(openFileOutput(filename, MODE_APPEND))); // fast cos buffering data + closed file
            writer.write(data);
            writer.close();
        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace(); // if data empty
        }
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