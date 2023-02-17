import { React, useState } from 'react';
import { Form } from 'react-bootstrap';
import Button from 'react-bootstrap/Button';

export default function NewEmail() {
    const [oldEmail, setOldEmail] = useState('');
    const [newEmail, setNewEmail] = useState('');
    const [confirmEmail, setConfirmEmail] = useState('');
    const [nonMatchingEmail, setNonMatchingEmails] = useState(false);
    const [invalidOriginalEmail, setInvalidOriginalEmail] = useState(false);
    const [emailChanged, setEmailChanged] = useState(false);

    const onChange = (e) => {
        switch (e.target.name) {
            case 'oldEmail':
                setOldEmail(e.target.value);
            break;
            case 'newEmail':
                setNewEmail(e.target.value);
            break;
            case 'confirmEmail':
                setConfirmEmail(e.target.value);
            break;
        }
    }

    const onSubmit = (e) => {
        e.preventDefault();

        if (newEmail !== confirmEmail) {
            setNonMatchingEmails(true);
            return;
        } else {
            setNonMatchingEmails(false);
        }

        fetch('http://localhost:8080/api/v1/customer/account/changeEmail', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + localStorage.getItem('token'),
            },
            body: JSON.stringify({'oldEmail': oldEmail, 'newEmail': newEmail}),
        }).then((resp) => {
            if (resp.status === 500) {
                setInvalidOriginalEmail(true);
            } else {
                setInvalidOriginalEmail(false);
                setEmailChanged(true);
            }
        })
    }

    return (
        <main>
            <Form onSubmit={onSubmit}>
                <Form.Group>
                    <Form.Control required style={{margin: '10px 0 10px 0'}} name='oldEmail' type='password' value={oldEmail} onChange={onChange} placeholder='Enter old password'/>
                    { invalidOriginalEmail ? <Form.Text style={{color: 'red'}}>Original password is invalid</Form.Text> : null}
                    <Form.Control required style={{margin: '10px 0 10px 0'}} name='newEmail' type='password' value={newEmail} onChange={onChange} placeholder='Enter new password'/>
                    <Form.Control required style={{margin: '10px 0 10px 0'}} name='confirmEmail' type='password' value={confirmEmail} onChange={onChange} placeholder='Confirm your new password'/>
                    { nonMatchingEmail ? <Form.Text style={{color: 'red'}}>Emails do not match</Form.Text> : null}
                </Form.Group>

                <Button variant='primary' type='submit'>
                    Change email
                </Button><br/>
                { emailChanged ? <Form.Text style={{color: 'green'}}>Email has been changed</Form.Text> : null }
            </Form>
        </main>
    )
}