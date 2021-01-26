import React from 'react';
import Header from "../../components/Header/Header";
import { withRouter } from "react-router-dom";
import {connect} from "react-redux";
import Footer from "../../components/Footer/Footer";
import { useHistory } from 'react-router-dom';
import { Redirect } from 'react-router-dom'

class Register extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            referrer: null,
            //
        }
    }

    handleClickProvider = () => {
        console.log('Button is clicked!');
        this.setState({referrer: '/register/provider'});
    }
    handleClickSeeker = () => {
        console.log('Button is clicked!');
        this.setState({referrer: '/register/seeker'});
    }

    render() {
        const {referrer} = this.state;
        if (referrer) return <Redirect to={referrer} />;

        return (
            <div>
                <Header/>
                <div className="register">
                    <h1 className="heading">PËRZGJEDHENI MËNYRËN E REGJISTRIMIT</h1>
                <div className="register-b">
                    <button onClick={this.handleClickProvider}>Ofrues shërbimesh</button>
                </div>
                    <div className="register-b">
                    <button onClick={this.handleClickSeeker}>Kërkues shërbimesh</button>
                </div>
                    </div>
                <Footer />
            </div>
        );
    }
}



export default Register;

// export default withRouter(connect(mapStateToProps, mapDispatchToProps)(RegisterSeeker));