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

public class AdminCountriesPageTest {

    private WebDriver driver;

    @BeforeEach
    public void start() {
        driver = new ChromeDriver();
    }

    @Test
    public void CheckCountriesSort() {
        authAdmin();
        openCountriesPage();

        ArrayList<String> countryNames = new ArrayList<>();
        List<WebElement> rows = driver.findElement(By.name("countries_form")).findElements(By.className("row"));
        for (WebElement row : rows) {
            countryNames.add(row.findElement(By.cssSelector("td:nth-of-type(5)")).getText());
        }
        assertTrue(isSorted(countryNames));
    }

    @Test
    public void CheckCountriesGeoZonesSort() {
        authAdmin();
        openCountriesPage();

        ArrayList<Integer> rowsWithGeoZones = new ArrayList<>();
        List<WebElement> rows = driver.findElement(By.name("countries_form")).findElements(By.className("row"));
        for (int i = 0; i < rows.size(); i++) {
            int countryGeoZonesQuantity = Integer.parseInt(rows.get(i).findElement(By.cssSelector("td:nth-of-type(6)")).getText());
            if (countryGeoZonesQuantity>0) {
                rowsWithGeoZones.add(i+2);
            }
        }

        for(Integer rowWithGeoZones: rowsWithGeoZones) {
            System.out.println(rowWithGeoZones);
        }

        for (Integer rowsWithGeoZone : rowsWithGeoZones) {
            driver
                    .findElement(By.name("countries_form"))
                    .findElement(By.cssSelector("tr:nth-of-type(" + rowsWithGeoZone + ")"))
                    .findElement(By.cssSelector("a:nth-of-type(1)")).click();

            ArrayList<String> geoZonesNames = new ArrayList<>();
            List<WebElement> geoZonesNamesRows = driver.findElement(By.id("table-zones")).findElements(By.tagName("tr"));
            geoZonesNamesRows.remove(0);
            geoZonesNamesRows.remove(geoZonesNamesRows.size()-1);
            for (WebElement row : geoZonesNamesRows) {
                geoZonesNames.add(row.findElement(By.cssSelector("td:nth-of-type(3)")).getText());
            }
            assertTrue(isSorted(geoZonesNames));
            openCountriesPage();
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

    public void openCountriesPage() {
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
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
