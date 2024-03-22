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