import Hero from './components/Hero/Hero';
import Main from './components/Main/Main';
import Signup from './components/Auth/Signup/Signup';
import Signin from './components/Auth/Signin/Signin';
import Logout from './components/Auth/Logout/Logout';
import CustomerView from './components/CustomerView/CustomerView';
import AdminView from './components/AdminView/AdminView';
import AdminPanel from './components/AdminView/AdminPanel';
import Menu from './components/CustomerView/Menu';
import { Routes, Route } from 'react-router-dom';
import { RecoilRoot } from 'recoil';
import './App.css';

export default function App() {
  return (
    <RecoilRoot>
      <div className='App'>
          <Routes>
            {/* Public routes */}
            <Route path='/' element={<Hero/>}>
              <Route index element={<Main/>}/>
              <Route path='signup' element={<Signup/>}/>
              <Route path='signin' element={<Signin/>}/>
              <Route path='logout' element={<Logout/>}/>
            </Route>

            {/* Protected routes */}
            <Route path='/customer' element={<CustomerView/>}>
              <Route index element={<Menu/>}/>
            </Route>

            <Route path='/admin' element={<AdminView/>}>
              <Route index element={<AdminPanel/>}/>
            </Route>
          </Routes>
      </div>
    </RecoilRoot>
  );
}
