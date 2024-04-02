import org.testng.annotations.Test;

public class Child2 extends Parent{

    @Test
    public void DesktopScript() {
        try {
            driver.get("https://lambdatest.github.io/sample-todo-app/");
            Thread.sleep(10000);
            status="passed";

        } catch (Exception e) {
            System.out.println(e);
            status = "failed";
        }
    }
}
