import { useState, useEffect, React } from 'react';
import './AccountDetails.css';

const fetchAccountDetails = async () => {
    return await fetch('http://localhost:8080/api/v1/customer/account', {
        method: 'GET',
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('token'),
        }
    }).then((data) => data.json());
}

export default function AccountDetails() {
    const [accountInfo, setAccountInfo] = useState({});

    useEffect(() => {
        fetchAccountDetails().then((body) => setAccountInfo(body));
    }, []);

    return (
        <main id='account-info'>
            <p>Name: { accountInfo.name} </p>
            <p>Email: { accountInfo.email} </p>
            <p>Loyalty points: { accountInfo.loyaltyPoints }</p>
        </main>
    )
}
