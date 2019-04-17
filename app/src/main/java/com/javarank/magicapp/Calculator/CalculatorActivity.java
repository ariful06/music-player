package com.javarank.magicapp.Calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.javarank.magicapp.R;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {
    EditText e1;
    EditText e2;
    int count=0;
    String expression="";
    String text="";
    Double result=0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        e1=findViewById(R.id.editText1);
        e2=findViewById(R.id.editText2);
        e2.setHint("0");
    }

    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.num0:

                e2.setText(e2.getText()+"0");
                break;

            case R.id.num1:

                e2.setText(e2.getText()+"1");
                break;

            case R.id.num2:

                e2.setText(e2.getText()+"2");
                break;

            case R.id.num3:

                e2.setText(e2.getText()+"3");
                break;


            case R.id.num4:

                e2.setText(e2.getText()+"4");
                break;

            case R.id.num5:

                e2.setText(e2.getText()+"5");
                break;

            case R.id.num6:

                e2.setText(e2.getText()+"6");
                break;

            case R.id.num7:

                e2.setText(e2.getText()+"7");
                break;

            case R.id.num8:

                e2.setText(e2.getText()+"8");
                break;

            case R.id.num9:

                e2.setText(e2.getText()+"9");
                break;

            case R.id.dot:
                if(count==0 && e2.length()!=0)
                {
                    e2.setText(e2.getText()+".");
                    count++;
                }
                break;

            case R.id.clear:
                e1.setText("");
                e2.setText("");
                count=0;
                expression="";
                break;

            case R.id.backSpace:
                text=e2.getText().toString();
                if(text.length()>0)
                {
                    if(text.endsWith("."))
                    {
                        count=0;
                    }
                    String newText=text.substring(0,text.length()-1);

                    if(text.endsWith(")"))
                    {
                        char []a=text.toCharArray();
                        int pos=a.length-2;
                        int counter=1;

                        for(int i=a.length-2;i>=0;i--)
                        {
                            if(a[i]==')')
                            {
                                counter++;
                            }
                            else if(a[i]=='(')
                            {
                                counter--;
                            }

                            else if(a[i]=='.')
                            {
                                count=0;
                            }

                            if(counter==0)
                            {
                                pos=i;
                                break;
                            }
                        }
                        newText=text.substring(0,pos);
                    }

                    if(newText.equals("-")||newText.endsWith("sqrt"))
                    {
                        newText="";
                    }

                    else if(newText.endsWith("^"))
                        newText=newText.substring(0,newText.length()-1);

                    e1.setText(newText);
                }
                break;

            case R.id.plus:
                operationClicked("+");
                break;

            case R.id.minus:
                operationClicked("-");
                break;

            case R.id.divide:
                operationClicked("/");
                break;

            case R.id.multiply:
                operationClicked("*");
                break;

            case R.id.sqrt:
                if(e2.length()!=0)
                {
                    text=e2.getText().toString();
                    e2.setText("sqrt(" + text + ")");
                }
                break;

            case R.id.square:
                if(e2.length()!=0)
                {
                    text=e2.getText().toString();
                    e2.setText("("+text+")^2");
                }
                break;

            case R.id.posneg:
                if(e2.length()!=0)
                {
                    String s=e2.getText().toString();
                    char arr[]=s.toCharArray();
                    if(arr[0]=='-')
                        e2.setText(s.substring(1,s.length()));
                    else
                        e2.setText("-"+s);
                }
                break;

            case R.id.equal:
                if(e2.length()!=0)
                {
                    text=e2.getText().toString();
                    expression=e1.getText().toString()+text;
                }
                e1.setText("");
                if(expression.length()==0)
                    expression="0.0";
                DoubleEvaluator evaluator = new DoubleEvaluator();
                try
                {
                    result=new ExtendedDoubleEvaluator().evaluate(expression);
                    if(!expression.equals("0.0"))

                        e1.setText(result+"");
                       e2.setText("");
                }
                catch (Exception e)
                {
                    e2.setText("Invalid Expression");
                    e1.setText("");
                    expression="";
                    e.printStackTrace();
                }
                break;
            case R.id.openBracket:
                e1.setText(e1.getText()+"(");
                break;
            case R.id.closeBracket:
                e1.setText(e1.getText()+")");
                break;
        }
    }

    private void operationClicked(String op)
    {
        if(e2.length()!=0)
        {
            String text=e2.getText().toString();
            e1.setText(e1.getText() + text+op);
            e2.setText("");
            count=0;
        }
        else
        {
            String text=e1.getText().toString();
            if(text.length()>0)
            {
                String newText=text.substring(0,text.length()-1)+op;
                e1.setText(newText);
            }
        }
    }
}
