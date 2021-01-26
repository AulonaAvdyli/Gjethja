import React from 'react';
import './Post.scss';
import {Edit,  Delete , Star}from '@material-ui/icons';

export default function Post({ id, title, status, description, info, handleClick, handlePay, handleEdit, handleDelete }) {
    return(
        <div className="post"
             // onClick={() => handleClick()}
            >
            <div className={"post-image"} />
            <div className={"post-name"}>
                { title }
            </div>
            <div className={"post-title"}>
                <span className="badge badge-primary">{ status }</span>
            </div>
            <div className={"post-description"}>
                { description }
            </div>
            <div className={"post-description"}>
                { info }
            </div>
            <div className="row">

                {handleEdit && <button className="btn btn-warning btn-sm col-3 mx-auto mt-4"
                                         onClick={handleEdit}><Edit  style={{ fill: '#ffffff' }}/></button>}
                {handleDelete && <button className="btn btn-danger btn-sm col-4 mx-auto mt-4"

                                         onClick={handleDelete}><Delete /></button>}
                {handlePay && <button className="col-3 btn-sm mx-auto mt-4"
                                         onClick={handlePay}><Star /></button>}
            </div>

        </div>
    )
}

