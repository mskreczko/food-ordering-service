import { React, useState } from 'react';

export default function OrderStatusChange(id) {
    const [val, setVal] = useState('');

    const handleStatusChange = async (e) => {
        e.preventDefault();
        await fetch('http://localhost:8080/api/v1/admin/orders/status/' + id.id + '?status=' + val, {
            method: 'PATCH',
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('token'),
            },
        });
    }

    const handleChange = (e) => {
        setVal(e.target.value);
    }

    return (
        <form onSubmit={handleStatusChange}>
            <select value={val} onChange={handleChange}>
                <option value='0'>AWAITS</option>
                <option value='1'>IN PREPARATION</option>
                <option value='2'>BEING DELIVERED</option>
                <option value='3'>DELIVERED</option>
            </select>
            <button type='submit'>CHANGE</button>
        </form>
    )
}