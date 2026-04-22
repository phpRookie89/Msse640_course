package boutique;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * TEST 1 — Part 1 (Required)
 *
 * User Story:
 *   As a shopper, I want to browse the Online Boutique homepage, select an item,
 *   add it to my cart, and confirm that the cart total matches the item's listed
 *   price so I can trust the checkout process is accurate.
 *
 * Steps:
 *   1. Open the Online Boutique homepage.
 *   2. Click the first product card.
 *   3. Record the price shown on the product detail page.
 *   4. Click "Add To Cart".
 *   5. On the cart page, confirm the price is still displayed.
 *   6. Print a clear PASS or FAIL message.
 */
public class AddToCartTest extends BaseTest {

    @Test
    @DisplayName("Add item to cart and verify price matches")
    public void testAddToCartPriceVerification() {

        // ── Step 1: Go to homepage ────────────────────────────────────────────
        driver.get(BASE_URL);
        System.out.println("\n=== TEST 1: Add To Cart Price Verification ===");
        System.out.println("Navigated to: " + BASE_URL);
        screenshot("01_homepage");

        // ── Step 2: Click the first product ──────────────────────────────────
        // Each product is: <div class="hot-product-card"><a href="/product/...">
        WebElement firstProductLink = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector(".hot-product-card a")));

        System.out.println("Clicking product link: "
                + firstProductLink.getAttribute("href"));
        firstProductLink.click();

        // ── Step 3: Read the product price ───────────────────────────────────
        // <p class="product-price">$19.99</p>
        WebElement priceElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("p.product-price")));

        String productPrice = priceElement.getText().trim();
        System.out.println("Product price on detail page: " + productPrice);
        screenshot("02_product_detail");

        // ── Step 4: Add to cart ───────────────────────────────────────────────
        // <button type="submit" class="cymbal-button-primary">Add To Cart</button>
        WebElement addToCartBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("button.cymbal-button-primary")));
        addToCartBtn.click();
        System.out.println("Clicked 'Add To Cart'");

        // ── Step 5: Verify the cart contains the product price ────────────────
        // After adding, the app redirects to /cart
        wait.until(ExpectedConditions.urlContains("/cart"));
        screenshot("03_cart_page");

        // The cart page body should contain the price we saw on the product page
        String pageText = driver.findElement(By.tagName("body")).getText();
        boolean pass = pageText.contains(productPrice);

        // ── Step 6: Print result ──────────────────────────────────────────────
        if (pass) {
            System.out.println("RESULT: PASS — Cart page contains expected price ["
                    + productPrice + "]");
        } else {
            System.out.println("RESULT: FAIL — Cart page does NOT contain price ["
                    + productPrice + "]");
        }

        assertTrue(pass,
                "Cart page should display the product price: " + productPrice);
    }
}
