package com.example.myapplicationfirst;

public class Command {
    public interface CommandInterface
    {
        boolean execute(String s, MainActivity parent);
    }
    public interface  CommandOperation
    {
        boolean execute(double a, double b);
    }

    public static CommandInterface DotCommand = (String s, MainActivity parent)->{
        if(!s.equals("."))
            return false;
        if( parent.getLastNumber().contains("."))
            return true;
        String res = (String) parent.getTextView().getText();
        parent.getTextView().setText(res + ".");
        parent.isCommand(false);
        return true;
    };

    public static CommandInterface SignCommand = (String s, MainActivity parent)->{

        if(s.equals("-") || s.equals("+") || s.equals("/") || s.equals("x") || s.equals("%") )
        {
            int l = parent.getTextView().getText().length();
            char last = parent.getTextView().getText().charAt(l - 2);
            if(last == '+' || last == '-' || last == '/' || last == 'x' || last == '%')
                return true;
            String res = (String) parent.getTextView().getText();
            parent.getTextView().setText(res + " " + s + " ");
            parent.isCommand(true);
            return true;
        }
        return false;
    };

    public static CommandInterface EqualsCommand = (String s, MainActivity parent) ->{
        if(!s.equals("="))
            return false;
        String reg = " ";
        String[] number = parent.getTextView().getText().toString().split(" ");
        if(number.length > 3)
            return true;
        double[] numberDouble = new double[2];
        for(int i = 0; i < number.length; i += 2)
        {
            numberDouble[i / 2] = Double.parseDouble(number[i]);
        }
        if(numberDouble[1] == 0.0 )
            return true;
        double res = 0.0;
        switch (number[1])
        {
            case "+":
                res = numberDouble[0] + numberDouble[1];
                break;
            case "-":
                res = numberDouble[0] - numberDouble[1];
                break;
            case "x":
                res = numberDouble[0] * numberDouble[1];
                break;
            case "/":
                res = numberDouble[0] / numberDouble[1];
                break;
        }
        parent.getTextView().setText(String.valueOf(res));
        //тут нужно будет сделать функцию парсера и подсчета
        return true;
    };

    public static CommandInterface CleanCommand = (String s, MainActivity parent) ->{
        if(!s.equals("C"))
            return false;
        parent.getTextView().setText("");
        parent.isCommand(true);
        return true;
    };

    public static CommandInterface DelCommand = (String s, MainActivity parent) ->{
        if(!s.equals("Del"))
            return false;
        int l = parent.getTextView().getText().length();
        if(l < 2)
            return true;
        String last = parent.getTextView().getText().toString();
        int a = 1;
        if(last.toCharArray()[l - 1] == ' ')
            a+=2;
        parent.getTextView().setText(parent.getTextView().getText().toString().substring(0, l - a)); /// подумать позже
        parent.isCommand(true);
        return true;
    };


}
