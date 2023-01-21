import Hero from './components/Hero/Hero';
import Main from './components/Main/Main';
import Signup from './components/Auth/Signup/Signup';
import Signin from './components/Auth/Signin/Signin';
import Logout from './components/Auth/Logout/Logout';
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
          </Routes>
      </div>
    </RecoilRoot>
  );
}
