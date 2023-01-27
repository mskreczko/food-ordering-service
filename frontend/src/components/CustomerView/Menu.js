import { useState, useEffect, React } from 'react';
import { Audio } from 'react-loader-spinner';
import './Menu.css';

export default function Menu() {
    const [pageInfo, setPageInfo] = useState(null);
    const [products, setProducts] = useState([]);


    useEffect(() => {
        const fetchData = async () => {
            await fetch('http://localhost:8080/api/v1/customer/menu', {
                method: 'GET',
                headers: {
                    'Authorization': 'Bearer ' + localStorage.getItem('token'),
                }
            }).then((data) => data.json())
            .then((body) => {setProducts(body.products); setPageInfo({totalItems: body.totalItems, itemsPerPage: body.itemsPerPage, totalPages: body.totalPages, currentPage: body.currentPage+1})});
        }

        fetchData();
    }, []);

    return (
        <div className='products'>
            {products ? products.map((p, idx) => (
                <div className='products-row' key={idx}>
                    <img src='https://via.placeholder.com/150' alt='placeholder'/>
                    <p>{p.name}</p>
                    <p>{p.price}</p>
                    <p>{p.description}</p>
                </div>
            )) : <Audio height='80' width='80' radius='6' color='gray' ariaLabel='loading'/>}
        </div>
    )
}