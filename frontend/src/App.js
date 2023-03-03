import Hero from './components/Hero/Hero';
import Main from './components/Main/Main';
import Signup from './components/Auth/Signup/Signup';
import Signin from './components/Auth/Signin/Signin';
import Logout from './components/Auth/Logout/Logout';
import CustomerView from './components/CustomerView/CustomerView';
import AdminView from './components/AdminView/AdminView';
import AdminPanel from './components/AdminView/AdminPanel';
import Menu from './components/CustomerView/Menu';
import AccountDetails from './components/CustomerView/AccountDetails/AccountDetails';
import { Routes, Route } from 'react-router-dom';
import { RecoilRoot } from 'recoil';
import './App.css';
import ShoppingCartDetails from './components/CustomerView/ShoppingCart/ShoppingCartDetails/ShoppingCartDetails';
import OrderCreation from './components/CustomerView/Order/OrderCreation';
import OrderDetails from './components/CustomerView/Order/OrderDetails';
import NewPassword from './components/CustomerView/AccountDetails/NewPassword';
import NewEmail from './components/CustomerView/AccountDetails/NewEmail';
import ForgotPassword from './components/Auth/ForgotPassword';
import ResetPassword from './components/Auth/ResetPassword';
import SingleOrderDetails from './components/CustomerView/Order/SingleOrderDetails';

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
              <Route path='forgotPassword' element={<ForgotPassword/>}/>
              <Route path='logout' element={<Logout/>}/>
              <Route exact path='resetPassword/:token' element={<ResetPassword/>}/>
            </Route>

            {/* Protected routes */}
            <Route path='/customer' element={<CustomerView/>}>
              <Route index element={<Menu/>}/>
              <Route path='products' element={<ShoppingCartDetails/>}/>
              <Route path='order/new' element={<OrderCreation/>}/>
              <Route path='order/:id' element={<OrderDetails/>}/>
              <Route path='account' element={<AccountDetails/>}>
                <Route path='changePassword' element={<NewPassword/>}/>
                <Route path='changeEmail' element={<NewEmail/>}/>
              </Route>
            </Route>

            <Route path='/admin' element={<AdminView/>}>
              <Route index element={<AdminPanel/>}/>
              <Route path='order/:id' element={<SingleOrderDetails/>}/>
            </Route>
          </Routes>
      </div>
    </RecoilRoot>
  );
}
