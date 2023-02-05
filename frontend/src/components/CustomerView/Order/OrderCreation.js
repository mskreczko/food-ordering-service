import { useState, React } from 'react';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';

function getProductsFromStorage() {
    return JSON.parse(sessionStorage.getItem('cart_content'));
}

async function makeAnOrder(deliveryAddress) {
    const ids = getProductsFromStorage().map((p) => p.id);
    return await fetch('http://localhost:8080/api/v1/customer/orders/1', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('token'),
        },
        body: JSON.stringify({'productsIds': ids, 'deliveryAddress': deliveryAddress}),
    });
}

export default function OrderCreation() {
    const [deliveryAddress, setDeliveryAddress] = useState('');

    const onChange = (e) => {
        switch (e.target.name) {
            case 'address':
                setDeliveryAddress(e.target.value);
            break;
        }
    }

    const onSubmit = (e) => {
        e.preventDefault();
        makeAnOrder(deliveryAddress).then((response) => console.log(response));
    }

    return (
        <Form style={{flex: '1', width: '40%', margin: '0 30% 0 30%'}} onSubmit={onSubmit}>
            <Form.Group className='mb-3' controlId='form-delivery-address'>
                <Form.Control name='address' type='text' value={deliveryAddress} onChange={onChange} placeholder='Enter delivery address'/>
            </Form.Group>

            <Button className='form-submit-btn' variant='primary' type='submit'>
                MAKE AN ORDER
            </Button>
        </Form>
    );
}