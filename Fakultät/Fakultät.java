import java.util.concurrent.TimeUnit;
public class Fakultät
{

    public Fakultät() 
    {
        
    }
    
    public int fakWhile(int n) 
    {
        int l = 1;
        long startTime = System.nanoTime();
        while(n > 0)
        {
            l = l * n;
            n--;
        }
        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;
        System.out.println("Execution time in nanoseconds: " + timeElapsed);
        System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
        return l;
    }
    public int fakRekursivMitZeit(int n)
    {
        int o = 0;
        long startTime = System.nanoTime();
        o = fakRekursiv(n);
        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;
        System.out.println("Execution time in nanoseconds: " + timeElapsed);
        System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
        return o;
    }
        
    public int fakRekursiv(int n)
    {
        int l = 1;
        if (n <= 1) 
        {
            return 1;
        }else 
        {
            return fakRekursiv(n - 1) * n;
        }
    }
}