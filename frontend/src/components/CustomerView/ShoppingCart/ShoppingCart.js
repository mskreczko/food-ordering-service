import { faShoppingCart } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { useState,  React } from 'react';

export function addProductToCart(product) {
    const newProduct = {
        id: product.id,
        name: product.name,
        price: product.price
    }


    if (sessionStorage.getItem('cart_content') != null) {
        const products = JSON.parse(sessionStorage.getItem('cart_content'));
        products.push(newProduct);
        sessionStorage.setItem('cart_content', JSON.stringify(products));
    } else {
        sessionStorage.setItem('cart_content', JSON.stringify([newProduct]));
    }
    window.dispatchEvent(new Event('storage'));
}

export default function ShoppingCart() {
    const [orderTotalValue, setOrderTotalValue] = useState(0);

    const onStorageChange = () => {
        let totalValue = JSON.parse(sessionStorage.getItem('cart_content')).reduce((acc, p) => {
            return acc + p.price
        }, 0);

        setOrderTotalValue(totalValue);
    };

    const removeListener = () => {
        window.addEventListener('storage', onStorageChange);
    }

    return (
        <div style={{ margin: '0 3% 0 0'}}className='shopping-cart'>
            { removeListener() }
            <a href='/customer/products'><FontAwesomeIcon icon={faShoppingCart}/></a>
            <p className='order-total-value'>{orderTotalValue}PLN</p>
        </div>
    )
}