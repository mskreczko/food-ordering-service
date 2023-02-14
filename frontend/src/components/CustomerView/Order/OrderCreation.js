import { useState, React } from 'react';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import './OrderCreation.css';

function getProductsFromStorage() {
    return JSON.parse(sessionStorage.getItem('cart_content'));
}

async function makeAnOrder(deliveryAddress, paymentMethod) {
    const ids = getProductsFromStorage().map((p) => p.id);
    return await fetch('http://localhost:8080/api/v1/customer/orders', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('token'),
        },
        body: JSON.stringify({'productsIds': ids, 'deliveryAddress': deliveryAddress, 'paymentMethod': paymentMethod}),
    });
}

export default function OrderCreation() {
    const [deliveryAddress, setDeliveryAddress] = useState('');
    const [paymentMethod, setPaymentMethod] = useState('');

    const onChange = (e) => {
        switch (e.target.name) {
            case 'address':
                setDeliveryAddress(e.target.value);
            break;
            case 'payment-method':
                setPaymentMethod(e.target.checked);
            break;
        }
    }

    const onSubmit = (e) => {
        e.preventDefault();
        makeAnOrder(deliveryAddress, paymentMethod).then((response) => {
            window.location.href = response.headers.get('Location');
        });
    }

    return (
        <main>
            <Form style={{flex: '1', width: '40%', margin: '0 30% 0 30%'}} onSubmit={onSubmit}>
                <Form.Group className='mb-3' controlId='form-delivery-address'>
                    <Form.Control required name='address' type='text' value={deliveryAddress} onChange={onChange} placeholder='Enter delivery address'/>
                </Form.Group>
                <Form.Group className='mb-3 pay-div'>
                    <Form.Control style={{ position: 'fixed' }} value={paymentMethod} onChange={onChange} className='btn-check' name='payment-method' id='payment-method-paypal' type='checkbox' autoComplete='off'/>
                    <Form.Label className='btn btn-outline-primary payment-btn' htmlFor='payment-method-paypal'>PayPal</Form.Label>
                </Form.Group>
                <Form.Group className='mb-3 pay-div'>
                    <Form.Control style={{ position: 'fixed' }} value={paymentMethod} onChange={onChange} className='btn-check' name='payment-method' id='payment-method-blik' type='checkbox' autoComplete='off'/>
                    <Form.Label className='btn btn-outline-primary payment-btn' htmlFor='payment-method-blik'>BLIK</Form.Label>
                </Form.Group>
                <Form.Group className='mb-3 pay-div'>
                    <Form.Control style={{ position: 'fixed' }} value={paymentMethod} onChange={onChange} className='btn-check' name='payment-method' id='payment-method-visa' type='checkbox' autoComplete='off'/>
                    <Form.Label className='btn btn-outline-primary payment-btn' htmlFor='payment-method-visa'>VISA</Form.Label>
                </Form.Group>
                <Form.Group className='mb-3 pay-div'>
                    <Form.Control style={{ position: 'fixed' }} value={paymentMethod} onChange={onChange} className='btn-check' name='payment-method' id='payment-method-mastercard' type='checkbox' autoComplete='off'/>
                    <Form.Label className='btn btn-outline-primary payment-btn' htmlFor='payment-method-mastercard'>MasterCard</Form.Label>
                </Form.Group>

                <Button style={{borderRadius: '25px'}} className='form-submit-btn' variant='primary' type='submit'>
                    MAKE AN ORDER
                </Button>
            </Form>
        </main>
    );
}
