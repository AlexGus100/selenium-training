import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.google.common.collect.Iterables.isEmpty;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdminGeoZonesPageTest {

    private WebDriver driver;

    @BeforeEach
    public void start() {
        driver = new ChromeDriver();
    }

    @Test
    public void CheckCountriesGeoZonesSort() {
        authAdmin();
        openGeoZonesPage();

        List<WebElement> rows = driver.findElement(By.name("geo_zones_form")).findElements(By.className("row"));
        for (int i = 0; i < rows.size(); i++) {
            ArrayList<String> geoZonesNames = new ArrayList<>();
            rows.get(i).findElement(By.cssSelector("a:nth-of-type(1)")).click();

            List<WebElement> geoZonesNamesRows = driver.findElement(By.id("table-zones")).findElements(By.tagName("tr"));
            geoZonesNamesRows.remove(0);
            geoZonesNamesRows.remove(geoZonesNamesRows.size()-1);

            for (WebElement row : geoZonesNamesRows) {
                geoZonesNames.add(row.findElement(By.cssSelector("td:nth-of-type(3)")).findElement(By.cssSelector("[selected]")).getText());
            }
            assertTrue(isSorted(geoZonesNames));

            openGeoZonesPage();
            rows = driver.findElement(By.name("geo_zones_form")).findElements(By.className("row"));
        }
    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }

    public void authAdmin() {
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    public void openGeoZonesPage() {
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
    }

    public static boolean isSorted(List<String> listOfStrings) {
        if (isEmpty(listOfStrings) || listOfStrings.size() == 1) {
            return true;
        }
        Iterator<String> iter = listOfStrings.iterator();
        String current, previous = iter.next();
        while (iter.hasNext()) {
            current = iter.next();
            if (previous.compareTo(current) > 0) {
                return false;
            }
            previous = current;
        }
        return true;
    }
}
