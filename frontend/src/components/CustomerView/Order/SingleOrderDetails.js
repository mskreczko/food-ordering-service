import { React, useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import './SingleOrderDetails.css';

const changeOrderStatus = (id, newStatus) => {

}

export default function SingleOrderDetails() {
    const [details, setDetails] = useState({});
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
            <button className='change-btn' onClick={changeOrderStatus()}>CHANGE STATUS</button>
        </main>
    )
}