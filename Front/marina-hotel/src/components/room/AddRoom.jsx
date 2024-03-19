/* eslint-disable no-unused-vars */
/* eslint-disable react/jsx-no-undef */
/* eslint-disable no-undef */

import React, { useState } from "react";

const AddRoom = () => {
    const[newRoom,setNewRoom] = useState(
        {
            photo :null ,
            roomType :"",
            roomPrice :""
        }
    )

cosnt[imagePreview,setImagePreview] = useState("")    
const [succesMessage,setSuccessMessage] = useState ("")
const [errorMessage ,setErrorMessage] = useState("")

const handleRoomInputChange = (e) =>{
    const name = e.target.name;
    let value = e.target.value
    if(name === "roomPrice"){
        if(!isNaN(value)){
            value.parseInt(value)
        }
        
    }else {
        value =""
    }
    setNewRoom({...newRoom,[name]:value,})
}

const handleImageChange =(e) => {
    const selectedImage = e.target.files[0]
    setNewRoom({...newRoom,photo:selectedImage})
    setImagePreview(URL.createObjectURL(selectedImage))

}

const handleSubmit = async (e) =>{
    e.preventDefault()
    try{
        const successs = await AddRoom(newRoom.photo,newRoom.roomType,newRoom.roomPrice)
        if(successs !==undefined){
            setSuccessMessage("A new room was added to the database")
            setNewRoom({photo:null,roomType:"",roomPrice:""})
            setImagePreview("")
            setErrorMessage("")
        }
        else{
            setErrorMessage("Error adding room")
        }
    }catch(error){
        setErrorMessage(errorMessage)
    }
}
    return (
        <>
        <section className="container, mt-5 mb-5">
            <div className="room justify-content-center"> 
                <div className="col-md-8 col-lg-6">
                    <h2 className="mt-5 mb-2">
                     Add a new room   
                    </h2>
                    <form onSubmit={handleSubmit}>
                        <div className="mb-3">
                            <label htmlFor="roomType" className="form-label">
                                Room Type
                                </label>
                                <div>
                                   <RoomTypeSelector handleRoomInputChange ={handleRoomInputChange} 
                                   newRoom={newRoom}/>

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
                                 value={newRoom.roomPrice}
                                 onChange={handleRoomInputChange}
                                />
                                
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
                        </div>
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
    )
}

export default AddRoom