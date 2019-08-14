import React from 'react';
import logoImg from '../../images/svg/github-logo.svg';

export default function Header() {
  return (
    <header className="site-header">
      <a href={`${process.env.PUBLIC_URL}/`} title="Git Search">
        <img src={logoImg} alt=""/>
      </a>
    </header>
  );
}
