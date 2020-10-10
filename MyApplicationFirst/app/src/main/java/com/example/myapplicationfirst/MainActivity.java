package com.example.myapplicationfirst;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Operation _operationState;
    private double[] _operands = new double[2];
    private int _size = 0;

    private List<Integer> _numberIds = Arrays.asList(
            R.id.nine,
            R.id.eight,
            R.id.seven,
            R.id.six,
            R.id.five,
            R.id.four,
            R.id.three,
            R.id.two,
            R.id.one,
            R.id.zero);
    private List<Integer> _operationsIds = Arrays.asList(
            R.id.sub,
            R.id.plus,
            R.id.sing,
            R.id.mul,
            R.id.division,
            R.id.percent,
            R.id.dot,
            R.id.equals,
            R.id.clear,
            R.id.del
    );

    public void setOperation(Operation op)
    {
        _operationState = op;
    }

    public void setText(String text)
    {
        _textView.setText(text);
    }

    public String getText()
    {
        return _textView.getText().toString();
    }
    public void addOperand(double a)
    {
        _operands[_size] = a;
        _size++;
    }

    public void delOperands()
    {
        _size--;
        _operands[_size] = 0; 
    }

    public void solve()
    {

    }

    private Command.CommandInterface[] _commandArray = {Command.CleanCommand, Command.DotCommand, Command.EqualsCommand, Command.SignCommand, Command.DelCommand};

    private int _startNumber = 0;
    private boolean _operation = false;
    private TextView _textView;

    public TextView getTextView()
    {
        return _textView;
    }

    public String getLastNumber()
    {
        return _textView.getText().toString().substring(_startNumber);
    }

    public void isCommand(boolean t)
    {
        _operation = t;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _textView = findViewById(R.id.output);
        _textView.setText("");
        View.OnClickListener onButtonClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                if(_textView.getText().equals("0"))
                {
                    _textView.setText(b.getText());
                    return;
                }

                String str = "" + _textView.getText() + b.getText();
                _textView.setText(str);
                if(_operation)
                {
                    _startNumber = _textView.getText().length() - 1;
                    _operation = false;
                }
            }
        };
        View.OnClickListener onOperationClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                if(_textView.getText().length() == 0)
                    return;
                for(Command.CommandInterface command : _commandArray)
                    if(command.execute(b.getText().toString(), MainActivity.this))
                        return;
            }
        };
            for(Integer numberId: _numberIds){
                findViewById(numberId).setOnClickListener(onButtonClick);
            }
            for(Integer operationId: _operationsIds){
                findViewById(operationId).setOnClickListener(onOperationClick);
            }


    }
}