package visual;

import base.BaseTest;
import io.qameta.allure.*;
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

@Epic("Visual Regression")
@Feature("Main Page Visuals")
public class VisualRegressionTest extends BaseTest {

    @Test(
            testName = "Main Page Visual Regression",
            description = "Performs a full-page visual comparison of the main page against a baseline image."
    )
    @Story("Full Page Screenshot Validation")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Performs a full-page visual comparison of the main page against a baseline image.")
    public void MainPageVisual() {
        log.info("Navigating to the main page for visual regression test.");
        MainPage mainPage = webApp.mainPage();
        getDriver().get(mainPage.mainUrl());

        log.info("Disabling animations for stable screenshot.");
        Utils.disableAnimations(getDriver());

        log.info("Taking full-page screenshot.");
        Screenshot currentScreenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(1000))
                .takeScreenshot(getDriver());

        BufferedImage currentImage = currentScreenshot.getImage();

        String baselinePath = "src/test/resources/visual/baseline/MainPage.png";
        File baselineFile = new File(baselinePath);

        if (!baselineFile.exists()) {
            log.warn("Baseline image not found. Saving current screenshot as baseline.");
            Utils.saveImage(currentImage, baselinePath);
            Assert.fail("Failure VisualRegressionTest/MainPageVisual: Baseline image was not found. Current screenshot saved as baseline at: " + baselinePath + ". Please run the test again.");
        } else {
            log.info("Loading baseline image for comparison.");
            BufferedImage expectedImage;
            try {
                expectedImage = ImageIO.read(baselineFile);
            } catch (IOException e) {
                log.error("Failed to read baseline image.", e);
                throw new RuntimeException("Failure VisualRegressionTest/MainPageVisual: Failed to read baseline image: " + baselinePath, e);
            }

            log.info("Comparing current screenshot with baseline.");
            ImageDiffer imgDiffer = new ImageDiffer();
            ImageDiff diff = imgDiffer.makeDiff(expectedImage, currentImage);

            if (diff.hasDiff()) {
                String diffPath = "target/visual-diffs/MainPage_diff.png";
                log.error("Visual difference found. Saving diff image to: " + diffPath);
                Utils.saveImage(diff.getMarkedImage(), diffPath);
                Assert.fail("Failure VisualRegressionTest/MainPageVisual: Visual comparison failed! Difference found. Diff image saved at: " + diffPath);
            } else {
                log.info("No visual differences found.");
            }
        }
    }

    @Test(
            testName = "Logo Size and Visual Validation",
            description = "Verifies the dimensions and visual appearance of the site logo against a baseline."
    )
    @Story("Component-Level Screenshot Validation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verifies the dimensions and visual appearance of the site logo against a baseline.")
    public void correctLogoTest(){
        log.info("Navigating to the main page for logo validation.");
        MainPage mainPage = webApp.mainPage();
        getDriver().get(mainPage.mainUrl());

        log.info("Asserting logo dimensions.");
        Rectangle logoRect = mainPage.getLogo().getRect();
        Assert.assertEquals(logoRect.getWidth(), 200, "Failure VisualRegressionTest/correctLogoTest: Logo width is incorrect.");
        Assert.assertEquals(logoRect.getHeight(), 39, "Failure VisualRegressionTest/correctLogoTest: Logo height is incorrect.");

        log.info("Taking screenshot of the logo.");
        Screenshot logoScreenshot = new AShot()
                .takeScreenshot(getDriver(), mainPage.getLogo());
        BufferedImage currentLogoImage = logoScreenshot.getImage();

        String baselinePath = "src/test/resources/visual/baseline/Logo.png";
        File baselineFile = new File(baselinePath);

        if (!baselineFile.exists()) {
            log.warn("Baseline logo image not found. Saving current logo as baseline.");
            Utils.saveImage(currentLogoImage, baselinePath);
            Assert.fail("Failure VisualRegressionTest/correctLogoTest: Baseline logo image not found. Saved current logo as baseline at: " + baselinePath);
        } else {
            log.info("Loading baseline logo image for comparison.");
            BufferedImage expectedLogoImage;
            try {
                expectedLogoImage = ImageIO.read(baselineFile);
            } catch (IOException e) {
                log.error("Failed to read baseline logo image.", e);
                throw new RuntimeException("Failure VisualRegressionTest/correctLogoTest: Failed to read baseline logo image", e);
            }

            log.info("Comparing current logo with baseline.");
            ImageDiffer imgDiffer = new ImageDiffer();
            ImageDiff diff = imgDiffer.makeDiff(expectedLogoImage, currentLogoImage);

            if (diff.hasDiff()) {
                String diffPath = "target/visual-diffs/Logo_diff.png";
                log.error("Logo visual difference found. Saving diff image to: " + diffPath);
                Utils.saveImage(diff.getMarkedImage(), diffPath);
                Assert.assertFalse(diff.hasDiff(), "Failure VisualRegressionTest/correctLogoTest: Logo visual comparison failed! Diff image saved at: " + diffPath);
            } else {
                log.info("No visual differences found in the logo.");
            }
        }
    }
}