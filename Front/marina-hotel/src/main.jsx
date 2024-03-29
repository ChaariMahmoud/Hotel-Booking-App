// main.jsx (or your entry point file)
import React from 'react';
import ReactDOM from "react-dom/client";
import App from './App.jsx'; // Assuming this is your main application component
import AddRoom from './components/room/AddRoom.jsx'; // Import AddRoom component
import './index.css';
import 'bootstrap/dist/css/bootstrap.min.css';

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <React.StrictMode>
    <App />
    <AddRoom /> {/* Render the AddRoom component */}
  </React.StrictMode>
);
