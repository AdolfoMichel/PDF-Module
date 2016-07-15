package com.example.adolfo.pdfprueba;

import android.graphics.Rect;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.graphics.pdf.PdfDocument.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static String logtag = "TwoButtonApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button PDFButton = (Button)findViewById(R.id.button);
        PDFButton.setOnClickListener(ButtonListener); //Registrar el listener del boton
    }

    private View.OnClickListener ButtonListener = new View.OnClickListener() {
        public void onClick(View v) {

            PdfDocument document = new PdfDocument();
            // crate a page description
            PageInfo pageInfo = new PageInfo.Builder(1500, 800, 1).create();
            // create a new page from the PageInfo
            Page page = document.startPage(pageInfo);
            // repaint the user's text into the page
            View content = findViewById(R.id.LayoutPDF);
            content.draw(page.getCanvas());
            // do final processing of the page
            document.finishPage(page);
            String path = " ";
            try {
                path = Environment.getExternalStorageDirectory() + "/prueba.pdf";
                File f = new File(path);
                FileOutputStream fos = new FileOutputStream(f);
                document.writeTo(fos);
                document.close();
                fos.close();

            } catch (IOException e) {
                throw new RuntimeException("Error generating file. " + e, e);
            }
        }
    };
}
