import React from 'react';
import { Link, withRouter  } from 'react-router-dom';
import './Header.scss';
import Logo from './../../assets/images/gjethja-logo.png';
import { connect } from "react-redux";
import {logOut} from "../../actions/user";

function Header(props) {
    return(
        <div class={"header"}>
            <div className="header_logo">
                <Link to={"/"}>
                        <img src={Logo} alt="logo"/>
                </Link>
            </div>
            <nav className={"header_nav"}>
                <ul>
                    <li>
                        <Link to={"/"}>Ballina</Link>
                    </li>
                    <li>
                        <Link to={"/aboutUs"}>Për ne</Link>
                    </li>



                    {
                        !props.token ?
                            <>
                                <li>
                                    <Link to={"/login"}>Kyçu</Link>
                                </li>
                                <button className="btn btn-primary">
                                    <Link to={"/register"}>Regjistrohu</Link>
                                </button>
                            </> :
                            <>
                                <li>
                                    <Link to={"/home"}>Shtëpia</Link>
                                </li>
                                <li>
                                    <Link to={"/profile"}>Profili</Link>
                                </li>
                                <li>
                                    <a href="#"
                                       to={"/profile"}
                                       onClick={(e) => {
                                           e.preventDefault();
                                           props.logOut();
                                       }}>Çkyçu</a>
                                </li>
                                <button className="btn btn-primary" id="create-post"
                                        onClick={() => props.history.push("/create")}>
                                    Create Post
                                </button>
                            </>
                    }
                </ul>
            </nav>
        </div>
    )
}

const mapStateToProps = state => {
    return {
        token: state.token
    }
}

const mapDispatchToProps = dispatch => ({
    logOut: data => dispatch(logOut())
})

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(Header));