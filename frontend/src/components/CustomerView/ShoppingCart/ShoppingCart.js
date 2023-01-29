import { faShoppingCart } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { useState, useEffect, React } from 'react';

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
}

export default function ShoppingCart() {
    const [orderTotalValue, setOrderTotalValue] = useState(0);
    // const [products, setProducts] = useState((sessionStorage.getItem('cart_content') != null) ? JSON.parse(sessionStorage.getItem('cart_content')) : []);

    // useEffect(() => {
    //     if (sessionStorage.getItem('cart_content') != null) {
    //         const newestProduct = JSON.parse(sessionStorage.getItem('cart_content').at(-1));
    //         setOrderTotalValue(orderTotalValue + newestProduct.price);
    //     }
    // }, [products]);

    return (
        <div style={{ margin: '0 3% 0 0'}}className='shopping-cart'>
            <a href='/customer/products'><FontAwesomeIcon icon={faShoppingCart}/></a>
            <p className='order-total-value'>{orderTotalValue}</p>
        </div>
    )
}