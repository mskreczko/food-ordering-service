import React from 'react';
import './Main.css';

export default function Main() {
    return (
        <main>
            <div>
                <img className='hero-img' alt='food image' src='https://www.freepnglogos.com/uploads/food-png/download-food-png-file-png-image-pngimg-1.png'/>
            </div>
            <div className='hero-text'>
                <h2 id='hero-slogan'>The food at your doorstep</h2>
            </div>
        </main>

    );
}