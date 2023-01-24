import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faFacebook, faInstagram } from '@fortawesome/free-brands-svg-icons';
import { React } from 'react';
import { useRecoilState } from 'recoil';
import { authenticationState } from '../atoms/AuthenticationAtom';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import { Outlet } from 'react-router-dom';
import { Container } from 'react-bootstrap';

export default function CustomerView() {
    const authenticated = useRecoilState(authenticationState)[0];

    if (!authenticated) {
        window.location.href = '/signin';
    }

    return (
        <div className='customer-view'>
            <Navbar expand='lg'>
                <Container>
                    <Navbar.Collapse id='response-navbar-nav'>
                        <Nav>
                            <Nav.Link className='nav-btn' href='/account'>My Account</Nav.Link>
                            <Nav.Link className='nav-btn' href='/logout'>Logout</Nav.Link>
                        </Nav>
                    </Navbar.Collapse>
                </Container>
            </Navbar>
            <Outlet/>
           <footer>
                <p>&copy; Michał Skreczko 2023. All rights reserved.</p>
                <FontAwesomeIcon className='social-icon' icon={faFacebook}/>
                <FontAwesomeIcon className='social-icon' icon={faInstagram}/>
            </footer> 
        </div>
    )
}