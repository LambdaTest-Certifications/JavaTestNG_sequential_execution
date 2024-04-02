import org.testng.annotations.Test;

public class Child1 extends Parent {

    @Test
    public void DesktopScript() {
        try {
            driver.get("https://www.google.com");
            Thread.sleep(10000);
            status="passed";

        } catch (Exception e) {
            System.out.println(e);
            status = "failed";
        }
    }
}
