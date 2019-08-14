import React from 'react';

export default function Footer() {

  return (
    <footer className="site-footer contain">
      <p className="site-footer__copyright">
        © {(new Date()).getFullYear()} Git Search , LLC
      </p>
    </footer>
  );
}
