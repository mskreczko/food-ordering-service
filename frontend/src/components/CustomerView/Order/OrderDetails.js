import { useEffect, useState, React } from 'react';
import { useParams } from 'react-router-dom';

export default function OrderDetails(props) {
    const [order, setOrderDetails] = useState({});
    const orderId = useParams();

    useEffect(() => {
        fetch('http://localhost:8080/api/v1/customer/orders/' + orderId.id, {
            method: 'GET',
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('token'),
            },
        })
        .then((response) => response.json()).then((body) => setOrderDetails(body));
    }, []);

    return (
        <main>
            <p>{ order.deliveryAddress }</p>
            <p>{ order.status }</p>
        </main>
    )
}
