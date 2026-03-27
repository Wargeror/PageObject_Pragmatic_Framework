package visual;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VisualTest extends BaseTest {

    @Test
    public void MainPageVisual() {
        MainPage mainPage = new MainPage(driver, wait);
        driver.get(mainPage.mainUrl());

        // Use AShot to take a full-page screenshot
        Screenshot currentScreenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(1000))
                .takeScreenshot(driver);

        BufferedImage currentImage = currentScreenshot.getImage();

        String baselinePath = "src/test/resources/visual/baseline/MainPage.png";
        File baselineFile = new File(baselinePath);

        if (!baselineFile.exists()) {
            // First time running the test: save current screenshot as baseline
            saveImage(currentImage, baselinePath);
            Assert.fail("Baseline image was not found. Current screenshot saved as baseline at: " + baselinePath + ". Please run the test again.");
        } else {
            // Load the baseline image
            BufferedImage expectedImage;
            try {
                expectedImage = ImageIO.read(baselineFile);
            } catch (IOException e) {
                throw new RuntimeException("Failed to read baseline image: " + baselinePath, e);
            }

            // Compare images
            ImageDiffer imgDiffer = new ImageDiffer();
            ImageDiff diff = imgDiffer.makeDiff(expectedImage, currentImage);

            if (diff.hasDiff()) {
                // Save diff image for debugging
                String diffPath = "target/visual-diffs/MainPage_diff.png";
                saveImage(diff.getMarkedImage(), diffPath);
                Assert.fail("Visual comparison failed! Difference found. Diff image saved at: " + diffPath);
            }
        }
    }

    private void saveImage(BufferedImage image, String path) {
        File file = new File(path);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
        try {
            ImageIO.write(image, "PNG", file);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save image to: " + path, e);
        }
    }
}
