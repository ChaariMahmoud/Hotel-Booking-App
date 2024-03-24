/* eslint-disable no-unused-vars */
import React, { useEffect } from "react";
import { useState } from "react";
import { updateRoom ,getRoomById } from "../utils/ApiFunctions";
import {useParams} from "react-router-dom"

const EditRoom =() =>{
    const[room,setRoom] = useState(
        {
            photo :null ,
            roomType :"",
            roomPrice :""
        }
    )

const[imagePreview,setImagePreview] = useState("")    

const [succesMessage,setSuccessMessage] = useState ("")
const [errorMessage ,setErrorMessage] = useState("")

const {roomId} =useParams()
const handleImageChange =(e) => {
    const selectedImage = e.target.files[0]
    setRoom({...room,photo:selectedImage})
    setImagePreview(URL.createObjectURL(selectedImage))
}

const handleInputChange = (event) => {
    const { name, value } = event.target;
    setRoom({ ...room, [name]: value });
}

const handleSubmit = async(event) => {
    event.preventDefault();
    try{
        const response = await updateRoom(roomId,room)
        if(response.status===200){
            setSuccessMessage("Room updated successfully")
            const updatedRoomData = await getRoomById(roomId)
            setRoom(updatedRoomData)
            setImagePreview(updatedRoomData.photo)
            setErrorMessage("")
        }else{
            setErrorMessage("Error updating room")
        }
    }catch(error){
        console.error(error)
        setErrorMessage(error.message)
    }
}

useEffect(() => {
    const fetchRoom = async () => {
        try{
            const roomData = await getRoomById(roomId)
            setRoom(roomData)
            setImagePreview(roomData.photo)

        }catch(error){
            console.error(error)
        }
    }
    fetchRoom()
},
[roomId]
)

return (
    <>
        <section className="container mt-5 mb-5">
            <div className="room justify-content-center"> 
                <div className="col-md-8 col-lg-6">
                    <h2 className="mt-5 mb-2">
                        Add a new room   
                    </h2>
                    {succesMessage && (
                        <div className="alert-success fade show">
                            {succesMessage}
                        </div>
                    )}
                    {errorMessage && (
                        <div className="alert-danger fade show">
                            {errorMessage}
                        </div>
                    )}

                    <form onSubmit={handleSubmit}>
                        <div className="mb-3">
                            <label htmlFor="roomType" className="form-label">
                                Room Type
                            </label>
                            
                        </div>
                        <div className="mb-3">
                            <label htmlFor="roomPrice" className="form-label">
                                Room Price
                            </label>
                            <input className="form-control"
                                required
                                id="roomPrice"
                                name="roomPrice"
                                type="number"
                                value={room.roomPrice}
                                onChange={handleInputChange}
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="roomPhoto" className="form-label">
                                Room Photo
                            </label>
                            <input 
                                id="photo"
                                name="photo"
                                type="file"
                                className="form-control"
                                onChange={handleImageChange}
                            />
                            {imagePreview && (
                                <img src={imagePreview}
                                    alt="Preview Room Photo"
                                    style={{maxWidth:"400px",maxHeight:"400px"}}
                                    className="mb-3"/> 
                            )}  
                        </div>
                        <div className="d-grid d-md-flex mt-2">
                            <button className="btn btn-online-primary ml-5">
                                Save Room
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </section>
    </>
);

}
export default EditRoom