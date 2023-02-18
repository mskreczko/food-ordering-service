import { React, useState } from 'react';
import { Button, Form } from 'react-bootstrap';

export default function ForgotPassword() {
    const [email, setEmail] = useState('');
    const [emailSent, setEmailSent] = useState(false);
    const [noUser, setNoUser] = useState(false);

    const onChange = (e) => {
        switch (e.target.name) {
            case 'email':
                setEmail(e.target.value);
            break;
        }
    }

    const onSubmit = (e) => {
        e.preventDefault();

        fetch('http://localhost:8080/api/v1/auth/forgotPassword?email=' + email)
        .then((resp) => {
            console.log(resp);
            if (resp.status === 200) {
                setEmailSent(true);
            } else if (resp.status === 404) {
                setNoUser(true);
            }
            return resp.text()
        }).then((body) => {})
    }

    if (emailSent) {
        return (
            <main>
                <p>Reset token has been sent to {email}</p>
            </main>
        )
    } else {
        return (
            <main>
                <Form onSubmit={onSubmit}>
                    <Form.Group>
                        <Form.Control required name='email' type='text' value={email} onChange={onChange} placeholder='Enter your email'/>
                        { noUser ? <Form.Text style={{color: 'red'}}>No such email found</Form.Text> : null }
                    </Form.Group>
                    <Button style={{ marginTop: '10px' }} variant='primary' type='submit'>
                        Reset password
                    </Button>
                </Form>
            </main>
        )
    }
}