package tests;
import org.testng.annotations.Factory;

    public class TestFactory {

        @Factory
        public Object[] factoryMethod() {
            return new Object[]{
                    new RegistroTest("chrome"),
                    new RegistroTest("edge"),
                    new LoginTest("chrome"),
                    new LoginTest("edge")
            };
        }
    }

