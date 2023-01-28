import { faCirclePlus } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { useState, useEffect, React } from 'react';
import Pagination from 'react-bootstrap/Pagination';
import { Audio } from 'react-loader-spinner';
import './Menu.css';

export default function Menu() {
    const [pageInfo, setPageInfo] = useState({});
    const [products, setProducts] = useState([]);
    const [pageNumber, setPageNumber] = useState(0);

    useEffect(() => {
        const fetchData = async () => {
            await fetch('http://localhost:8080/api/v1/customer/menu?pageNumber=' + pageNumber, {
                method: 'GET',
                headers: {
                    'Authorization': 'Bearer ' + localStorage.getItem('token'),
                }
            }).then((data) => data.json())
            .then((body) => {setProducts(body.products); setPageInfo({totalItems: body.totalItems, itemsPerPage: body.itemsPerPage, totalPages: body.totalPages, currentPage: body.currentPage+1})});
        }

        fetchData();
    }, [pageNumber]);

    let items = [];
    for (let n = 1; n <= pageInfo.totalPages; n++) {
        items.push(
            <Pagination.Item key={n} active={n === pageInfo.currentPage} onClick={() => setPageNumber(n-1)}>
                {n}
            </Pagination.Item>
        );
    }

    return (
        <div className='products-wrapper'>
            <div className='products'>
                {products ? products.map((p, idx) => (
                    <div className='products-row' key={idx}>
                        <img src='https://via.placeholder.com/150' alt='placeholder'/>
                        <p>{p.name}</p>
                        <p>{p.price}</p>
                        <p>{p.description}</p>
                        <a><FontAwesomeIcon icon={faCirclePlus}></FontAwesomeIcon></a>
                    </div>
                )) : <Audio height='80' width='80' radius='6' color='gray' ariaLabel='loading'/>}
            </div>
            <Pagination>{items}</Pagination>
        </div>
    )
}