import { useState, useEffect, React } from 'react';
import './AdminPanel.css';

export default function AdminPanel() {
    const [orders, setOrders] = useState([]);

    useEffect(() => {
        fetch('http://localhost:8080/api/v1/admin/orders', {
            method: 'GET',
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('token'),
            }
        }).then((data) => data.json())
        .then((body) => setOrders(body))
    }, []);

    return (
        <main>
            <table style={{marginBottom: '40%'}} className='table table-bordered table-striped table-fixed'>
                <thead className='table-dark'>
                    <tr>
                        <th>Order Id</th>
                        <th>Delivery Address</th>
                        <th>Status</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    { orders ? orders.map((o) => (
                        <tr key={o.id}>
                            <td className='fixed-col'>{o.id}</td>
                            <td className='fixed-col'>{o.deliveryAddress}</td>
                            <td className='fixed-col'>{o.orderStatus}</td>
                            <td className='fixed-col'><a className='details-btn' href='/#'>VIEW DETAILS</a></td>
                        </tr>
                    )) : null }
                </tbody>
            </table>
        </main>
    )
}