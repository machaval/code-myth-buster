package myth.buster;


public class MyMessageProcessor
{

    private int counter = 0;

    public int increase()
    {
    //    System.out.println("MyMessageProcessor.increase");
        return ++counter;
    }

    public int decrease()
    {
        System.out.println("MyMessageProcessor.decrease");
        return --counter;
    }


}
