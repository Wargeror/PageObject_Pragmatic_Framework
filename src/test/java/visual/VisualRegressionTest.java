package visual;

import base.BaseTest;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import utils.Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VisualRegressionTest extends BaseTest {

    @Test(
            testName = "Main Page Visual Regression",
            description = "Performs a full-page visual comparison of the main page against a baseline image."
    )
    public void MainPageVisual() {
        MainPage mainPage = webApp.mainPage();
        getDriver().get(mainPage.mainUrl());

        // Freeze animations to ensure consistent screenshots
        Utils.disableAnimations(getDriver());

        // Use AShot to take a full-page screenshot
        Screenshot currentScreenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(1000))
                .takeScreenshot(getDriver());

        BufferedImage currentImage = currentScreenshot.getImage();

        String baselinePath = "src/test/resources/visual/baseline/MainPage.png";
        File baselineFile = new File(baselinePath);

        if (!baselineFile.exists()) {
            // First time running the test: save current screenshot as baseline
            Utils.saveImage(currentImage, baselinePath);
            Assert.fail("Failure VisualRegressionTest/MainPageVisual: Baseline image was not found. Current screenshot saved as baseline at: " + baselinePath + ". Please run the test again.");
        } else {
            // Load the baseline image
            BufferedImage expectedImage;
            try {
                expectedImage = ImageIO.read(baselineFile);
            } catch (IOException e) {
                throw new RuntimeException("Failure VisualRegressionTest/MainPageVisual: Failed to read baseline image: " + baselinePath, e);
            }

            // Compare images
            ImageDiffer imgDiffer = new ImageDiffer();
            ImageDiff diff = imgDiffer.makeDiff(expectedImage, currentImage);

            if (diff.hasDiff()) {
                // Save diff image for debugging
                String diffPath = "target/visual-diffs/MainPage_diff.png";
                Utils.saveImage(diff.getMarkedImage(), diffPath);
                Assert.fail("Failure VisualRegressionTest/MainPageVisual: Visual comparison failed! Difference found. Diff image saved at: " + diffPath);
            }
        }
    }

    @Test(
            testName = "Logo Size and Visual Validation",
            description = "Verifies the dimensions and visual appearance of the site logo against a baseline."
    )
    public void correctLogoTest(){
        MainPage mainPage = webApp.mainPage();
        getDriver().get(mainPage.mainUrl());

        // 1. Assert Size of the element
        Rectangle logoRect = mainPage.getLogo().getRect();
        Assert.assertEquals(logoRect.getWidth(), 200, "Failure VisualRegressionTest/correctLogoTest: Logo width is incorrect.");
        Assert.assertEquals(logoRect.getHeight(), 39, "Failure VisualRegressionTest/correctLogoTest: Logo height is incorrect.");

        // 2. Visual Comparison of the Logo element
        Screenshot logoScreenshot = new AShot()
                .takeScreenshot(getDriver(), mainPage.getLogo());
        BufferedImage currentLogoImage = logoScreenshot.getImage();

        String baselinePath = "src/test/resources/visual/baseline/Logo.png";
        File baselineFile = new File(baselinePath);

        if (!baselineFile.exists()) {
            Utils.saveImage(currentLogoImage, baselinePath);
            Assert.fail("Failure VisualRegressionTest/correctLogoTest: Baseline logo image not found. Saved current logo as baseline at: " + baselinePath);
        } else {
            BufferedImage expectedLogoImage;
            try {
                expectedLogoImage = ImageIO.read(baselineFile);
            } catch (IOException e) {
                throw new RuntimeException("Failure VisualRegressionTest/correctLogoTest: Failed to read baseline logo image", e);
            }

            ImageDiffer imgDiffer = new ImageDiffer();
            ImageDiff diff = imgDiffer.makeDiff(expectedLogoImage, currentLogoImage);

            if (diff.hasDiff()) {
                String diffPath = "target/visual-diffs/Logo_diff.png";
                Utils.saveImage(diff.getMarkedImage(), diffPath);
                Assert.assertFalse(diff.hasDiff(), "Failure VisualRegressionTest/correctLogoTest: Logo visual comparison failed! Diff image saved at: " + diffPath);
            }
        }
    }
}
