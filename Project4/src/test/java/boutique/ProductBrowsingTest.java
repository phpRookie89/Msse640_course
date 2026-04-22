package boutique;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TEST 2 — Product Browsing
 *
 * User Story:
 *   As a shopper, I want to browse the product catalog, click into a product,
 *   and verify that the product detail page loads correctly with a name and price
 *   displayed — so I know the storefront is functioning before I attempt to buy.
 *
 * Steps:
 *   1. Open the Online Boutique homepage.
 *   2. Verify that multiple products are displayed in the catalog.
 *   3. Click a product and confirm the detail page loads with a title and price.
 *   4. Use the browser Back button and confirm the homepage reloads correctly.
 */
public class ProductBrowsingTest extends BaseTest {

    @Test
    @DisplayName("Homepage shows products and product detail page loads correctly")
    public void testProductBrowsing() {

        // ── Step 1: Load homepage ─────────────────────────────────────────────
        driver.get(BASE_URL);
        System.out.println("\n=== TEST 2: Product Browsing ===");

        // ── Step 2: Confirm multiple products are visible ─────────────────────
        // Structure: <div class="hot-product-card"><a href="/product/...">
        List<WebElement> productCards = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.cssSelector(".hot-product-card")));

        screenshot("01_homepage");
        System.out.println("Product cards found on homepage: " + productCards.size());
        assertTrue(productCards.size() > 1,
                "Expected more than one product on the homepage, found: "
                        + productCards.size());
        System.out.println("PASS — Catalog contains " + productCards.size()
                + " products");

        // ── Step 3: Click a product and verify detail page ────────────────────
        // Pick the second card for variety; find the <a> inside it
        WebElement secondCardLink = productCards.get(1)
                .findElement(By.tagName("a"));
        secondCardLink.click();

        // Confirm the detail page has a price: <p class="product-price">
        WebElement productPrice = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("p.product-price")));
        String priceText = productPrice.getText().trim();
        assertFalse(priceText.isEmpty(), "Product price should not be empty");
        assertTrue(priceText.contains("$"),
                "Price should contain a dollar sign, got: " + priceText);

        // Confirm the Add To Cart button is present
        WebElement addToCartBtn = driver.findElement(
                By.cssSelector("button.cymbal-button-primary"));
        assertTrue(addToCartBtn.isDisplayed(),
                "Add To Cart button should be visible on the product page");

        screenshot("02_product_detail");
        System.out.println("PASS — Detail page loaded with price [" + priceText
                + "] and Add To Cart button visible");

        // ── Step 4: Navigate back and verify homepage reloads ─────────────────
        driver.navigate().back();

        List<WebElement> cardsAfterBack = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.cssSelector(".hot-product-card")));

        screenshot("03_back_to_homepage");
        assertTrue(cardsAfterBack.size() > 0,
                "Homepage should still show products after navigating back");
        System.out.println("PASS — Homepage reloaded with "
                + cardsAfterBack.size() + " products after pressing Back");
    }
}
