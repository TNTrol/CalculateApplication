package com.example.myapplicationfirst;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplicationfirst.Command.CommandOperation;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private double[] _operands = new double[2];
    private int[] _indOp = new int[2];
    private int _size = 0;
    private CommandOperation _operationCommand;

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
            R.id.zero,
            R.id.dot);
    private List<Integer> _operationsIds = Arrays.asList(
            R.id.sub,
            R.id.plus,
            R.id.sing,
            R.id.mul,
            R.id.division,
            R.id.percent,
            R.id.equals,
            R.id.clear,
            R.id.del
    );

    public void setText(String text)
    {
        _textView.setText(text);
    }

    public String getText()
    {
        return _textView.getText().toString();
    }

    public void solve()
    {
        if(_size == 0 || _operationCommand == null)
            return;
        try {
                String number = _textView.getText().toString().substring(_indOp[_size] + 3);
                _operands[_size] = Double.parseDouble(number);
            }catch (Exception e){
            return;
        }
        _size = 0;
        String res = _operationCommand.execute(_operands[0], _operands[1]);
        _textView.setText(res);
        _operationCommand = null;
        if(res.equals("Error"))
            return;
        _operands[_size] = Double.parseDouble(res);
    }

    public void setOperation(CommandOperation op)
    {
        _operands[_size] = Double.parseDouble(getLastNumber());
        _size++;
        _indOp[_size] = _textView.getText().length();

        _operationCommand = op;
    }

    public void deleteChar()
    {
        String text;
        if(_textView.getText().toString().lastIndexOf(" ") == _textView.getText().length() - 1) {
            _operationCommand = null;
            text = _textView.getText().toString().substring(0, _textView.getText().length() - 3);
            _size--;
        }
        else {
            text = _textView.getText().toString().substring(0, _textView.getText().length() - 1);
        }
        _textView.setText(text);
    }

    public CommandOperation getCommandOperation()
    {
        return _operationCommand;
    }

    private Command.CommandInterface[] _commandArray = {Command.CleanCommand, Command.EqualsCommand, Command.SignCommand, Command.DelCommand};

    private TextView _textView;

    public String getLastNumber()
    {
        return _textView.getText().toString().substring(_indOp[_size]);
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
                String s = b.getText().toString();
                if(_textView.getText().equals("0") || _textView.getText().equals("Error") )
                {
                    _textView.setText(s);
                    return;
                }

                if(s.equals(".") && getLastNumber().contains("."))
                        return;
                if(getLastNumber().length() > 12)
                    return;
                _textView.setText("" + _textView.getText() + s);
            }
        };
        System.out.println( R.string.app_name);
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