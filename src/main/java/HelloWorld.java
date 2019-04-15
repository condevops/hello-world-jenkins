
public class HelloWorld
{
    public static void main(String[] args)
    {
        System.out.println("hello world");
        System.out.println("hello world");
        System.out.println("hello world");
        System.out.println("hello world");

        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
