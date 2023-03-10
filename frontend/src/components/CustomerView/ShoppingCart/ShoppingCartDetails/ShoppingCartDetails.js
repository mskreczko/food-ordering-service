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
    const [invalidPromoCode, setInvalidPromoCode] = useState(true);
    const [totalValue, setTotalValue] = useState(0.0);
    const [discount, setDiscount] = useState(1.0);

    const onChange = (e) => {
        setPromoCode(e.target.value);
    }

    const onSubmit = (e) => {
        e.preventDefault();
        getDiscount(promoCode).then((response) => {
            if (response.status === 404) {
                setInvalidPromoCode(false);
                throw("");
            }
            return response.text();
        }).then((data) => {
            setDiscount(data);
            setInvalidPromoCode(true);
            setTotalValue(totalValue - (totalValue * data));
        }).catch(() => {});
    }

    useEffect(() => {
        const products = JSON.parse(sessionStorage.getItem('cart_content'));
        setListProducts(products);
        let x = products.reduce((acc, p) => {
            return acc + p.price
        }, 0.0);
        setTotalValue(x);

    }, []);

    return (
        <main id='shopping-cart-details'>
            <div id='shopping-cart-wrapper'>
                <ul id='items-list'>
                    { listProducts && listProducts.map((p, idx) => (
                        <li key={idx}>
                            <div className='item-details'>
                                <p className='item-text left-align'>{p.name} </p>
                                <p className='item-text'>{p.price} PLN</p>
                            </div>
                        </li>
                    ))}
                </ul>
                <p>TOTAL: { totalValue } PLN</p>
                <Form id='promo-code-form' onSubmit={ onSubmit }>
                    <Form.Group>
                        <Form.Control name='promo-code' type='text' value={ promoCode } onChange={ onChange } placeholder='Enter your code here'/>
                        { !invalidPromoCode ? <Form.Text style={{color: 'red'}}>Invalid promo code</Form.Text> : null }
                        <Button id='code-submit-btn' variant='primary' type='submit'>Use promo code</Button>
                    </Form.Group>
                </Form>
                <a className='new-order-btn' href='/customer/order/new'>Make an order</a>
            </div>
        </main>
    )
}
