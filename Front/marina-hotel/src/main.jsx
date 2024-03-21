// main.jsx (or your entry point file)
import React from 'react';
import { createRoot } from 'react-dom';
import App from './App.jsx'; // Assuming this is your main application component
import AddRoom from './components/room/AddRoom.jsx'; // Import AddRoom component
import './index.css';
import 'bootstrap/dist/css/bootstrap.min.css';

createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <App />
    <AddRoom /> {/* Render the AddRoom component */}
  </React.StrictMode>
);
