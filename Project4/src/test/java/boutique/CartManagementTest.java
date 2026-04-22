package boutique;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TEST 3 — Cart Management (Add and Remove)
 *
 * User Story:
 *   As a shopper, I want to add an item to my cart and then remove it
 *   so that I can change my mind before checkout without leaving unwanted
 *   items in my cart.
 *
 * Steps:
 *   1. Navigate directly to a known product page.
 *   2. Add the product to the cart.
 *   3. Verify the cart page loaded and contains the product name.
 *   4. Click "Empty Cart".
 *   5. Verify the empty cart message appears.
 */
public class CartManagementTest extends BaseTest {

    // Use a hardcoded product URL found from the homepage HTML
    private static final String PRODUCT_URL = BASE_URL + "/product/OLJCESPC7Z";
    private static final String PRODUCT_NAME = "Sunglasses";

    @Test
    @DisplayName("Add item to cart then empty the cart")
    public void testCartAddAndEmpty() {

        // ── Step 1: Navigate directly to a product ───────────────────────────
        driver.get(PRODUCT_URL);
        System.out.println("\n=== TEST 3: Cart Management ===");
        System.out.println("Opened product page: " + PRODUCT_URL);

        // Confirm we're on the right page
        WebElement priceEl = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("p.product-price")));
        System.out.println("Product price: " + priceEl.getText().trim());

        // ── Step 2: Add to cart ───────────────────────────────────────────────
        WebElement addToCartBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("button.cymbal-button-primary")));
        addToCartBtn.click();
        System.out.println("Clicked 'Add To Cart' for " + PRODUCT_NAME);

        // ── Step 3: Verify the cart page loaded with the item ─────────────────
        wait.until(ExpectedConditions.urlContains("/cart"));
        System.out.println("Redirected to: " + driver.getCurrentUrl());

        // The cart page body should mention the product name
        String pageText = driver.findElement(By.tagName("body")).getText();
        assertTrue(pageText.contains(PRODUCT_NAME),
                "Cart should contain the product: " + PRODUCT_NAME);

        screenshot("01_cart_with_item");
        System.out.println("PASS — [" + PRODUCT_NAME + "] is in the cart");

        // ── Step 4: Empty the cart ────────────────────────────────────────────
        // Button: <button class="cymbal-button-secondary cart-summary-empty-cart-button">
        // Form POSTs to /cart/empty, which then redirects to / (homepage)
        WebElement emptyCartBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("button.cart-summary-empty-cart-button")));
        emptyCartBtn.click();
        System.out.println("Clicked 'Empty Cart'");

        // After /cart/empty the app redirects to the homepage (/).
        // Navigate back to /cart to confirm it is now empty.
        wait.until(ExpectedConditions.urlToBe(BASE_URL + "/"));
        driver.get(BASE_URL + "/cart");

        // ── Step 5: Verify the cart is now empty ──────────────────────────────
        // <section class="empty-cart-section">
        //   <h3>Your shopping cart is empty!</h3>
        WebElement emptySection = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("section.empty-cart-section")));

        screenshot("02_cart_empty");
        assertNotNull(emptySection);
        System.out.println("PASS — Empty cart section is visible: \""
                + emptySection.findElement(By.tagName("h3")).getText() + "\"");
        System.out.println("TEST 3 COMPLETE — Add and remove cart flow verified");
    }
}
