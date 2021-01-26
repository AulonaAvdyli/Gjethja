import React from 'react';
import './Profile.scss';


export default function Profile({id, fullName, title, description}) {
    return(
        <div className="profile">
            <div className={"profile-image"} />
            <div className={"profile-name"}>
                { fullName }
            </div>
            <div className={"profile-title"}>
                { title }
            </div>
            <div className={"profile-description"}>
                { description }
            </div>
        </div>
    )
}
