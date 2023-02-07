import { useState, useEffect, React } from 'react';
import './ShoppingCartDetails.css';

export default function ShoppingCartDetails() {
    const [listProducts, setListProducts] = useState([]);

    useEffect(() => {
        const products = JSON.parse(sessionStorage.getItem('cart_content'));
        setListProducts(products);
    }, []);

    return (
        <main id='shopping-cart-details'> 
            <div className='list-div'>
                <ul>
                    { listProducts && listProducts.map((p, idx) => (
                        <li key={idx}>
                            <div style={{display: 'inline'}}>
                                <p>{p.name}</p>
                                <p>{p.price}</p>
                            </div>
                        </li>
                    ))}
                </ul>
            </div>
            <a className='new-order-btn' href='/customer/order/new'>Make an order</a>
        </main>
    )
}