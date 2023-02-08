import { useState, useEffect, React } from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import './ShoppingCartDetails.css';

async function getDiscount(code) {
    return await fetch('http://localhost:8080/api/v1/customer/orders/promocodes/verify?code=' + code, {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('token'),
        }
    });
}

export default function ShoppingCartDetails() {
    const [listProducts, setListProducts] = useState([]);
    const [promoCode, setPromoCode] = useState('');
    const [validPromoCode, setValidPromoCode] = useState(true);
    const [totalValue, setTotalValue] = useState(0.0);
    const [discount, setDiscount] = useState(1.0);

    const onChange = (e) => {
        setPromoCode(e.target.value);
    }

    const onSubmit = (e) => {
        e.preventDefault();
        getDiscount(promoCode).then((response) => {
            if (!response.ok) {
                setValidPromoCode(false);
            }
            return response.text();
        }).then((data) => {
            setDiscount(data);
            // console.log(totalValue - (totalValue * discount));
            setTotalValue(totalValue - (totalValue * data));
        }).catch(() => {});
    }

    useEffect(() => {
        const products = JSON.parse(sessionStorage.getItem('cart_content'));
        setListProducts(products);
        let x = products.reduce((acc, p) => {
            return acc + p.price
        }, 0);
        setTotalValue(x);

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
            <p>TOTAL: {totalValue}</p>
            <Form onSubmit={onSubmit}>
                <Form.Group>
                    <Form.Control name='promo-code' type='text' value={promoCode} onChange={onChange} placeholder='Enter promotional code'/>
                    <Button variant='primary' type='submit'>USE CODE</Button>
                </Form.Group>
                { !validPromoCode ? <Form.Text style={{color: 'red'}}>Invalid code.</Form.Text> : null }
            </Form>
            <a className='new-order-btn' href='/customer/order/new'>Make an order</a>
        </main>
    )
}