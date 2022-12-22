import java.lang.Math;

abstract class Figury {
    public enum OneValueFigures implements InterfaceOneValue { 
        
        KOLO{
            public double obliczPole(int value){ 
                return Math.PI*value*value;
            }
            public double obliczObwod(int value){
                return Math.PI*2*value;
            }
        }, KWADRAT{
            public double obliczPole(int value){ 
                return value*value;
            }
            public double obliczObwod(int value){
                return 4*value;
            }
        }, SZESCIOKAT{
            public double obliczPole(int value){ 
                return (3*value*value*Math.sqrt(3))/2;
            }
            public double obliczObwod(int value){
                return 6*value;
            }
        }, PIECIOKAT{
            public double obliczPole(int value){ 
                return (value*value*Math.sqrt(25+10*Math.sqrt(5)))/4;
            }
            public double obliczObwod(int value){
                return 5*value;
            }
        };
         
    }

    public enum TwoValueFigures implements InterfaceTwoValue { 
        
        ROMB{
            public double obliczPole(int value1,int value2 ){ 
                
                return (Math.sin(Math.toRadians(value2)))*value1*value1;
            }
            public double obliczObwod(int value1,int value2 ){
                return 4*value1;
            }
        }, PROSTOKAT{
            public double obliczPole(int value1,int value2 ){ 
                
                return value1*value1;
            }
            public double obliczObwod(int value1,int value2 ){
                return 2*value1 + 2*value2;
            }
        };
         
    }
}