package myth.buster;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by machaval on 7/18/14.
 */
public class ReflectionIsAsFastAsDirectCall
{

    public static final int LOOP_AMOUNT = 10000;
    private Method increase;
    private MyMessageProcessor reflected = new MyMessageProcessor();
    private MyMessageProcessor direct = new MyMessageProcessor();

    @Before
    public void warmup() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException
    {
        increase = MyMessageProcessor.class.getDeclaredMethod("increase");
        int i = 0;
        while (i < 50)
        {
            increase.invoke(reflected);
            direct.increase();
            i++;
        }
    }


    @Test
    public void reflectionVsDirectCall() throws InvocationTargetException, IllegalAccessException
    {

        final long start = System.nanoTime();
        int i = 0;
        while (i < LOOP_AMOUNT)
        {
            increase.invoke(reflected);
            i++;
        }


        long timeTakenReflected = System.nanoTime() - start;


        final long startDirect = System.nanoTime();
        i = 0;
        while (i < LOOP_AMOUNT)
        {
            direct.increase();
            i++;
        }

        long timeTakenDirect = System.nanoTime() - startDirect;

        System.out.println("timeTakenDirect = " + timeTakenDirect);
        System.out.println("timeTakenReflected = " + timeTakenReflected);
        System.out.println("Reflected is " + (timeTakenReflected / timeTakenDirect) + " slower.");

    }

}
