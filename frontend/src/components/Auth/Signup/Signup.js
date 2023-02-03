import { React, useState} from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import './Signup.css';

async function signUpUser(email, name, password) {
    return await fetch('http://localhost:8080/api/v1/auth/signup', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({'email': email, 'name': name, 'password': password}),
    });
}

export default function Signup() {
    const [email, setEmail] = useState('');
    const [name, setName] = useState('');
    const [password, setPassword] = useState('');
    const [emailAlreadyTaken, setEmailAlreadyTaken] = useState(false);

    const onChange = (e) => {
        switch (e.target.name) {
            case 'email':
                setEmail(e.target.value);
            break;
            case 'name':
                setName(e.target.value);
            break;
            case 'password':
                setPassword(e.target.value);
            break;
        }
    }

    const onSubmit = (e) => {
        e.preventDefault();
        signUpUser(email, name, password).then((response) => {
            if (response.status === 201) {
                window.location.href = '/signin';
            } else if (response.status === 409) {
                setEmailAlreadyTaken(true);
            }
        }).catch(() => {});
    }

    return (
        <main>
            <Form onSubmit={onSubmit}>
                <Form.Group className='mb-3' controlId='form-email'>
                    <Form.Control name='email' type='email' value={email} onChange={onChange} placeholder='Enter email'/>
                    { emailAlreadyTaken ? <Form.Text style={{color: 'red'}}>Email already taken.</Form.Text> : null }
                </Form.Group>

                <Form.Group className='mb-3' controlId='form-name'>
                    <Form.Control name='name' type='name' value={name} onChange={onChange} placeholder='Enter name'/>
                </Form.Group>

                <Form.Group className='mb-3' controlId='form-password'>
                    <Form.Control name='password' type='password' value={password} onChange={onChange} placeholder='Password'/>
                </Form.Group>

                <Button className='form-submit-btn' variant='primary' type='submit'>
                    SIGN UP
                </Button>
            </Form>
        </main>
    );
}