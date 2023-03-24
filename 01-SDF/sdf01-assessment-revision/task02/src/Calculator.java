package task02.src;

import java.io.Console;
import java.util.Arrays;
import java.util.List;

public class Calculator {

    // Constants
    public static final String ADD = "+";
    public static final String MINUS = "-";
    public static final String MULTI = "*";
    public static final String DIVIDE = "/";
    public static final List<String> OPERATORS = Arrays.asList(ADD, MINUS, MULTI, DIVIDE);

    public static void main(String[] args) {

        float $last = 0;

        Console cons = System.console();
        Boolean isQuit = false;
        System.out.println("Welcome!");

        while(!isQuit) {

            String input = cons.readLine("> ");

            if(input.toLowerCase().contains("exit")) {
                System.out.println("Bye bye!");
                isQuit = true;
            } else {

                String[] splitString = input.split(" ");
                if(splitString.length < 3 || splitString.length > 3) {
                    System.out.println("Invalid input");
                } else {

                    if(!OPERATORS.contains(splitString[1])) {
                        System.out.println("Please use a valid operator(+ - * /)");
                    } else {

                        if(splitString[0].equals("$last") && splitString[2].equals("$last")) {
                            
                            float firstNum = $last;
                            String operator = splitString[1];
                            float secondNum = $last;

                            $last = Calculate(firstNum, operator, secondNum);


                        } else if(splitString[0].equals("$last")) {

                            try {

                                float firstNum = $last;
                                String operator = splitString[1];
                                float secondNum = Float.parseFloat(splitString[2]);

                                $last = Calculate(firstNum, operator, secondNum);

                                
                            } catch (NumberFormatException e) {
                                System.out.println("Value is invalid, make sure you've entered a number or $last");
                            }


                        } else if(splitString[2].equals("$last")) {

                            try {

                                float firstNum = Float.parseFloat(splitString[0]);
                                String operator = splitString[1];
                                float secondNum = $last;

                                $last = Calculate(firstNum, operator, secondNum);

                                
                            } catch (NumberFormatException e) {
                                System.out.println("Value is invalid, make sure you've entered a number or $last");
                            }

                        } else {
                            try {

                                float firstNum = Float.parseFloat(splitString[0]);
                                String operator = splitString[1];
                                float secondNum = Float.parseFloat(splitString[2]);

                                $last = Calculate(firstNum, operator, secondNum);

                                
                            } catch (NumberFormatException e) {
                                System.out.println("Value is invalid, make sure you've entered a number or $last");
                            }
                        }

                        if($last == (int)$last) {
                            System.out.println((int)$last);
                        } else {

                            System.out.println($last);

                        }

                    }      

                }

            }

        }
        
    }

    public static float Calculate(float firstNum, String operator, float secondNum) {
        float sum = 0;

        switch(operator) {

        case("+"):
            sum = firstNum + secondNum;
            break;
        case("-"):
            sum = firstNum - secondNum;
            break;
        case("*"):
            sum = firstNum * secondNum;
            break;
        case("/"):
            sum = firstNum / secondNum;
            break;
        default:
            break;

        }

        if(sum == (int)sum) {
            return (int)sum;
        }

        return sum;
    }
    
}
