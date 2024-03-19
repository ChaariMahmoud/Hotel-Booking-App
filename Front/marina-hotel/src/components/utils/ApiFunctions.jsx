/* eslint-disable react-refresh/only-export-components */
import axios from "axios"

export const api = axios.create(
    {
        baseURL :"http://localhost:9192"
    }
)

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

export async function getRoomTypes(){
    try{
        const response = await api.get("/rooms/room-types")
        return response.data 

    }catch(error){
      throw new Error("Error fetching room types")  

    }
}