import org.analyzer.console.core.ConsoleBuild;
import org.analyzer.console.util.PlatformUtil;
import org.junit.Test;

public class ConsoleGenerateTest {

    @Test
    public void test(){
    System.out.println(PlatformUtil.OS);
    System.out.println(
            new ConsoleBuild("tar")
                    .addOptions("x")
                    .addOptions("v")
                    .addOptions("f")
                    .addParam("/app/a.tar").build()
      );

    System.out.println(
            new ConsoleBuild("jstat")
                    .addOptions("gc")
                    .addParam("15672").build()
    );
    }
}
