/* eslint-disable react-refresh/only-export-components */
import axios from "axios"

export const api = axios.create(
    {
        baseURL :"http://localhost:9192"
    }
)
//add new room to the bd
export async function AddRoom(photo ,roomType ,roomPrice){
    const fromData = new fromData ()
    fromData.append("photo",photo)
    fromData.append("roomType",roomType)
    fromData.append("roomPrice",roomPrice)

    const response = await api.post("/rooms/add/new-room" ,fromData)
    if (response === 201){
        return true
    } else {
        return false
    }
}
//get all room types from db
export async function getRoomTypes(){
    try{
        const response = await api.get("/rooms/room/types")
        return response.data 

    }catch(error){
      throw new Error("Error fetching room types")  

    }
}
//get all rooms from the db
export async function getAllRooms(){
    try{
        const result = await api.get("/rooms/all-rooms")
        return result.data

    }catch(erroe){
        throw new Error("Error fetching rooms")

    }
}

//delete room by id
export async function deleteRoom(roomId){
    try{
        const result = await api.delete(`/rooms/delete/room/${roomId}`);
        return result.data


    }catch(error){
        throw new Error(`Error deleting room ${error.message}`);

    }
}


//Updates a room
export async function updateRoom(roomId,roomData){
    const formData = new FormData()
    formData.append("roomType",roomData.roomType)
    formData.append("roomPrice",roomData.roomPrice)
    formData.append("photo",roomData.photo)
    const response = await api.put(`/rooms/update/${roomId}`);
    return response
}


//Get a room by the id
export async function getRoomById(roomId){
    try{
        const result = await api.get(`/rooms/room/${roomId}`);
        return result.data

    }catch(error){
        throw new Error(`Error fetching room ${error.message}`)

    }
}

//Save a new booking to the database
export async function bookRoom(roomId,booking){
    try{
        const response = await api.post(`/bookings/room/${roomId}/booking`,booking)
        return response.data
    }catch(error){
        if(error.response && error.response.data){
            throw new Error (error.response.data)
        }else{
            throw new Error (`Error booking room : ${error.message}`)
        }
    }    
}


//Get all bookings from the database
export async function getAllBookings() {
    try{
        const result = await api.get("/bookings/all-bookings")
        return result.data
    }catch(error){
        throw new Error (`Error fetching bookings : ${error.message}`)
    }
}

//Get booking by the confirmation code
export async function getBookingByConfirmationCode(confirmationCode){
    try{
        const result = await api.get(`/bookings/confirmation/${confirmationCode}`)
        return result.data
    }catch(error){
        if(error.response && error.response.data){
            throw new Error (error.response.data)
        }else{
            throw new Error(`Error finding booking : ${error.message}`)
        }
    }
}


//Cancel booking
export async function cancelBooking(bookingId){
    try{
        const result = await api.delete(`/bookings/booking/${bookingId}/delete`)
        return result.data
    }catch(error){
        throw new Error(`Error cancelling booking : ${error.message}`)
    }
}