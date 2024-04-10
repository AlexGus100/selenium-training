package ProductPagesDataOnMainPage;

import org.openqa.selenium.WebElement;

public class TestBase {

    public boolean isTextGrey(WebElement element) {
        String r = element.getCssValue("text-decoration-color").substring(4,7);
        String g = element.getCssValue("text-decoration-color").substring(9,12);
        String b = element.getCssValue("text-decoration-color").substring(14,17);
        return r.equals(g) && r.equals(b);
    }

    public boolean isTextRed(WebElement element) {
        char g = element.getCssValue("color").charAt(10);
        char b = element.getCssValue("color").charAt(13);
        return g=='0' && b=='0';
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
        int leftElementSize = leftElement.getSize().height * leftElement.getSize().width;
        int rightElementSize = rightElement.getSize().height * rightElement.getSize().width;
        return rightElementSize>leftElementSize;
    }

}
