import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
// global import for symbol ponyfill - Required for React.Fragment usage
import 'es6-symbol';
// import Header from './components/Header/Header';
import Main from './components/Main';
import Footer from './components/Footer/Footer';
import registerServiceWorker from './registerServiceWorker';

ReactDOM.render(
  <div>    
    <Main />
    <Footer />
  </div>,
  document.getElementById('root'),
);
registerServiceWorker();
