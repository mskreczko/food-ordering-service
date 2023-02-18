import { React, useState} from 'react';
import { Form } from 'react-bootstrap';
import Button from 'react-bootstrap/Button';
import { useParams } from 'react-router-dom';

export default function ResetPassword() {
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [nonMatchingPassword, setNonMatchingPasswords] = useState(false);
    let token = useParams();

    const onChange = (e) => {
        switch (e.target.name) {
            case 'password':
                setPassword(e.target.value);
            break;
            case 'confirmPassword':
                setConfirmPassword(e.target.value);
            break;
        }
    }

    const onSubmit = (e) => {
        e.preventDefault();

        if (password !== confirmPassword) {
            setNonMatchingPasswords(true);
            return;
        } else {
            setNonMatchingPasswords(false);
            fetch('http://localhost:8080/api/v1/auth/resetPassword?token=' + token.token, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: password,
            })
        }
    }

    return (
        <main>
            <Form onSubmit={onSubmit}>
                <Form.Group>
                    <Form.Control style={{ marginBottom: '10px' }} required type='password' placeholder='Enter new password' value={password} onChange={onChange} name='password'/>
                    <Form.Control required type='password' placeholder='Confirm new password' value={confirmPassword} onChange={onChange} name='confirmPassword'/>
                    { nonMatchingPassword ? <Form.Text style={{color: 'red'}}>Passwords do not match</Form.Text> : null}
                </Form.Group>
                <Button variant='primary' type='submit'>
                    Change password
                </Button>
            </Form>
        </main>
    )
}