import { useState, useEffect, React } from 'react';

export default function ShoppingCartDetails() {
    const [listProducts, setListProducts] = useState([]);

    useEffect(() => {
        const products = JSON.parse(sessionStorage.getItem('cart_content'));
        setListProducts(products);
    }, []);

    return (
        <div style={{display: 'flex', flex: '1'}}>
            <ul>
                { listProducts && listProducts.map((p) => (
                    <li key={p.id}>
                        <p>{p.name}</p>
                        <p>{p.price}</p>
                    </li>
                ))}
            </ul>
        </div>
    )
}