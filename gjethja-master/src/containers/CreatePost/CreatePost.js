import React from 'react';
import './CreatePost.scss';
import { createPost } from "../../actions/posts";
import { connect } from "react-redux";
import { withRouter } from "react-router-dom";


class CreatePost extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            title: "",
            description: "",
            status: "",
        }
    }
    //
    // handleSubmit = () => {
    //     this.props.createPost(this.state);
    handleSubmit = event => {
        this.props.createPost(this.state).then(res => {
            console.log(res);
            this.props.history.push("/home");
        })
    }

    render() {
        return (
            <div className="create-post-container">

                <div className="row">
                    <div className="col-6 mx-auto">

                        <div className="create-post-title"> Krijo një post </div>

                        <form className="create-post-form">
                            <div className="form-group">
                                <label for="name-input">Titulli: </label>
                                <input type="text"
                                       id="title-input"
                                       className="form-control"
                                       placeholder="Titulli"
                                       value={this.state.firstName}
                                       onChange={(e) => this.setState({ title: e.target.value })} /></div>

                            <div className="form-group">
                                <label htmlFor="name-input">Përshkrimi: </label>
                                <input type="text"
                                       id="name-input"
                                       className="form-control"
                                       placeholder="Përshkrimi"
                                       value={this.state.description}
                                       onChange={(e) => this.setState({ description: e.target.value })}/></div>

                            <div className="form-group">
                                <label htmlFor="status-input">Statusi: </label>
                                <select
                                    id="status-input"
                                    className="form-control"
                                    defaultValue="Select status"
                                    onChange={(e) => this.setState({ status: e.target.value })}>
                                    <option defaultValue>Zgjedh statusin</option>
                                    <option value="open" id="opened">Hapur</option>
                                    <option value="closed" id="closed">Mbyllur</option>
                                </select>
                            </div>

                            <button type="button"  id="submit-button" onClick={() => this.handleSubmit()}>Posto</button>
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
    };
}

const mapDispatchToProps = dispatch => ({
    createPost: (data) => dispatch(createPost(data)),
})

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(CreatePost));