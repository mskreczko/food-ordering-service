import React from 'react';
import { Outlet } from 'react-router-dom';
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faFacebook, faInstagram } from '@fortawesome/free-brands-svg-icons';
import './Hero.css';

export default function Hero() {
    return (
        <div className='hero'>
            <Navbar expand='lg'>
                <Container>
                    <Navbar.Brand className='brand' href='/'>FoodOrder</Navbar.Brand>
                    <Navbar.Collapse id='response-navbar-nav'>
                        <Nav className='nav-route me-auto'>
                            <Nav.Link className='nav-btn' href='/menu'>Menu</Nav.Link>
                            <Nav.Link className='nav-btn' href='/delivery'>Delivery</Nav.Link>
                        </Nav>
                        <Nav>
                            <Nav.Link className='sign-btn' href='/signin'>Sign in</Nav.Link>
                            <Nav.Link className='sign-btn' href='/signup'>Sign up</Nav.Link>
                        </Nav>
                    </Navbar.Collapse>
                </Container>
            </Navbar>
            <Outlet/>
            <footer>
                <p>&copy; Michał Skreczko, 2023. All rights reserved.</p>
                <FontAwesomeIcon className='social-icon' icon={faFacebook}/>
                <FontAwesomeIcon className='social-icon' icon={faInstagram}/>
            </footer>
        </div>
    );
}