/* eslint-disable no-unused-vars */
import React from 'react';
import AddRoom from './components/room/AddRoom';
import ExistingRooms from './components/room/ExistingRooms'
import "../node_modules/bootstrap/dist/css/bootstrap.min.css"
import "../node_modules/bootstrap/dist/js/bootstrap.min.js"
import { BrowserRouter as Router ,Routes,Route } from 'react-router-dom';
import Home from './components/home/Home.jsx'
import EditRoom from './components/room/EditRoom'
import NavBar from './components/layout/NavBar';
import Footer from './components/layout/Footer';
import RoomListing from './components/room/RoomListing';
import Admin from './components/admin/Admin';
import BookingSuccess from './components/bookings/BookingSuccess.jsx';
import Bookings from './components/bookings/Bookings.jsx';
import FindBooking from './components/bookings/FindBooking.jsx';

function App() {
  return (
   <>
   <main>
    <Router>
      <NavBar/>
      <Routes>
        <Route path='/' element={<Home/>}/>
        <Route path='/edit-room/:roomId' element={<EditRoom/>}/>
        <Route path='/existing-rooms' element={<ExistingRooms/>}/>
        <Route path='/add-room' element={<AddRoom/>}/>
        <Route path='/browse-all-rooms' element={<RoomListing/>}/>
        <Route path='/admin' element={<Admin/>}/>
        <Route path='/booking-success' element={<BookingSuccess/>}/>
        <Route path='/existing-bookings' element={<Bookings/>}/>
        <Route path='/find-booking' element={<FindBooking/>}/>

      </Routes>
    </Router>
    <Footer/>
   </main>
   </>
  );
}

export default App;
