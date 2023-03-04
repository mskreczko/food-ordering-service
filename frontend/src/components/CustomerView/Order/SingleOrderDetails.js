import { React, useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import OrderStatusChange from './OrderStatusChange';
import './SingleOrderDetails.css';

export default function SingleOrderDetails() {
    const [details, setDetails] = useState({});
    const [popup, showPopup] = useState(false);
    const params = useParams();

    useEffect(() => {
        fetch('http://localhost:8080/api/v1/admin/orders/' + params.id, {
            method: 'GET',
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('token'),
            }
        })
        .then((resp) => resp.json())
        .then((body) => setDetails(body));
    }, []);

    const handlePopupClick = () => {
        if (!popup) {
            showPopup(true);
        } else {
            showPopup(false);
        }
    }

    return (
        <main className='single-order-details'>
            <h2>Order Details</h2>
            <div className='info'>
                <p>Delivery address: { details.deliveryAddress }</p>
                <p>Status: { details.status }</p>
                <ul>
                { details.products ? details.products.map((p, idx) => (
                    <li key={idx}>{ p.name }</li>
                )) : null}
                </ul>
            </div>
            <button className='change-btn' onClick={(e) => handlePopupClick()}>CHANGE STATUS</button>
            { popup ? <OrderStatusChange id={params.id}/> : null }
        </main>
    )
}