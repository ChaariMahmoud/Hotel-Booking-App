/* eslint-disable no-unused-vars */
/* eslint-disable react/prop-types */
import React, { useState } from "react";

const RoomFilter =({data,setFilteredData}) =>{
    const[filter,setFilter] =useState("")
    const handleSelectChange =(e) => {
        const selectedRoomType= e.target.value
        setFilter(selectedRoomType)
        const filtredRooms = data.filter((room) => 
        room.roomType.toLowerCase().includes(selectedRoomType.toLowerCase()))
        setFilteredData(filtredRooms)
    }


const clearFlter = ()=>{
    setFilter("")
    setFilteredData(data)

}

const roomTypes =["",...new Set(data.map((room) => room.roomType))]
    return(
        <div className="input-group mb-3">
            <span className="input-group-text" id="room-type-filter">
                Filter rooms by type
            </span>
            <select className="form-select"
            value={filter}
            onChange={handleSelectChange}
            >
                <option value={""}>select a room type to filter ...</option>
                {roomTypes.map((type,index)=>(
                  <option key={index} value={String(type)}>
                    {String(type)}
                  </option>  
                ))}
            </select>
           <button className="btn btn-hotel" type="button" onClick={clearFlter}>
            Clear Filter
            </button> 

        </div>
    )
}
export default RoomFilter