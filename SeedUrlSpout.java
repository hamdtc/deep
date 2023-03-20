import java.io.*;
import java.net.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataExtractorBolt extends BaseRichBolt {
    private WebDriver driver;
    private OutputCollector collector;

    @Override
    public void prepare(Map conf, TopologyContext context, OutputCollector collector) {
        this.collector = collector;
        // Initialize the Selenium WebDriver
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        driver = new ChromeDriver(options);
    }

    @Override
    public void execute(Tuple tuple) {
        String url = tuple.getStringByField("url");
        try {
            // Navigate to the URL and get the HTML source code
            driver.get(url);
            String html = driver.getPageSource();
            // Take a screenshot of the full page and encode it in base64
            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            String base64Image = Base64.getEncoder().encodeToString(screenshotBytes);
            // Emit the extracted data for storage
            collector.emit(new Values(url, html, base64Image));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


