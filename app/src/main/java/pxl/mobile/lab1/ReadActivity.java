package pxl.mobile.lab1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import static pxl.mobile.lab1.MainActivity.file;
import static pxl.mobile.lab1.MainActivity.filename;

public class ReadActivity extends AppCompatActivity{

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        TextView testText;
        Button readBtn, delBtn;
        testText = findViewById(R.id.testText);
        readBtn = findViewById(R.id.readBtn);
        delBtn = findViewById(R.id.delBtn);

        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFile(filename);
            }
        });

        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (readData(file) != null) testText.setText(readData(file));
                else testText.setText("Файл пуст :(");
            }
        });
    }

    public String readData(File f){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput(filename)));
            String line = "";
            while ((line = reader.readLine()) != null) {
                return line;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}