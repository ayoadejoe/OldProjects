package newIntros;

interface FuncInterface 
{ 
    // An abstract function 
    void abstractFun(int x); 
} 


class Test 
{ 
    public static void main(String args[]) 
    { 
        // lambda expression to implement above 
        // functional interface. This interface 
        // by default implements abstractFun() 
        FuncInterface fobj = (int x)->System.out.println(2*x); 
  
        // This calls above lambda expression and prints 10. 
        fobj.abstractFun(5); 
    } 
} 