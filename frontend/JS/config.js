const IS_PRODUCTION = window.location.hostname !== 'localhost' && window.location.hostname !== '127.0.0.1';

const API_BASE = IS_PRODUCTION 
  ? 'https://servervault-gf7i.onrender.com' 
  : 'http://localhost:8080';

// This makes the variable available to your other JS files
export { API_BASE };