import React from 'react';
import { connect } from 'react-redux';
import './Login.scss';
import {Link} from "react-router-dom";
import Header from "../../components/Header/Header";
import Footer from "../../components/Footer/Footer";
import {confirm, login} from "../../actions/user";
import { withRouter } from "react-router-dom";

class Login extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            email:"",
            password:"",
            emailError: "",
            passwordError: "",
        }
    }

    componentDidMount() {
        const url = window.location.href;
        const token = url.split("token=")[1];
        if(token) {
            this.props.confirm(token).then(res => {
                alert("Email confirmed successfully");
                console.log(res);
            })
        }
    }


    emailhandler = event => {
        this.setState({
            email: event.target.value
        })
    }
    passwordhandler = event => {
        this.setState({
            password: event.target.value
        })
    }


    validate = () => {
        let emailError = "";
        let passwordError = "";

        if (!this.state.email.includes('@') || (!this.state.email)) {
            emailError = "Kjo email adresë nuk është valide!"
        }
        if (!this.state.password > 8 || (!this.state.password)) {
            passwordError = "Ky fjalëkalim nuk është valid!"
        }
        if (emailError || passwordError) {
            this.setState({emailError, passwordError})
            return true;
        }
    }


    handleSubmit = event => {
        this.props.login(this.state).then(res => {
            this.props.history.push("/home");
        })
            const isValid = this.validate();
            if(isValid){
                console.log(this.state)
            }
    }

    render() {
        return (
             <div className="container-fluid login-container">
                 <div className="row">
                     <div className="col">
                         <form onSubmit={this.handleSubmit} className="login-form">
                             <div className="login-title"> Kyçja e përdoruesit </div>

                             <div className="form-group">
                                 <label htmlFor="email">Email adresa:</label>
                                 <input type="email"
                                        id="email-input"
                                        className="form-control"
                                        placeholder="Email adresa"
                                        value={this.state.email}
                                        onChange={this.emailhandler} />
                                        <div className="error-style">{this.state.emailError}</div>
                             </div>

                             <div className="form-group">
                                 <label htmlFor="password">Fjalëkalimi: </label>
                                 <input type="password"
                                        id="password-input"
                                        className="form-control"
                                        placeholder="Fjalëkalimi"
                                        value={this.state.password}
                                        onChange={this.passwordhandler} />
                                 <div className="error-style">{this.state.passwordError}</div>
                             </div>

                             <div className="login-button">
                                 <button type="button" id="button-input"
                                         onClick={(e) => this.handleSubmit(e)}>Kyçu</button>
                             </div>

                         </form>
                     </div>
                 </div>
            </div>
        );
    }
}

const mapStateToProps = state => {
    return {
        //
    }
}

const mapDispatchToProps = dispatch => ({
    login: data => dispatch(login(data)),
    confirm: data => dispatch(confirm(data))
})

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(Login));