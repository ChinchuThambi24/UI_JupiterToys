package Base;

public class AppConfig {
    public String browservalue;
    public Boolean windowMaximize;
    public String url;
    public String driverPath;
       public String implicitlyWait;

    public AppConfig() {
    }

    public String getBrowser() {
        return browservalue;
    }
   public Boolean getWindowMaximize() {
        return windowMaximize;
    }
    public String getUrl() {
        return url;
    }
    public String getDriverPath() {
        return driverPath;
    }
   public String getImplicitlyWait() {
        return implicitlyWait;
    }

}
