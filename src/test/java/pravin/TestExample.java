package pravin;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestExample {

    @Test
    public void m1() {
        Assert.fail();
    }

    @Test(dependsOnMethods = "m1")
    public void m2() {
        System.out.println("m2");
        System.out.println("m3");
        System.out.println("m4");
        System.out.println("m5");
        System.out.println("m6");
    }

}
