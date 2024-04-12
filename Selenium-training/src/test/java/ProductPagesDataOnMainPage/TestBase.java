package ProductPagesDataOnMainPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;

public class TestBase {

    public boolean isTextGrey(WebElement element) {
        Color color = Color.fromString(element.getCssValue("color"));
        int r = color.getColor().getRed();
        int g = color.getColor().getGreen();
        int b = color.getColor().getBlue();
        return r==g && r==b;
    }

    public boolean isTextRed(WebElement element) {
        Color color = Color.fromString(element.getCssValue("color"));
        int g = color.getColor().getGreen();
        int b = color.getColor().getBlue();
        return g==0 && b==0;
    }

    public boolean isTextCrossed(WebElement element) {
        String fontWeight = element.getCssValue("text-decoration-line");
        return fontWeight.equals("line-through");
    }

    public boolean isTextBoldOnMainPage(WebElement element) {
        String fontWeight = element.getCssValue("font-weight");
        return fontWeight.equals("700");
    }

    public boolean isTextBoldOnProductPage(WebElement element) {
        String fontWeight = element.getCssValue("font-weight");
        return fontWeight.equals("700");
    }

    public boolean isRightBiggerThanLeft(WebElement leftElement, WebElement rightElement) {
        float leftElementSize = Float.parseFloat(leftElement.getCssValue("font-size").replaceFirst("px", ""));
        float rightElementSize = Float.parseFloat(rightElement.getCssValue("font-size").replaceFirst("px", ""));
        return rightElementSize>leftElementSize;
    }

}
