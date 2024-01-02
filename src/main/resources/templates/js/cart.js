document.addEventListener('DOMContentLoaded', (event) => {
    const cartItems = document.querySelectorAll('.cart-item');
    cartItems.forEach((item) => {
        const minusButton = item.querySelector('.quantity-minus');
        const plusButton = item.querySelector('.quantity-plus');
        const quantityDisplay = item.querySelector('.quantity');
        const totalDisplay = item.querySelector('.item-total');
        const price = parseInt(item.dataset.price, 10);

        minusButton.addEventListener('click', () => {
            updateQuantity(item, quantityDisplay, totalDisplay, price, -1);
        });

        plusButton.addEventListener('click', () => {
            updateQuantity(item, quantityDisplay, totalDisplay, price, 1);
        });
    });
});

function updateQuantity(item, quantityDisplay, totalDisplay, price, change) {
    let quantity = parseInt(quantityDisplay.textContent, 10) + change;
    quantity = quantity < 1 ? 1 : quantity; // Prevent negative quantities
    quantityDisplay.textContent = quantity;
    totalDisplay.textContent = `${(price * quantity).toLocaleString()}원`;
    updateCartTotal();
}

function updateCartTotal() {
    const totalAmountDisplay = document.querySelector('.summary-total-amount');
    let total = 0;
    document.querySelectorAll('.cart-item').forEach((item) => {
        const quantity = parseInt(item.querySelector('.quantity').textContent, 10);
        const price = parseInt(item.dataset.price, 10);
        total += quantity * price;
    });
    totalAmountDisplay.textContent = `${total.toLocaleString()}원`;
}
