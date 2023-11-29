package Base;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class AppConfigReader {
    public  static ObjectMapper objectMapper;
    public  static File appConfigJsonFile;
    public  static Base.AppConfig appConfig;
    public static String AppConfigLocation = "src//test//java//Base//AppConfig.json";
    public AppConfigReader() {
    }

    public  static String GetBrowser() throws IOException {
        objectMapper = new ObjectMapper();
        appConfigJsonFile = new File(AppConfigLocation);
        appConfig = objectMapper.readValue(appConfigJsonFile, AppConfig.class);
        return appConfig.getBrowser();
    }

    public static Boolean GetBrowserWindowSize() throws IOException {
        appConfig = objectMapper.readValue(appConfigJsonFile, AppConfig.class);
        return appConfig.getWindowMaximize();
    }

    public static String GetUrl() throws IOException {
        appConfig = objectMapper.readValue(appConfigJsonFile, AppConfig.class);
        return appConfig.getUrl();
    }

    public static String GetDriverPath() throws IOException {
        appConfig = objectMapper.readValue(appConfigJsonFile, AppConfig.class);
        return appConfig.getDriverPath();
    }

    public static String GetImplicitlyWait() throws IOException {
        appConfig = objectMapper.readValue(appConfigJsonFile, AppConfig.class);
        return appConfig.getImplicitlyWait();
    }
}
