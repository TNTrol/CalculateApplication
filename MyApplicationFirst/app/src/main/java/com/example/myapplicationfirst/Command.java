package com.example.myapplicationfirst;

public class Command {
    public interface CommandInterface
    {
        boolean execute(String s, MainActivity parent);
    }
    public interface  CommandOperation
    {
        String execute(double a, double b);
    }

    public static CommandInterface SignCommand = (String s, MainActivity parent)->{

        if(s.equals("-") || s.equals("+") || s.equals("/") || s.equals("x") || s.equals("%") ) {
            if (parent.getCommandOperation() != null)
                return true;

            switch (s) {
                case "+": {
                    parent.setOperation(Command.sum);
                    parent.setText(parent.getText() + " + ");
                    return true;
                }
                case "-": {
                    parent.setOperation(Command.minus);
                    parent.setText(parent.getText() + " - ");
                    return true;
                }
                case "/": {
                    parent.setOperation(Command.sub);
                    parent.setText(parent.getText() + " / ");
                    return true;

                }
                case "x": {
                    parent.setOperation(Command.mul);
                    parent.setText(parent.getText() + " + ");
                    return true;
                }
                default:
                    return false;
            }
        }
        return false;
    };

    public static CommandInterface EqualsCommand = (String s, MainActivity parent) ->{
        if(!s.equals("="))
            return false;
        parent.solve();
        return true;
    };

    public static CommandInterface CleanCommand = (String s, MainActivity parent) ->{
        if(!s.equals("C"))
            return false;
        parent.setText("");
        return true;
    };

    public static CommandInterface DelCommand = (String s, MainActivity parent) ->{
        if(!s.equals("Del"))
            return false;
        parent.deleteChar();
        return true;
    };

    public static CommandOperation sum = (double a, double b)->{
        return a + b + "";
    };
    public static CommandOperation minus = (double a, double b)->{
        return a - b + "";
    };
    public static CommandOperation mul = (double a, double b)->{
        return a * b + "";
    };
    public static CommandOperation sub = (double a, double b)->{
        if(b == 0)
            return "Error";
        return a / b + "";
    };


}
