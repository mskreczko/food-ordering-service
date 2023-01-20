import React from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import './Signin.css';

export default function Signin() {
    return (
        <main>
            <Form>
                <Form.Group className='mb-3' controlId='form-email'>
                    <Form.Control type='email' placeholder='Enter email'/>
                </Form.Group>

                <Form.Group className='mb-3' controlId='form-password'>
                    <Form.Control type='password' placeholder='Password'/>
                </Form.Group>

                <Button className='form-submit-btn' variant='primary' type='submit'>
                    SIGN IN
                </Button>
            </Form>
        </main>
    );

}