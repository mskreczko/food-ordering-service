import { useState, useEffect, React } from 'react';

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
            { orders ? orders.map((o) => (
                <div className='orders-row' key={o.id}>
                    <p>{o.id}</p>
                    <p>{o.deliveryAddress}</p>
                    <p>{o.orderStatus}</p>
                </div>
            )) : null}
        </main>
    )
}