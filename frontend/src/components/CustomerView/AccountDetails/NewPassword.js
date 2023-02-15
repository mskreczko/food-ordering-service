import { React, useState } from 'react';
import { Form } from 'react-bootstrap';
import Button from 'react-bootstrap/Button';

export default function NewPassword() {
    const [oldPassword, setOldPassword] = useState('');
    const [newPassword, setNewPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [nonMatchingPassword, setNonMatchingPasswords] = useState(false);
    const [invalidOriginalPassword, setInvalidOriginalPassword] = useState(false);
    const [passwordChanged, setPasswordChanged] = useState(false);

    const onChange = (e) => {
        switch (e.target.name) {
            case 'oldPassword':
                setOldPassword(e.target.value);
            break;
            case 'newPassword':
                setNewPassword(e.target.value);
            break;
            case 'confirmPassword':
                setConfirmPassword(e.target.value);
            break;
        }
    }

    const onSubmit = (e) => {
        e.preventDefault();

        if (newPassword !== confirmPassword) {
            setNonMatchingPasswords(true);
            return;
        } else {
            setNonMatchingPasswords(false);
        }

        fetch('http://localhost:8080/api/v1/customer/account/changePassword', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + localStorage.getItem('token'),
            },
            body: JSON.stringify({'oldPassword': oldPassword, 'newPassword': newPassword}),
        }).then((resp) => {
            if (resp.status === 500) {
                setInvalidOriginalPassword(true);
            } else {
                setInvalidOriginalPassword(false);
                setPasswordChanged(true);
            }
        })
    }

    return (
        <main>
            <Form onSubmit={onSubmit}>
                <Form.Group>
                    <Form.Control required style={{margin: '10px 0 10px 0'}} name='oldPassword' type='password' value={oldPassword} onChange={onChange} placeholder='Enter old password'/>
                    { invalidOriginalPassword ? <Form.Text style={{color: 'red'}}>Original password is invalid</Form.Text> : null}
                    <Form.Control required style={{margin: '10px 0 10px 0'}} name='newPassword' type='password' value={newPassword} onChange={onChange} placeholder='Enter new password'/>
                    <Form.Control required style={{margin: '10px 0 10px 0'}} name='confirmPassword' type='password' value={confirmPassword} onChange={onChange} placeholder='Confirm your new password'/>
                    { nonMatchingPassword ? <Form.Text style={{color: 'red'}}>Passwords do not match</Form.Text> : null}
                </Form.Group>

                <Button variant='primary' type='submit'>
                    Change password
                </Button><br/>
                { passwordChanged ? <Form.Text style={{color: 'green'}}>Password has been changed</Form.Text> : null }
            </Form>
        </main>
    )
}