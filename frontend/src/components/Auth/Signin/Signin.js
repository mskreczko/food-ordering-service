import { useState, React } from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import { useRecoilState } from 'recoil';
import { authenticationState } from '../../atoms/AuthenticationAtom';
import './Signin.css';

async function signInUser(email, password) {
    return await fetch('http://localhost:8080/api/v1/auth/signin', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({'email': email, 'password': password}),
    });
}

export default function Signin() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [authenticationSucessfull, setAuthenticationSucessfull] = useState(true);
    const setAuthenticated = useRecoilState(authenticationState)[1];

    const onChange = (e) => {
        switch (e.target.name) {
            case 'email':
                setEmail(e.target.value);
            break;
            case 'password':
                setPassword(e.target.value);
            break;
        }
    }

    const onSubmit = (e) => {
        e.preventDefault();
        signInUser(email, password).then((response) => {
            if (!response.ok) {
                setAuthenticationSucessfull(false);
                throw new Error('error');
            }
            return response.text();
        }).then((data) => {
            localStorage.setItem("token", data);
            setAuthenticated(true);
            window.location.href = '/customer';
        }).catch(() => {});
    }

    return (
        <main>
            <Form onSubmit={onSubmit}>
                <Form.Group className='mb-3' controlId='form-email'>
                    <Form.Control name='email' type='email' value={email} onChange={onChange} placeholder='Enter email'/>
                </Form.Group>

                <Form.Group className='mb-3' controlId='form-password'>
                    <Form.Control name='password' type='password' value={password} onChange={onChange} placeholder='Password'/>
                </Form.Group>

                { !authenticationSucessfull ? <Form.Text style={{color: 'red'}}>Invalid email or password</Form.Text> : null }

                <Button className='form-submit-btn' variant='primary' type='submit'>
                    SIGN IN
                </Button>
            </Form>
        </main>
    );

}