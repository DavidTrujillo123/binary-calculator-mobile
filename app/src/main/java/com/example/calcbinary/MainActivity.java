package com.example.calcbinary;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Binary binary;
    private String operator;

    private TextView txtNumBin,
                txtOperationBin, txtNumDecimal, txtOperationDecimal;

    private Button btnResult, btnOne, btnCero;

    private void initVariables(){
        this.txtNumBin = findViewById(R.id.txtNumBin);
        this.txtOperationBin = findViewById(R.id.txtOperationBin);
        this.txtNumDecimal = findViewById(R.id.txtNumDecimal);
        this.txtOperationDecimal = findViewById(R.id.txtOperationDecimal);
        this.btnResult = findViewById(R.id.btnResult);
        this.btnCero = findViewById(R.id.btnCero);
        this.btnOne = findViewById(R.id.btnOne);

        this.binary = new Binary();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initVariables();
    }

    private String getStrTxtNumBin(){
        return this.txtNumBin.getText().toString();
    }

    //Controlers to operations
    private void genericController(String operator){
        this.operator = operator;
        this.txtOperationBin.setText(getOperationText(this.operator));
        this.txtOperationDecimal.setText(getOperationTextDec(this.operator));

        this.binary.setBinNumA(getStrTxtNumBin());
        this.txtNumBin.setText("0");
        this.txtNumDecimal.setText("0");
    }

    public void addControl(View v){
        genericController("+");
    }
    public void minusControl(View v){
        genericController("-");
    }
    public void multControl(View v){
        genericController("*");
    }
    public void divControl(View v){
        genericController("/");
    }

    public void equalControl(View v){
        String result = "";

        if(operator.equals("+")){
            result = this.binary.suma();
        }
        else if(operator.equals("-")){
            result = this.binary.resta();
        }
        else if(operator.equals("*")){
            result = this.binary.multiplicacion();
        }
        else if(operator.equals("/")){
            result = this.binary.division();
        }

        this.txtOperationBin.setText(
                this.binary.getBinNumA()+this.operator+ this.binary.getBinNumB()+"=");
        this.txtOperationDecimal.setText(
                this.binary.getDecNumA()+this.operator+this.binary.getDecNumB()+"="
        );

        this.txtNumBin.setText(result);
        this.txtNumDecimal.setText(this.binary.binarioADecimal(result)+"");

        this.binary.setBinNumA(result);
    }

    private String getOperationText(String operator){
        return this.binary.getBinNumB() + operator;
    }
    private String getOperationTextDec(String operator){
        String num = this.binary.binarioADecimal(this.binary.getBinNumB())+"";
        return num + operator;
    }

    //Delete digits
    private String deleteLastDigit(){
        String strNumBin = getStrTxtNumBin();

        if(strNumBin.length() == 1){
            return "0";
        }

        return strNumBin.substring(0, strNumBin.length() - 1);
    }

    public void deleteLast(View v){
        if(this.txtOperationBin.getText().toString().contains("=")){
            this.binary.setBinNumA("0");
            this.txtOperationBin.setText("");
            this.txtOperationDecimal.setText("");

            this.binary.setBinNumB(getStrTxtNumBin());
        }
        else{
            this.txtNumBin.setText(deleteLastDigit());
            this.txtNumDecimal.setText(
                    this.binary.binarioADecimal(this.txtNumBin.getText().toString())+"");
            this.binary.setBinNumB(getStrTxtNumBin());
        }
    }

    public void deleteAll(View v){
        this.binary.setBinNumA("0");
        this.binary.setBinNumB("0");

        this.txtNumBin.setText("0");
        this.txtOperationBin.setText("");

        this.txtNumDecimal.setText("0");
        this.txtOperationDecimal.setText("");

    }

    //Set Digits
    public void setDigit(String digitBin){
        String strNumBin = getStrTxtNumBin();
        if(strNumBin.length() == 1 && strNumBin.equals("0")){
            if(!digitBin.equals("0")){
                this.txtNumBin.setText(digitBin);
            }
        }
        else{
            String result = strNumBin+digitBin;
            this.txtNumBin.setText(result);
        }
        this.txtNumDecimal.setText(
                this.binary.binarioADecimal(this.txtNumBin.getText().toString())+"");
        this.binary.setBinNumB(getStrTxtNumBin());
    }

    public void setDigitOne(View v){
        setDigit("1");
    }
    public void setDigitCero(View v){
        setDigit("0");
    }
}